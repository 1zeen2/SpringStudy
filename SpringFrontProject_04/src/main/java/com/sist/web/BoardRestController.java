package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;

// 화면 제어는 불가능하다. => JavaScript 또는 다른 언어로 Data 전송.
//											--------- Kotlin, Flutter, Dart, ...
// 								AJAX, Vue.js, React, Next
//								서버를 나눠서 처리 => MSA(Spring Cloud)
// JSON, 일반 문자열, 숫자, ...
@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping(value = "board/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String board_list(int page) throws Exception {
		int rowSize = 10;
		int start = (rowSize * page) - (rowSize - 1);
		int end = rowSize * page;
		
		List<BoardVO> list = dao.boardListData(start, end);
		int count = dao.boardTotalPage();
		
		int totalpage = (int)(Math.ceil(count / 10.0));
		count = count - ((rowSize * page) - rowSize);
		
		// 화면 출력을 위해 데이터를 전송. (JavaScript가 받아야 하기 때문에 => JSON)
		Map map = new HashMap();
		map.put("list", list);
		map.put("curpage", page); 
		map.put("totalpage", totalpage);
		map.put("count", count);
		map.put("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		return json;
	}
	
	@PostMapping(value = "board/insert_vue.do", produces = "text/plain; charset=UTF-8")
	public String board_insert(BoardVO vo) {
		String result="yes";
		try {
			result = "yes";
			dao.boardInsert(vo);
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "no";
		}
		return result;
	}
	
	@GetMapping(value = "board/detail_vue.do", produces = "text/plain; charset=UTF-8")
	public String board_detail(int no) throws Exception {
		// JSON이 넘어가야 함.
		BoardVO vo = dao.boardDetailData(no);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(vo);
		
		return json;
	}
	
	@GetMapping(value = "board/belete_vue.do", produces = "text/plain; charset=UTF-8")
	public String board_delete(int no, String pwd ) {
		String result = dao.boardDelete(no, pwd);
		return result;
	}
	
	@GetMapping(value = "board/update_vue.do", produces = "text/plain; charset=UTF-8") 
		public String board_update(int no) throws Exception {
			BoardVO vo = dao.boardUpdateData(no);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(vo);
			
			return json;
		}
	
	@PostMapping(value = "board/update_ok_vue.do", produces = "text/plain; charset=UTF-8")
	public String board_update_ok(BoardVO vo) {
		String result = dao.boardUpdate(vo);
		return result;
	}
}
