package org.zerock.myapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
//@RequestMapping("/sample")
@Controller
public class CommonColtroller {
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth , Model model) {
		log.info("access Denied : {}",auth);
		
		model.addAttribute("msg","Access Denied");
		
	}

}
