package com.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PreparedStatementDemo {
	
	static Connection con=null;
	ResultSet rs;
	Statement st;
	PreparedStatement pst;
	static Scanner sc= new Scanner (System.in);
	public PreparedStatementDemo() {
		con=DBConnect.getConnect();
		System.out.println("Connection done...");
		try {
			st=con.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertModel() {
		System.out.println("enter the model id");
		int mid=sc.nextInt();
		System.out.println("enter the model name");
		String mname=sc.next();
		System.out.println("enter the model cost");
		int mcost=sc.nextInt();
		try {
			pst=con.prepareStatement("insert into model values(?,?,?)");
			pst.setInt(1, mid);
			pst.setString(2, mname);
			pst.setInt(3, mcost);
			
			int noOfRowsInserted=pst.executeUpdate();
			if(noOfRowsInserted>0) {
				System.out.println("Model inserted");
			}else {
				System.out.println("not inserted");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteModel() {
		//using statement
		System.out.println("enter the model id");
		int mid= sc.nextInt();
		try {
			int noOdRowsDeleted=st.executeUpdate("delete from model where mid="+mid);
			if(noOdRowsDeleted>0) {
				System.out.println("model deleted");
			}
			else {
				System.out.println("Model not deleted");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	public void deleteModelPst() {
		//faster
		System.out.println("enter the model id");
		int mid=sc.nextInt();
		try {
			pst=con.prepareStatement("delete from model where mid=?");
			pst.setInt(1, mid);
			int noOfRowsDeleted=pst.executeUpdate();
			if(noOfRowsDeleted>0) {
				System.out.println("model deleted");
			}else {
				System.out.println("Not deleted");
			}
			
		} catch (SQLException e) {
			
			System.out.println(e);
		}
	}
	public void updateModel() {
		//using statement
		System.out.println("enter the model id");
		int mid=sc.nextInt();
		try {
			int noOfRowsUpdated=st.executeUpdate("update model set mcost=9000000 where mid="+mid);
			if(noOfRowsUpdated>0){
				System.out.println("Model updated");
			}else {
				System.out.println("not updated");
			}
		
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	public void updateModelPst() {
		System.out.println("enter the model id");
		int mid=sc.nextInt();
		System.out.println("enter the new cost");
		int mcost=sc.nextInt();
		try {
			pst=con.prepareStatement("update model set mcost=? where mid=?");
			pst.setInt(1, mcost);
			pst.setInt(2, mid);
			int noOfRowsUpdated=pst.executeUpdate();
			if(noOfRowsUpdated>0) {
				System.out.println("model updated");
			}else {
				System.out.println("model not found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	public static void main(String[] args) {
		PreparedStatementDemo psd= new PreparedStatementDemo();
		//psd.insertModel();
		//psd.deleteModel();
		//psd.deleteModelPst();
		psd.updateModel();
		psd.updateModelPst();

	}

}
