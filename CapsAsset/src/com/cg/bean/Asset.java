package com.cg.bean;

public class Asset 
{
	private int AssetId;
	private String AssetName;
	private String AssetDes;
	private int Quantity;
	private String Status;
	
	public int getAssetId()
	{
		return AssetId;
	}
	public void setAssetId(int AssetId) 
	{
		this.AssetId = AssetId;
	}
	
	public String getAssetName()
	{
		return AssetName;
	}
	public void setAssetName(String AssetName) 
	{
		this.AssetName = AssetName;
	}
	
	public String getAssetDes()
	{
		return AssetDes;
	}
	public void setAssetDes(String AssetDes) 
	{
		this.AssetDes = AssetDes;
	}
	
	public int getQuantity()
	{
		return Quantity;
	}
	public void setQuantity(int Quantity) 
	{
		this.Quantity = Quantity;
	}
	
	public String getStatus()
	{
		return Status;
	}
	public void setStatus(String Status) 
	{
		this.Status = Status;
	}
}
