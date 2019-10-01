package com.ycar.server.par.driver.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycar.server.par.domain.PaymentDetail;
import com.ycar.server.par.driver.dao.DriverPaymentDao;

@Service("getDepositListService")
public class GetDepositListService {

	@Autowired
	private SqlSessionTemplate template;

	private DriverPaymentDao dao;

	public List<PaymentDetail> getDepListDriver(int d_idx) {
		System.out.println("입금리스트 03  " + d_idx);

		dao = template.getMapper(DriverPaymentDao.class);

		List<PaymentDetail> list = dao.selectListDriver(d_idx);
		System.out.println("입금리스트  04  " + list);

		return list;
	}

}
