package com.ycar.server.par.passenger.dao;

import java.util.List;

import com.ycar.server.par.domain.Review;
import com.ycar.server.par.domain.ReviewEditPassenger;
import com.ycar.server.par.domain.ReviewListInfo;

public interface PassengerReviewDao {

	public int selectD_idx(int payidx);
	
	public int insertReview(Review review);

	public Review selectReviewRecordByPayidx(int payidx, String writer);
	
	public List<ReviewListInfo> selectListByPassenger(int p_idx);
	
	public Review selectByRvIdx(int rv_idx);
	
	public int editPassengerReview(ReviewEditPassenger reviewEdit);
	
	public int deletePassengerReview(int rv_idx);

}
