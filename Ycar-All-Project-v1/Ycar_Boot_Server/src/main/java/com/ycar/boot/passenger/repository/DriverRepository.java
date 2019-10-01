package com.ycar.boot.passenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ycar.boot.passenger.entity.DriverEntity;

public interface DriverRepository extends JpaRepository<DriverEntity, Integer> {

}
