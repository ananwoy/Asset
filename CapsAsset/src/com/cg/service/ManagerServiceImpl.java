package com.cg.service;

import com.cg.Dao.ManagerDao;

public class ManagerServiceImpl implements ManagerServices
{
    @Override
	public int requestAsset(int empno, int assetid)
	{
    	ManagerServiceImpl requestasset = new ManagerServiceImpl();
    	return requestasset.requestAsset(empno, assetid);
    	
	}
    
    @Override
	public String viewStatus(int reqID)
	{
    	ManagerDao status = new ManagerDao();
    	return status.viewStatus(reqID);
	}
    
}
