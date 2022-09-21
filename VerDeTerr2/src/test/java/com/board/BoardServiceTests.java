package com.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.board.domain.BoardDTO;
import com.board.service.BoardServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
public class BoardServiceTests {

	@Autowired
	private BoardServiceImpl boardserviceimpl;


	@Test
	public void testOfRegister() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 게시글 제목");
		params.setContent("1번 게시글 내용");
		params.setWriter("테스터");
		params.setPostType("분석가형");

		boolean result = boardserviceimpl.registerBoard(params);
		if(result == true) {
			System.out.println("글이 등록되었습니다.");
		}
		else {
			System.out.println("글 등록에 실패하였습니다.");
		}
	}
	
	@Test
	public void testOfGetDetail() {
		BoardDTO board = boardserviceimpl.getBoardDetail((long) 3);
		try {
			//String boardJson = new ObjectMapper().writeValueAsString(board);
            String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);

			System.out.println("=========================");
			System.out.println(boardJson);
			System.out.println("=========================");

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOfDelete() {
		boolean result = boardserviceimpl.deleteBoard((long) 1);
		if (result == true) {
			BoardDTO board = boardserviceimpl.getBoardDetail((long) 1);
			try {
				//String boardJson = new ObjectMapper().writeValueAsString(board);
                String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);

				System.out.println("=========================");
				System.out.println(boardJson);
				System.out.println("=========================");

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testOfGetList() {
		BoardDTO params = new BoardDTO();
		params.setPostType("관리자형");
		List<BoardDTO> boardList = boardserviceimpl.getBoardList(params);
		if (CollectionUtils.isEmpty(boardList) == false) {
			for (BoardDTO board : boardList) {
				System.out.println("=========================");
				System.out.println(board.getTitle());
				System.out.println(board.getPostType());
				System.out.println(board.getContent());
				System.out.println(board.getWriter());
				System.out.println("=========================");
			}
		}
	}

}
