package com.ycha.par.driver.dao;

import java.util.List;

import com.ycha.par.domain.Review;
import com.ycha.par.domain.ReviewEditDriver;
import com.ycha.par.domain.ReviewEditPassenger;
import com.ycha.par.domain.ReviewInfo;

public interface DriverReviewDao {

	public ReviewInfo selectByRIdx(int r_idx);

	public int insertReview(Review review);

	public Review selectByPayidx(int payidx);

	public int updateDriverReview(Review review);

	public int updatePassengerReview(Review review);

	public List<Review> selectListByDrivers(int p_idx);

	public Review selectByRvIdx(int rv_idx);

	public int deleteOnlyPassengerReview(int rv_idx);

	public int deleteReview(int rv_idx);

	public int editPassengerReview(ReviewEditPassenger reviewEdit);

	public List<Review> selectListByPassengers(int d_idx);

	public int deleteOnlyDriverReview(int rv_idx);

	public int editDriverReview(ReviewEditDriver reviewEdit);
	
}
