package com.kkarlberg.conan.deployer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;

public class DeployerStatsServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        Connection conn = null;
        try {
            conn = getDataSource("deployerDS").getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from table");
            ResultSet res = pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            safeClose(conn);
        }
    }

    private static void safeClose(Connection conn) {
        if (conn!=null) { 
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            } 
        }		
    }

    private static DataSource getDataSource(String jndiName) {
        try {
            Context ctx = new InitialContext();
            return (DataSource) ctx.lookup("java:/comp/env/jdbc/"+jndiName);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        System.err.println("HELLO WORLD");

    }

    @Override
    public String getServletInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
}
