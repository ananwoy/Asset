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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.cg.bean.Asset;
import com.cg.bean.Status;
public class AdminDao
{

	public String addAsset(Asset aid) {
		int assetid = aid.getAssetId();
		String assetname = aid.getAssetName();
		String assetdes = aid.getAssetDes();
		int quantity = aid.getQuantity();
		String status = aid.getStatus();
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
			String query = "INSERT into asset values(?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, assetid);
			pstmt.setString(2, assetname);
			pstmt.setString(3, assetdes);
			pstmt.setInt(4, quantity);
			pstmt.setString(5, status);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return "success";
			} else
				return "failed";
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
		return "failed";
	}
	

	public void modifyAsset(String colname, int value, int assetid) {
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
			String query = "UPDATE asset set " + colname + "=? where assetid=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, value);
			pstmt.setInt(2, assetid);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				System.out.println("Asset details changed succesfully");
			} else
				System.out.println("Asset details couldnot be changed");

		} catch (Exception e) {
			System.out.println("Asset details couldnot be changed");
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
	}
	
	
	
	public void allocateAsset(int reqid,int opt)
	{
		int newreqId=0;
		int newempno=0;
		int newassetid=0;
		String nstatus=null;
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
		Statement stmt = null;
		try {
			// 2. get the DB connection via Driver
			String dbUrl = "jdbc:mysql://localhost:3306/caps_asset";
			con = DriverManager.getConnection(dbUrl, prop);

			// 3. Issue the SQL query via connection
			String query1 = "select * from request_id r, employee e where r.empno=e.empno having r.status='Pending' and reqID=?";
			PreparedStatement pstmt = con.prepareStatement(query1);
			pstmt.setInt(1, reqid);
			ResultSet rs1 = pstmt.executeQuery();
			while (rs1.next()) {
				newreqId=rs1.getInt("reqID");
				newempno=rs1.getInt("empno");
				newassetid=rs1.getInt("assetid");
			} 
			System.out.println("Enter option: ");
			System.out.println("1. Allocate");
			System.out.println("2. Unallocate");
			if(opt==1)
			{
				nstatus="Allocated";
				String query2="INSERT INTO Asset_Allocation (AssetId, Empno, Allocation_date, Release_date) values(?,?,?,?)";
				PreparedStatement pstmt1=con.prepareStatement(query2);
				pstmt1.setInt(1, newassetid);
				pstmt1.setInt(2, newempno);
				Calendar calendar = Calendar.getInstance();
			    java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());	
			    calendar.add(Calendar.YEAR, 1);
			    java.sql.Date ndate=new java.sql.Date(calendar.getTime().getTime());
				pstmt1.setDate(3, date);
				pstmt1.setDate(4, ndate);
				int cnt1=pstmt1.executeUpdate();
				if(cnt1>0)
				{
					System.out.println("Successfully allocated");
				}
			}
			else if(opt==2)
			{
				nstatus="Unallocated";
				String qry="UPDATE asset SET quantity=quantity+1 where assetid=?";
				PreparedStatement pstmt2 = con.prepareStatement(qry);
				pstmt2.setInt(1, newassetid);
				int count1=pstmt2.executeUpdate();
				if(count1>0)
				{
					System.out.println("Successfully unallocated");
				}
			}
			String query12="UPDATE request_id SET Status=? where reqID=?";
			PreparedStatement pstmt3=con.prepareStatement(query12);
			pstmt3.setString(1, nstatus);
			pstmt3.setInt(2, newreqId);
			int cnt=pstmt3.executeUpdate();
			
			if(cnt>0)
			{
				System.out.println("Successfully updated tables");
			}
			else
			{
				System.out.println("Some error occurred");
			}
			} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("************************************************************");
			if (stmt != null)
				try {
					stmt.close();
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
	}
	
	
	
	public List<Asset> generateReport(String status)
	{
		List li=null;
		
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
			String query = "select * from asset where status=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, status);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("************************************************************");
			li=new ArrayList<Asset>();
			while (rs.next()) {
				Asset as=new Asset();
				int assetid=rs.getInt("assetid");
				as.setAssetId(assetid);
				String assetname=rs.getString(2);
				as.setAssetName(assetname);
				String assetdes=rs.getString(3);
				as.setAssetDes(assetdes);
				int quantity=rs.getInt(4);
				as.setQuantity(quantity);
				as.setStatus(status);
				li.add(as);
				} 
			System.out.println("************************************************************");
		}
			catch (Exception e) {
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
		return li;
	}
	public List<Status> viewStatus()
	{
		List li = null;
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
		Statement stmt = null;
		try {
			// 2. get the DB connection via Driver
			String dbUrl = "jdbc:mysql://localhost:3306/caps_asset";
			con = DriverManager.getConnection(dbUrl, prop);

			// 3. Issue the SQL query via connection
			String query = "select * from request_id r, employee e where r.empno=e.empno having r.status='Pending'";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("************************************************************");
			li=new ArrayList<Status>();
			while (rs.next()) {
				Status si=new Status();
				int reqId = rs.getInt("reqID");
				si.setReqID(reqId);
				int empno=rs.getInt("empno");
				si.setEmpno(empno);
				int assetid=rs.getInt("assetid");
				si.setAssetid(assetid);
				String status=rs.getString("Status");
				si.setStatus(status);
				String ename=rs.getString("ename");
				si.setEname(ename);
				String job=rs.getString("job");
				si.setJob(job);
				int mgrno=rs.getInt("mgrno");
				si.setMgrno(mgrno);
				Date hdate=rs.getDate("hiredate");
				si.setHiredate(hdate);
				int deptid=rs.getInt("deptid");
				si.setDeptid(deptid);
				
				li.add(si);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return li;
	}
}

	