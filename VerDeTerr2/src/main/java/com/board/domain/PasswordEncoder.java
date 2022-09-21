package com.board.domain;

public interface PasswordEncoder {
	
	String encode(CharSequence rawPassword);
	
	boolean matches(CharSequence rawPassword, String encodedPassword);
	
	default boolean upgradeEncoding(String encodedPassword) {
		return false;
	}


}
