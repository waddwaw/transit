package com.qingkong.bean;

public class cnbusw {
	int id;
	String shijian;
	String kid;
	String gigs;
	String piao;
	String note;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShijian() {
		return shijian;
	}

	@Override
	public String toString() {
		return "cnbusw [id=" + id + ", shijian=" + shijian + ", kid=" + kid
				+ ", gigs=" + gigs + ", piao=" + piao + ", note=" + note + "]";
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public String getKid() {
		return kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	public String getGigs() {
		return gigs;
	}

	public void setGigs(String gigs) {
		this.gigs = gigs;
	}

	public String getPiao() {
		return piao;
	}

	public void setPiao(String piao) {
		this.piao = piao;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
