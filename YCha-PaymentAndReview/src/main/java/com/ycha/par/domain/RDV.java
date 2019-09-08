package com.ycha.par.domain;

import java.util.Date;

public class RDV {
	
	private int r_idx;
	private int dr_idx;
	private int pr_idx;
	private Date r_date;
	private int rs_time;
	private int re_time;
	private String r_commute;
	private String r_startpoint;
	private String r_endpoint;
	private int r_fee;
	private int r_distance;
	
	public RDV () {}
	
	public RDV(int r_idx, int dr_idx, int pr_idx, Date r_date, int rs_time, int re_time, String r_commute,
			String r_startpoint, String r_endpoint, int r_fee, int r_distance) {
		super();
		this.r_idx = r_idx;
		this.dr_idx = dr_idx;
		this.pr_idx = pr_idx;
		this.r_date = r_date;
		this.rs_time = rs_time;
		this.re_time = re_time;
		this.r_commute = r_commute;
		this.r_startpoint = r_startpoint;
		this.r_endpoint = r_endpoint;
		this.r_fee = r_fee;
		this.r_distance = r_distance;
	}

	public int getR_idx() {
		return r_idx;
	}

	public void setR_idx(int r_idx) {
		this.r_idx = r_idx;
	}

	public int getDr_idx() {
		return dr_idx;
	}

	public void setDr_idx(int dr_idx) {
		this.dr_idx = dr_idx;
	}

	public int getPr_idx() {
		return pr_idx;
	}

	public void setPr_idx(int pr_idx) {
		this.pr_idx = pr_idx;
	}

	public Date getR_date() {
		return r_date;
	}

	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}

	public int getRs_time() {
		return rs_time;
	}

	public void setRs_time(int rs_time) {
		this.rs_time = rs_time;
	}

	public int getRe_time() {
		return re_time;
	}

	public void setRe_time(int re_time) {
		this.re_time = re_time;
	}

	public String getR_commute() {
		return r_commute;
	}

	public void setR_commute(String r_commute) {
		this.r_commute = r_commute;
	}

	public String getR_startpoint() {
		return r_startpoint;
	}

	public void setR_startpoint(String r_startpoint) {
		this.r_startpoint = r_startpoint;
	}

	public String getR_endpoint() {
		return r_endpoint;
	}

	public void setR_endpoint(String r_endpoint) {
		this.r_endpoint = r_endpoint;
	}

	public int getR_fee() {
		return r_fee;
	}

	public void setR_fee(int r_fee) {
		this.r_fee = r_fee;
	}

	public int getR_distance() {
		return r_distance;
	}

	public void setR_distance(int r_distance) {
		this.r_distance = r_distance;
	}

	@Override
	public String toString() {
		return "RDV [r_idx=" + r_idx + ", dr_idx=" + dr_idx + ", pr_idx=" + pr_idx + ", r_date=" + r_date + ", rs_time="
				+ rs_time + ", re_time=" + re_time + ", r_commute=" + r_commute + ", r_startpoint=" + r_startpoint
				+ ", r_endpoint=" + r_endpoint + ", r_fee=" + r_fee + ", r_distance=" + r_distance + "]";
	}

}
