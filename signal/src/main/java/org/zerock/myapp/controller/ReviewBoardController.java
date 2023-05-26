package org.zerock.myapp.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.AttachFileDTO;
import org.zerock.myapp.domain.ReviewBoardDTO;
import org.zerock.myapp.domain.ReviewBoardVO;
import org.zerock.myapp.domain.ReviewPageDTO;
import org.zerock.myapp.domain.ReviewBoardCriteria;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.ReviewBoardService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RequestMapping("/board/review")
@Controller
public class ReviewBoardController {

	@Setter(onMethod_ = @Autowired )
	private ReviewBoardService service;

	// 1. 게시판 목록 조회
	@GetMapping(path = "/list")
	String list(ReviewBoardCriteria cri, Model model) throws ControllerException {
		log.trace("list({}) invoked.", model);

		try {
			
			List<ReviewBoardVO> list = this.service.getList(cri);

			// Request Scope에 공유속성 생성
			model.addAttribute("__LIST__", list);

			ReviewPageDTO pageDTO = new ReviewPageDTO(cri, this.service.getTotal());
			model.addAttribute("pageMaker", pageDTO);
			
			return "/board/review/list";

		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	}// list

	
	// 2. 게시물 등록
	@PostMapping(path = "/register", params = { "nickname", "title", "content" })
	String register(ReviewBoardDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.trace("register({}) invoked.", dto);

		try {
			Objects.requireNonNull(dto);

			log.info("==========================================================");
			log.info("\t+ register: {}",dto);
			log.info("==========================================================");

			
			// 첨부파일이 있을시
			if (dto.getAttachList() != null) {
				dto.getAttachList().forEach(attach -> log.info(attach));
				//AttachFileDTO에서 수집한 정보 출력
				
				if (this.service.registerWithFile(dto)) {
					rttrs.addAttribute("result", "true");
					rttrs.addAttribute("postNo", dto.getPostNo());
				} // if

				return "redirect:/board/review/list";

			} else {	//첨부파일이 없을 시
				log.info("첨부파일을 추가하지 않았습니다.");
				
				return "redirect:/board/review/register";
			} // if-else

		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // register


	@GetMapping(path = { "/register" })
	void register() { // 단순 등록화면 요청
		log.trace("register() invoked");
	} // register

	
	
	// 3. 게시물 상세 조회
	@GetMapping(path = { "/get", "/modify" }, params = "postNo")
	void get(Integer postNo, Model model) throws ControllerException {
		log.trace("get() invoked.");

		try {
			ReviewBoardVO vo = this.service.get(postNo);
			log.info("vo:{}", vo);

			// Request Scope에 공유속성 생성
			model.addAttribute("__REVIEW__", vo);

		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	}// Get
	
	
	// 3-2. 첨부파일 상세 조회
	@GetMapping(value="/getAttachList", produces = "application/json")
	@ResponseBody
	ResponseEntity<List<AttachFileDTO>> getAttachList(Integer postNo) throws ControllerException {
		log.trace("get() invoked.");

		try {
			
			log.info("getAttachList:{}", postNo);

			return new ResponseEntity<>(service.getAttachList(postNo),HttpStatus.OK);

		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	}// getAttachList
	
	
	
	

	// 4. 게시물 수정(갱신)
	@PostMapping("/modify")
//	String modify(ReviewBoardDTO dto, RedirectAttributes rttrs)
	String modify(ReviewBoardDTO dto, Integer currPage, RedirectAttributes rttrs)
			throws ControllerException {
		log.trace("modify({}) invoked.", dto);

		try {
			Objects.requireNonNull(dto);

			if (this.service.modify(dto)) {
				rttrs.addAttribute("currPage", currPage);
				rttrs.addAttribute("result", "true");
				rttrs.addAttribute("postNo", dto.getPostNo());
			} // if

			return "redirect:/board/review/list";

		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // modify

	// 게시물 삭제
	@PostMapping("/remove")
	String remove(Integer currPage, Integer postNo, RedirectAttributes rttrs)
			throws ControllerException {
		log.trace("remove({}, {}) invoked.", currPage, postNo);
		
		try {
//			 rttrs.addAttribute("currPage", currPage);

			if (this.service.remove(postNo)) {
				rttrs.addAttribute("currPage", currPage);
				rttrs.addAttribute("result", "true");
				rttrs.addAttribute("postNo", postNo);
			} // if
			
			return "redirect:/board/review/list";
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // remove

}// end class
