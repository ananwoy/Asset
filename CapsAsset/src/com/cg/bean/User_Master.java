package com.cg.bean;

public class User_Master
{
	private int UserId;
	private String UserName;
	private int UserPassword;
	private String UserType;
	
	public int getUserId()
	{
		return UserId;
	}
	public void setUserId(int UserId) 
	{
		this.UserId = UserId;
	}
	
	public String getUserName()
	{
		return UserName;
	}
	public void setUserName(String UserName) 
	{
		this.UserName = UserName;
	}
	
	public int getUserPassword()
	{
		return UserPassword;
	}
	public void setUserPassword(int UserPssword) 
	{
		this.UserPassword = UserPassword;
	}	
	public String getUserType()
	{
		return UserType;
	}
	
	public void setUserType(String UserType) 
	{
		this.UserType = UserType;
	}
}
