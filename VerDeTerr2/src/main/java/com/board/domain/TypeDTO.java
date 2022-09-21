package com.board.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeDTO {

	/**MBTI_Type Table*/
	
	private String userType;
	
	private String category;

	private String feature;
	
	private String feature2;
	
	private String inCompany;

	private String job;
	
	private String goodMatch;

	private String badMatch;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getFeature2() {
		return feature2;
	}

	public void setFeature2(String feature2) {
		this.feature2 = feature2;
	}

	public String getInCompany() {
		return inCompany;
	}

	public void setInCompany(String inCompany) {
		this.inCompany = inCompany;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getGoodMatch() {
		return goodMatch;
	}

	public void setGoodMatch(String goodMatch) {
		this.goodMatch = goodMatch;
	}

	public String getBadMatch() {
		return badMatch;
	}

	public void setBadMatch(String badMatch) {
		this.badMatch = badMatch;
	}

	@Override
	public String toString() {
		return "TypeDTO [userType=" + userType + ", category=" + category + ", feature=" + feature + ", feature2="
				+ feature2 + ", inCompany=" + inCompany + ", job=" + job + ", goodMatch=" + goodMatch + ", badMatch="
				+ badMatch + ", getUserType()=" + getUserType() + ", getCategory()=" + getCategory() + ", getFeature()="
				+ getFeature() + ", getFeature2()=" + getFeature2() + ", getInCompany()=" + getInCompany()
				+ ", getJob()=" + getJob() + ", getGoodMatch()=" + getGoodMatch() + ", getBadMatch()=" + getBadMatch()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
