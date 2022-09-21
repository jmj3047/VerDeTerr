package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.domain.MailDTO;
import com.board.domain.UserDTO;
import com.board.mapper.SignUpMapper;
import com.board.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SignUpServiceImpl implements SignUpService { 

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public SignUpMapper signUpMapper;
	
	@Autowired
	public UserMapper userMapper;

	@Override
	public int signUp(UserDTO params) {
		int userId = signUpMapper.selectUserIdCount(params.getId());
		String userPw = params.getPw();
		if (userPw.length() >= 6 || userPw.length() < 20) {
			System.out.println("비번적절");
			if (userId == 0) {
				// controller에서 받아온 pw를 암호화
				String encodedPassword = passwordEncoder.encode(params.getPw());
				// 암호화된 pw로 pw를 세팅
				params.setPw(encodedPassword);
				return signUpMapper.insertUser(params);
			} else {
				System.out.println("id가 존재함.");
				return 0;
			}
		}
		System.out.println("비번 부적절");
		return 0;

	}

	@Override
	public int checkId(String id) {
		int result = signUpMapper.selectUserIdCount(id);
		if(result==0) {
			System.out.println("중복된 아이디 없음");
			return 0;
		}else {
			System.out.println("중복된 아이디 있음");
			return result;
		}
		
	}
	
	@Override
	public int checkEmail(String email) {
		int result = signUpMapper.selectUserEmailCount(email);
		if(result==0) {
			System.out.println("중복된 이메일 없음1111111");
		return 0;
		}else {
			System.out.println("중복된 이메일 있음111111");
			return result;
		}
	}

	@Override
	public int delete(String id) {
		System.out.println("사인서비스딜리트 잘됨?");
		int result = userMapper.deleteUser(id);
		System.out.println("나 result임"+result);
		return result;
	}

    @Override
    public MailDTO createMailContent(String Email) {
        MailDTO dto = new MailDTO();
        dto.setAddress(Email);
        dto.setTitle("VerDeTerr 가입을 환영합니다.");
        dto.setMessage("환영합니다. VerDeTerr 가입이 완료되었습니다.");
        return dto;
    }
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    // 메일 보내기
    @Override
    public void mailSend(MailDTO mailDTO) {
        System.out.println("전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getAddress());
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());
        message.setFrom("verdeterr123@naver.com");
        message.setReplyTo("verdeterr123@naver.com");
        System.out.println("message"+message);
        javaMailSender.send(message);
    }

}
