package com.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.CharacterDTO;
import com.board.domain.MailDTO;
import com.board.domain.SurveyOutputDTO;
import com.board.domain.TypeDTO;
import com.board.domain.UserDTO;
import com.board.service.CharacterService;
import com.board.service.SurveyService;
import com.board.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CharacterService characterService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/logoutProc")
	public String logout(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("msgLogout", "로그아웃되었습니다.");
		return "redirect:/main";
	}

	@GetMapping("/findId")
	public String findId() {
		return "findId";
	}

	@GetMapping("/findPw")
	public String findPw() {
		return "findPw";
	}

	/**
	 * 
	 * @param id
	 * @param pw
	 * @param model
	 * @return
	 */
	@PostMapping("/login_proc")
	public String loginProcess(RedirectAttributes redirectAttributes, HttpServletRequest request, UserDTO params,
			Model model) {
		HttpSession session = request.getSession(true);
		String myID = params.getId();
		String myPW = params.getPw();
		UserDTO user = userService.loginCheck(myID, myPW);
		System.out.println("!!!!!!!" + user);
		if (user == null) {
			redirectAttributes.addFlashAttribute("msgLoginError", "아이디 혹은 비밀번호 오류");
		} else {
			redirectAttributes.addFlashAttribute("msgLoginSuccess", "로그인되었습니다. " + myID + "님, 환영합니다!");
			session.setAttribute("user", user);
		}
		return "redirect:/main";
	}


	@GetMapping(value = "/main")
	public String openMainpage(HttpSession session, Model model) {
		return "main";
	}

	@GetMapping("/identify")
	public String identify(HttpSession session, Model model) {
		return "identify";
	}

	@GetMapping(value = "/mypage")
	public String openMypage(HttpSession session, Model model) {

		UserDTO user = (UserDTO) session.getAttribute("user");
		
		List<SurveyOutputDTO> testList = userService.getUserHistory(user.getId());
		int listCount = testList.size();
		model.addAttribute("testList", testList);
		model.addAttribute("listCount", listCount);
		
		List<CharacterDTO> charList = new ArrayList<>();
		for(int i=0; i<listCount; i++) {
			List<CharacterDTO> tempList = characterService.getCharacterList(testList.get(i).getUsertype());
			CharacterDTO character = tempList.get(tempList.size() - 1);
			charList.add(character);
		}
		model.addAttribute("charList", charList);
		
		return "mypage";
	}

	@GetMapping(value = "/managerpage")
	public String openManagerpage(HttpSession session, Model model) {
		
		List<UserDTO> userList = userService.getUserList();
		int listCount = userList.size();
		model.addAttribute("userList", userList);
		model.addAttribute("listCount", listCount);

		return "managerpage";
	}

	@GetMapping("/modify")
	public String modify(HttpSession session, Model model) {
		return "modify";
	}

	/**
	 * 
	 * @param pw
	 * @param model
	 * @return
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/identify_proc")
	public String identifyProcess(HttpServletRequest request, UserDTO params, Model model) {
		HttpSession session = request.getSession(true);
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (!passwordEncoder.matches(params.getPw(), user.getPw())) {
			model.addAttribute("msgIden", "비밀번호가 틀렸습니다.");
			return "identify";
		} else if (user.isManagerYn()) {
			System.out.println("1111");
			return "redirect:/managerpage";
		} else {
			System.out.println("2222");
			return "redirect:/mypage";
		}
	}

	/**
	 * 
	 * @param pw
	 * @param pw2
	 * @param model
	 * @return
	 */
	@PostMapping("/modify_proc")
	public String modifyProcess(HttpSession session, String pw, String pw2, Model model) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (!passwordEncoder.matches(pw, user.getPw())) {
			model.addAttribute("msgMod", "현재 비밀번호가 틀렸습니다.");
			return "modify";

		} else if (pw.equals(pw2)) {
			model.addAttribute("msgMod", "현재 비밀번호와 같은 비밀번호로 변경할 수 없습니다.");
			return "modify";
			
		} else {
			user.setPw(passwordEncoder.encode(pw2));
			userService.updateUserDetail(user);
			model.addAttribute("msgMod", "비밀번호 변경이 완료되었습니다.");
			return "main";
		}
	}

	/**
	 * 
	 * @param email
	 * @param model
	 * @return
	 */
	@PostMapping("/findId_proc")
	public String findUserId(String email, Model model) {
		UserDTO params = userService.findLoginId(email);
		System.out.println(params);
		if (params == null) {
			model.addAttribute("msgFindID", "입력하신 이메일로 가입된 아이디가 없습니다.");
			return "/findId";
		} else {
			System.out.println("********************");
			System.out.println(params.getId());
			model.addAttribute("msgFindID", "입력하신 이메일로 가입된 아이디는 " + params.getId() + "입니다.");
			return "/login";
		}
	}

	/**
	 * 
	 * @param id
	 * @param pwHint
	 * @param model
	 * @return
	 */

	@PostMapping("/findPw_proc")
	public String findUserPw(String id, String pwHint, Model model) {
		UserDTO params = userService.findLoginPw(id, pwHint);
		if (params == null) {
			model.addAttribute("msgFindPW", "정보를 잘못입력하셨습니다.");
			return "/findPw";
		} else {
			MailDTO dto = userService.createMailContent(params.getEmail());
//		userService.mailSend(dto);
			userService.newPassword(dto.getStr(), id);
			model.addAttribute("msgFindPW", "임시 비밀번호는 " + dto.getStr() + " 입니다.");
			return "/login";
		}
	}

}
