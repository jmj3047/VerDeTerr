package com.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyDTO {
	
	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public String getAnswer5() {
		return answer5;
	}

	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}

	public String getAnswer6() {
		return answer6;
	}

	public void setAnswer6(String answer6) {
		this.answer6 = answer6;
	}

	public String getAnswer7() {
		return answer7;
	}

	public void setAnswer7(String answer7) {
		this.answer7 = answer7;
	}

	public String getAnswer8() {
		return answer8;
	}

	public void setAnswer8(String answer8) {
		this.answer8 = answer8;
	}

	public String getAnswer9() {
		return answer9;
	}

	public void setAnswer9(String answer9) {
		this.answer9 = answer9;
	}

	public String getAnswer10() {
		return answer10;
	}

	public void setAnswer10(String answer10) {
		this.answer10 = answer10;
	}

	public String getAnswer11() {
		return answer11;
	}

	public void setAnswer11(String answer11) {
		this.answer11 = answer11;
	}

	public String getAnswer12() {
		return answer12;
	}

	public void setAnswer12(String answer12) {
		this.answer12 = answer12;
	}

	public String getAnswer13() {
		return answer13;
	}

	public void setAnswer13(String answer13) {
		this.answer13 = answer13;
	}

	public String getAnswer14() {
		return answer14;
	}

	public void setAnswer14(String answer14) {
		this.answer14 = answer14;
	}

	public String getAnswer15() {
		return answer15;
	}

	public void setAnswer15(String answer15) {
		this.answer15 = answer15;
	}

	public String getAnswer16() {
		return answer16;
	}

	public void setAnswer16(String answer16) {
		this.answer16 = answer16;
	}

	/** IDX (PK) */
	private Long idx;
	
	/** ID */
	private String id;
	
	/** Answer */
	private String answer1;	private String answer2;	private String answer3;	private String answer4;
	private String answer5;	private String answer6;	private String answer7;	private String answer8;
	private String answer9;	private String answer10;	private String answer11;	private String answer12;
	private String answer13;	private String answer14;	private String answer15;	private String answer16;
	
	/** Constructor */
	public SurveyDTO() {
		super();
	}
	
	public SurveyDTO(String id) {
		super();
		this.id = id;
		this.answer1 = "";		this.answer2 = "";		this.answer3 = "";		this.answer4 = "";
		this.answer5 = "";		this.answer6 = "";		this.answer7 = "";		this.answer8 = "";
		this.answer9 = "";		this.answer10 = "";		this.answer11 = "";		this.answer12 = "";
		this.answer13 = "";		this.answer14 = "";		this.answer15 = "";		this.answer16 = "";
	}
	
	/** toString */
	@Override
	public String toString() {
		return "SurveyDTO [idx=" + idx + ", id=" + id + ", answer1=" + answer1 + ", answer2=" + answer2 + ", answer3="
				+ answer3 + ", answer4=" + answer4 + ", answer5=" + answer5 + ", answer6=" + answer6 + ", answer7="
				+ answer7 + ", answer8=" + answer8 + ", answer9=" + answer9 + ", answer10=" + answer10 + ", answer11="
				+ answer11 + ", answer12=" + answer12 + ", answer13=" + answer13 + ", answer14=" + answer14
				+ ", answer15=" + answer15 + ", answer16=" + answer16 + "]";
	}
	
}