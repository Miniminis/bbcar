package com.ycar.server.reservation.service;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.ycar.server.reservation.dao.ReservationDao;
import com.ycar.server.reservation.domain.lonlat;

@Service("lonlatService")
public class LonLatService {

	@Inject
	private SqlSessionTemplate template;
	
	private ReservationDao dao;
	
	public lonlat selectlonlat(int r_idx) {
		
		dao = template.getMapper(ReservationDao.class);
		
		lonlat DR = dao.selectlonlat(r_idx);
		
		System.out.println("서비스 " + DR);
		
		
		return DR;
	}
}