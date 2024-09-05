package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface FoodMapper {
	/*	
	 * 	public class FoodVO {
			private int fno, hit, score, jjimcount, likecount;
			private String name, type, phone, address, theme, poster, content;
		}
	 * 
	 */
	// 목록 출력
	@Select("SELECT fno, poster, name, num "
			+ "FROM (SELECT fno, poster, name, rownum as num "
			+ "FROM (SELECT fno, poster, name "
			+ "FROM food_house "
			+ "ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(Map map);
	
	// 총 개수 카운트
	@Select("SELECT COUNT(*) FROM food_house")
	public int foodRowCount();
	
	// 조회수 증가
	@Update("UPDATE food SET "
			+ "hit=hit+1 "
			+ "WHERE fno=#{fno}")
	public void foodHitIncrement(int fno);
	
	// 상세 보기
	@Select("SELECT * "
			+ "FROM food_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	
}
