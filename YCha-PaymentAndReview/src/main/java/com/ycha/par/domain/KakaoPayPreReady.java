package com.ycha.par.domain;

public class KakaoPayPreReady {
	
	private String r_idx;
	private String p_idx;
	private String r_fee;
	private String tax_free_amount;
	
	public String getR_idx() {
		return r_idx;
	}
	public void setR_idx(String r_idx) {
		this.r_idx = r_idx;
	}
	public String getP_idx() {
		return p_idx;
	}
	public void setP_idx(String p_idx) {
		this.p_idx = p_idx;
	}
	public String getR_fee() {
		return r_fee;
	}
	public void setR_fee(String r_fee) {
		this.r_fee = r_fee;
	}
	public String getTax_free_amount() {
		return tax_free_amount;
	}
	public void setTax_free_amount(String tax_free_amount) {
		this.tax_free_amount = tax_free_amount;
	}
	
	
	@Override
	public String toString() {
		return "KakaoPayPreReady [r_idx=" + r_idx + ", p_idx=" + p_idx + ", r_fee=" + r_fee + ", tax_free_amount="
				+ tax_free_amount + "]";
	}

}
