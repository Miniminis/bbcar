package com.ycha.par.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycha.par.dao.ReviewDao;
import com.ycha.par.domain.Review;

@Service("reviewListByPassengerService")
public class ReviewListByPassengerService {
	
	@Autowired
	private SqlSessionTemplate template;
	
	private ReviewDao dao;

	public List<Review> getReviewByPassengerList(int d_idx) {
		
		dao = template.getMapper(ReviewDao.class);
		
		List<Review> rvlist = dao.selectListByPassengers(d_idx);
		
		return rvlist;
	}

}
