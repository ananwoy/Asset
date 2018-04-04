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

public class AllMethods {
	private int reqId;
	public String validateUser(String usern, String userp) {
		String utype = null;
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
				utype = res.getString("usertype");
				System.out.println("User " + userid + " logged in successfully as " + utype);
			} else
				return "invalid";

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
		return utype;
	}

	public void addAsset(Asset aid) {
		/*int assetid = aid.getAssetid();
		String assetname = aid.getAssetname();
		String assetdes = aid.getAssetdes();*/
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
			//pstmt.setInt(1, assetid);
			//pstmt.setString(2, assetname);
			//pstmt.setString(3, assetdes);
			pstmt.setInt(4, quantity);
			pstmt.setString(5, status);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				System.out.println("Asset details inserted succesfully");
			} else
				System.out.println("Asset details couldnot be inserted");

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
	}

	public int requestAsset(int empno, int assetid) {
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
			String query = "INSERT INTO request_id (Empno,assetid,status) values (?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, empno);
			pstmt.setInt(2, assetid);
			pstmt.setString(3, "Pending");
			int count = pstmt.executeUpdate();
			if (count > 0) {
				String qry="UPDATE asset SET quantity=quantity-1 where assetid=?";
				pstmt = con.prepareStatement(qry);
				pstmt.setInt(1, assetid);
				int count1=pstmt.executeUpdate();
				if(count1>0)
				{
				System.out.println("Request id generated successfully");
				String query1 = "SELECT MAX(reqID) FROM request_id";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query1);
				if(rs.next())
				{
					int reqID=rs.getInt(1);
					return reqID;
				}
			} else
				System.out.println("Request id generation failed");
			}
		} catch (Exception e) {
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
	public void viewStatus(int reqID)
	{
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
				String status=rs.getString(4);
				System.out.println("Status is: "+status);
			} else
				System.out.println("Enter correct request id");

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
	}
	public void allocateAsset()
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
			String query = "select * from request_id r, employee e where r.empno=e.empno having r.status='Pending'";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("************************************************************");
			while (rs.next()) {
				int reqId = rs.getInt("reqID");
				int empno=rs.getInt("empno");
				int assetid=rs.getInt("assetid");
				String status=rs.getString("Status");
				String ename=rs.getString("ename");
				String job=rs.getString("job");
				int mgrno=rs.getInt("mgrno");
				Date hdate=rs.getDate("hiredate");
				int deptid=rs.getInt("deptid");
				System.out.println(reqId+" - "+empno+" - "+assetid+" - "+ename+" - "+job+" - "+mgrno+" - "+hdate+" - "+deptid+" - "+status);
			} 
			System.out.println("************************************************************");
			System.out.println("Enter requestID for which you want to allocate/unallocate");
			Scanner scn=new Scanner(System.in);
			int reqid=scn.nextInt();
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
			int opt=scn.nextInt();
			if(opt==1)
			{
				nstatus="Allocated";
				String query2="INSERT INTO Asset_Allocation (AssetId, Empno, Allocation_date, Release_date) values(?,?,?,?)";
				PreparedStatement pstmt1=con.prepareStatement(query2);
				pstmt1.setInt(1, newassetid);
				pstmt1.setInt(2, newempno);
				Calendar calendar = Calendar.getInstance();
			    java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());			
				pstmt1.setDate(3, date);
				pstmt1.setDate(4, date);
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
	public void generateReport(String status)
	{
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
			String query = "select * from request_id r, employee e where r.empno=e.empno having r.status=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, status);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("************************************************************");
			while (rs.next()) {
				int reqId = rs.getInt("reqID");
				int empno=rs.getInt("empno");
				int assetid=rs.getInt("assetid");
				String ename=rs.getString("ename");
				String job=rs.getString("job");
				int mgrno=rs.getInt("mgrno");
				Date hdate=rs.getDate("hiredate");
				int deptid=rs.getInt("deptid");
				System.out.println(reqId+" - "+empno+" - "+assetid+" - "+ename+" - "+job+" - "+mgrno+" - "+hdate+" - "+deptid+" - "+status);
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
	}
	
}
