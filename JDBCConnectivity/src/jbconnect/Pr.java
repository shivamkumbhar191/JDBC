package jbconnect;

import java.sql.Connection;
import java.sql.DriverManager;

public class Pr {
	static Connection conn= null;
	
	public static Connection getConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "root");
			System.out.println("connesction established");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}
	public static void main(String[] args) {
		getConnect();
	}

}
