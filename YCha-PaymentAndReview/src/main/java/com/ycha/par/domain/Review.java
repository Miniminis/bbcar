package com.ycha.par.domain;

public class Review {
	
	private int rv_idx; 
	private int payidx; 
	private int p_idx; //from client
	private int d_idx; 
	private String pr_content; //from client
	private int pr_star; //from client
	private String dr_content;
	private int dr_star;
	
	//DB 저장은 X 출력 시 표현 및 다른 변수들을 구하기 위한 매개값 
	private String p_nickname; //from client
	private String d_nickname;
	private int r_idx; //from client
	
	public Review() {}
	

	public int getRv_idx() {
		return rv_idx;
	}

	public void setRv_idx(int rv_idx) {
		this.rv_idx = rv_idx;
	}

	public int getPayidx() {
		return payidx;
	}

	public void setPayidx(int payidx) {
		this.payidx = payidx;
	}

	public int getP_idx() {
		return p_idx;
	}

	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
	}

	public int getD_idx() {
		return d_idx;
	}

	public void setD_idx(int d_idx) {
		this.d_idx = d_idx;
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

	public String getP_nickname() {
		return p_nickname;
	}

	public void setP_nickname(String p_nickname) {
		this.p_nickname = p_nickname;
	}

	public String getD_nickname() {
		return d_nickname;
	}

	public void setD_nickname(String d_nickname) {
		this.d_nickname = d_nickname;
	}

	public int getR_idx() {
		return r_idx;
	}

	public void setR_idx(int r_idx) {
		this.r_idx = r_idx;
	}

	@Override
	public String toString() {
		return "Review [rv_idx=" + rv_idx + ", payidx=" + payidx + ", p_idx=" + p_idx + ", d_idx=" + d_idx
				+ ", pr_content=" + pr_content + ", pr_star=" + pr_star + ", dr_content=" + dr_content + ", dr_star="
				+ dr_star + ", p_nickname=" + p_nickname + ", d_nickname=" + d_nickname + ", r_idx=" + r_idx + "]";
	}
	
}
