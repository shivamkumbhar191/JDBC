package com.connectemployee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpData {
	
	static Connection con=null;
	Statement st;
	ResultSet rs;
	
	public EmpData() {
		con=DbConnectivity.getConnect();
		System.out.println("Connection Done...");
		
		try {
			st=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	public void fetchEmp() {
		try {
			rs=st.executeQuery("select first_name,last_name,salary,department_id from employees");
			System.out.println("------------Employee------------");
			System.out.println("fName  "+"lName     "+"salary  "+"DeptNo ");
			while(rs.next()) {
				System.out.println(rs.getString(1)+"   "+rs.getString(2)+"    "+rs.getInt(3)+"  "+rs.getInt(4));
			}
			System.out.println("-------------------------------------------");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		EmpData ed= new EmpData();
		ed.fetchEmp();

	}

}
