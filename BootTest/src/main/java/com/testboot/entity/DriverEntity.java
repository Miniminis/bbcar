package com.testboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DRIVER")
public class DriverEntity {
	
	@Id
	@Column
	private long d_idx;

	public long getD_idx() {
		return d_idx;
	}

	public void setD_idx(long d_idx) {
		this.d_idx = d_idx;
	}

	@Override
	public String toString() {
		return "DriverEntity [d_idx=" + d_idx + "]";
	}


}
