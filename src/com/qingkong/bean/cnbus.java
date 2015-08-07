package com.qingkong.bean;

public class cnbus {
	int xid ;
	int pm;
	String zhan;
	int kid;
	public int getXid() {
		return xid;
	}
	public void setXid(int xid) {
		this.xid = xid;
	}
	public int getPm() {
		return pm;
	}
	public void setPm(int pm) {
		this.pm = pm;
	}
	public String getZhan() {
		return zhan;
	}
	public void setZhan(String zhan) {
		this.zhan = zhan;
	}
	public int getKid() {
		return kid;
	}
	public void setKid(int kid) {
		this.kid = kid;
	}
	@Override
	public String toString() {
		return "cnbus [xid=" + xid + ", pm=" + pm + ", zhan=" + zhan + ", kid="
				+ kid + "]";
	}
	
}
