package com.megacity.utils;
import java.sql.*;

public class DBConnection {
	
	private static final String URL = "jdbc:sqlserver://127.0.0.1\\SQLEXPRESS;databaseName=MegaCityCab;integratedSecurity=true;encrypt=false";
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server Driver not found.", e);
        }
    }
    
}
