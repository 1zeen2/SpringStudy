package com.sist.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;

@RestController
public class RecipeRestController {
	@Autowired
	private RecipeDAO rDao;
	
	@GetMapping(value = "recipe/list_vue.do", produces = "text/plain; charset=UTF-8")
	public String recipe_list(int page) throws Exception {
		int rowSize = 12;
		   int start = (rowSize * page) - (rowSize - 1); // rownum = 1
		   int end=rowSize*page;
		   
		   Map map = new HashMap();
		   map.put("start", start);
		   map.put("end", end);
		   
		   List<RecipeVO> list = rDao.recipeListData(map);
		   int totalpage=rDao.recipeTotalPage();
		   
		   final int BLOCK=10;
		   int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
		   int endPage = ((page - 1) / BLOCK * BLOCK) + BLOCK;
		   
		   if (endPage > totalpage) {
			   endPage = totalpage;
		   }
		   
		   map = new HashMap();
		   map.put("list", list); // list:[]
		   map.put("curpage",page);
		   map.put("totalpage", totalpage);
		   map.put("startPage", startPage);
		   map.put("endPage",endPage);
		   
		   // 자바스크립트가 인식한는 언어로 변경 => JSON
		   ObjectMapper mapper = new ObjectMapper();
		   String json = mapper.writeValueAsString(map);
		   
		   return json;
	}
	
	@GetMapping(value = "recipe/detail_vue.do", produces = "text/plain;charset=UTF-8")
	   public String recipe_detail(int no) throws Exception  {
		   RecipeDetailVO vo = rDao.recipeDetailData(no);
		   ObjectMapper mapper = new ObjectMapper();
		   String json = mapper.writeValueAsString(vo);
		   return json;
	   }
}
