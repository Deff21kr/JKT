package org.zerock.myapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.exception.ControllerException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RequestMapping("board/FAQ")
@Controller
public class FaqBoardController {
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
	@PostMapping("/register")
	String register() {
		log.trace("register() invoked");
		
		return "redirect:/board/qnafive";
	} // register

	// 2-1. 게시판 작성
	@PostMapping("/register")
	String register() {
		log.trace("register() invoked");

		return "redirect:/faqboard/list";
	} // register

	// 3. 게시판 상세조회 페이지 (상세조회 화면, 수정화면)
	@GetMapping(path = { "/get", "/modify" })
	void get() {
		log.trace("get() invoked");

	} // get

	// 4. 게시판 업데이트
	@PostMapping("/modify")
	String modify() {
		log.trace("modify() invoked");

		return "redirect:/faqboard/list";
	} // modify

	// 5. 게시판 삭제
	@PostMapping("/remove")
	String remove() {
		log.trace("remove() invoked");

		return "redirect:/faqboard/list";
	} // remove

//		===== 댓글 컨트롤러 =====

	// 7. 댓글 목록 페이지
	@GetMapping("/commentList")
	void commentList() {
		log.trace("commentList() invoked");

	} // commentList

	// 7. 댓글 작성
	@PostMapping("/commentRegister")
	String commentRegister() {
		log.trace("commentRegister() invoked");

		return "redirect:/faqboard/get";
	} // commentRegister

	// 8. 댓글 수정 페이지
	@GetMapping("/commentModify")
	void commentModify() {
		log.trace("commentModify() invoked");

	} // commentModify

	// 8-1. 댓글 수정
	@PostMapping("/commentModify")
	String commentModify() {
		log.trace("commentModify() invoked");

		return "redirect:/faqboard/get";
	} // commentModify

	// 9. 댓글 삭제 페이지
	@GetMapping("/commnetRemove")
	void commnetRemove() {
		log.trace("commentRemove() invoked");

	} // commnetRemove

	// 9-1. 댓글 삭제
	@PostMapping("/commnetRemove")
	String commnetRemove() {
		log.trace("commentRemove() invoked");

		return "redirect:/faqboard/get";
	} // commentRemove

}
