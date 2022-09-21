package com.board;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.domain.CommentDTO;
import com.board.service.CommentService;

@SpringBootTest
class CommentTests {

	@Autowired
	private CommentService commentService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void registerComments() {
	
			CommentDTO params = new CommentDTO();
			params.setBoardIdx((long) 2); // 댓글을 추가할 게시글 번호
			params.setContent("번 댓글을 추가합니다!");
			params.setWriter("번 회원");
			commentService.registerComment(params);
		

		logger.debug("댓글 개가 등록되었습니다.");
	}

	@Test
	public void deleteComment() {
		commentService.deleteComment((long) 10); // 삭제할 댓글 번호

		getCommentList();
	}

	@Test
	public void getCommentList() {
		CommentDTO params = new CommentDTO();
		params.setBoardIdx((long) 6529); // 댓글을 추가할 게시글 번호

		commentService.getCommentList(params);
	}

}