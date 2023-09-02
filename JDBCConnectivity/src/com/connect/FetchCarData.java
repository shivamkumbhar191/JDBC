package com.connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FetchCarData {
	
	static Connection con =null;
	Statement st;
	ResultSet rs;
	
	public FetchCarData() {
		con=DBConnect.getConnect();
		System.out.println("Connection done...");
		try {
			// create statmenet -dml quries 
			st=con.createStatement();
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		
	}
	public void fetchModel() {
		try {
			//execute query or update
			//st=con.createStatement();
			rs=st.executeQuery("select * from model");
			
			//cursor point is before the first row
			//process the result
			System.out.println("-------------------Model-------------------");
			System.out.println("mid"+" "+"mname"+"     "+"mcost");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+"   "+rs.getInt(3));
			}
			System.out.println("-------------------------------------------");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void fetchCustomer() {
		
	}
	public void fetchPurchaseDetails() {
		try {
			rs=st.executeQuery("select p.pid,c.fname,c.lname,m.mname,p.pdate,p.bkamount,f.rating from purchase p,model m,customer c, feedbackrating f where p.mid=m.mid and p.cid=c.cid and p.rid=f.rid");
			System.out.println("-------------------Purchase-------------------");
			System.out.println("pid"+" "+"firstName"+" "+"lastName"+" "+"ModelName"+" "+"PurchaseDate"+" "+"bookingAmount"+" "+"Rating");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"    "+rs.getString(2)+"    "+rs.getString(3)+"    "+rs.getString(4)+"    "+rs.getString(5)+" "+rs.getInt(6)+"    "+rs.getInt(7));
			}
			System.out.println("-------------------------------------------");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	public static void main(String[] args) {
		FetchCarData fd= new FetchCarData();
		fd.fetchModel();
		fd.fetchPurchaseDetails();

	}

}
