package com.sist.service;

import java.util.*;

import com.sist.vo.*;
import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDAO cDao;

	@Override
	public List<CommentVO> commentListData(Map map) {
		return cDao.commentListData(map);
	}

	@Override
	public int commentTotalPage(Map map) {
		return cDao.commentTotalPage(map);
	}

	@Override
	public void commentInsert(CommentVO vo) {
		cDao.commentInsert(vo);
	}

	@Override
	public void commentReplyReplyInsert(CommentVO vo, int cno) {
		cDao.commentReplyReplyInsert(vo, cno);
	}
}
