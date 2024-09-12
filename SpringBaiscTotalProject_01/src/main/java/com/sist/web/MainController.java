package com.sist.web;

import com.sist.dao.*;
import com.sist.vo.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@Autowired
	private EmpDAO eDao;
	
	@GetMapping("main/main.do")
	public String main_main(Model model) {
		// 1. 데이터베이스 연동
		List<EmpVO> list = eDao.empListData();
		// 2. 결과 값을 읽어서 => JSP로 전송
		model.addAttribute("list", list);
		return "main/main";
	}
	// 동적 쿼리 => forEach ==> In 연산자 수행 ===> IN('', '', '', ....)
	@GetMapping("name_find.do")
	public String main_find(Model model) {
		List<String> names=eDao.empEnameList();
		model.addAttribute("names", names);
		
		return "main/name_find";
	}
	
	@PostMapping("main/name_find_ok.do")
	public String main_find_ok(String[] names, Model model) {
		// DB연동
		Map map = new HashMap();
		map.put("names", names);
		// Data 전송
		List<EmpVO> list = eDao.empNameFindData(map);
		model.addAttribute("list", list);
		
		return "main/name_find_ok";
	}
	
	@RequestMapping("main/find.do")
	public String emp_find(String fd, String ss, Model model) {
		if (fd == null)
			fd = "all";
		// Connect DataBase
		Map map = new HashMap();
		map.put("fd", fd);
		if (ss != null) {
			map.put("ss", ss.toUpperCase());
		}
		System.out.println(ss + "이 문제 입니다.");
		
		List<EmpVO> list = eDao.empFindData(map);
		// Send Data
		model.addAttribute("list", list);
		
		return "main/find";
	}
	
}
