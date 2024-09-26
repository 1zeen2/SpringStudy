package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class ChefDAO {
	@Autowired
	private ChefMapper mapper;
	
/*
	@Select("SELECT chef, poster "
		+ "FROM chef "
		+ "WHERE chef=(SELECT chef FROM recipe WHERE hit=(SELECT MAX(hit) FROM recipe))")
	public ChefVO chefToday();
 */
	public ChefVO chefToday() {
		return mapper.chefToday();
	}
}
