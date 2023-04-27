package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TestController {
	
	@GetMapping(path="/transfer")
	String test() {
		
		return "transfer";
	}
	
	@GetMapping(path="/mypage")
	String test2() {
		
		return "mypage";
	}

}
