package com.ycha.par.dao;

import java.util.List;

import com.ycha.par.domain.Review;
import com.ycha.par.domain.ReviewInfo;

public interface ReviewDao {

	public ReviewInfo selectByRIdx(int r_idx);

	public int insertReview(Review review);

	public Review selectByPayidx(int payidx);

	public int updateDriverReview(Review review);

	public int updatePassengerReview(Review review);

	public List<Review> selectListByDrivers(int p_idx);

	public Review selectByRvIdx(int rv_idx);

	public int deleteOnlyPassengerReview(int rv_idx);

	public int deleteReview(int rv_idx);
	
}
