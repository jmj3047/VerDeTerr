package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.SurveyDTO;
import com.board.domain.SurveyOutputDTO;
import com.board.domain.TypeDTO;

@Mapper
public interface SurveyMapper {
	
	public int insertSurvey(SurveyDTO params);

	public int updateSurvey(SurveyDTO params);
	
	public SurveyOutputDTO selectSurveyOutput(String id);
	
	public SurveyOutputDTO selectSurveyList(String id);
	
	public TypeDTO selectTypeInfo(String type);
	
}
