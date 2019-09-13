package com.ycha.par.service;

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
	
	private ReviewDao dao;
	
	public int insertReview(Review review) {
		
		System.out.println("리뷰 등록 04  "+review);
		
		dao = template.getMapper(ReviewDao.class);
		
		/*2. form 제출 --> DB에 insert 
		
		//from client 
		private int p_idx; //from client
		private String pr_content; //from client
		private String p_nickname; //from client
		private int pr_star; //from client
			private int r_idx; //from client

		//null 로 insert  
		private int rv_idx; - auto increment 
		
		//삽입안함
		private String dr_content;
		private int dr_star;
		
		//서버단에서 구해야하는 값 
		private int payidx; 
		private int d_idx; 
		
		*/
		
		ReviewInfo reviewInfo = dao.selectByRIdx(review.getR_idx());
		System.out.println("리뷰 등록 05  "+reviewInfo);
		
		review.setPayidx(reviewInfo.getPayidx());
		review.setD_idx(reviewInfo.getD_idx());
		
		int rscnt = dao.insertReview(review);
		System.out.println("리뷰 등록 06  "+rscnt);
		
		return rscnt;
	}

}
