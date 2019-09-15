package com.ycha.par.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycha.par.dao.PaymentDao;
import com.ycha.par.domain.Payment;

@Service("getDepositListService")
public class GetDepositListService {

	@Autowired
	private SqlSessionTemplate template;

	private PaymentDao dao;

	public List<Payment> getDepListDriver(int d_idx) {
		System.out.println("입금리스트 03  " + d_idx);

		dao = template.getMapper(PaymentDao.class);

		List<Payment> list = dao.selectListDriver(d_idx);
		System.out.println("입금리스트  04  " + list);

		return list;
	}

}
