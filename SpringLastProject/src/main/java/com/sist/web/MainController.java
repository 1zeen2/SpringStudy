package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;

import com.sist.service.*;
import com.sist.vo.*;
/*
 * 	정리
 * 		스프링
 * 			= DI => Spring은 클래스를 관리하는 영역
 * 			  == 			=============
 * 			  |					1) 클래스 한 개 : Component
 * 			  |					2) 클래스 여러 개 : Container
 * 			  |						Spring은 컨테이너다.(클래스로 제작됨 : 경량 컨테이너)
 * 			  |
 * 			  |=> 클래스를 관리하기 위해서는 객체 생성 / 객체 소멸 (Object LifeCycle)
 * 			  |=> 객체 생성 시에 멤버 변수의 초기화가 필요 시에 사용한다.			
 * 									=============== setterDI 	  => p:name ==> setName()
 * 									=============== ConstructorDI => 
 * 			= AOP(Aspect-Oriented Programming : 관점 지향 프로그래밍) : 공통 모듈 : 실행 시 마다 호출하는 기능이 있는 경우 자동 호출이 가능하다.
 * 						=> 사용자 정의 보다는 Transaction / Security / Log
 * 			
 * 			= MVC : Web => Library 
 * 						   ======= 있는 그대로 사용. => 사용법 / 특정 기능 / 찾기
 * 									(ORM(Object-Relational Mapping) => MyBatis)
 * 									==== 객체 지향 프로그래밍(OOP)의 객체와 관계형 데이터베이스(RDB)의 테이블을 자동으로 매핑해주는 기술
 * 		=======================================================================================================================================
 * 		Framework : 기본 동작을 위한 틀이 만들어져있다.
 * 					=> 형식에 맞게 세팅해서 사용
 * 						==== xml / Annotaion
 * 						1) Library 추가 : pom.xml / gradle
 * 		========================================================================================================================================
 * 		MVC
 * 			model :  => @Controller / @RestController
 * 						=> 사용자의 요청을 받아 처리 결과를 JSP로 전송하는 역할
 * 														  === @Controller
 * 														  === JavaScript => @RestController
 * 
 * 			=> 관리 : HandlerMapping ==> 해당 Method를 찾는다.
 * 				View => JSP (HTML)
 * 			=> ViewResolver ==> JSP를 찾아서 request를 전송
 * 				Controller : 사용자의 요청을 받는 클래스 : 이미 스프링에서 제공
 * 					=> DispatcherServlet : 메뉴얼만 제작
 * 					=> web.xml
 * 				WebApplicationContext : 사용자 정의 클래스를 관리
 * 					=> 클래스를 등록
 * 						application-context.xml
 * 						application-datasource.xml
 * 						application-security.xml
 * 					
 * 					=> 넘겨주는 방법
 * 						<init-param>
 * 							<param-name>contextConfigLocation</param-name>
 * 							<param-value>/WEB-INF/config/application-*.xml</param-value>
 * 						</init-param>
 * 																요청하는 메서드 찾기
 * 																	=> @GetMapping(URI)
 * 																	=> @PostMapping(URI)
 * 																	=> @RequestMapping(URI)
 * 					=> 사용자 요청 ==> DispatcherServlet ===> HandlerMapping ====> Model 처리
 * 																					| Request
 * 																				ViewResolver
 * 																					p:prefix => 경로명
 * 																					p:suffix => 확장자
 * 																					| Request
 * 																				   JSP
 * 				.do
 * 				요청에 대한 처리
 * 				===============
 * 					1) DB (MyBatis) => DAO / Service
 * 						테이블 1개 / 관련 DAO를 묶어서 처리
 * 
 * 					2) External API (외부 API) => (날씨, 뉴스, 메일)
 * 								
 * 					3) 전송
 * 						Redirect : 기존에 있는 메서드를 재호출 => _ok (DML => INSERT / UPDATE / DELETE)
 * 							=> return "redirect:list.do"
 * 
 * 						forward  : 해당 데이터 전송 (select)
 * 							=> return "경로/파일명"
 *
 * 				화면 출력
 * 				========
 * 					1) JSP => EL / JSTL
 * 					2) JavaScript : AJAX / Vue.js / ReactJS
 * 							|
 * 						List	=> []
 * 						VO		=> {}
 * 						============= JSON (Jackson) => Spring-Boot에서는 자동으로 처리.
 * 						일반 데이터형(Primitive  data type) : integer / float / boolean / character
 * 						
 * 		
 * 							
 * 
 * 
 */					
@Controller
@RequestMapping("main/")
public class MainController {
	// 필요한 클래스 => 스프링에서 가지고 온다. (객체 주소)
	@Autowired
	private RecipeService rService;
	
	@Autowired
	private FoodService fService;
	
	// 사용자 요청에 따라 처리
	@GetMapping("main.do")
	public String main_main(Model model) {
		RecipeVO rvo = rService.recipeMaxHitData();
		ChefVO cvo = rService.chefToday();
		
		List<RecipeVO> rList = rService.recipeHitTop8();
		List<FoodVO> fList = fService.foodHitTop5();
		
		
		model.addAttribute("rvo", rvo);
		model.addAttribute("cvo", cvo);
		model.addAttribute("rList", rList);
		model.addAttribute("fList", fList);
		
		return "main";
	}
	
	
	
	
}
