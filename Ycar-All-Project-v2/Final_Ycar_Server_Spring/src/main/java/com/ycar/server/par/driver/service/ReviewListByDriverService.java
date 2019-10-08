package com.ycar.server.par.driver.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycar.server.par.domain.Review;
import com.ycar.server.par.driver.dao.DriverReviewDao;

@Service("reviewListByPassengerService")
public class ReviewListByDriverService {
	
	@Autowired
	private SqlSessionTemplate template;
	
	private DriverReviewDao dao;

	public List<Review> getReviewByPassengerList(int d_idx) {
		
		dao = template.getMapper(DriverReviewDao.class);
		
		List<Review> rvlist = dao.selectListByDrivers(d_idx);
		
		return rvlist;
	}

}
