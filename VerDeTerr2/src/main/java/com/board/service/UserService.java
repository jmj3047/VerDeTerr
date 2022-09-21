package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.domain.MailDTO;
import com.board.domain.SurveyOutputDTO;
import com.board.domain.UserDTO;

@Service
public interface UserService {

	public UserDTO loginCheck(String id, String pw);

	public UserDTO getUserDetail(String ID);

	public UserDTO findLoginId(String Email);

	public UserDTO findLoginPw(String id,String PwHint);

    public List<SurveyOutputDTO> getUserHistory(String ID);

	public int updateUserDetail(UserDTO params);
	
	public List<UserDTO> getUserList();

	public MailDTO createMailContent(String Email);
	
	public String getTempPassword();
	
	public void newPassword(String str, String id);
	
	public void mailSend(MailDTO mailDTO);
}
