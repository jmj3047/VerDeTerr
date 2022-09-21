package com.board.util;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.constant.Method;

@Controller
public class UiUtils {
//리다이렉트.=> 사용자가 어떠한 url을 요청하였을때 , 프로그램이 다른 url을 제시해주는것.
	//@RequstParam =>  아래의 함수에서 받아야 하는 ,  파라미터들.  
	public String showMessageWithRedirect(@RequestParam(value = "message", required = false) String message,
										  @RequestParam(value = "redirectUri", required = false) String redirectUri,
										  @RequestParam(value = "method", required = false) Method method,
										  @RequestParam(value = "params", required = false) Map<String, Object> params,
										  Model model) {
 // model.addAttribute 는 백단에 있는 데이터를 화면에 보낼때,  담아놓는 객체에 추가해주는것을 의미한다. 
		model.addAttribute("message", message);
		model.addAttribute("redirectUri", redirectUri);
		model.addAttribute("method", method);
		model.addAttribute("params", params);

		return "utils/message-redirect";
	}

}