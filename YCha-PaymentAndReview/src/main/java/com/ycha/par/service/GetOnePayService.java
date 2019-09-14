package com.ycha.par.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycha.par.dao.PaymentDao;
import com.ycha.par.domain.Payment;

@Service("getOnePayService")
public class GetOnePayService {
	
	@Autowired
	private SqlSessionTemplate template;
	
	private PaymentDao dao;

	
	public Payment getPayDetail(int r_idx) {
		
		System.out.println("입금내역 03  "+r_idx);
		
		dao = template.getMapper(PaymentDao.class);
		
		Payment payment = dao.selectOneByRIdx(r_idx);
		
		System.out.println("입금내역 04  "+payment.toString());

		return payment;
	}
	
}
