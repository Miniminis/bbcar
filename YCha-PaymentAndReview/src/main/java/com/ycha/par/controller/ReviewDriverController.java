package com.ycha.par.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycha.par.domain.Review;
import com.ycha.par.domain.ReviewEditDriver;
import com.ycha.par.domain.ReviewEditPassenger;
import com.ycha.par.service.DeleteReviewByDriverService;
import com.ycha.par.service.DeleteReviewByPassengerService;
import com.ycha.par.service.EditReviewByDriverService;
import com.ycha.par.service.EditReviewByPassengerService;
import com.ycha.par.service.ReviewListByDriverService;
import com.ycha.par.service.ReviewListByPassengerService;
import com.ycha.par.service.ReviewService;

@RestController
@RequestMapping("/review/driver")
public class ReviewDriverController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ReviewListByPassengerService reviewListByPassengerService;
	
	@Autowired
	private DeleteReviewByDriverService deleteReviewByDriverService;
	
	@Autowired
	private EditReviewByDriverService editReviewByDriverService;
	
	
	//리뷰 등록
	@PostMapping()
	@CrossOrigin
	public int writeReview(@RequestBody Review review) {
		
		System.out.println("운전자 리뷰 등록 02  "+review);
		
		return reviewService.insertReview(review);
		
	}
	
	//리뷰 리스트
	@GetMapping("/{idx}")
	@CrossOrigin
	public List<Review> getReviewListByPassengers(
							@PathVariable("idx") int d_idx) {
		
		return reviewListByPassengerService.getReviewByPassengerList(d_idx);
	}
	
	//리뷰 삭제
	@DeleteMapping("/{idx}/d_idx/{didx}")
	@CrossOrigin
	public int deleteReview(@PathVariable("idx") int rv_idx,
							@PathVariable("didx") int d_idx) {
		
		System.out.println("후기 삭제 01  "+rv_idx);
		System.out.println("후기 삭제 02  "+d_idx);
		
		return deleteReviewByDriverService.deleteReview(rv_idx, d_idx);
		
	}
	
	//리뷰 수정
	@PutMapping("/{idx}")
	@CrossOrigin
	public int editReview(@RequestBody ReviewEditDriver reviewEdit) {
		
		System.out.println("리뷰 수정 02"+reviewEdit);
		
		return editReviewByDriverService.editReview(reviewEdit);
		
	}

}
