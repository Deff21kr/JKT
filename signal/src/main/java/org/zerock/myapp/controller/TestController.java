package org.zerock.myapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.domain.QnABoardVO;
import org.zerock.myapp.exception.ControllerException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
@NoArgsConstructor
public class TestController {
	
//	@GetMapping(path="/register")
//	String test() {
//		
//		return "register";
//	}
	@GetMapping("/list")
	String list(Criteria cri, Model model) throws ControllerException {
		log.trace("list({}, {}) invoked.", cri, model);
		
		try {
		List<QnABoardVO> list = this.service.getList(cri);
		// Request Scope  공유속성 생성
		model.addAttribute("__LIST__", list);
		
		PageDTO pageDTO = new PageDTO(cri, this.service.getTotal());
		model.addAttribute("pageMaker", pageDTO);
		
		return "board/qna/list";
		
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // list
	
	@GetMapping(path="/user/mygroup")
	String test2() {
		
		return "user/mygroup";
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
