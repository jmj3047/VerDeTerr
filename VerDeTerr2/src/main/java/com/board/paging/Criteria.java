package com.board.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {

	/** 현재 페이지 번호 */
	private int currentPageNo;

	/** 페이지당 출력할 데이터 개수 */
	private int recordsPerPage;

	/** 화면 하단에 출력할 페이지 사이즈 */
	private int pageSize;

	/** 검색 키워드 */
	private String searchKeyword;

	private String type;

	/** 검색 유형 */
	private String searchType;

	// 생성자를 통해서 인스턴스가 생성될떄 초기화를 해준다.
	public Criteria() {
		this.currentPageNo = 1;
		this.recordsPerPage = 10;
		this.pageSize = 5;
	}

	public int getStartPage() {
		return (currentPageNo - 1) * recordsPerPage;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String makeQueryString(int pageNo) {
		System.out.println("pageNO에 뭐들어있지?" + pageNo);

		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("currentPageNo", pageNo)
				.queryParam("recordsPerPage", recordsPerPage).queryParam("pageSize", pageSize)
				.queryParam("searchType", searchType).queryParam("searchKeyword", searchKeyword)
				.queryParam("type", type).build().encode();

		return uriComponents.toUriString();
	}

}
