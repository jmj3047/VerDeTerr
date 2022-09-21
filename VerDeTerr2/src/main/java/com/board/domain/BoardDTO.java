package com.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO extends CommonDTO {

	
	@Override
	public String toString() {
		return "BoardDTO [idx=" + idx + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", viewCnt=" + viewCnt + ", noticeYn=" + noticeYn + ", postType=" + postType + "]";
	}

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public Boolean getNoticeYn() {
		return noticeYn;
	}

	public void setNoticeYn(Boolean noticeYn) {
		this.noticeYn = noticeYn;
	}


	

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	/** 번호 (PK) */
	private Long idx;

	/** 제목 */
	private String title;

	/** 내용 */
	private String content;

	/** 작성자 */
	private String writer;

	/** 조회 수 */
	private int viewCnt;

	/** 공지 여부 */
	private Boolean noticeYn;

	/** 유형 */
	private String postType;

}