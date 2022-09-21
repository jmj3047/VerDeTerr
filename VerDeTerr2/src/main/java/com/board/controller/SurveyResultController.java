package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.board.domain.CharacterDTO;
import com.board.domain.SurveyOutputDTO;
import com.board.domain.TypeDTO;
import com.board.domain.UserDTO;
import com.board.service.CharacterService;
import com.board.service.SurveyService;
import com.board.service.UserService;

@Controller
public class SurveyResultController {

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private UserService userService;
		
	@GetMapping(value = "/survey/surveyresult")
	public String getSurveyList(Model model, HttpSession session) {

		UserDTO user = (UserDTO) session.getAttribute("user");
		SurveyOutputDTO surveyList = surveyService.getSurveyList(user.getId());
		System.out.println("***********" + surveyList);
		model.addAttribute("surveyList", surveyList);

		if(user!=null) {
			
			userService.updateUserDetail(user);
			user = userService.getUserDetail(user.getId());
			session.setAttribute("user", user);
			System.out.println("***********" + user);
			
			TypeDTO typeInfo = surveyService.getTypeInfo(user.getUserType());
			System.out.println("***********" + typeInfo);
			model.addAttribute("typeInfo", typeInfo);
			
		}
		return "survey/surveyresult";
	}	
}
