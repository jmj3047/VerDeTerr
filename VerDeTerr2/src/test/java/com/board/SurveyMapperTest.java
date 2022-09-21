package com.board;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.domain.SurveyDTO;
import com.board.domain.SurveyOutputDTO;
import com.board.mapper.SurveyMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
class SurveyMapperTest {

	@Autowired
	private SurveyMapper surveyMapper;

	@Test
	public void testOfSurveyInsert() {
		SurveyDTO params = new SurveyDTO(null);
		params.setId("아이디");
		params.setAnswer1("유저 정답");
		params.setAnswer2("유저 정답");
		params.setAnswer3("유저 정답");
		params.setAnswer4("유저 정답");
		params.setAnswer5("유저 정답");
		params.setAnswer6("유저 정답");
		params.setAnswer7("유저 정답");
		params.setAnswer8("유저 정답");
		params.setAnswer9("유저 정답");
		params.setAnswer10("유저 정답");
		params.setAnswer11("유저 정답");
		params.setAnswer12("유저 정답");
		params.setAnswer13("유저 정답");
		params.setAnswer14("유저 정답");
		params.setAnswer15("유저 정답");
		params.setAnswer16("유저 정답");

		int result = surveyMapper.insertSurvey(params);
		System.out.println("결과는 " + result + "입니다.");
	}
	
	@Test
	public void testOfSurveyOutput() {
	
		SurveyOutputDTO survey = surveyMapper.selectSurveyOutput((String) "mj");
		try {
            String surveyJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(survey);

			System.out.println("=========================");
			System.out.println(surveyJson);
			System.out.println("=========================");

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
//	@Test
//	public void testOfUpdate() {
//		SurveyDTO params = new SurveyDTO();
//		params.setUserType("유저타입 수정");
//		params.setAnswer("유저정답 수정");
//
//		int result = surveyMapper.updateSurvey(params);
//		if (result == 1) {
//			SurveyDTO survey = surveyMapper.selectBoardDetail((long) 1);
//			try {
//				//String boardJson = new ObjectMapper().writeValueAsString(board);
//                String boardJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(survey);
//
//				System.out.println("=========================");
//				System.out.println(boardJson);
//				System.out.println("=========================");
//
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	

}