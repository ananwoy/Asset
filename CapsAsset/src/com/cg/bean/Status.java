package com.cg.bean;

import java.util.Date;

public class Status {
private int reqID;
private int empno;
private int assetid;
private String status;
private String ename;
private String job;
private int mgrno;
private Date hiredate;
private int deptid;
public int getReqID() {
	return reqID;
}
public void setReqID(int reqID) {
	this.reqID = reqID;
}
public int getEmpno() {
	return empno;
}
public void setEmpno(int empno) {
	this.empno = empno;
}
public int getAssetid() {
	return assetid;
}
public void setAssetid(int assetid) {
	this.assetid = assetid;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getEname() {
	return ename;
}
public void setEname(String ename) {
	this.ename = ename;
}
public String getJob() {
	return job;
}
public void setJob(String job) {
	this.job = job;
}
public int getMgrno() {
	return mgrno;
}
public void setMgrno(int mgrno) {
	this.mgrno = mgrno;
}
public Date getHiredate() {
	return hiredate;
}
public void setHiredate(Date hiredate) {
	this.hiredate = hiredate;
}
public int getDeptid() {
	return deptid;
}
public void setDeptid(int deptid) {
	this.deptid = deptid;
}

}
