package com.ycha.par.driver.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycha.par.dao.ReviewDao;
import com.ycha.par.domain.Review;
import com.ycha.par.domain.ReviewInfo;

@Service("reviewService")
public class ReviewService {

	@Autowired
	private SqlSessionTemplate template;
	
	private PassengerReviewDao dao;
	
	//탑승자와 운전자 겹치는 코드가 많아서 1개의 서비스에서 분기처리함 
	public int insertReview(Review review) {
		System.out.println("리뷰 등록 03  "+review);
		
		//반환할 결과값 
		int rscnt = 0;
		
		dao = template.getMapper(PassengerReviewDao.class);
		
		//payidx, p_idx, d_idx 구하기
		ReviewInfo reviewInfo = dao.selectByRIdx(review.getR_idx());
		System.out.println("리뷰 등록 04  "+reviewInfo);
		
		//운전자의 리뷰등록 : 사용자의 idx 값은 부재, 운전자의 idx 값만 존재
		if(review.getP_idx()==0
						&& review.getD_idx()>0) {
			
			//결제 내역에 있는 d_idx 와 현재 review 를 작성한 d_idx 가 일치하는지 확인
			if(reviewInfo != null 
					&& reviewInfo.getD_idx() >0
					&& reviewInfo.getD_idx() == review.getD_idx()) {
				
				review.setPayidx(reviewInfo.getPayidx());
				review.setP_idx(reviewInfo.getP_idx());
				
			}
			System.out.println("리뷰 등록 05-1  "+review);
			
			//리뷰 등록하기 전에 만약 DB에 payidx로 조회된 review 존재 여부  체크 
			Review reviewRecord = dao.selectByPayidx(review.getPayidx());
			
			if(reviewRecord != null) { //1. 있다면, update 처리
				rscnt = dao.updateDriverReview(review);
			} else { //2. 없다면, insert 처리 
				rscnt = dao.insertReview(review);
			}
		
		//탑승자의 리뷰등록 : 운전자의 idx 값은 부재, 사용자의 idx 값만 존재
		} else if(review.getD_idx()==0
						&& review.getP_idx()>0) {
			
			
			//결제 내역에 있는 p_idx 와 현재 review 를 작성한 p_idx 가 일치하는지 확인
			if(reviewInfo != null 
					&& reviewInfo.getP_idx()>0
					&& reviewInfo.getP_idx() == review.getP_idx()) {
				
				review.setPayidx(reviewInfo.getPayidx());
				review.setD_idx(reviewInfo.getD_idx());
				
			}
			System.out.println("리뷰 등록 05-2  "+review);
			
			//리뷰 등록하기 전에 만약 DB에 payidx로 조회된 review 존재 여부  체크 
			Review reviewRecord = dao.selectByPayidx(review.getPayidx());
			
			if(reviewRecord != null) { //1. 있다면, update 처리
				rscnt = dao.updatePassengerReview(review);
			} else { //2. 없다면, insert 처리 
				rscnt = dao.insertReview(review);
			}
			
		} else { 
			//그 밖의 예외상황들 
			rscnt = 0;
		}
		
		System.out.println("리뷰 등록 06  "+rscnt);
		return rscnt;
	}

}
