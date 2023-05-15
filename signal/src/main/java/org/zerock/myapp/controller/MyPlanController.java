package org.zerock.myapp.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.MyPlanDTO;
import org.zerock.myapp.domain.MyPlanVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.MyPlanService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RequestMapping("board/myplan")
@Controller
public class MyPlanController {
	
	@Setter(onMethod_ = {@Autowired})
	private MyPlanService service;

	@GetMapping("/main")
	void planMain(MyPlanVO vo ,Model model) {
		log.info("planMain() invoked");
		
		Objects.requireNonNull(vo);
		log.info("\t + vo: {}");

//		this.service.
//		model.addAttribute("__myplan__", vo);
	} // planMain
	
	// 나의 플래너 만들기 페이지
	@GetMapping("/makePlan")
	void makePlan() {
		log.info("/makePlan() invoked");
		
	} // makePlan
	
	// 나의 플래너 등록
	@PostMapping("/makePlan")
	String makePlan(MyPlanDTO dto, RedirectAttributes rttrs) throws ServiceException {
		log.info("/makePlan() invoked");
		
		Objects.requireNonNull(dto);
		Boolean result = this.service.generate(dto);
		
		log.info("\t + result : {}", result);
		
		return "redirect:/board/myplan/main";
	} // makePlan
	
	
	
	
}
