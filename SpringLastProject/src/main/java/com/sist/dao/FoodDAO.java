package com.sist.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class FoodDAO {
  @Autowired
  private FoodMapper mapper;

  public List<FoodVO> foodHitTop5() {
	  return mapper.foodHitTop5();
  }

  public List<FoodVO> foodListData(int start,int end) {
	  return mapper.foodListData(start, end);
  }
  
  public int foodTotalPage() {
	  return mapper.foodTotalPage();
  }

  public FoodVO foodDetailData(int fno) {
	  mapper.hitIncrement(fno);
	  
	  return mapper.foodDetailData(fno);
  }
  
  public FoodVO foodInfoData(int fno) {
	  return mapper.foodDetailData(fno);
  }

  public List<FoodVO> foodFindListData(Map map) {
	  
	  return mapper.foodFindListData(map);
  }
  
  public int foodFindTotalPage(Map map) {
	  
	  return mapper.foodFindTotalPage(map);
  }
//  
//  @Select("SELECT fno, name, poster "
//	  		+ "FROM project_food_house "
//	  		+ "WHERE type LIKE '%'||#{type}||'%'")
	  public List<FoodVO> foodTypeListData(String type) {
		  return mapper.foodTypeData(type);
	  }
}