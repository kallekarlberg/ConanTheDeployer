package com.kkarlberg.conan.db;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtils {

    private final static Logger cLogger = LoggerFactory.getLogger(HibernateUtils.class);

    public static SessionFactory SESSION_FACTORY = null;

    public static void initSf(URL cfg) {
        if ( SESSION_FACTORY == null ) {
            Configuration configuration = getConfiguration(getProps(cfg));
            ServiceRegistryBuilder srb = new ServiceRegistryBuilder().applySettings(configuration
                    .getProperties());
            SESSION_FACTORY = configuration.buildSessionFactory(srb.buildServiceRegistry());
        }
    }

    private static Properties getProps(URL cfg) {
        Properties p = new Properties();
        try {
            p.load(cfg.openStream());
            return p;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Configuration getConfiguration(Properties props) {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(ConanUser.class);
        cfg.addAnnotatedClass(ConanRole.class);
        cfg.addAnnotatedClass(DeployedApp.class);
        cfg.setProperties(props);
        return cfg;
    }

    public static void safeCloseSession(Session s) {
        try {
            if ( s != null )
                s.close();
        } catch ( Exception e ) {
            cLogger.warn("problems closing session",e);
        }
    }
}
