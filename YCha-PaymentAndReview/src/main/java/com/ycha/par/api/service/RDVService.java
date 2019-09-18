package com.ycha.par.api.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycha.par.dao.PaymentDao;
import com.ycha.par.domain.RDV;

@Service("rDVService")
public class RDVService {
	
	@Autowired
	SqlSessionTemplate template;

	private PassengerPaymentDao paymentDao;
	
	public RDV getRDVInfo(int p_idx) {
		
		System.out.println("RDV 받아오기03"+p_idx);
		
		paymentDao = template.getMapper(PassengerPaymentDao.class);
		
		//p_idx 통해 RDV 전체 정보 조회 
		RDV rdv = paymentDao.selectRDVByPidx(p_idx);
		System.out.println("RDV 받아오기04"+rdv.toString());
		
		return rdv;
		
	}

}
