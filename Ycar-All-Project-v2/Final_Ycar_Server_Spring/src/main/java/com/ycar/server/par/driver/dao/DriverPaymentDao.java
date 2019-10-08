package com.ycar.server.par.driver.dao;


import java.util.List;

import com.ycar.server.par.domain.PaymentDetail;

public interface DriverPaymentDao {

	public List<PaymentDetail> selectListDriver(int d_idx);

}
