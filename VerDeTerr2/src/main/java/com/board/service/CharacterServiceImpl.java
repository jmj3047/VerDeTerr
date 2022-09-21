package com.board.service;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.CharacterDTO;
import com.board.mapper.CharacterMapper;

@Service
public class CharacterServiceImpl implements CharacterService{

	@Autowired
	public CharacterMapper characterMapper;
	
	@Override
	public void registerCharacter(CharacterDTO params) {
		/*
		 * String projectpath = System.getProperty("user.dir")+
		 * "/src/main/resources/static/files"; // user.dir은 프로젝트 경로를 담아줌 UUID uuid =
		 * UUID.randomUUID(); //랜덤으로 이름 생성 String filename =
		 * uuid+"_"+file.getOriginalFilename(); //파일 이름은 UUID에 있는 랜덤값 + 원래 파일 이름으로 설정된다.
		 * File saveFile = new File(projectpath, filename); //위에 적힌 경로에 name으로 저장
		 * file.transferTo(saveFile); params.setFilename(filename);
		 * params.setFilepath("/files"+filename);
		 */
		characterMapper.insertCharacter(params);
	};

	@Override
	public CharacterDTO getCharacterDetail(Long idx) {
		return characterMapper.selectCharacterDetail(idx);
	};
	
	@Override
	public int updateCharacter(CharacterDTO params) {
		return characterMapper.updateCharacter(params);
	};

	@Override
	public int deleteCharacter(Long idx) {
		return characterMapper.deleteCharacter(idx);
	};

	@Override
	public List<CharacterDTO> getCharacterList(String type){
		List<CharacterDTO> characterList = Collections.emptyList();

		int characterCount = characterMapper.selectCharacterTotalCount(type);
		if(characterCount > 0) {
			characterList = characterMapper.selectCharacterList(type);
		}
		return characterList;
	};
	
}
