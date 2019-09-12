package com.ycha.par.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycha.par.dao.ReviewDao;
import com.ycha.par.domain.Review;

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
		private String dr_content;
		private int dr_star;
		
		//서버단에서 구해야하는 값 
		private int payidx; 
		private int d_idx; 
		
		*/
		
		int payidx = dao.selectPayIdx(review.getR_idx());
		System.out.println("리뷰 등록 05  "+ payidx);
		
		List<Map<String, Object>> map = dao.selectRDVByRIdx(review.getR_idx());
		System.out.println("리뷰 등록 03  "+map.get(0));
		
		return 0;
	}

}
