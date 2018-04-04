package com.cg.service;

import com.cg.bean.Asset;
import com.cg.bean.Requests;

public interface ManagerServices 
{
	public int requestAsset(int empno, int assetid);
	public String viewStatus(int reqID);
}
