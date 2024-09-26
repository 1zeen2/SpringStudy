package com.sist.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;

@RestController
public class FoodRestController {
	@Autowired
	private FoodService fService;
	
	@GetMapping(value = "food/list_vue.do",produces = "text/plain; charset=UTF-8")
	public String food_list(int page) throws Exception {
		int rowSize = 12;
		int start = (rowSize * page) - (rowSize - 1);
		int end = rowSize * page;
		
		List<FoodVO> list = fService.foodListData(start, end);
		int totalPage = fService.foodTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((page - 1 )/ BLOCK * BLOCK) + 1;
		int endPage = ((page - 1) / BLOCK * BLOCK) + BLOCK;
		
		if(endPage > totalPage)
			endPage = totalPage;
		
		if (endPage > totalPage)
			endPage = totalPage;
		
		Map map = new HashMap(); 
		map.put("list", list);
		map.put("curpage", map);
		map.put(totalPage, map);
		map.put(startPage, map);
		map.put(endPage, map);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		return json;
				
		
	}
}
