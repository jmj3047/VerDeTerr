package com.board;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.board.service.UserService;

@SpringBootTest
public class PasswordEncoderTest {
	   @Autowired
	   private UserService userService;

	   @Autowired
	   private PasswordEncoder passwordEncoder;
	  
	   @Test
	   @DisplayName("패스워드 암호화 테스트")
	   public void passwordEncode() {
	      // given
		  System.out.println("================================");
	      String rawPassword = "12345678";
	      System.out.println(rawPassword);

	      // when
	      System.out.println("================================");
	      String encodedPassword = passwordEncoder.encode(rawPassword);
	      System.out.println(encodedPassword);
	      System.out.println(encodedPassword.length());

	      // then
	      assertAll(
	            () -> assertNotEquals(rawPassword, encodedPassword),
	            () -> assertTrue(passwordEncoder.matches(rawPassword, encodedPassword))
	      );
	   } 
}
