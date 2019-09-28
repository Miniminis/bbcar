package com.testboot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "RESERVATION")
@SecondaryTables({
		@SecondaryTable(name = "PASSENGER", pkJoinColumns = @PrimaryKeyJoinColumn(name = "p_idx")),
		@SecondaryTable(name = "D_CARPOOL", pkJoinColumns = @PrimaryKeyJoinColumn(name = "dr_idx"))	
})
public class ReservationEntity {
	
	@Id
	@Column
	private long r_idx;
	
	@Column(length = 10, nullable = false)
	private int p_idx;
	
	@Column(length = 10, nullable = false)
	private int dr_idx;
	
	@Column(nullable = false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm", timezone="Asia/Seoul")
	private Date r_date;
	
	@Column(length = 10, nullable = false)
	private char r_confirm;
	
	//passenger table 
	@Column(table = "PASSENGER")
	private String nickname;
	
	//d_carpool 
	@Column(table = "D_CARPOOL")
	private String d_date;
	
	@Column(table = "D_CARPOOL")
	private String d_starttime;
	
	@Column(table = "D_CARPOOL")
	private String d_endtime;
	
	@Column(table = "D_CARPOOL")
	private String d_startpoint;
	
	@Column(table = "D_CARPOOL")
	private String d_endpoint;
	
	@Column(table = "D_CARPOOL")
	private String d_commute;
	
	@Column(table = "D_CARPOOL")
	private String d_fee;
	
	@Column(table = "D_CARPOOL")
	private String d_startlon;
	
	@Column(table = "D_CARPOOL")
	private String d_startlat;
	
	@Column(table = "D_CARPOOL")
	private String d_endlon;
	
	@Column(table = "D_CARPOOL")
	private String d_endlat;
	

	//Lombok 으로 나중에 수정하기 
	public long getR_idx() {
		return r_idx;
	}

	public void setR_idx(long r_idx) {
		this.r_idx = r_idx;
	}

	public int getP_idx() {
		return p_idx;
	}

	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
	}

	public int getDr_idx() {
		return dr_idx;
	}

	public void setDr_idx(int dr_idx) {
		this.dr_idx = dr_idx;
	}

	public Date getR_date() {
		return r_date;
	}

	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}

	public char getR_confirm() {
		return r_confirm;
	}

	public void setR_confirm(char r_confirm) {
		this.r_confirm = r_confirm;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getD_date() {
		return d_date;
	}

	public void setD_date(String d_date) {
		this.d_date = d_date;
	}

	public String getD_starttime() {
		return d_starttime;
	}

	public void setD_starttime(String d_starttime) {
		this.d_starttime = d_starttime;
	}

	public String getD_endtime() {
		return d_endtime;
	}

	public void setD_endtime(String d_endtime) {
		this.d_endtime = d_endtime;
	}

	public String getD_startpoint() {
		return d_startpoint;
	}

	public void setD_startpoint(String d_startpoint) {
		this.d_startpoint = d_startpoint;
	}

	public String getD_endpoint() {
		return d_endpoint;
	}

	public void setD_endpoint(String d_endpoint) {
		this.d_endpoint = d_endpoint;
	}

	public String getD_commute() {
		return d_commute;
	}

	public void setD_commute(String d_commute) {
		this.d_commute = d_commute;
	}

	public String getD_fee() {
		return d_fee;
	}

	public void setD_fee(String d_fee) {
		this.d_fee = d_fee;
	}

	public String getD_startlon() {
		return d_startlon;
	}

	public void setD_startlon(String d_startlon) {
		this.d_startlon = d_startlon;
	}

	public String getD_startlat() {
		return d_startlat;
	}

	public void setD_startlat(String d_startlat) {
		this.d_startlat = d_startlat;
	}

	public String getD_endlon() {
		return d_endlon;
	}

	public void setD_endlon(String d_endlon) {
		this.d_endlon = d_endlon;
	}

	public String getD_endlat() {
		return d_endlat;
	}

	public void setD_endlat(String d_endlat) {
		this.d_endlat = d_endlat;
	}

	@Override
	public String toString() {
		return "ReservationEntity [r_idx=" + r_idx + ", p_idx=" + p_idx + ", dr_idx=" + dr_idx + ", r_date=" + r_date
				+ ", r_confirm=" + r_confirm + ", nickname=" + nickname + ", d_date=" + d_date + ", d_starttime="
				+ d_starttime + ", d_endtime=" + d_endtime + ", d_startpoint=" + d_startpoint + ", d_endpoint="
				+ d_endpoint + ", d_commute=" + d_commute + ", d_fee=" + d_fee + ", d_startlon=" + d_startlon
				+ ", d_startlat=" + d_startlat + ", d_endlon=" + d_endlon + ", d_endlat=" + d_endlat + ", getR_idx()="
				+ getR_idx() + ", getP_idx()=" + getP_idx() + ", getDr_idx()=" + getDr_idx() + ", getR_date()="
				+ getR_date() + ", getR_confirm()=" + getR_confirm() + ", getNickname()=" + getNickname()
				+ ", getD_date()=" + getD_date() + ", getD_starttime()=" + getD_starttime() + ", getD_endtime()="
				+ getD_endtime() + ", getD_startpoint()=" + getD_startpoint() + ", getD_endpoint()=" + getD_endpoint()
				+ ", getD_commute()=" + getD_commute() + ", getD_fee()=" + getD_fee() + ", getD_startlon()="
				+ getD_startlon() + ", getD_startlat()=" + getD_startlat() + ", getD_endlon()=" + getD_endlon()
				+ ", getD_endlat()=" + getD_endlat() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	
	
	
	
//	@ManyToOne	//다 대 일 관계 : 예약 - 카풀경로
//	@JoinColumn(name = "dr_idx", insertable = false, updatable = false) //조인 컬럼 정의 
//	private CarpoolEntity carpoolEntity; //카풀
//
//	public CarpoolEntity getCarpoolEntity() {
//		return carpoolEntity;
//	}
//
//	public void setCarpoolEntity(CarpoolEntity carpoolEntity) {
//		this.carpoolEntity = carpoolEntity;
//	}
//	
//	@OneToOne(mappedBy = "reservationEntity")  //PAYMENT와 일대일 관계 
//	private PaymentEntity paymentEntity;
}
