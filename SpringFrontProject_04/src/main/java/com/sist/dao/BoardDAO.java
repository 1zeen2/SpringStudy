package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class BoardDAO {
	// Spring으로부터 구현된 Mapper의 주소를 대입 => 요청
	// 주소 값 자동 주입 => @Autowired는 ==> 객체에만 적용이 된다.
	// Spring => 클래스의 생명 주기 관리 ==> 주로 객체 주소를 주입
	// @Autowired => 1) 파라미터 / 2) Constructor / 3) setXxx
	// Annotation은 밑, 또는 옆에 있는 변수 / 메서드 / 생성자를 제어
	@Autowired						
	private BoardMapper mapper;		
	
//	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD')as dbday, hit, num "
//			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
//			+ "FROM (SELECT no, subject, name, regdate, hit "
//			+ "FROM vue_board ORDER BY no DESC)) "
//			+ "WHERE num BETWEEN #{start} AND #{end}")
//	public List<BoardVO> boardListData(@Param("start") int start, @Param("end") int end);
	public List<BoardVO> boardListData(int start, int end) {
		return mapper.boardListData(start, end);
	}
	
//	@Select("SELECT COUNT(*) FROM vue_board")
//	public int boardTotalPage();
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	
//	@Insert("INSERT INTO vue_board(no, name, subject, content, pwd) "
//			+ "VALUES(vb_no_seq.nextval, #{name}, #{subject}, #{content}, #{pwd})")
//	public void boardInsert(BoardVO vo);
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	
//	@Update("UPDATE vue_board SET "
//			+ "hit=hit+1 "
//			+ "WHERE no=#{no}")
//	public void hitIncrement(int no);
//	
//	@Select("SELECT no, name, subject, content, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday, hit "
//			+ "FROM vue_board "
//			+ "WHERE no=#{no}")
//	public BoardVO boardDetailData(int no);
	public BoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);	// Vue에서 받는 방법
		return mapper.boardDetailData(no);
	}
	
//	@Select("SELECT pwd "
//			+ "FROM vue_board "
//			+ "WHERE no=#{no}")
//	public String boardGetPassword(int no);
	
//	@Delete("DELETE "
//			+ "FROM vue_board "
//			+ "WHERE no=#{no}")
//	public void boardDelete(int no);
	public String boardDelete(int no, String pwd) {
		String result = "no";
		String db_pwd = mapper.boardGetPassword(no);
		
		if (db_pwd.equals(pwd)) {
			result = "yes";
			mapper.boardDelete(no);
		}
		return result;
	}
	
	// 수정
//	@Select("SELECT name, subject, content "
//			+ "FROM vue_board "
//			+ "WHERE no=#{no}")
//	public BoardVO boardUpdateData();
	public BoardVO boardUpdateData(int no) {
		return mapper.boardUpdateData(no);
	}
	
//	@Update("UPDATE vue_board SET "
//			+ "name=#{name}, subject=#{subject}, content=#{content} "
//			+ "WHERE no=#{no}")
//	public void boardUpdate(BoardVO vo);
	public String boardUpdate(BoardVO vo) {
		String result = "no";
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		 if (db_pwd.equals(vo.getPwd())) {
			   result = "yes";
			   mapper.boardUpdate(vo);
		 }
		return result;
	}
	
	
}
