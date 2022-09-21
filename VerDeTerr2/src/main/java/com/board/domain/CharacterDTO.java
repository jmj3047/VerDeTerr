package com.board.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDTO {

	/**Character Table*/
	private int idx;

	private String name;
	
	private String title;
	
	private String category;
	
	private String userType;
	
	private LocalDateTime regDate;
	
	private String filename;
	
	private String filepath;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	
	@Override
	public String toString() {
		return "CharacterDTO [idx=" + idx + ", name=" + name + ", title=" + title + ", category=" + category
				+ ", userType=" + userType + ", regDate=" + regDate + ", filename=" + filename + ", filepath="
				+ filepath + "]";
	}


}
