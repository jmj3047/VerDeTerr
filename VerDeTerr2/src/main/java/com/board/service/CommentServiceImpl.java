package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.CommentDTO;
import com.board.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;

	@Override
	public boolean registerComment(CommentDTO params) {
		int queryResult = 0;

		if (params.getIdx() == null) {
			queryResult = commentMapper.insertComment(params);
		} else {
			queryResult = commentMapper.updateComment(params);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public boolean deleteComment(Long idx) {
		int queryResult = 0;
		System.out.println("deleComment 입성");
		CommentDTO comment = commentMapper.selectCommentDetail(idx);
		System.out.println("comment입니다"+comment);
		if (comment != null && comment.getDeleteYn()==false) {
			queryResult = commentMapper.deleteComment(idx);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<CommentDTO> getCommentList(CommentDTO params) {
		List<CommentDTO> commentList = Collections.emptyList();

		int commentTotalCount = commentMapper.selectCommentTotalCount(params);
		if (commentTotalCount > 0) {
			commentList = commentMapper.selectCommentList(params);
		}

		return commentList;
	}

}