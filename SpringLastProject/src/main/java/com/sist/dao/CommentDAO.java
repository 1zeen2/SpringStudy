package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sist.vo.*;
import com.sist.mapper.*;

/*
 * 	스프링에서 메모리 할당 => @Autowired
 * 			  ==========
 * 			1. @Component : 일반 클래스 (Naver API, News, 다른 프로그램 연동)
 * 
 * 			2. @Respository : DAO (Data Access Object) - Connect DB
 * 
 * 			3. @Service : DAO 여러 개 연동
 * 					=> Food / Reply
 * 
 * 			4. @Controller : DispatcherServlet와 연결 => 사이트 페이지 이동
 * 					=> 1. Forward	: Send to request
 * 											  ======= Model(전송 객체)
 * 							return "경로명/JSP명";
 * 
 * 					   2. Redirect	: 이미 만들어진 메서드 호출
 * 							_ok
 * 							return "redirect:~.do";
 * 

 * 
 * 			5. @RestController : DispatcherServlet과 연결된 상태 => 다른 프로그램과 연동할 때 사용
 * 								 모든 프로그램 언어 (Python, JavaScript, Kotlin, ...)
 * 								 ===================================================
 * 										| XML(모든 언어가 파싱이 가능), JSON
 * 									=> 데이터만 전송.
 * 
 * 			6. @ControllerAdvice, @RestControllerAdvide => 공통 예외 처리
 * 					=> 메모리 할당(X) => @CrossOrigin, @Aspect, @RequestMapping
 * 										============  =======  ===============
 * 											  |			 |			  |
 * 											  |			 |		   공통 경로
 * 											  |		  공통 모듈
 * 								포트 번호가 다른 경우 연결 허용 
 * 										@Async : 비동기
 * 	
 * 				=> 메서드 위에 올라가는 애너테이션 : @GetMapping, @PostMapping
 * 				   ==========
 * 					멤버 변수 : @Autowired
 * 						=> 구분자 : 애너테이션에 따라 주소 대입, 메서드 호출
 * 
 * 				=> MVC
 * 					클라이언트 : <a>, <form>, JavaScript
 * 						|		요청 => .do => 화면 출력 / 저장 / 수정 / 삭제 ...
 * 						|
 * 				DispatcherServlet : .do 자동 호출
 * 						| 요청 처리 => 사용자 정의 (Model) => Controller
 * 						  ========
 * 							찾기 => 구분 (GetMapping, PostMapping)
 * 							==== HandlerMapping
 * 							 | Model, Request 값을 전송
 * 						ViewResolver
 * 							 | 
 * 				JSP를 찾아서 요청 결과 출력.
 * 
 * 			====> Model(Controller, RestController)
 * 					=> Controller + DAO + Service + VO ==> 재사용, 수정 용이하게 하고 에처 처리를 수월하게 하기 위해 기능 별로 나눠서 제작
 * 					=> Spring은 컴퓨터에 Mainboard 역할을 하고, Component (Class)를 통해 결합하는 방식으로 제작.
 * 
 * 			====> JSP
 * 
 * 		*** 유지 보수 : Spring-Framework => 신입 때 주로 업무 처리
 * 						AJAX => Vue / React
 * 			=> 전자 정부 프레임워크 (공기업) => 관리자 모드
 *	========================================================================
 * 		*** 개발 : Spring-Boot => 2년차 이상
 * 				   =========== JSP가 없다.
 * 					HTML => ThymeLeaf / Front를 별도로 작성
 * 										==================
 * 											연결 => MSA
 * 
 * 		Python Server (Django) == React
 * 		Spring-Boot == React-Query, Redux
 * 			  |	MySQL / MariaDB
 * 			  | MyBatis / JPA
 * 			  | Vue + React => NextJS
 * 
 * 										Static 		/ 	Instance
 * 		 								  |					|=> New를 이용한 메모리 할당
 * 									메모리 공통 사용 			|=> 각자 다른 값을 사용할 수 있음.
 * 										  |
 * 									모든 값이 변경됨
 * 	===========================================================================
 * 			OverRoding => New	 => 값을 추가하여 사용
 * 			OverRiding => Modify => 메서드를 재정의하여 사용
 * 	===========================================================================
 * 			Framework => 사용 형식이 같은 것 ==> MyBatis, Spring, Vue.JS
 * 			========= 내 마음대로 커스텀하여 사용.
 * 			Library => 
 * 			========= 완제품 
 */
@Repository
public class CommentDAO {
	@Autowired
	private CommentMapper mapper;
/*
	@Select("SELECT cno, rno, type, id, name, msg, likecount, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS')as dbday, num "
			+ "FROM(cno, rno, type, id, name, msg, likecount, regdate, rownum as num "
			+ "FROM(cno, rno, type, id, name, msg, likecount, regdate "
			+ "FROM spring_comment WHERE rno=#{rno} "
			+ "ORDER BY group_id DESC, group_step ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<CommentVO> commentListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_comment "
			+ "WHERE rno=#{rno}")
	public int commentTotalPage(int rno);
 */
	public List<CommentVO> commentListData(Map map) {
		return mapper.commentListData(map);
	}
	
	public int commentTotalPage(Map map) {
		return mapper.commentTotalPage(map);
	}
	
	/*
 		@Insert("INSERT INTO spring_comment(cno, rno, id, name, sex, msg, group_id, type) "
				+ "VALUES(sc_cno_seq.nextval, #{rno}, #{id}, #{name}, #{sex}, #{msg}, "
				+ "(SELECT NVL(MAX(group_id)+1,1) FROM spring_comment), #{type})")
		public void replyInsert(CommentVO vo);
	 */
	public void commentInsert(CommentVO vo) {
		mapper.commentInsert(vo);
	}
	
	/*
		@Insert("SELECT group_id, group_step, group_tab "
				+ "FROM spring_comment "
				+ "WHERE cno=#{cno}")
		public CommentVO commentParentInfoData(int cno);
		
		@Update("UPDATE spring_comment SET "
				+ "group_step=group_step+1 "
				+ "WHERE group_id=#{group_id} AND group_step>#{group_step}")
		public void commentGroupStepIncrement(CommentVO vo);
		
		// cno, rno, id, name, sex, msg, group_id, type
		@Insert("INSERT INTO spring_comment(cno, rno, id, name, sex, msg, group_id, "
				+ "group_step, group_tab, root, type) "
				+ "VALUES(sc_cno_seq.nextval, #{rno}, #{id}, #{name}, #{sex}, #{msg}, #{group_id}, #{group_step}, #{group_tab}, #{root}, #{type})")
		public void commentReplyReplyInsert(CommentVO vo);
		
		@Update("UPDATE spring_comment SET "
				+ "depth=depth+1 "
				+ "WHERE cno=#{cno}")
		public void commentDepthIncrement(int cno);
	 */
	
	// Insert, Update, 등 코드가 꼬이면 문제가 생길 수 있어 Transaction을 적용. 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void commentReplyReplyInsert(CommentVO vo, int cno) {
		CommentVO pvo = mapper.commentParentInfoData(cno);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step() + 1);
		vo.setGroup_tab(pvo.getGroup_tab() + 1);
		
		mapper.commentGroupStepIncrement(pvo);
		mapper.commentReplyReplyInsert(vo);
		mapper.commentDepthIncrement(cno);
		
	}
}
