package com.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.domain.CharacterDTO;
import com.board.mapper.CharacterMapper;

@SpringBootTest
public class CharacterTests {
	
	@Autowired
	public CharacterMapper characterMapper;
	
	@Test
	public void testOfInsert() {
		CharacterDTO params = new CharacterDTO();
		params.setName("하울");
		params.setTitle("하울의 움직이는 성");
		params.setCategory("만화");;
		params.setUserType("ISFP");
		
		int result = characterMapper.insertCharacter(params);
		System.out.println(result);
	}
}
