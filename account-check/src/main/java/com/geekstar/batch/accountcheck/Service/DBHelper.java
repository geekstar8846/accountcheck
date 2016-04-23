package com.geekstar.batch.accountcheck.Service;

import java.sql.*;

public class DBHelper {
    public static final String url = "jdbc:mysql://192.168.0.244/testdb";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "wuyg";
    public static final String password = "123456";
    
    private Connection conn = null;
    
    public DBHelper() {
        try {
            Class.forName(name);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        return conn;
    }

    public void close() {
        try {
        	if(null != conn) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
