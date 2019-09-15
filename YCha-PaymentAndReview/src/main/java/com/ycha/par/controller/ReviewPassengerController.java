package com.ycha.par.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ycha.par.domain.Review;
import com.ycha.par.service.ReviewService;

@RestController
@RequestMapping("/review/passenger")
public class ReviewPassengerController {
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping()
	@CrossOrigin
	public int writeReview(@RequestBody Review review) {
		
		System.out.println("탑승자 리뷰 등록 03  "+review);
		
		return reviewService.insertReview(review);
		
	}

}
