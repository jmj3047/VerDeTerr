package com.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;

@Service
public interface BoardService {

	public boolean registerBoard(BoardDTO params);

	public BoardDTO getBoardDetail(Long idx);

	public boolean deleteBoard(Long idx);

	public List<BoardDTO> getBoardList(BoardDTO params);

}