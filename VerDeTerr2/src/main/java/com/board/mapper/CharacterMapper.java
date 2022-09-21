package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.board.domain.CharacterDTO;

@Mapper
public interface CharacterMapper {

	public int insertCharacter(CharacterDTO params);

	public CharacterDTO selectCharacterDetail(Long idx);

	public int updateCharacter(CharacterDTO params);

	public int deleteCharacter(Long idx);

	public List<CharacterDTO> selectCharacterList(String type);

	public int selectCharacterTotalCount(String type);	

}