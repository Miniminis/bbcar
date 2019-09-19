package com.ycha.par.passenger.dao;

import java.util.List;

import com.ycha.par.domain.PaymentDetail;
import com.ycha.par.domain.ReservationBasicInfo;

public interface PassengerPaymentDao {

	public ReservationBasicInfo selectReservationBasicInfo(int p_idx);

	public int insertPayRecord(int r_idx, String paymethod);

	public PaymentDetail selectOneByRIdx(int r_idx);

	public List<PaymentDetail> selectListPassenger(int p_idx);

	//public List<Payment> selectListDriver(int d_idx);

}
