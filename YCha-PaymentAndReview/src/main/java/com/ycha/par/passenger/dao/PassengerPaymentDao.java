package com.ycha.par.passenger.dao;

import com.ycha.par.domain.ReservationBasicInfo;

public interface PassengerPaymentDao {

	public ReservationBasicInfo selectReservationBasicInfo(int p_idx);

	public int insertPayRecord(int r_idx, String paymethod);

	//public Payment selectOneByRIdx(int r_idx);

	//public List<Payment> selectListPassenger(int p_idx);

	//public List<Payment> selectListDriver(int d_idx);

}
