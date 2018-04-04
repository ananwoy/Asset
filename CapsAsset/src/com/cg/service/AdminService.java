package com.cg.service;

import com.cg.bean.Asset;

public interface AdminService
{
	public String addAsset(Asset aid);
	public void modifyAsset(String colname, int value, int assetid);
	public void allocateAsset();
	public void generateReport(String status);
}
