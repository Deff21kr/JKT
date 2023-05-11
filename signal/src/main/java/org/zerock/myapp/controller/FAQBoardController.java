package org.zerock.myapp.controller;

import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.FAQBoardDTO;
import org.zerock.myapp.exception.ControllerException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RequestMapping("board/FAQ")
@Controller
public class FAQBoardController {
	/*
	 * 각 게시판은 CRUD 가 모두 가능 등록하기 위해 글을 쓰고 수정을 하고 특정 글을 조회하고 모든 글을 조회하고 삭제도 가능! FAQ는
	 * 하는사람이 알아서 생각바람
	 * 
	 * 추가적으로 페이지네이션과 댓글도
	 */

	// 1. 게시판 목록 조회 페이지
	@GetMapping("/list")
	void list(Criteria cri, Model model) throws ControllerException {
//		log.trace("list({} , {}) invoked",cri,model);
//		try {
//			List<FAQVO> list = this.service.getList(cri);
//			model.addAttribute("__LIST__", list);
//			
//			PageDTO pageDTO = new PageDTO(cri, this.service.getTotal());
//			model.addAttribute("pageMaker", pageDTO);
//		} catch(Exception e) {
//			throw new ControllerException(e);
//		} // try-catch
	} // list

	
	// 2. 게시판 작성 페이지
	@GetMapping("/register")
	String register() {
		log.trace("register() invoked");
		
		return "redirect:/board/FAQ";
	} // register

	// 2-1. 게시판 작성
	@PostMapping("/register")
	String register(FAQBoardDTO dto ,RedirectAttributes rttrs ) {
		log.trace("register() invoked");

		return "redirect:/board/FAQ";
	} // register

	
	// 3. 특정 게시물 상세조회
	@GetMapping(path={ "/get", "/modify" }, params = "postNo")
	void get(@RequestParam("postNo") Integer postNo, Model model)
			throws ControllerException {
		log.trace("get() invoked.");
		
		try {
//			FAQBoardVO vo = this.service.get(postNo);
			
//			model.addAttribute("__BOARD__", vo);
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // get

	// 4. 게시판 업데이트
	@PostMapping("/modify")
	String modify(FAQBoardDTO dto, Integer currPage, RedirectAttributes rttrs) throws ControllerException {
		log.trace("modify({}) invoked",dto);
		
		try {
			Objects.requireNonNull(dto);
			
//			if(this.service.modify(dto)) { // if success
//				rttrs.addAttribute("currPage", currPage);
//				rttrs.addAttribute("result", "true");
//				rttrs.addAttribute("bno", dto.getBno());
//			} // if
			
			return "redirect:/board/FAQ";
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // modify

	// 5. 게시판 삭제
	@PostMapping("/remove")
	String remove(Integer currPage, Integer postNo, RedirectAttributes rttrs) {
		log.trace("remove() invoked");
		
//
//		try {
//			rttrs.addAttribute("currPage", currPage);
//			
//			if(this.service.remove(bno)) { // if success
//				
//				rttrs.addAttribute("result", "true");
//				rttrs.addAttribute("bno", bno);
//				
//			} // if
//			
//			return "redirect:/board/list";
//		} catch(Exception e) {
//			throw new ControllerException(e);
//		} // try-catch

		return "redirect:/board/FAQ";
	} // remove


}
