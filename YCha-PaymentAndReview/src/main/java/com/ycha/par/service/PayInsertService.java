package com.ycha.par.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycha.par.dao.PaymentDao;
import com.ycha.par.domain.Payment;

@Service("payInsertService")
public class PayInsertService {

	@Autowired
	private SqlSessionTemplate template;
	
	private PaymentDao dao;
	
	//결제 내역 DB에 등록 
	public int insert(Payment payment) {
		
		System.out.println("결제내역03 "+ payment);
		
		dao = template.getMapper(PaymentDao.class);
		
		int rscnt = dao.insertPayRecord(payment);
		
		System.out.println("결제내역04 "+ rscnt);
		
		return rscnt;
	}
	
	

}
