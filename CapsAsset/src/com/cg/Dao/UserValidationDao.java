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
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import com.cg.bean.Asset;
import com.cg.bean.User_Master;

public class UserValidationDao {
	private int reqId;
	public User_Master validateUser(String usern, String userp) {
		User_Master um=new User_Master();
		String utype = null;
		String uname=null;
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
			String query = "SELECT * from User_Master where username=? and userpassword=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, usern);
			pstmt.setString(2, userp);
			ResultSet res = pstmt.executeQuery();
			if (res.next()) {
				int userid = res.getInt("userid");
				um.setUserId(userid);
				utype = res.getString("usertype");
				um.setUserType(utype);
				uname=res.getString("username");
				um.setUserName(uname);
				System.out.println("User " + userid + " logged in successfully as " + utype);
			} else
				System.out.println("Please enter correct username/password");

		} catch (Exception e) {
			e.printStackTrace();
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
		return um;
	}
}