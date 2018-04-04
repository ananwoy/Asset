package com.cg.service;

import com.cg.Dao.AdminDao;
import com.cg.bean.Asset;

public class AdminServiceImpl implements AdminService
{
	@Override
	public String addAsset(Asset aid) 
	{
		String st=null;
		if(aid!=null)
		{
			AdminDao addasset = new AdminDao();
			st=addasset.addAsset(aid);
		}
		return st;
	}
	
	
	@Override
	public void modifyAsset(String colname, int value, int assetid)
	{
		
		AdminServiceImpl modify = new AdminServiceImpl();
		modify.modifyAsset(colname, value, assetid);
	}
	
	@Override
	public void allocateAsset()
	{
		AdminServiceImpl allocate= new AdminServiceImpl();
		allocate.allocateAsset();
	}
	
	@Override
	public void generateReport(String status)
	{
		AdminServiceImpl report = new AdminServiceImpl();
		report.generateReport(status);
	}
}
