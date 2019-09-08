package com.ycha.par.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycha.par.dao.PaymentDao;
import com.ycha.par.domain.RDV;

@Service("rDVService")
public class RDVService {
	
	@Autowired
	SqlSessionTemplate template;

	private PaymentDao paymentDao;
	
	public RDV getRDVInfo(int p_idx) {
		
		System.out.println("RDV 받아오기03"+p_idx);
		
		paymentDao = template.getMapper(PaymentDao.class);
		
		//탑승자번호 통해 탑승자 예약번호 조회 
		int pr_idx = paymentDao.selectPridxByPidx(p_idx);
		
		System.out.println("RDV 받아오기04"+pr_idx);
		
		//탑승자 예약번호 통해 RDV 전체 정보 조회 
		RDV rdv = paymentDao.selectRDVByPridx(pr_idx);
		System.out.println("RDV 받아오기05"+rdv.toString());
		
		return rdv;
		
	}

}
