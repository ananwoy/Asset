import java.util.Scanner;

import com.cg.bean.Asset;

public class Main {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		boolean flag = true;
		AllMethods uv = new AllMethods();
		do {
			System.out.println("*******************");
			System.out.println("Enter you choice");
			System.out.println("1. Login");
			System.out.println("2. Exit ");
			System.out.println("*******************");
			int n = scn.nextInt();
			if (n == 1) {
				System.out.println("Enter your username: ");
				String usern = scn.next();
				System.out.println("Enter your password: ");
				String userp = scn.next();
				String utype = uv.validateUser(usern, userp);
				if (utype != null)
					if (utype.equals("admin")) {
						boolean inflag = true;
						do {
							System.out.println("Enter your choice: ");
							System.out.println("1. Add asset ");
							System.out.println("2. Modify asset");
							System.out.println("3. View requests");
							System.out.println("4. Generate Report");
							System.out.println("5. Logout");
							int xx = scn.nextInt();
							if (xx == 1) {
								Asset aid = new Asset();
								System.out.println("Enter asset ID");
								int assetid = scn.nextInt();
								System.out.println("Enter asset name");
								String assetname = scn.next();
								System.out.println("Enter asset description");
								String assetdes = scn.next();
								System.out.println("Enter quantity");
								int quantity = scn.nextInt();
								System.out.println("Enter status");
								String status = scn.next();
								//aid.setAsset(assetid, assetname, assetdes, quantity, status);

								//uv.addAsset(aid);
							} else if (xx == 2) {

								System.out.println("Choose column name");
								System.out.println("1. assetname");
								System.out.println("2. assetdes");
								System.out.println("3. quantity");
								System.out.println("4. status");
								int col = scn.nextInt();
								String colname = null;
								if (col == 1)
									colname = "assetname";
								else if (col == 2)
									colname = "assetdes";
								else if (col == 3)
									colname = "quantity";
								else if (col == 4)
									colname = "status";
								else {
									System.out.println("enter correct option");
									continue;
								}
								System.out.println("Enter asset id");
								int assetid = scn.nextInt();
								System.out.println("Enter new value");
								int value = scn.nextInt();
								uv.modifyAsset(colname, value, assetid);

							}
							else if(xx==3)
							{
								uv.allocateAsset();
							}
							else if(xx==4)
							{
								System.out.println("Choose option to generate report: ");
								System.out.println("1. Allocated");
								System.out.println("2. Unallocated");
								int nn=scn.nextInt();
								String status=null;
								if(nn==1)
								{
									status="Allocated";
								}
								else if(nn==2)
								{
									status="Unallocated";
								}
								uv.generateReport(status);
							}
							else if (xx == 5) {
								System.out.println("\nLogged out\n");
								inflag = false;
							}
						} while (inflag);
					} else if (utype.equals("manager")) {
						boolean inflagm = true;
						do {
							System.out.println("Enter your choice: ");
							System.out.println("1. Raise request ");
							System.out.println("2. View Status");
							System.out.println("3. Logout");
							int sel = scn.nextInt();
							if (sel == 1) {
								System.out.println("Enter the empno:");
								int empno = scn.nextInt();
								System.out.println("Enter asset id");
								int assetid = scn.nextInt();
								System.out.println("******************************");
								int reqid = uv.requestAsset(empno, assetid);
								System.out.println("Your request id is " + reqid);
								System.out.println("******************************");
							} else if (sel == 2) {
								System.out.println("Enter the request id");
								int reqID = scn.nextInt();
								System.out.println("******************************");
								uv.viewStatus(reqID);
								System.out.println("******************************");
							} else if (sel == 3) {
								System.out.println("Logged out");
								inflagm = false;
							} else {
								System.out.println("Choose correct option");
							}
						} while (inflagm);
					}
			} else if (n == 2) {
				System.out.println("\nTransaction complete\n");
				flag = false;
			} else {
				System.out.println("Choose correct option please");
			}
		} while (flag);
	}
}
