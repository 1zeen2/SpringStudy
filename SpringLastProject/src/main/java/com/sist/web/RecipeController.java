package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {
	// return은 반드시 파일명, .do => Router 방식
	@GetMapping("recipe/list.do")
	public String recipe_list() {
		
		return "recipe/list";
	}
	
	@GetMapping("recipe/detail.do")
	public String recipe_detail(int no, Model model) {
		model.addAttribute("no", no);
		
		return "recipe/detail";
	}
}
