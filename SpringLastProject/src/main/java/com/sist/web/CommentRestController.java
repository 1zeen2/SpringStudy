package com.sist.web;

import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;

import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentRestController {
	@Autowired
	private CommentService cService;
	
	public String commonsListData(int rno, int page, int type) throws Exception {
		Map map = new HashMap();
		int rowSize = 10;
		int start = (rowSize * page) - (rowSize - 1);
		int end = rowSize * page;
		
		map.put("start", start);
		map.put("end", end);
		map.put("rno", rno);
		map.put("type", type);
		
		List<CommentVO> list = cService.commentListData(map);
		int totalpage = cService.commentTotalPage(map);
		
		final int BLOCK = 5;
		
		int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((page -1) / BLOCK * BLOCK) + BLOCK;
		
		if (endPage > totalpage) {
			endPage = totalpage;
		}
		
		map = new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		return json;
	}
	
	@GetMapping(value = "comment/list_vue.do", produces = "text/plain; charset=UTF-8")
	public String comment_list(int rno, int page, int type) throws Exception {
		
		return commonsListData(rno, page, type);
	}
	
	@PostMapping(value = "comment/insert_vue.do", produces = "text/plain; charset=UTF-8")
	public String comment_insert(CommentVO vo, HttpSession session) throws Exception {
		// @Insert("INSERT INTO spring_comment(cno, rno, id, name, sex, msg, group_id, type)")
		// id, name을 session으로 저장해서 값을 가지고 와야 함.
		String id = (String)session.getAttribute("userId");
		String name = (String)session.getAttribute("userName");
		String sex = (String)session.getAttribute("sex");
		vo.setId(id);
		vo.setName(name);
		vo.setSex(sex);
		
		cService.commentInsert(vo);
		
		return commonsListData(1, vo.getRno(), vo.getType());
	}
	
	@PostMapping(value = "comment/reply_insert_vue.do", produces = "text/plain; charset=UTF-8")
	public String comment_replyInsert(int cno, CommentVO vo, HttpSession session) throws Exception {
		String id = (String)session.getAttribute("userId");
		String name = (String)session.getAttribute("userName");
		String sex = (String)session.getAttribute("sex");
		vo.setId(id);
		vo.setName(name);
		vo.setSex(sex);
		
		cService.commentReplyReplyInsert(vo, cno);
		
		return commonsListData(1, vo.getRno(), vo.getType());
	}
	
	@GetMapping(value = "comment/delete_vue.do", produces = "text/plain; charset=UTF-8")
	public String comment_delete(int cno, int rno, int type) throws Exception {
		// DB Connect
		
		return commonsListData(1, rno, type);
	}
}
