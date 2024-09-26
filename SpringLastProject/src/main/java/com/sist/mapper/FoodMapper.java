package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface FoodMapper {
	@Select("SELECT fno, name, poster, address, rownum "
			+ "FROM (SELECT fno, name, poster, address "
			+ "FROM project_food_house ORDER BY hit DESC) "
			+ "WHERE rownum<=5")
	public List<FoodVO> foodHitTop5();
	
	// 목록	***
	@Select("SELECT fno, name, poster, phone, type, num "
			+ "FROM (SELECT fno, name, poster, phone, type, rownum as num "
			+ "FROM (SELECT fno, name, poster, phone, type "
			+ "FROM project_food_house ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start, @Param("end") int end);
	
	// 총 페이지
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM project_food_house")
	public int foodTotalPage();
	
	// 상세 보기	=> Cookie
	
	// 예약	***
	
	// 추천 		=> 네이버 카페
}
