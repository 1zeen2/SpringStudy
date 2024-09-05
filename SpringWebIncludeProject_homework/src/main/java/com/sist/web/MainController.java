package com.sist.web;

import com.sist.service.*;
import com.sist.vo.*;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@Autowired
	private FoodService fService;

	@GetMapping("main/main.do")
	public String main_main(String page, Model model, HttpServletRequest request) {
		if (page == null)
			page = "1";
		int curpage = Integer.parseInt(page);
		
		Map map = new HashMap();
		map.put("start", (curpage * 20) - 19);
		map.put("end", curpage * 20);
		
		List<FoodVO> list = fService.foodListData(map);
		
		for (FoodVO vo : list) {
			String name = vo.getName();
			// 정규 표현식으로 대괄호 안의 내용 제거
		    name = name.replaceAll("\\[.*?\\]", "").trim();
		    
		    if (name.length() > 10) {
	            name = name.substring(0, 10) + "...";
	        }
		    
		    vo.setName(name);  // 제거된 결과를 다시 설정
		}
		
		int count = fService.foodRowCount();
		int totalpage = (int)(Math.ceil(curpage / 20.0));
		
		final int BLOCK = 10;
		int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;
		
		if (endPage > totalpage)
			endPage = totalpage;
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		model.addAttribute("main_jsp", "../main/home.jsp");
		
		return "main/main";
	}
	
	@GetMapping("food/detail.do")
	public String food_detail(int fno, Model model) {
		// 데이터 읽기
		FoodVO vo = fService.foodDetailData(fno);

		model.addAttribute("vo", vo);
		
//		List<String> fList = new ArrayList<String>();
//		model.addAttribute("flist", fList);
		
		// JSP로 전송하기 위해 Model 사용
		model.addAttribute("main_jsp", "../food/detail.jsp");
		
		return "main/main";
	}
}
