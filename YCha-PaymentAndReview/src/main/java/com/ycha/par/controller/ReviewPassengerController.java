package com.ycha.par.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ycha.par.domain.Review;
import com.ycha.par.service.DeleteReviewByPassengerService;
import com.ycha.par.service.ReviewListByDriverService;
import com.ycha.par.service.ReviewService;

@RestController
@RequestMapping("/review/passenger")
public class ReviewPassengerController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ReviewListByDriverService reviewListByDriverService;
	
	@Autowired
	private DeleteReviewByPassengerService deleteReviewByPassenger;
	
	@PostMapping()
	@CrossOrigin
	public int writeReview(@RequestBody Review review) {
		
		System.out.println("탑승자 리뷰 등록 03  "+review);
		
		return reviewService.insertReview(review);
		
	}
	
	@GetMapping("/{idx}")
	@CrossOrigin
	public List<Review> getReviewListByDrivers(@PathVariable("idx") int p_idx) {
		
		return reviewListByDriverService.getReviewByDriversList(p_idx);
	}
	
	@DeleteMapping("/{idx}/p_idx/{pidx}")
	@CrossOrigin
	public int deleteReview(@PathVariable("idx") int rv_idx,
							@PathVariable("pidx") int p_idx) {
		
		System.out.println("후기 삭제 01  "+rv_idx);
		System.out.println("후기 삭제 02  "+p_idx);
		
		return deleteReviewByPassenger.deleteReview(rv_idx, p_idx);
		
	}

}