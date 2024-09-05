package com.sist.dao;

import com.sist.mapper.*;
import com.sist.vo.*;

import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
//	// 목록 출력
//	@Select("SELECT fno, poster, name, num "
//			+ "FROM (SELECT fno, poster, name, rownum as num "
//			+ "FROM (SELECT fno, poster, name, num "
//			+ "FROM food_house)) "
//			+ "WHERE num BETWEEN #{start} AND #{end}")
//	public List<FoodVO> foodListData(Map map);
	public List<FoodVO> foodListData(Map map) {
		
		return mapper.foodListData(map);
	}
	
//	// 총 개수 카운트
//	@Select("SELECT COUNT(*) FROM food_house")
//	public int foodRowCount();
	public int foodRowCount() {
		return mapper.foodRowCount();
	}
	
//	 조회수 증가
//	@Update("UPDATE food SET "
//			+ "hit=hit+1 "
//			+ "WHERE fno=#{fno}")
//	public void foodHitIncrement(int fno);
	public void foodHitIncrement(int fno) {
		mapper.foodHitIncrement(fno);
	}
//	 상세 보기
//	@Select("SELECT * "
//			+ "FROM food_house "
//			+ "WHERE fno=#{fno}")
//	public int foodDetailData(int fno);
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
}
