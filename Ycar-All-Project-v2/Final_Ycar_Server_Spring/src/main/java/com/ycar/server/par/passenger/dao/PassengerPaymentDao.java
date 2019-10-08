package com.ycar.server.par.passenger.dao;

import java.util.List;

import com.ycar.server.par.domain.Payment;
import com.ycar.server.par.domain.PaymentDetail;
import com.ycar.server.par.domain.ReservationBasicInfo;

public interface PassengerPaymentDao {
	
	public Payment selectPaymentByR_idx(int r_idx);

	public ReservationBasicInfo selectReservationBasicInfo(int r_idx);

	public int insertPayRecord(int r_idx, String paymethod);

	public PaymentDetail selectOneByRIdx(int r_idx);

	public List<PaymentDetail> selectListPassenger(int p_idx);
}
