package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.CharacterDTO;
import com.board.domain.SurveyOutputDTO;
import com.board.domain.UserDTO;

@Mapper
public interface UserMapper {

	public int insertUser(UserDTO params);

	public UserDTO selectUserDetail(String id);
	
	public CharacterDTO selectUserCharacter(String id);

	public int updateUser(UserDTO params);

	public int deleteUser(String id);

	public List<SurveyOutputDTO> selectUserHistory(String id);

	public int selectUserHistoryCount(String id);
	
	public UserDTO findId(String email);
	
	public UserDTO findPw(String pwHint);

	public List<UserDTO> selectUserList();

	public int selectUserTotalCount();

}