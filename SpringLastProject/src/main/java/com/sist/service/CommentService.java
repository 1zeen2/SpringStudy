package com.sist.service;

import java.util.*;

import com.sist.vo.*;
import com.sist.dao.*;

public interface CommentService {
	public List<CommentVO> commentListData(Map map);
	public int commentTotalPage(Map map);
	public void commentInsert(CommentVO vo);
	public void commentReplyReplyInsert(CommentVO vo, int cno);
}
