package com.testboot.entity;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table(name = "PASSENGER")
public class PassengerEntity {
	
	@Id
	@Column
	private long p_idx;
	
	@Column(length = 50, nullable = false)
	private String id;
	
	@Column(length = 50, nullable = false)
	private String pw;
	
	@Column(length = 20, nullable = false)
	private String name;
	
	@Column(length = 50, nullable = false)
	private String nickname;
	
	@Column(length = 100, nullable = false)
	private String email; 
	
	@Column(length = 1, nullable = false)
	private char gender;
	
	@Column(length = 1, nullable = false)
	private char verify;
	
	@Column(length = 50, nullable = false)
	private String code;
	
	@Column(length = 1, nullable = false)
	private char type;
	
	@Column(length = 50, nullable = false)
	private String company;

	@Column(length = 100, nullable = false)
	private String cemail;
	
	@Column(length = 20, nullable = false)
	private int cnum;
	
	@Column(length = 1, nullable = false)
	private char ctype; 
	
	@Column(length = 20, nullable = false)
	private String p_option;

	public long getP_idx() {
		return p_idx;
	}

	public void setP_idx(long p_idx) {
		this.p_idx = p_idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public char getVerify() {
		return verify;
	}

	public void setVerify(char verify) {
		this.verify = verify;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCemail() {
		return cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public char getCtype() {
		return ctype;
	}

	public void setCtype(char ctype) {
		this.ctype = ctype;
	}

	public String getP_option() {
		return p_option;
	}

	public void setP_option(String p_option) {
		this.p_option = p_option;
	}

	@Override
	public String toString() {
		return "PassengerEntity [p_idx=" + p_idx + ", id=" + id + ", pw=" + pw + ", name=" + name + ", nickname="
				+ nickname + ", email=" + email + ", gender=" + gender + ", verify=" + verify + ", code=" + code
				+ ", type=" + type + ", company=" + company + ", cemail=" + cemail + ", cnum=" + cnum + ", ctype="
				+ ctype + ", p_option=" + p_option + "]";
	}

}
