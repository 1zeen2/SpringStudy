package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;

// 화면 출력에 필요한 데이터 전송 => JavaScript와 연결 (return value => JSON) 리턴 값은 대부분 JSON으로 넘어간다.
// 화면 제어는 할 수 없다.
@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	// 일반 자바스크립트 / HTML / 일반 문자열 => text/html
	// JSON => text/plain;
	@GetMapping(value = "food/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_list_vue(int page) throws Exception {
		int rowSize = 20;
		int start = (rowSize * page) - (rowSize - 1);
		int end = (rowSize * page);
		
		List<FoodVO> list = dao.foodListData(start, end);
		int totalpage = dao.foodTotalPage();
		
		final int BLOCK = 10;
		
		int startPage = (((page - 1) / BLOCK ) * BLOCK) + 1; // 1, 11, 21, ...
		int endPage = (((page - 1) / BLOCK) * BLOCK) + BLOCK; // 10, 20, 30, ...
		if (endPage > totalpage) {
			endPage = totalpage;
		}
		
		Map map = new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage",  totalpage);
		map.put("startPage",  startPage);
		map.put("endPage", endPage);
		
		// JSON 변경 => Boot에서는 자동 처리
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		return json;
	}
	
	@GetMapping(value = "food/find_vue.do", produces = "text/plain;charset=UTF-8")
	public String food_find(String address, int page) throws Exception {
		int rowSize = 20;
		int start = (rowSize * page) - (rowSize - 1);
		int end = (rowSize * page);
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("address", address);
		
		List<FoodVO> list = dao.foodFindListData(map);
		int totalpage = dao.foodFindTotalPage(map);
		
		final int BLOCK = 10;
		
		int startPage = (((page - 1) / BLOCK ) * BLOCK) + 1; // 1, 11, 21, ...
		int endPage = (((page - 1) / BLOCK) * BLOCK) + BLOCK; // 10, 20, 30, ...
		if (endPage > totalpage) {
			endPage = totalpage;
		}
		
		Map smap = new HashMap();
		smap.put("list", list);
		smap.put("curpage", page);
		smap.put("totalpage",  totalpage);
		smap.put("startPage",  startPage);
		smap.put("endPage", endPage);
		smap.put("address", address);
		
		// JSON 변경 => Boot에서는 자동 처리
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(smap);
		
		return json;
	}
}
