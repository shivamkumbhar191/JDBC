package com.resultset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ResultSetDemo {
	
	Connection conn= null;
	ResultSet rs;
	Statement st;
	static Scanner sc= new Scanner(System.in);
	public ResultSetDemo() {
		conn=DbConnectCar.getConnect();
		try {
			st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			e.printStackTrace();
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
	public void fetchModelreverse() {
		try {
			//set resultset cursor after  at last row
			
			//execute query or update
			//st=con.createStatement();
			rs=st.executeQuery("select * from model");
			rs.afterLast();
			
			//cursor point is before the first row
			//process the result
			System.out.println("-------------------Model-------------------");
			System.out.println("mid"+" "+"mname"+"     "+"mcost");
			
			while(rs.previous()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+"   "+rs.getInt(3));
			}
			System.out.println("-------------------------------------------");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void insertModel() {
		try {
			rs=st.executeQuery("select * from model");
			System.out.println("enter the model id");
			int mid= sc.nextInt();
			System.out.println("enter the model name");
			String mname=sc.next();
			System.out.println("enter the model cost");
			int mcost= sc.nextInt();
			rs.moveToInsertRow();
			rs.updateInt(1, mid);
			rs.updateString(2, mname);
			rs.updateInt(3, mcost);
			rs.insertRow();
			fetchModel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteModel() {
		try {
			rs=st.executeQuery("select * from model");
			System.out.println("enter the model id");
			int mid=sc.nextInt();
			while(rs.next()) {
				if(rs.getInt(1)==mid) {
					rs.deleteRow();
				}
			}
			fetchModel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateModel() {
		try {
			rs=st.executeQuery("select * from model");
			System.out.println("enter the model id");
			int mid= sc.nextInt();
			while(rs.next()) {
				if(rs.getInt(1)==mid) {
					System.out.println("Model name :"+rs.getString(2));
					System.out.println("Model old cost :"+rs.getInt(3));
					System.out.println("Enter the new cost");
					int mcost=sc.nextInt();
					rs.updateInt(3, mcost);
					rs.updateRow();
				}
			}
			fetchModel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		ResultSetDemo rd= new ResultSetDemo();
		rd.fetchModel();
		//System.out.println("-------------------------------------------");
		rd.fetchModelreverse();
		//rd.insertModel();
		//rd.deleteModel();
		//rd.updateModel();
	}

}
