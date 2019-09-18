package com.ycha.par.passenger.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycha.par.dao.PaymentDao;
import com.ycha.par.domain.Payment;

@Service("getPayListService")
public class GetPayListService {
	
	@Autowired
	private SqlSessionTemplate template;

	private PassengerPaymentDao dao;

	public List<Payment> getListPassenger(int p_idx) {
		System.out.println("결제 리스트 03  "+p_idx);
		
		dao = template.getMapper(PassengerPaymentDao.class);
		
		List<Payment> list = dao.selectListPassenger(p_idx);
		System.out.println("결제 리스트 04  "+list);
		
		return list;
	}
	
	
}
