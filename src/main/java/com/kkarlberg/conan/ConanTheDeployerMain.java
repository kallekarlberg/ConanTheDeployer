package com.kkarlberg.conan;

import java.sql.SQLException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kkarlberg.conan.db.HibernateUtils;

public class ConanTheDeployerMain {

	private static final Logger cLogger = LoggerFactory.getLogger(ConanTheDeployerMain.class);

	public static void main(String[] args ) throws Exception {
		cLogger.info("Start database...");
		org.h2.tools.Server dbServ = startH2Db();
		HibernateUtils.initSf(ConanTheDeployerMain.class.getResource("/hibernate.properties"));
		//		Server jettyServ = startJetty(9001);
		Server jettyServ = startWebXmlServer(9001);
		addShutdownHook(jettyServ, dbServ);
	}

	private static void addShutdownHook(final Server jettyServ,
			final org.h2.tools.Server dbServ) throws Exception {
		Runtime.getRuntime().addShutdownHook(new Thread( new Runnable() {
			public void run() {
				try {
					jettyServ.stop();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jettyServ.destroy();
				dbServ.shutdown();
			}
		}));
	}

	private static org.h2.tools.Server startH2Db() throws SQLException {
		org.h2.tools.Server server = org.h2.tools.Server.createTcpServer("-tcpAllowOthers").start();
		org.h2.tools.Server.createWebServer().start();
		return server;
	}

	private static Server startWebXmlServer(int port) throws Exception {
		Server server = new Server(port);

		WebAppContext context = new WebAppContext();
		context.setDescriptor("/WEB-INF/web.xml");
		context.setResourceBase("src/main/webapp");
		context.setContextPath("/");
		context.setParentLoaderPriority(true);

		server.setHandler(context);

		server.start();
		return server;
	}
	//	private static Server startJetty(int port) throws Exception {
	//		final Server server = new Server(port);
	//
	//		ServletContextHandler context = new ServletContextHandler();
	//
	//		context.setContextPath("/");
	//		CXFNonSpringServlet cxfRestServlet = new CXFNonSpringJaxrsServlet();
	//		ServletHolder servlet = new ServletHolder(cxfRestServlet);
	//		servlet.setInitParameter("jaxrs.serviceClasses", "com.kkarlberg.conan.deployer.api.DeployerServiceImpl");
	//		context.addServlet(servlet, "/deployedApps");
	//
	//		server.setHandler(context);
	//		server.start();
	//		return server;
	//	}
}
