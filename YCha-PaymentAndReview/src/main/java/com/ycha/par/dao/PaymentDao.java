package com.ycha.par.dao;


import com.ycha.par.domain.Payment;
import com.ycha.par.domain.RDV;

public interface PaymentDao {

	public RDV selectRDVByPidx(int p_idx); 
	public int insertPayRecord(Payment payment);
	public Payment selectOneByRIdx(int r_idx);

}
