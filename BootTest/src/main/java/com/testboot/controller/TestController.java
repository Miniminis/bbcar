package com.testboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.testboot.entity.ReservationEntity;
import com.testboot.repository.TestRepository;

@RestController
public class TestController {
	
	@Autowired
	private TestRepository repository;
	
	@GetMapping()
	public List<ReservationEntity> getlist() {
		List<ReservationEntity> list = repository.listAll(2, 'B');
		
		for(ReservationEntity r : list) {
			System.out.println("list 출력 "+r);
		}
		
		return list;
	}
}
