package com.ycha.par.dao;

import java.util.List;
import java.util.Map;

public interface ReviewDao {

	public int selectPayIdx(int r_idx);

	public List<Map<String, Object>> selectRDVByRIdx(int r_idx);

}
