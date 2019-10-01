package com.ycar.server.par.passenger.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycar.server.par.domain.Review;
import com.ycar.server.par.domain.ReviewListInfo;
import com.ycar.server.par.passenger.dao.PassengerReviewDao;

@Service("reviewListByPassenger")
public class ReviewListByPassenger {

	@Autowired
	private SqlSessionTemplate template;
	
	private PassengerReviewDao dao;
	
	public List<ReviewListInfo> getReviewByPassenger(int p_idx) {
		
		dao = template.getMapper(PassengerReviewDao.class);
		
		List<ReviewListInfo> rvlist = dao.selectListByPassenger(p_idx);
		
		return rvlist;
	}
	
	

}
