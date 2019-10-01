package com.ycar.server.carpool.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycar.server.carpool.dao.DriverReservationDao;

@Service("deleteService")
public class deleteService {

	@Autowired
	private SqlSessionTemplate template;
	private DriverReservationDao dao;
	
	public int deleteCarpool(int dr_idx) {
		
		dao = template.getMapper(DriverReservationDao.class);
		
		System.out.println("삭제) dr_idx  : " + dr_idx);
		
		int cnt = 0;
		cnt = dao.deleteCarpool(dr_idx);
		
		return cnt;
	}
	
}
