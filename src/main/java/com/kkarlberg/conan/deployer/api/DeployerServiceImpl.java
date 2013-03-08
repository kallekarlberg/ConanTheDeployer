package com.kkarlberg.conan.deployer.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kkarlberg.conan.db.DeployedApp;
import com.kkarlberg.conan.db.HibernateUtils;

@Path("/")
public class DeployerServiceImpl {

    private static final Logger cLogger = LoggerFactory.getLogger(DeployerServiceImpl.class);

    public DeployerServiceImpl() {}

    @PUT
    @Path("{appName}/")
    public void deployApp(@PathParam("appName") String appName,
            @QueryParam("version") String version,
            @QueryParam("host") String host) {
        System.err.println("hello world");
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void releaseBundle(@QueryParam("bundle") List<DeployedApp> bundle ) {
        cLogger.info("Releasing bundle with {} apps",bundle.size());
        saveApps(bundle);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DeployedApp> getDeployedApps(@QueryParam("appName") String appName,
            @QueryParam("version") String version,
            @QueryParam("host") String host) {
        cLogger.info("Obtaining info on app {}, version {} on host "+ host,appName,version);
        Session s = null;
        try {
            s = HibernateUtils.SESSION_FACTORY.openSession();
            return getDeployedApps(appName,version,host,s);
        } catch (HibernateException e) {
            cLogger.error("Database issues...",e);
            throw new WebApplicationException(e, Status.INTERNAL_SERVER_ERROR);
        } finally {
            HibernateUtils.safeCloseSession(s);
        }
    }

    private static void saveApps( List<DeployedApp> apps ) {
        Session s = null;
        Transaction dbTx = null;
        try {
            s = HibernateUtils.SESSION_FACTORY.openSession();
            dbTx = s.beginTransaction();
            for (DeployedApp a : apps) {
                s.save(a);
            }
            dbTx.commit();
        } catch ( HibernateException e ) {
            cLogger.error("error saving txs",e);
            dbTx.rollback();
        } finally {
            HibernateUtils.safeCloseSession(s);
        }
    }

    @SuppressWarnings("unchecked")
    private static List<DeployedApp> getDeployedApps(String appName, String version, String host, Session s) {
        Criteria crit = s.createCriteria(DeployedApp.class);
        //        crit.addOrder(Order.desc("when"));
        if ( appName != null ) 
            crit.add(Restrictions.eq("name", appName));
        if ( version != null ) 
            crit.add(Restrictions.eq("version", version));
        if ( host != null ) 
            crit.add(Restrictions.eq("host", host));
        return crit.list();
    }
}
