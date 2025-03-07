package com.tcs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection{
	private static final String URL = "jdbc:mysql://localhost:3306/cust";
    private static final String USER = "root";
    private static final String PASSWORD = "dipamdey1234";
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
