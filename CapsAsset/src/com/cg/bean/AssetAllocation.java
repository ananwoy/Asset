package com.cg.bean;

public class AssetAllocation 
{
	private int AllocationId;
	private int AssetId;
	private int Empno;
	private String Allocationdate;
	private String Releasedate;
	
	public int getAssetId()
	{
		return AssetId;
	}
	public void setAssetId(int AssetId) 
	{
		this.AssetId = AssetId;
	}
	
	public int getAllocationId()
	{
		return AllocationId;
	}
	public void setAllocationId(int AllocationId) 
	{
		this.AllocationId = AllocationId;
	}
	
	public int getEmpno()
	{
		return  Empno;
	}
	public void setEmpno(int  Empno) 
	{
		this.Empno =  Empno;
	}
	
	public String getAllocationdate()
	{
		return Allocationdate;
	}
	public void setAllocationdate(String Allocationdate) 
	{
		this.Allocationdate = Allocationdate;
	}
	
	public String getReleasedate()
	{
		return Releasedate;
	}
	public void setReleasedate(String Releasedate) 
	{
		this.Releasedate = Releasedate;
	}
}
