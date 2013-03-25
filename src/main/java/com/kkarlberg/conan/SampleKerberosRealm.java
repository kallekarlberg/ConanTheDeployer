package com.kkarlberg.conan;

import java.net.URL;
import java.security.Principal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.impl.auth.SPNegoSchemeFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kkarlberg.conan.db.ConanPermission;
import com.kkarlberg.conan.db.ConanRole;
import com.kkarlberg.conan.db.ConanUser;
import com.kkarlberg.conan.db.HibernateUtils;

public class SampleKerberosRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(SampleKerberosRealm.class);

    public static void main(String[] args) throws Exception {

        System.setProperty("java.security.auth.login.config", "login.conf");
        System.setProperty("java.security.krb5.conf", "krb5.conf");
        System.setProperty("sun.security.krb5.debug", "true");
        System.setProperty("javax.security.auth.useSubjectCredsOnly","false");

        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            httpclient.getAuthSchemes().register(AuthPolicy.SPNEGO, new SPNegoSchemeFactory());

            Credentials use_jaas_creds = new Credentials() {

                public String getPassword() {
                    return null;
                }

                public Principal getUserPrincipal() {
                    return null;
                }

            };

            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(null, -1, null),
                    use_jaas_creds);

            HttpUriRequest request = new HttpGet("http://kerberoshost/");
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println("----------------------------------------");
            if (entity != null) {
                System.out.println(EntityUtils.toString(entity));
            }
            System.out.println("----------------------------------------");

            // This ensures the connection gets released back to the manager
            EntityUtils.consume(entity);

        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }

    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //identify account to log to
        UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
        String username = userPassToken.getUsername();

        if (username == null) {
            log.debug("Username is null.");
            return null;
        }

        ConanUser usr = getUserFromDb(username);

        if (usr == null) {
            log.debug("No account found for user [" + username + "]");
            return null;
        }
        ByteSource bs = new SimpleByteSource(Base64.decode(usr.getSalt())); //3c29517719ae4e5ba58bbcabea0690c4 
        return new SimpleAuthenticationInfo(username, usr.getPassword(), bs, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        String username = (String) getAvailablePrincipal(principals);

        Session s = null;
        Set<String> roles = null;
        Set<String> permissions = null;
        try {
            s = HibernateUtils.SESSION_FACTORY.openSession();
            roles = getRoleNames(username,s);
        } catch (HibernateException e) {
            final String message = "There was a SQL error while authorizing user [" + username + "]";
            throw new AuthorizationException(message, e);
        } finally {
            HibernateUtils.safeCloseSession(s);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    @SuppressWarnings("unchecked")
    private static Set<String> getRoleNames(String username, Session s) {
        List<ConanRole> roles = s.createCriteria(ConanRole.class).add(Restrictions.eq("username", username)).list();
        Set<String> roleSet = new HashSet<String>();
        for (ConanRole conanRole : roles) {
            roleSet.add(conanRole.getRole());
        }
        return roleSet;
    }

    private static ConanUser getUserFromDb(String username) {
        Session s = null;
        try {
            s = HibernateUtils.SESSION_FACTORY.openSession();
            return (ConanUser) s.get(ConanUser.class, username);
        } catch (Exception e) {
            final String message = "There was a SQL error while authenticating user [" + username + "]";
            throw new AuthenticationException(message, e);
        } finally {
            HibernateUtils.safeCloseSession(s);
        }
    }
}
