package com.ycha.par.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Payment {
	
	private int payidx = 1;
	private int r_idx = 0;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm", timezone="Asia/Seoul")
	private Date serdate;
	private int sertime = 0;
	private int serdistance = 0;
	private int serprice = 0;
	private int dtime = 0;
	private String dplace;
	private int atime = 0;
	private String aplace;
	
	public Payment() {}
	
	public Payment(int payidx, int r_idx, Date serdate, int sertime, int serdistance, int serprice, int dtime,
			String dplace, int atime, String aplace) {
		super();
		this.payidx = payidx;
		this.r_idx = r_idx;
		this.serdate = serdate;
		this.sertime = sertime;
		this.serdistance = serdistance;
		this.serprice = serprice;
		this.dtime = dtime;
		this.dplace = dplace;
		this.atime = atime;
		this.aplace = aplace;
	}

	public int getPayidx() {
		return payidx;
	}

	public void setPayidx(int payidx) {
		this.payidx = payidx;
	}

	public int getR_idx() {
		return r_idx;
	}

	public void setR_idx(int r_idx) {
		this.r_idx = r_idx;
	}

	public Date getSerdate() {
		return serdate;
	}

	public void setSerdate(Date serdate) {
		this.serdate = serdate;
	}

	public int getSertime() {
		return sertime;
	}

	public void setSertime(int sertime) {
		this.sertime = sertime;
	}

	public int getSerdistance() {
		return serdistance;
	}

	public void setSerdistance(int serdistance) {
		this.serdistance = serdistance;
	}

	public int getSerprice() {
		return serprice;
	}

	public void setSerprice(int serprice) {
		this.serprice = serprice;
	}

	public int getDtime() {
		return dtime;
	}

	public void setDtime(int dtime) {
		this.dtime = dtime;
	}

	public String getDplace() {
		return dplace;
	}

	public void setDplace(String dplace) {
		this.dplace = dplace;
	}

	public int getAtime() {
		return atime;
	}

	public void setAtime(int atime) {
		this.atime = atime;
	}

	public String getAplace() {
		return aplace;
	}

	public void setAplace(String aplace) {
		this.aplace = aplace;
	}

	@Override
	public String toString() {
		return "Payment [payidx=" + payidx + ", r_idx=" + r_idx + ", serdate=" + serdate + ", sertime=" + sertime
				+ ", serdistance=" + serdistance + ", serprice=" + serprice + ", dtime=" + dtime + ", dplace=" + dplace
				+ ", atime=" + atime + ", aplace=" + aplace + "]";
	}

}
