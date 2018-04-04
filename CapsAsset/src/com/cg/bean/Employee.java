package com.cg.bean;

public class Employee
{
	private int Empno ;
	private String Ename;
	private String job;
	private String mgr;
	private String hiredate;
	private int DeptId;
	
	public int getEmpno()
	{
		return Empno;
	}
	public void setEmpno(int Empno) 
	{
		this.Empno = Empno;
	}
	
	public String getEname()
	{
		return Ename;
	}
	public void setEname(String Ename) 
	{
		this.Ename =Ename;
	}
	
	public String getjob()
	{
		return job;
	}
	public void setjob(String job) 
	{
		this.job =job;
	}
	
	public String getmgr()
	{
		return mgr;
	}
	public void setmgr(String mgr) 
	{
		this.mgr =mgr;
	}
	
	public String gethiredate()
	{
		return hiredate;
	}
	public void sethiredate(String hiredate) 
	{
		this.hiredate =hiredate;
	}
	
	public int getDeptId()
	{
		return DeptId;
	}
	public void setDeptId(int DeptId) 
	{
		this.DeptId = DeptId;
	}
}

