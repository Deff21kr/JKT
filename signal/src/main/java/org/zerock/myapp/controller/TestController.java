package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {
	
//	@GetMapping(path="/register")
//	String test() {
//		
//		return "register";
//	}
	
	@GetMapping(path="/mypage")
	String test2() {
		
		return "mypage";
	}
	
	@GetMapping(path="/reviewboard")
	String test3() {
		
		return "reviewboard";
	}

}
