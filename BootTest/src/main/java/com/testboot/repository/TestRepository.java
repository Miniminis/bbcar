package com.testboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.testboot.entity.ReservationEntity;

public interface TestRepository extends JpaRepository<ReservationEntity, Long> {
	
	@Query("select d from ReservationEntity d where d_idx= :d_idx and r_confirm = :confirm")
	public List<ReservationEntity> listAll(long d_idx, char confirm);
}
