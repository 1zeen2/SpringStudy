package com.sist.service;

import java.util.*;

import com.sist.vo.*;

// Recipe와 관련된 기능을 모아서 관리 => 결합성(의존성)이 낮은 프로그램 제작 방식.
// 수정 시 다른 클래스에 영향을 주지 않는다.
// 개발자가 독립적인 개발, 유지보수가 가능하게 제작.
public interface RecipeService {
	// Recipe
	public RecipeVO recipeMaxHitData();
	public List<RecipeVO> recipeHitTop8();
	
	// Chef
	public ChefVO chefToday();
}
