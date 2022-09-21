package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.domain.CharacterDTO;

@Service
public interface CharacterService {

	public void registerCharacter(CharacterDTO params);

	public CharacterDTO getCharacterDetail(Long idx);
	
	public int updateCharacter(CharacterDTO params);

	public int deleteCharacter(Long idx);

	public List<CharacterDTO> getCharacterList(String type);
	
}