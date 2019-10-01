package com.ycar.boot.par.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ycar.boot.par.entity.ReservationEntity;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

}
