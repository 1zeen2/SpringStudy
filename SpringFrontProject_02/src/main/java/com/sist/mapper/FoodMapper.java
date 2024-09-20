package com.sist.mapper;

import com.sist.vo.*;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FoodMapper {
	@Select("SELECT fno, name, poster, num "
			+ "FROM (SELECT fno, name, poster, rownum as num "
			+ "FROM (SELECT fno, name, poster "
			+ "FROM project_food_house ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	// 여러 개의 매개 변수 => @Param , Map, VO
	public List<FoodVO> foodListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) "
			+ "FROM project_food_house")
	public int foodTotalPage();
	
	@Select("SELECT fno, name, poster, address, num "
			+ "FROM (SELECT fno, name, poster, address, rownum as num "
			+ "FROM (SELECT fno, name, poster, address "
			+ "FROM project_food_house "
			+ "WHERE address LIKE '%'||#{address}||'%' ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	// 여러 개의 매개 변수 => @Param , Map, VO
	public List<FoodVO> foodFindListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) "
			+ "FROM project_food_house "
			+ "WHERE address LIKE '%'||#{address}||'%'")
	public int foodFindTotalPage(Map map);
}
