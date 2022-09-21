package com.board.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.board.paging.Criteria;
import com.board.paging.PaginationInfo;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean registerBoard(BoardDTO params) {
		int queryResult = 0;
		
		System.out.println("idx not null"+params.getIdx());
		
		if (params.getIdx() == null) {
			
			System.out.println("idx null"+params.getIdx());
			System.out.println("serviceImpl"+params.getWriter());
			System.out.println("여기까지 들어옵?");
//			memberDTO member=new memberDTO();
//			member.setNickname("버즈우주선형");
			
			queryResult = boardMapper.insertBoard(params);
			System.out.println(queryResult);
			
		} else {
			queryResult = boardMapper.updateBoard(params);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public BoardDTO getBoardDetail(Long idx) {
			//조회수 늘리는것   
			boardMapper.updateViewcnt(idx);
			
		return boardMapper.selectBoardDetail(idx);
	}

	@Override
	public boolean deleteBoard(Long idx) {
		int queryResult = 0;

		BoardDTO board = boardMapper.selectBoardDetail(idx);
		System.out.println(board);
		System.out.println();
		if (board != null && board.getDeleteYn() == false) {
			System.out.println("if문");
			queryResult = boardMapper.deleteBoard(idx);
			 System.out.println("queryResult:"+queryResult); 
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<BoardDTO> getBoardList(BoardDTO params) {
		System.out.println("params.getcriteria"+params);
		List<BoardDTO> boardList = Collections.emptyList();
		System.out.println("도대체 getboardlist의 param은 뭐야? 내가 보았을때는 posttype"+params);
		int boardTotalCount = boardMapper.selectBoardTotalCount(params);
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(boardTotalCount);
		
		params.setPaginationInfo(paginationInfo);
		
		if (boardTotalCount > 0) {
			
			boardList = boardMapper.selectBoardList(params);
			//System.out.println("boardlist:"+boardList);
			//System.out.println("params 뭐 나오지"+params.getIdx());
			System.out.println("getboardlist paginationinfo는??"+params.getPaginationInfo());
		}

		return boardList;
	}

}