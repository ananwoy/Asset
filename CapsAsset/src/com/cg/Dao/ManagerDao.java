package com.cg.Dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ManagerDao {

	public int requestAsset(int empno, String assetname, String assetdes) {
		FileReader fr = null;
		try {
			fr = new FileReader("D:/dbprop/db.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fr);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 2. get the DB connection via Driver
			String dbUrl = "jdbc:mysql://localhost:3306/caps_asset";
			con = DriverManager.getConnection(dbUrl, prop);

			// 3. Issue the SQL query via connection
			String query = "SELECT * FROM asset where assetname=? and assetdes=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, assetname);
			pstmt.setString(2, assetdes);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int assetid = rs.getInt(1);
				String query1 = "INSERT INTO request_id (Empno,assetid,status) values (?,?,?)";
				pstmt = con.prepareStatement(query1);
				pstmt.setInt(1, empno);
				pstmt.setInt(2, assetid);
				pstmt.setString(3, "Pending");
				int count = pstmt.executeUpdate();
				if (count > 0) {
					String qry = "UPDATE asset SET quantity=quantity-1 where assetid=?";
					pstmt = con.prepareStatement(qry);
					pstmt.setInt(1, assetid);
					int count1 = pstmt.executeUpdate();
					if (count1 > 0) {
						System.out.println("Request id generated successfully");
						String query2 = "SELECT MAX(reqID) FROM request_id";
						Statement stmt = con.createStatement();
						ResultSet rs1 = stmt.executeQuery(query2);
						if (rs1.next()) {
							int reqID = rs1.getInt(1);
							return reqID;
						}
					} else
						System.out.println("Request id generation failed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to generate request");
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return -1;
	}

	public String viewStatus(int reqID) {
		FileReader fr = null;
		try {
			fr = new FileReader("D:/dbprop/db.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fr);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 2. get the DB connection via Driver
			String dbUrl = "jdbc:mysql://localhost:3306/caps_asset";
			con = DriverManager.getConnection(dbUrl, prop);

			// 3. Issue the SQL query via connection
			String query = "SELECT * FROM request_id where reqID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, reqID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String status = rs.getString(4);
				return status;
			} else
				return null;
		} catch (Exception e) {
			System.out.println("Failed to fetch status");
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
	
}
