package com.connectcar;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class CallFunctionsProcedure {
	static Scanner sc = new Scanner(System.in);
	public Connection conn = null;
	ResultSet rs, rs1;
	Statement st;
	CallableStatement cst;

	public CallFunctionsProcedure() {
		conn = DbConnectCar.getConnect();
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void showModelCustomer() {
		try {
			cst = conn.prepareCall("{call showModelCustomer()}");
			System.out.println("-----------------------------------------");
			if (cst.execute()) {
				rs = cst.getResultSet();
				while (rs.next()) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
					// System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+"
					// "+rs.getInt(4)+" "+rs.getString(5)+" "+rs.getString(6));
				}
			}
			System.out.println("-----------------------------------------");

			if (cst.getMoreResults()) {
				rs1 = cst.getResultSet();
				while (rs1.next()) {
					// System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
					System.out.println(rs1.getInt(1) + "  " + rs1.getString(2) + "  " + rs1.getString(3) + "  "
							+ rs1.getInt(4) + "  " + rs1.getString(5) + "  " + rs1.getString(6));
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("-----------------------------------------");
	}

	public void showCustomer() {
		System.out.println("Enter the model number");
		int mid = sc.nextInt();
		try {
			cst = conn.prepareCall("{call showCustName(?)}");
			cst.setInt(1, mid);
			if (cst.execute()) {
				rs = cst.getResultSet();
				while (rs.next()) {
					System.out.println(rs.getString(1) + " " + rs.getString(2) +
							"  " + rs.getString(3));

				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("-----------------------------------------");
	}

	public void countModel() {
		System.out.println("enter the model number");
		int mid = sc.nextInt();
		try {
			cst = conn.prepareCall("{call countMOdel(?,?,?)}");
			cst.setInt(1, mid);
			cst.registerOutParameter(2, Types.VARCHAR);
			cst.registerOutParameter(3, Types.INTEGER);

			if (cst.execute()) {
				rs = cst.getResultSet();
				while (rs.next()) {
					System.out.println(rs.getString(1) + " " + rs.getInt(2));
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("-----------------------------------------");
	}

	public void custDetails() {
		System.out.println("enter the customer id");
		int cid = sc.nextInt();
		try {
			cst = conn.prepareCall("{call custName(?)}");
			cst.setInt(1, cid);
			cst.registerOutParameter(1, Types.INTEGER);
			if (cst.execute()) {
				rs = cst.getResultSet();
				while (rs.next()) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2) 
					+ " " + rs.getString(3) + " " + rs.getInt(4)
							+ " " + rs.getString(5));
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("-----------------------------------------");
	}

	public static void main(String[] args) {
		CallFunctionsProcedure cfp = new CallFunctionsProcedure();
//		cfp.showModelCustomer();
//		cfp.showCustomer();
		cfp.countModel();
		cfp.custDetails();

	}

}
