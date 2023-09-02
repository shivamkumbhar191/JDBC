package jbconnect;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	static Connection conn=null;
	
	public static Connection getConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded....");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/car", "root", "root")	;
			System.out.println("connection established...");
			}catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}
	public static void main(String[] args) {
		getConnect();
	}
	
}
