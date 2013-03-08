package com.kkarlberg.conan;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kkarlberg.conan.db.HibernateUtils;
import com.kkarlberg.conan.deployer.api.DeployerServiceImpl;

public class Bootstrap implements ServletContextListener {

    private static final Logger cLogger = LoggerFactory.getLogger(Bootstrap.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            cLogger.info("Init database...");
            HibernateUtils.initSf(Bootstrap.class.getResource("/hibernate.properties"));
            //            cLogger.info("Init rest services...");
            //            startRestServices(9001);
        } catch (Exception e) {
            cLogger.error("usless container crap want to stop this app, but is forced to terminate container...",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.err.println("destroy");
    }

    public static void startRestServices(int port) {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(DeployerServiceImpl.class);
        sf.setResourceProvider(DeployerServiceImpl.class, new SingletonResourceProvider(new DeployerServiceImpl()));
        sf.setAddress("http://localhost:"+port+"/");
        sf.create();
    }
}
