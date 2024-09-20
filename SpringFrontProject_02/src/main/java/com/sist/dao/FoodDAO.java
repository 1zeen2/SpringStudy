package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
/*
 * 	1. Spring Annotation
 * 		=> 메모리 할당 => 스프링에 클래스 관리
 * 								 ============ 생성 ~ 소멸 => 필요 시에는 주소 값을 얻어서 사용한다.
 * 		구분
 * 		====
 * 			DAO	==========>	@Repository 	: 데이터 접근 계층, DB와의 연결을 처리
 * 
 * 			SERVICE ======> @Service		: 비즈니스 로직을 처리, 여러 DAO를 통합하여 사용 가능
 * 
 * 			MODEL ========> @Controller 	: 요청을 처리하고 화면(View)을 제어하는 역할 (MVC 패턴의 Controller)
 * 							@ResController 	: RESTful API를 처리하며, 주로 JSON 형식의 데이터를 반환
 * 
 * 			Exception ====> @ControllerAdvice		: 공통 예외 처리를 위한 어드바이스 클래스
 * 							@RestControllerAdvice	: 모든 `@RestController` 클래스의 예외 처리를 공통으로 처리
 * 			일반 클래스 
 * 				OpenAI ===> @Component		: Spring에서 관리하는 일반적인 빈(bean) 클래스
 * 		
 * 		= 모든 클래스를 메모리 할당 요청하지는 않는다.
 * 			~VO (사용자 정의 데이터 타입)
 * 			인터페이스는 메모리 할당을 하지 않는다.
 * 
 * 		= 공통으로 사용되는 기능을 모아서 관리 => 공통 모듈
 * 			@Aspect => AOP
 * 
 * 		= Java로 환경 설정 => @Bean
 * 	
 * 		= React 연결 : port가 다르다.(기본적으로는 서버 연결을 할 수 없음)
 * 									================================== 포트 연결 허용을 따로 해야 함.
 * 		= Security / Scheduler
 * 							
 */
@Repository
public class FoodDAO {
	// Spring에서 구현된 객체 주소를 받는다. => 자동 주입
	@Autowired
	private FoodMapper mapper;
/*
 * @Select("SELECT fno, name, poster, num "
			+ "FROM (SELECT fno, name, poster, rownum as num "
			+ "FROM (SELECT fno, name, poster "
			+ "FROM project_food_house ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	// 여러 개의 매개 변수 => @Param , Map, VO
	public List<FoodVO> foodListData(@Param("start") int start, @Param("end") int end);
*/
	public List<FoodVO> foodListData(int start, int end) {
		return mapper.foodListData(start, end);
	}
/*
	@Select("SELECT CEIL(COUNT(*)/20.0 "
			+ "FROM project_food_house")
	public int foodTotalPage();
 */
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
/*
	@Select("SELECT fno, name, poster, num "
			+ "FROM (SELECT fno, name, poster, rownum as num "
			+ "FROM (SELECT fno, name, poster "
			+ "FROM project_food_house "
			+ "WHERE address LIKE '%'||#{address}||'%' ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	// 여러 개의 매개 변수 => @Param , Map, VO
	public List<FoodVO> foodFindListData(Map map);
*/
	public List<FoodVO> foodFindListData(Map map) {
		return mapper.foodFindListData(map);
	}
/*
	@Select("SELECT CEIL(COUNT(*)/20.0) "
			+ "FROM project_food_house "
			+ "WHERE address LIKE '%'||#{address}||'%'")
	public int foodFindTotalPage();
 */
	public int foodFindTotalPage(Map map) {
		return mapper.foodFindTotalPage(map);
	}
}
