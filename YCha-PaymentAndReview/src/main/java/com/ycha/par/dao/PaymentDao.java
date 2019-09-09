package com.ycha.par.dao;

import com.ycha.par.domain.Payment;
import com.ycha.par.domain.RDV;

public interface PaymentDao {

	public int selectPridxByPidx(int p_idx); 
	public RDV selectRDVByPridx(int pr_idx);
	public int insertPayRecord(Payment payment);

}
