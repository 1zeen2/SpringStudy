package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

/*
 * 	SELECT : 데이터 검색
 * 		1) 컬럼을 대신할 수 있다.	 ~~(SELECT~) =======================> 스칼라 서브쿼리
 * 		2) Table 대신 사용할 수 있다. FROM (SELECT ~) ===============> 인라인 뷰 (페이징)
 * 		3) 조건문에서 처리가 가능하다. WHERE Column name (SELECT ~) ==> 서브쿼리
 * 
 * 		정렬 시에는 속도의 최적화를 위해 => INDEX를 사용
 * 										 ====== 자동 생성 : PK, UK
 * 											| CRUD가 많은 경우 => 속도가 저하됨 (게시판, 댓글, ...)
 * 												Crawling해서 저장된 경우 => 데이터를 갱신하지 않기 때문에 => INDEX를 사용한다.
 */
public interface BoardMapper {
	// 자동 구현 => 리턴 타입 / 파라미터 (#이 들어가는 값)
	// Parameter : #{}, ${} => WHERE 문장이 있는 경우.
	// Return type => 실행 후 여러 개의 row가 나오면 List, 1개의 row가 나오면 VO (또는 일반 Data)
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD')as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM vue_board ORDER BY no DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(@Param("start") int start, @Param("end") int end);
	
	// 일률적으로 가져와서 Java에서 총페이지로 처리한다.
	// 중간에 삭제되거나 사라지는 숫자가 있기 때문에 CEIL 사용하지 않음.
	@Select("SELECT COUNT(*) FROM vue_board")
	public int boardTotalPage();
	
	// 글쓰기
	@Insert("INSERT INTO vue_board(no, name, subject, content, pwd) "
			+ "VALUES(vb_no_seq.nextval, #{name}, #{subject}, #{content}, #{pwd})")
	public void boardInsert(BoardVO vo);
	
	// 내용 보기
	@Update("UPDATE vue_board SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday, hit "
			+ "FROM vue_board "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	// 삭제
	// 비밀번호 검색
	@Select("SELECT pwd "
			+ "FROM vue_board "
			+ "WHERE no=#{no}")
	public String boardGetPassword(int no);
	// 실제 삭제
	@Delete("DELETE "
			+ "FROM vue_board "
			+ "WHERE no=#{no}")
	public void boardDelete(int no);
	
	// 수정 => 데이터 읽기
	@Select("SELECT name, subject, content "
			+ "FROM vue_board "
			+ "WHERE no=#{no}")
	public BoardVO boardUpdateData(int no);
	
	@Update("UPDATE vue_board SET "
			+ "name=#{name}, subject=#{subject}, content=#{content} "
			+ "WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
}
