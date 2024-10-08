package com.sist.mapper;

import java.util.*;

import com.sist.vo.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CommentMapper {
	@Select("SELECT cno, rno, type, id, name, msg, sex, group_tab, likecount, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS')as dbday, num "
			+ "FROM(SELECT cno, rno, type, id, name, msg, sex, group_tab, likecount, regdate, rownum as num "
			+ "FROM(SELECT cno, rno, type, id, name, msg, sex, group_tab, likecount, regdate "
			+ "FROM spring_comment WHERE rno=#{rno} AND type=#{type} "
			+ "ORDER BY group_id DESC, group_step ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<CommentVO> commentListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_comment "
			+ "WHERE rno=#{rno} AND type=#{type}")
	public int commentTotalPage(Map map);
	
	@Insert("INSERT INTO spring_comment(cno, rno, id, name, sex, msg, group_id, type) "
			+ "VALUES(sc_cno_seq.nextval, #{rno}, #{id}, #{name}, #{sex}, #{msg}, "
			+ "(SELECT NVL(MAX(group_id)+1,1) FROM spring_comment), #{type})")
	public void commentInsert(CommentVO vo);
	
	/*
	 * 	AAAA
	 * 		= FFFF ====> 댓글이 추가되기 전에 depth의 값을 1씩 올려 순서가 섞이지 않도록 하기 위한 코드.
	 * 		= BBBB 		 B, C, D의 값을 전부 지웠다가 +1을 한 후 마지막으로 남겨진 댓글이 가장 낮은 숫자가 되게 만들어
	 * 		= CCCC 		 ASC했을 때 순서대로 나올 수 있도록 출력 예정.
	 * 		= DDDD
	 * 	EEEE
	 */
	
	// 대댓글
	@Select("SELECT group_id, group_step, group_tab "
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
	
}
