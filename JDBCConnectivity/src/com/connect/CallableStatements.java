package com.connect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

import com.connectemployee.DbConnectivity;

public class CallableStatements {
	
	static Scanner sc= new Scanner(System.in);
	ResultSet rs;
	Statement st;
	CallableStatement cst;
	public Connection conn=null;
	
	public CallableStatements() {
		conn=DbConnectivity.getConnect();
		try {
			st=conn.createStatement();
		} catch (SQLException e) {
			
			System.out.println(e);
		}
	}
	public void showEmpSalary() {
		System.out.println("enter the employee id");
		int empid=sc.nextInt();
		try {
			cst=conn.prepareCall("{?=call empSalary(?)}");
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, empid);
			
			if(!cst.execute()) {
				System.out.println("the salary of employees is "+cst.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-----------------------------------------");
	}
	public void getNameSalary() {
		System.out.println("enter the employee id");
		int eid=sc.nextInt();
		try {
			cst=conn.prepareCall("{call getNameSalary(?)}");
			cst.setInt(1, eid);
			if(cst.execute()) {
				rs=cst.getResultSet();
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);		
		}
		System.out.println("-----------------------------------------");
	}
	public void getEmpIdNameSalary() {
		System.out.println("enter the employee id");
		int eid=sc.nextInt();
		try {
			cst=conn.prepareCall("{call getEmpNameSalary(?,?,?,?)}");
			cst.setInt(1, eid);
			cst.registerOutParameter(2, Types.VARCHAR);
			cst.registerOutParameter(3, Types.VARCHAR);
			cst.registerOutParameter(4,Types.FLOAT);
			boolean status=cst.execute();
			if(status) {
				rs=cst.getResultSet();
				while(rs.next()) {
					System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getFloat(3));
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("-----------------------------------------");
	}

	public static void main(String[] args) {
		CallableStatements cs= new CallableStatements();
//		cs.showEmpSalary();
//		cs.getNameSalary();
		cs.getEmpIdNameSalary();

	}

}
