package com.ycha.par.domain;

public class ReviewInfo {
	
	private int payidx;
	private int d_idx;
	private int p_idx;
	
	public int getPayidx() {
		return payidx;
	}
	public void setPayidx(int payidx) {
		this.payidx = payidx;
	}
	public int getD_idx() {
		return d_idx;
	}
	public void setD_idx(int d_idx) {
		this.d_idx = d_idx;
	}
	public int getP_idx() {
		return p_idx;
	}
	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
	}
	@Override
	public String toString() {
		return "ReviewInfo [payidx=" + payidx + ", d_idx=" + d_idx + ", p_idx=" + p_idx + "]";
	}
	
	

}
