package com.resultset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ResultSetCustomer {
	Connection conn= null;
	ResultSet rs;
	Statement st;
	Scanner sc= new Scanner(System.in);
	public ResultSetCustomer() {
		conn=DbConnectCar.getConnect();
		try {
			st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=st.executeQuery("select * from customer");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchCustomer() {
		
		System.out.println("-------------------Customer--------------------");
		try {
			rs.beforeFirst();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "
						+rs.getInt(4)+" "+rs.getString(5)+" "+rs.getString(6));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		System.out.println("----------------------------------------------");
	}
	public void insertCustomer() {
		try {
			//rs.beforeFirst();
			rs.moveToInsertRow();
			System.out.println("enter the customer id");
			int cid=sc.nextInt();
			System.out.println("enter the customer fisrt name");
			String fname=sc.next();
			System.out.println("enter the customer last name");
			String lname=sc.next();
			System.out.println("enter the customer mobile no");
			int mobno=sc.nextInt();
			System.out.println("enter the customer gender");
			String gender=sc.next();
			System.out.println("enter the customer email");
			String email=sc.next();
			rs.updateInt(1,cid);
			rs.updateString(2,fname);
			rs.updateString(3,lname);
			rs.updateInt(4,mobno);
			rs.updateString(5,gender);
			rs.updateString(6,email);
			rs.insertRow();
			fetchCustomer();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateCustomer() {
		System.out.println("-------------------Customer--------------------");
		try {
			rs.beforeFirst();
			System.out.println("enter the customer id");
			int cid=sc.nextInt();
			while(rs.next()) {
				if(rs.getInt(1)==cid) {
					System.out.println("Customer name :"+rs.getString(2)+" "+rs.getString(3));
					System.out.println("old mobile no :"+rs.getInt(4));
					System.out.println("enter the new mobile no");
					int mno=sc.nextInt();
					rs.updateInt(4, mno);
					rs.updateRow();
				}
			}
			fetchCustomer();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteCustomer() {
		try {
			rs.beforeFirst();
			System.out.println("enter the customer id");
			int cid=sc.nextInt();
			while(rs.next()) {
				if(rs.getInt(1)==cid) {
					rs.deleteRow();
				}
			}
			fetchCustomer();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public static void main(String[] args) {
		ResultSetCustomer rsc= new ResultSetCustomer();
		rsc.fetchCustomer();
		rsc.insertCustomer();
		rsc.updateCustomer();
		rsc.deleteCustomer();
	}
}
