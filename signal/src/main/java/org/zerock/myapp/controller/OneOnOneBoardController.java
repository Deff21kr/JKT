package org.zerock.myapp.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.OneOnOneBoardDTO;
import org.zerock.myapp.domain.OneOnOneBoardVO;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.OneOnOneBoardService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("board")
@Controller
public class OneOnOneBoardController {

	@Setter(onMethod_ = { @Autowired })
	private OneOnOneBoardService service;

	// 1. 게시판 목록 조회
	@GetMapping("/oneonone/list")
	String list(Criteria cri, Model model) throws ControllerException {
		log.trace("list({}) invoked", model);

		try {
			List<OneOnOneBoardVO> list = this.service.getList(cri);
			model.addAttribute("__LIST__", list);

			PageDTO pageDtO = new PageDTO(cri, this.service.getTotal());
			model.addAttribute("pageMaker", pageDtO);
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch

		return "/board/oneonoeList";
	} // list

	// 2. 새로운 게시물 등록
	@PostMapping(path = "/register", params = { "title", "content", "nickname" })
	String register(OneOnOneBoardDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.trace("register() invoked");

		try {
			Objects.requireNonNull(dto);
			if (this.service.register(dto)) {
				rttrs.addAttribute("result", "true");
				rttrs.addAttribute("postno", dto.getPostno());
			} // if

			return "redirect:/oneononeboard/list";

		} catch (Exception e) {
			throw new ControllerException(e);
		}

	} // register

	@GetMapping("/register")
	void register() {
		log.trace("register() invoked");
	} // register
	
	// 3. 특정 게시물 상세조회
	@GetMapping(path = {"/get", "/modify"}, params = "postno")
	void get(@RequestParam("postno") Integer postno, Model model) throws ControllerException {
		log.trace("get() invoked");

		try {
			OneOnOneBoardVO vo = this.service.get(postno);
			model.addAttribute("__BOARD__", vo);

		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch

	} // get

	// 4. 특정 게시물 업데이트
	@PostMapping("/modify")
	String modify(OneOnOneBoardDTO dto, Integer currPage, RedirectAttributes rttrs) throws ControllerException {
		log.trace("modify({}, {}) invoked", dto, currPage);

		try {
			Objects.requireNonNull(dto);
			
			rttrs.addAttribute("currPage", currPage);
			
			if (this.service.modify(dto)) {
				rttrs.addAttribute("result", "true");
				rttrs.addAttribute("postno", dto.getPostno());
			} // if
			
			return "redirect:/oneononeboard/list";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch

	} // modify

	
	// 6. 특정 게시물 삭제
	@PostMapping
	String remove(Integer currPage, Integer postno, RedirectAttributes rttrs) throws ControllerException {
		log.trace("remove() invoked");
		
		try {
			rttrs.addAttribute("currPage", currPage);
			
			if(this.service.remove(postno)) {
				rttrs.addAttribute("result", "true");
				rttrs.addAttribute("postno", postno);
			} // if
			
			return "redirect:/OneOnOneBoard/list";
				
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // remove
	
	
} // end class
