package com.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.domain.BoardDTO;
import com.board.domain.CommentDTO;
import com.board.mapper.BoardMapper;
import com.board.paging.Criteria;
import com.board.paging.PaginationInfo;
import com.board.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;



@SpringBootTest
class BoardMapperTests {
	@Autowired
	private CommentService commentService;


	@Autowired
	private BoardMapper boardMapper;

	@Test
	public void testOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 게시글 제목");
		params.setContent("1번 게시글 내용");
		params.setWriter("테스터");
		params.setPostType("분석가형");

		int result = boardMapper.insertBoard(params);
		System.out.println("결과는 " + result + "입니다.");
	}
	
	@Test
	public void testOfSelectDetail() {
		BoardDTO board = boardMapper.selectBoardDetail((long) 2);
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
	   public void testOfUpdate() {
	      BoardDTO params = new BoardDTO();
	      params.setTitle("3번 게시글 제목을 수정합니다.");
	      params.setContent("3번 게시글 내용을 수정합니다.");
	      params.setWriter("홍길동");
	      params.setIdx((long) 3);
	      int result=boardMapper.updateBoard(params);
	      if (result == 1) {
	         BoardDTO board = boardMapper.selectBoardDetail((long) 3);
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
	  public void testOfRestore() {
	      BoardDTO params = new BoardDTO();
	      for(int i=1;i<=20;i++) 
	      {
	         int result=boardMapper.restore(params);
	         params.setIdx((long) i);
	         System.out.println(result);
	      
	      }
	}
	
	@Test
	public void testOfDelete() {
		int result = boardMapper.deleteBoard((long) 2);
		if (result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long) 2);
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
	public void testMultipleInsert() {
		for (int i = 3; i <= 50; i++) {
			BoardDTO params = new BoardDTO();
			params.setTitle(i + "번 게시글 제목");
			params.setContent(i + "번 게시글 내용");
			params.setWriter(i + "번 게시글 작성자");
			params.setPostType("관리자형");
			boardMapper.insertBoard(params);
		}
	}
	
	@Test
	public void selectBoardListTest() {
		System.out.println("test 들어옴");
		BoardDTO boardDTO=new BoardDTO();
		Criteria criteria=new Criteria();
		
		PaginationInfo pageinfo = new PaginationInfo(criteria);
		boardDTO.setPostType("ESTJ");
		pageinfo.setFirstRecordIndex(3);
		boardDTO.setPaginationInfo(pageinfo);
		
		System.out.println("세팅완료");
		
		try {
			System.out.println("매퍼실행직전 로그");
			BoardDTO board = (BoardDTO) boardMapper.selectBoardList(boardDTO);
			System.out.println("매퍼실행직후 로그");
			//String boardJson = new ObjectMapper().writeValueAsString(board);
            String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);

			System.out.println("=========================");
			System.out.println(boardJson);
			System.out.println("=========================");

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	// commenttest 따로만드니까 안되서 임시로 boarTest 에서~ 
	@Test
	public void registerComments() {
			
			CommentDTO params = new CommentDTO();
			
			params.setBoardIdx((long) 3); // 댓글을 추가할 게시글 번호
			params.setContent("도오대체 왜되는 것이냐");
			params.setWriter("won9914_ESTJ");
			commentService.registerComment(params);
			

	}

	@Test
	public void deleteComment() {
		commentService.deleteComment((long) 12); // 삭제할 댓글 번호

		getCommentList();
	}

	@Test
	public void getCommentList() {
		CommentDTO params = new CommentDTO();
		params.setBoardIdx((long) 2); // 댓글을 추가할 게시글 번호

		commentService.getCommentList(params);
	}
	
//	@Test
//	public void testSelectList() {
//		BoardDTO params = new BoardDTO();
//		params.setPostType("관리자형");
//		int boardTotalCount = boardMapper.selectBoardTotalCount(params);
//		if (boardTotalCount > 0) {
//			List<BoardDTO> boardList = boardMapper.selectBoardList(params);
//			if (CollectionUtils.isEmpty(boardList) == false) {
//				for (BoardDTO board : boardList) {
//					System.out.println("=========================");
//					System.out.println(board.getTitle());
//					System.out.println(board.getPostType());
//					System.out.println(board.getContent());
//					System.out.println(board.getWriter());
//					System.out.println("=========================");
//				}
//			}
//		}
//	}

}