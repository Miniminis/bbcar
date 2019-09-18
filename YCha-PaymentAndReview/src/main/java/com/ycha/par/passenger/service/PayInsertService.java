package com.ycha.par.passenger.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycha.par.domain.Payment;
import com.ycha.par.passenger.dao.PassengerPaymentDao;

@Service("payInsertService")
public class PayInsertService {

	@Autowired
	private SqlSessionTemplate template;
	
	private PassengerPaymentDao dao;
	
	//결제 내역 DB에 등록 
	public int insert(int r_idx, String paymethod) {
		
		System.out.println("결제내역03 "+ paymethod);
		
		dao = template.getMapper(PassengerPaymentDao.class);
		
		int rscnt = dao.insertPayRecord(r_idx, paymethod);
		
		System.out.println("결제내역04 "+ rscnt);
		
		return rscnt;
	}
	
	

}
