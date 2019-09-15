package com.ycha.par.dao;

import com.ycha.par.domain.Review;
import com.ycha.par.domain.ReviewInfo;

public interface ReviewDao {

	public ReviewInfo selectByRIdx(int r_idx);

	public int insertReview(Review review);

	public Review selectByPayidx(int payidx);

	public int updateDriverReview(Review review);

	public int updatePassengerReview(Review review);
	
}
