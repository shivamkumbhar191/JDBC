package com.connectemployee;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnectivity {
	
	public static final String JDBC_Driver="com.mysql.cj.jdbc.Driver" ;
	public static final String JDBC_URL="jdbc:mysql://localhost:3306/testdb";
	public static final String USERNAME="root";
	public static final String PASSWORD="root";
	static Connection conn=null;

	public static Connection getConnect() {
		try {
			Class.forName(JDBC_Driver);
			System.out.println("Driver loaded");
			conn=DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
			System.out.println("Connection Established");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);

		}
		return conn;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//DbConnectivity dc= new DbConnectivity();
		getConnect();

	}

}
