package com.board.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.http.HttpSession;

//import org.python.core.io.BufferedReader;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.SurveyDTO;
import com.board.domain.UserDTO;
import com.board.service.SurveyService;

@Controller
public class SurveyController {

	@Autowired
	private SurveyService surveyService;

	@GetMapping(value = "/survey/surveylist")
	public String openSurveyWrite(RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user == null) {
			redirectAttributes.addFlashAttribute("surveyError", "로그인 후 사용 가능합니다.");
			return "redirect:/main";
		} else {
			SurveyDTO survey = surveyService.getSurveyResult(user.getId());
			System.out.println("survey : " + survey);
			if (survey == null) {
				survey = new SurveyDTO(user.getId());
			}
			model.addAttribute("survey", survey);
			model.addAttribute("type", user.getUserType());
			if(user.getUserType()!=null) {
				model.addAttribute("myChar",  user.getUserCharacter());
			}
			return "survey/surveylist";
		}

	}
	
	private static PythonInterpreter intPre;

	@Async("executor")
	@PostMapping(value = "/survey/surveydone")
	public String registerSurvey(final SurveyDTO params, Model model, HttpSession session) {

		try {
			System.out.println("Controller surveyresult 시작");
			boolean isRegistered = surveyService.registerSurvey(params);
			System.out.println("Controller isRegistered 받아옴");
			System.out.println("Controller isRegistered params: " + params);
			if (isRegistered == false) {
				System.out.println("정답등록실패");
			}
			System.out.println(params.getId());
			String convertID = params.getId();

			System.setProperty("python.import.site", "false");
			PythonInterpreter intPre = new PythonInterpreter();

			System.out.println(convertID.getClass().getName());

			String t = "src/main/python/python_batch.bat".concat(" " + convertID);

			Process p = Runtime.getRuntime().exec(t);
			System.out.println("python finished");

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;

			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

			intPre.close();
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 처리과정 문제");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("시스템 문제 발생");
		}

		return "survey/surveyresult";
	}

}
