package com.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO extends CommonDTO {

	private Long idx;

	private Long boardIdx;

	private String content;

	private String writer;

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public Long getBoardIdx() {
		return boardIdx;
	}

	public void setBoardIdx(Long boardIdx) {
		this.boardIdx = boardIdx;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "CommentDTO [idx=" + idx + ", boardIdx=" + boardIdx + ", content=" + content + ", writer=" + writer
				+ ", getIdx()=" + getIdx() + ", getBoardIdx()=" + getBoardIdx() + ", getContent()=" + getContent()
				+ ", getWriter()=" + getWriter() + ", toString()=" + super.toString() + ", getPaginationInfo()="
				+ getPaginationInfo() + ", getDeleteYn()=" + getDeleteYn() + ", getInsertTime()=" + getInsertTime()
				+ ", getUpdateTime()=" + getUpdateTime() + ", getDeleteTime()=" + getDeleteTime() + ", getStartPage()="
				+ getStartPage() + ", getCurrentPageNo()=" + getCurrentPageNo() + ", getRecordsPerPage()="
				+ getRecordsPerPage() + ", getPageSize()=" + getPageSize() + ", getSearchKeyword()="
				+ getSearchKeyword() + ", getType()=" + getType() + ", getSearchType()=" + getSearchType()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
}