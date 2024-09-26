package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
/*
	@Select("SELECT fno, name, poster, address, rownum "
			+ "FROM (SELECT fno, poster, address "
			+ "FROM project_food_house OREDE BY hit DESC) "
			+ "WHERE rownum<=5")
	public List<FoodVO> foodHitTop5();
 */
	public List<FoodVO> foodHitTop5() {
		return mapper.foodHitTop5();
	}
	
/*
	 		@Select("SELECT fno, name, poster, phone, type, num "
			+ "FROM (SELECT fno, name, poster, phone, type, rownum as num "
			+ "FROM (SELECT fno, name, poster, phone, type "
			+ "FROM project_food_house ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM project_food_house")
	public int foodTotalPage();
 */
	public List<FoodVO> foodListData(int start, int end) {
		return mapper.foodListData(start, end);
	}
	
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
}
