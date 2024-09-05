package com.sist.service;

import com.sist.vo.*;

import java.util.*;

public interface FoodService {
	public List<FoodVO> foodListData(Map map);
	public int foodRowCount();
	public void foodHitIncrement(int fno);
	public FoodVO foodDetailData(int fno);
}
