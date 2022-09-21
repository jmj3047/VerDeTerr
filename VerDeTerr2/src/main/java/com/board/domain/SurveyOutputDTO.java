package com.board.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyOutputDTO {

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getTestdate() {
		return testdate;
	}

	public void setTestdate(LocalDateTime testdate) {
		this.testdate = testdate;
	}

	public String getUseranswer() {
		return useranswer;
	}

	public void setUseranswer(String useranswer) {
		this.useranswer = useranswer;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	@Override
	public String toString() {
		return "SurveyOutputDTO [idx=" + idx + ", id=" + id + ", testdate=" + testdate + ", useranswer=" + useranswer
				+ ", usertype=" + usertype + "]";
	}

	/**MBTI_ML_output Table*/
	/** IDX (PK)*/
	private int idx;
	
	private String id;
	
	private LocalDateTime testdate;
	
	private String useranswer;
	
	private String usertype;

}
