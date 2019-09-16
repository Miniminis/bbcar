package com.ycha.par.domain;

public class ReviewEditPassenger {
	
	private int rv_idx; 
	private int p_idx; //from client
	private String pr_content; //from client
	private int pr_star; //from client

	//public ReviewEditPassenger () {}

	public int getRv_idx() {
		return rv_idx;
	}

	public void setRv_idx(int rv_idx) {
		this.rv_idx = rv_idx;
	}

	public int getP_idx() {
		return p_idx;
	}

	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
	}

	public String getPr_content() {
		return pr_content;
	}

	public void setPr_content(String pr_content) {
		this.pr_content = pr_content;
	}

	public int getPr_star() {
		return pr_star;
	}

	public void setPr_star(int pr_star) {
		this.pr_star = pr_star;
	}

	@Override
	public String toString() {
		return "ReviewEditPassenger [rv_idx=" + rv_idx + ", p_idx=" + p_idx + ", pr_content=" + pr_content
				+ ", pr_star=" + pr_star + "]";
	}
	
	
	
	
}
