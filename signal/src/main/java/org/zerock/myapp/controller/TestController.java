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
	
	@GetMapping(path="/mainpage")
	String test2() {
		
		return "mainpage";
	}
	
	@GetMapping(path="/mypage")
	String test3() {
		
		return "mypage";
	}
	
	@GetMapping(path="/transfer")
	String test4() {
		
		return "transfer";
	}

}
