package com.board.service;

import com.board.domain.SurveyDTO;
import com.board.domain.SurveyOutputDTO;
import com.board.domain.TypeDTO;

public interface SurveyService {
	
	public boolean registerSurvey(SurveyDTO params);
	
	public SurveyDTO getSurveyResult(String id);
	
	public SurveyOutputDTO getSurveyOuput(String id);
	
	public SurveyOutputDTO getSurveyList(String id);

	public TypeDTO getTypeInfo(String type);

}


