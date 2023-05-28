package org.zerock.myapp.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.DetailPlanDTO;
import org.zerock.myapp.domain.DetailPlanVO;
import org.zerock.myapp.domain.JoinMyPlanDTO;
import org.zerock.myapp.domain.MyPlanDTO;
import org.zerock.myapp.domain.MyPlanVO;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ControllerException;
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
	void planMain(Criteria cri, HttpServletRequest req, Model model) throws ControllerException {
		log.info("planMain() invoked");
		
		try {

			HttpSession session = req.getSession();
			UsersVO vo = (UsersVO) session.getAttribute("__AUTH__");
			String nickName = vo.getNickName();
			
			List<MyPlanVO> list = this.service.getList(cri, nickName);
			Objects.requireNonNull(list);
			log.info("\t + list: {}", list);
			model.addAttribute("__MYPLAN__", list);
			
			List<JoinMyPlanDTO> joinList = this.service.joinList(nickName);
			model.addAttribute("__JOINLIST__" ,joinList);
			
			PageDTO pageDTO = new PageDTO(cri, this.service.getTotal());
			model.addAttribute("pageMaker", pageDTO);
		
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch

	} // planMain
	
	// 나의 플래너 만들기 페이지
	@GetMapping("/makePlan")
	void makePlan() {
		log.info("/makePlan() invoked");
		
	} // makePlan
	
	// 나의 플래너 등록
	@PostMapping("/makePlan")
	String makePlan(MyPlanDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.trace("makePlan({}, {}) invoked", dto, rttrs);
		
		try {
			
			Objects.requireNonNull(dto);
			Boolean result = this.service.generate(dto);
			
			log.info("\t + result : {}", result);

			return "redirect:/board/myplan/main";
			
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
	} // makePlan
	
	// 플래너 삭제
	@PostMapping("/deletePlan")
	String deletePlan(Integer planNo, RedirectAttributes rttrs) throws ControllerException {
		log.trace("deletePlan({}) invoked", planNo);
		
		try {
			Boolean result = this.service.remove(planNo);
			rttrs.addAttribute("result", result);
			
			return "redirect:/board/myplan/main";
		} catch (Exception e) {
			throw new ControllerException(e);   
		} // try-catch
	} // deletePlan

	// 플래너 수정
	@PostMapping("/modifyPlan")
	String modifyPlan(String planName ,Integer planNo, RedirectAttributes rttrs) throws ControllerException {
		log.trace("deletePlan({}, {}) invoked",planName, planNo);
		
		try {
			Boolean result = this.service.modify(planName, planNo);
			rttrs.addAttribute("planNo", planNo);
			rttrs.addAttribute("result", result);
			
			return "redirect:/board/myplan/main";
		} catch (Exception e) {
			throw new ControllerException(e);   
		} // try-catch
	} // deletePlan
	
	// 플래너 페이지
	@GetMapping("/get")
	void get(@RequestParam Integer planNo, Model model) throws ControllerException {
		log.info("get({}) invoked", planNo);
	
		try {
			List<DetailPlanVO> detailList = this.service.getDetailPlanList(planNo);
			
			Objects.requireNonNull(detailList);
			log.info("\t + detailList : {}", detailList);
			
			model.addAttribute("__DETAILPLAN__" ,detailList);
			
			MyPlanVO vo = this.service.get(planNo);
			Objects.requireNonNull(vo);
			
			model.addAttribute("__MYPLAN__" ,vo);
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // get
	
	@GetMapping("/modifyPlan")
	void modifyPlan(Integer planNo, Model model) throws ControllerException {
		log.trace("modifyPlan() invoked");
		
		try {
			MyPlanVO vo = this.service.get(planNo);
			Objects.requireNonNull(vo);
			
			model.addAttribute("__PLAN__", vo);
			
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
	} // modifyPlan
	
	// 상세계획 플래너 페이지
	@GetMapping("/register")
	void register() throws ControllerException {

	} // register
	
	
	// 상세계획 플래너 등록
	@PostMapping("/register")
	String register(DetailPlanDTO dto ,RedirectAttributes rttrs) throws ControllerException {
		log.trace("register({}) invoked", dto);
		
		try {
			Objects.requireNonNull(dto);
			Boolean result = this.service.registerDetailPlan(dto);
			log.info("result : {}", result);
			
			rttrs.addAttribute("planNo", dto.getPlanNo());
			
			return "redirect:/board/myplan/get";
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // register
		
	} // register
	
	// 장소 검색 페이지
	@GetMapping("/placeSerch")
	void placeSerch() {
		log.trace("placeSerch() invoked");
		
	} // placeSerch

	// 글 수정페이지
	@GetMapping("/modify")
	void modify(@RequestParam Integer detailPlanNo ,Model model) throws ControllerException {
		log.trace("modify() invoked");
		
		try {
			
			DetailPlanVO vo = this.service.getDetailPlan(detailPlanNo);
			model.addAttribute("__DETAILPLAN__" ,vo);
			
		} catch (Exception e) {
			throw new ControllerException(e);
		}

	} // modify
	
	// 글 수정
	@PostMapping("/modify")
	String modify(DetailPlanDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.trace("modify() invoked");
		
		 try {
			Boolean result = this.service.modifyDetailPlan(dto);
			rttrs.addAttribute("planNo", dto.getPlanNo());
			rttrs.addAttribute("result", result);

			
			return "redirect:/board/myplan/get";
			
		} catch (Exception e) {
			throw new ControllerException(e);
		}
	} // modify
	
	// 글 삭제
	@PostMapping("/remove")
	String remove(Integer planNo, Integer detailPlanNo, RedirectAttributes rttrs) throws ControllerException {
		log.trace("remove({}) invoked", detailPlanNo);
		
		try {
			
			Boolean result = this.service.removeDetailPlan(detailPlanNo);
			rttrs.addAttribute("planNo", planNo);
			rttrs.addAttribute("result", result);

			return "redirect:/board/myplan/get";

		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
	} // remove
	
	
}
