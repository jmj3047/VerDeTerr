package com.board.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.MailDTO;
import com.board.domain.UserDTO;
import com.board.service.SignUpService;
import com.board.service.UserService;

@Controller
public class SignUpController {

	@Autowired
	private SignUpService signUpService;

	@Autowired
	private UserService userService;

	@GetMapping("/signup")
	public String signUp() {
		return "signup";
	}

	@PostMapping("/signup_proc")
	public String signUpProcess(HttpServletRequest request, UserDTO params, Model model) {
		HttpSession session = request.getSession(true);
		System.out.println("컨트롤러에 넘어온 파람스 : " + params);
		String myID = params.getId();
		String myPW = params.getPw();
		String myHint = params.getPwHint();
		int result = 0;
		if (myID != "" && myPW != "" && myHint != "") {
			result = signUpService.signUp(params);
		}

		if (result == 1) {
			UserDTO user = userService.getUserDetail(myID);
			session.setAttribute("user", user);
			model.addAttribute("msgSignupSuccess", "회원가입 되었습니다. " + myID + "님, 환영합니다!");
			
			/*
			 * MailDTO dto = signUpService.createMailContent(params.getEmail());
			 * signUpService.mailSend(dto);
			 */
	
		} else {
			model.addAttribute("msgSignupError", "회원가입 오류");
			return "signup";
		}
		return "main";
	}

	@GetMapping("/checkId")
	public String checkId() {
		return "checkId";
	}

	@GetMapping("/checkId_proc")
	public String checkIdProcess(@RequestParam String id, Model model) {
		model.addAttribute("idInput", id);
		int result = signUpService.checkId(id);
		if (result == 0) {
			System.out.println("중복된 아이디 없음");
			model.addAttribute("msgCheckId", "사용 가능합니다.");
		} else {
			System.out.println("중복된 아이디 있음");
			model.addAttribute("msgCheckId", "이미 사용중입니다.");
		}
		return "checkId";
	}

	@GetMapping("/checkEmail")
	public String checkEmail() {
		return "checkEmail";
	}

	@GetMapping("/checkEmail_proc")
	public String checkEmailProcess(@RequestParam String email, Model model) {
		System.out.println("체크이메일프로세스");
		String regx = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";
		Pattern pattern = Pattern.compile(regx);
		model.addAttribute("emailInput", email);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			System.out.println("체크이메일정규식");
			int result = signUpService.checkEmail(email);
			if (result == 0) {
				System.out.println("중복된 이메일 없음22222" + result);
				model.addAttribute("msgCheckEmail", "사용 가능합니다.");
			} else {
				System.out.println("중복된 이메일 있음22222" + result);
				model.addAttribute("msgCheckEmail", "이미 사용중입니다.");
				return "/checkEmail";
			}
		} else {
			System.out.println("이메일형식이 맞지 않음222222");
			model.addAttribute("msgCheckEmail", "이메일 형식에 맞지 않습니다.");
		}
		return "checkEmail";
	}

	@GetMapping("/deleteUser")
	public String deleteUser() {

		return "deleteUser";
	}

	@PostMapping("/deleteUser_proc")
	public String deleteUserProcess(HttpServletRequest request, UserDTO params, Model model) {
		HttpSession session = request.getSession(true);
		String myID = params.getId();
		String myPW = params.getPw();
		UserDTO user = userService.loginCheck(myID, myPW);
		System.out.println("탈퇴창 열림");
		if (user == null) {
			System.out.println("탈퇴정보잘못넣음");
			model.addAttribute("msgDeleteUser", "아이디 혹은 비밀번호 오류");
			return "deleteUser";

		} else {
			System.out.println("탈퇴정보잘넣음");
			int deleteUser = signUpService.delete(myID);
			System.out.println("탈퇴됨." + deleteUser);
			session.removeAttribute("user");
			model.addAttribute("msgDeleteUser", myID + "님. 그 동안 저희 서비스를 이용해주셔서 감사합니다.");
			return "main";
		}

	}

}
