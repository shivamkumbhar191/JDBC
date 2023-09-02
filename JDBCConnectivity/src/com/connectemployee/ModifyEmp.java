package com.connectemployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.connect.DBConnect;

public class ModifyEmp {
	static Connection con=null;
	ResultSet rs;
	Statement st;
	PreparedStatement pst;
	static Scanner sc= new Scanner (System.in);
	public ModifyEmp() {
		con=DBConnect.getConnect();
		System.out.println("Connection done...");
		try {
			st=con.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertEmp() {
		System.out.println("enter the employee id");
		int eid=sc.nextInt();
		System.out.println("enter the first name");
		String fname=sc.next();
		System.out.println("enter the last name");
		String lname=sc.next();
		System.out.println("enter the emp salary");
		int sal=sc.nextInt();
		
		try {
			pst=con.prepareStatement("insert into employees(employee_id,fisrt_name,last_name,salary) values(?,?,?,?)");
		    pst.setInt(1, eid);
		    pst.setString(2, fname);
		    pst.setString(3, lname);
		    pst.setInt(4, sal);
		    int noOfRowsInserted=pst.executeUpdate();
		    if(noOfRowsInserted>0) {
		    	System.out.println("employee inserted");
		    }else {
		    	System.out.println("employee not inserted");
		    }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
