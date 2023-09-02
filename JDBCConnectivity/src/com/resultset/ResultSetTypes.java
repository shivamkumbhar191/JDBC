package com.resultset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ResultSetTypes {
	Connection conn= null;
	ResultSet rs;
	Statement st;
	Scanner sc= new Scanner(System.in);
	public ResultSetTypes() {
		conn=DbConnectCar.getConnect();
		try {
			st=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void fetchModels() {
		try {
			rs=st.executeQuery("select * from model");
			System.out.println("-------------------Model--------------------");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("--------------------------------------------");
	}
	public void fetchModelReverse() {
		try {
			
			rs=st.executeQuery("select * from model");
			rs.afterLast();
			System.out.println("-------------------Model--------------------");
			while(rs.previous()) {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("--------------------------------------------");
	}
	public void fetchSecondLast() {
		try {
			rs=st.executeQuery("select * from model");
			rs.absolute(5);
			System.out.println("-------------------Model--------------------");
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));
			
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("--------------------------------------------");
	}
	public void updateModel() {	
		try {
			rs=st.executeQuery("select * from model");
			System.out.println("enter the model id");
			int mid=sc.nextInt();
			while(rs.next()) {
				if(rs.getInt(1)==mid) {
					System.out.println("Model name :"+rs.getString(2));
					System.out.println("Model old cost :"+rs.getInt(3));
					System.out.println("enter the new cost");
					int cost=sc.nextInt();
					rs.updateInt(3,cost);
					rs.updateRow();
				}
			}
			fetchModels();
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
			fetchModels();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		ResultSetTypes rst= new ResultSetTypes();
		rst.fetchModels();
		//rst.fetchModelReverse();
		//rst.fetchSecondLast();
		//rst.updateModel();
		rst.deleteModel();
	}

}
