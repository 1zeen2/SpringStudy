package com.sist.service;

import com.sist.dao.*;
import com.sist.vo.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService {
	@Autowired
	private FoodDAO dao;
	
	public List<FoodVO> foodListData(Map map) {
		
		return dao.foodListData(map);
	}
	
	public int foodRowCount() {
		return dao.foodRowCount();
	}

	public void foodHitIncrement(int fno) {
		dao.foodHitIncrement(fno);
	}
	
	public FoodVO foodDetailData(int fno) {
		return dao.foodDetailData(fno);
	}
}
