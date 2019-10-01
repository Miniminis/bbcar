package com.ycar.boot.passenger.dao;

import java.io.Serializable;
import java.util.List;

import com.ycar.boot.passenger.entity.PRouteEntity;
import com.ycar.boot.passenger.entity.PassengerEntity;

public interface PassengerDao extends Serializable {
	
	// 선호 탑승 환경 select
	public PassengerEntity findEnvByIdx(long idx);
	// 선호 루트 select
	public List<PRouteEntity> findRouteByIdx(int idx);

}
