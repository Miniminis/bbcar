package com.ycha.par.driver.dao;


import java.util.List;

import com.ycha.par.domain.Payment;
import com.ycha.par.domain.RDV;

public interface DriverPaymentDao {

	public RDV selectRDVByPidx(int p_idx); 
	public int insertPayRecord(Payment payment);
	public Payment selectOneByRIdx(int r_idx);
	public List<Payment> selectListPassenger(int p_idx);
	public List<Payment> selectListDriver(int d_idx);

}
