package com.board.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class memberDTO {
	@Override
	public String toString() {
		return "memberDTO [nickname=" + nickname + "]";
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	String nickname;
	
}
