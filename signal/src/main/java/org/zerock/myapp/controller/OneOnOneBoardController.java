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

@RequestMapping("/board/oneonone")
@Controller
public class OneOnOneBoardController {

	@Setter(onMethod_ = { @Autowired })
	private OneOnOneBoardService service;

	// 1. 게시판 목록 조회
	@GetMapping("/list")
	String list(Criteria cri, Model model) throws ControllerException {
		log.trace("list({}) invoked", model);

		try {
			List<OneOnOneBoardVO> list = this.service.getList(cri);
			model.addAttribute("__1on1_LIST__", list);

			PageDTO pageDTO = new PageDTO(cri, this.service.getTotal());
			model.addAttribute("pageMaker", pageDTO);
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch

		return "/board/oneonone/list";
	} // list

	// 2. 새로운 게시물 등록
	@PostMapping(path = "/register", params = { "title", "content", "nickName" })
	String register(OneOnOneBoardDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.trace("register() invoked");

		try {
			Objects.requireNonNull(dto);
			if (this.service.register(dto)) {
				rttrs.addAttribute("result", "true");
				rttrs.addAttribute("postno", dto.getPostNo());
			} // if

			return "redirect:/board/oneonone/list";

		} catch (Exception e) {
			throw new ControllerException(e);
		}

	} // register

	// 2-1. 새로운 게시물 등록화면  --> 로그인기능이 없어 register.jsp에 임시적으로 하드코딩함 (** 나중에 수정해야함!! **)
	@GetMapping("/register")
	String register() {
		log.trace("register() invoked");
		
		return "/board/oneonone/register";
	} // register
	
	// 3. 특정 게시물 상세조회
	@GetMapping(path = {"/get"}, params = "postNo")
	String get(@RequestParam("postNo") Integer postNo, Model model) throws ControllerException {
		log.trace("get() invoked");

		try {
			OneOnOneBoardVO vo = this.service.get(postNo);
			model.addAttribute("__1on1_BOARD__", vo);

		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch

		return "/board/oneonone/get";
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
				rttrs.addAttribute("postno", dto.getPostNo());
			} // if
			
			return "redirect:/board/oneonone/list";
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch

	} // modify

	
	// 6. 특정 게시물 삭제
	@PostMapping("/remove")
	String remove(Integer currPage, Integer postNo, RedirectAttributes rttrs) throws ControllerException {
		log.trace("remove() invoked");
		
		try {
			rttrs.addAttribute("currPage", currPage);
			
			if(this.service.remove(postNo)) {
				rttrs.addAttribute("result", "true");
				rttrs.addAttribute("postno", postNo);
			} // if
			
			return "redirect:/board/oneonone/list";
				
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // remove
	
	// 7. 답변글 등록 => 수정해야됨
	@PostMapping(path = "/reply", params = {"title", "content", "nickName", "repRoot", "repStep", "repIndent" })
	String reply(OneOnOneBoardDTO dto, RedirectAttributes rttrs)throws ControllerException{
		log.trace("reply() invoked");
		
		try {
			Objects.requireNonNull(dto);
						
			if(this.service.reply(dto)) {
				rttrs.addAttribute("result", "true");
				rttrs.addAttribute("postno", dto.getPostNo());
			}
			return "redirect:/board/oneonone/list";
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
	} // reply
	
	@GetMapping(path="/reply", params = {"postNo"})
	String reply(Integer postNo , Model model) throws ControllerException {
		log.trace("reply() invoked");
		
		try {
			OneOnOneBoardVO vo = this.service.get(postNo);
			model.addAttribute("__1on1_BOARD__", vo);

		} catch (Exception e) {
			throw new ControllerException(e);
		}

		return "/board/oneonone/reply";
	} // reply
	
	
	// 3. 특정 게시물 수정화면
	@GetMapping(path = {"/modify"}, params = "postNo")
	String modify(@RequestParam("postNo") Integer postNo, Model model) throws ControllerException {
		log.trace("modify() invoked");

		try {
			OneOnOneBoardVO vo = this.service.get(postNo);
			model.addAttribute("__1on1_BOARD__", vo);

		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch

		return "/board/oneonone/modify";
	} // get
	
	
} // end class
