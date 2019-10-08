package com.ycar.server.carpool.service;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycar.server.carpool.dao.DriverReservationDao;
import com.ycar.server.carpool.domain.DriverReservation;

@Service
public class InsertReservationService {

	@Autowired
	private SqlSessionTemplate template;
	private DriverReservationDao dao;
	
	public int InsertReservation(
			DriverReservation DRV){
		
		dao = template.getMapper(DriverReservationDao.class);
		
		int Cnt = 0;

		Cnt = dao.InsertReservation(DRV);
		
		System.out.println("서비스 들어옴" + Cnt);
		
		return Cnt;
	}
	
}
