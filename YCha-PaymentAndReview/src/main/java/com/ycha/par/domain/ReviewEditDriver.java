package com.ycha.par.domain;

public class ReviewEditDriver {
	
	private int rv_idx; 
	private int d_idx; //from client
	private String dr_content; //from client
	private int dr_star; //from client
	
	public int getRv_idx() {
		return rv_idx;
	}
	public void setRv_idx(int rv_idx) {
		this.rv_idx = rv_idx;
	}
	public int getD_idx() {
		return d_idx;
	}
	public void setD_idx(int d_idx) {
		this.d_idx = d_idx;
	}
	public String getDr_content() {
		return dr_content;
	}
	public void setDr_content(String dr_content) {
		this.dr_content = dr_content;
	}
	public int getDr_star() {
		return dr_star;
	}
	public void setDr_star(int dr_star) {
		this.dr_star = dr_star;
	}
	
	@Override
	public String toString() {
		return "ReviewEditDriver [rv_idx=" + rv_idx + ", d_idx=" + d_idx + ", dr_content=" + dr_content + ", dr_star="
				+ dr_star + "]";
	}	
}
