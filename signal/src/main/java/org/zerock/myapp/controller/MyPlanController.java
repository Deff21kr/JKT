package org.zerock.myapp.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.MyPlanDTO;
import org.zerock.myapp.domain.MyPlanVO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ControllerException;
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
	void planMain(Criteria cri ,HttpServletRequest req, Model model) throws ControllerException {
		log.info("planMain() invoked");
		
		try {

			HttpSession session = req.getSession();
			UsersVO vo = (UsersVO) session.getAttribute("__AUTH__");
			String nickName = vo.getNickName();
			
			List<MyPlanVO> list = this.service.getList(cri, nickName);
			Objects.requireNonNull(list);
			log.info("\t + list: {}", list);

			model.addAttribute("__MYPLAN__", list);
			
		}catch(NumberFormatException e) {

		} 
		
		catch (Exception e) {
			throw new ControllerException(e);
		}

	} // planMain
	
	// 나의 플래너 만들기 페이지
	@GetMapping("/makePlan")
	void makePlan() {
		log.info("/makePlan() invoked");
		
	} // makePlan
	
	// 나의 플래너 등록
	@PostMapping("/makePlan")
	String makePlan(MyPlanDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.info("/makePlan() invoked");
		
		try {
			
			Objects.requireNonNull(dto);
			Boolean result = this.service.generate(dto);
			
			log.info("\t + result : {}", result);

			return "redirect:/board/myplan/main";
			
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
	} // makePlan
	
	
	@GetMapping("/signalplan2")
	void signalplan2() {
		log.info("/signalplan2() invoked");
		
	} // signalplan2
	
	
	
}
