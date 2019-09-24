package com.parboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parboot.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

	//findAll() : list 
	//saveAndFlush(entity) : insert 
	//saveAndFlush(entity) : edit
	//delete(entity) : delete
	
}
