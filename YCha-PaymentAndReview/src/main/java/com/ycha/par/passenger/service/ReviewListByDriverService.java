package com.ycha.par.passenger.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycha.par.dao.ReviewDao;
import com.ycha.par.domain.Review;

@Service("reviewListByDriverService")
public class ReviewListByDriverService {

	@Autowired
	private SqlSessionTemplate template;
	
	private PassengerReviewDao dao;
	
	public List<Review> getReviewByDriversList(int p_idx) {
		
		dao = template.getMapper(PassengerReviewDao.class);
		
		List<Review> rvlist = dao.selectListByDrivers(p_idx);
		
		return rvlist;
	}
	
	

}
