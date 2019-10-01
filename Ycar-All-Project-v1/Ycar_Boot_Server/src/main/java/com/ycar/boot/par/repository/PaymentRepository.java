package com.ycar.boot.par.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ycar.boot.par.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

	//findAll() : list 
	//saveAndFlush(entity) : insert 
	//saveAndFlush(entity) : edit
	//delete(entity) : delete
	
	@Query("select p from PaymentEntity p where p.r_idx= :r_idx")
	public PaymentEntity findByIdx(Integer r_idx);
	
}
