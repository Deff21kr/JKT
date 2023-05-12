package org.zerock.myapp.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.domain.QnABoardDTO;
import org.zerock.myapp.domain.QnABoardVO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.LoginService;
import org.zerock.myapp.service.QnABoardService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Controller
@RequestMapping("/board/qna")
public class QnABoardController {
	
	@Setter(onMethod_ = @Autowired)
	private QnABoardService service;
	
	@Autowired
	private LoginService login;
	
	// 1. 게시판 목록 조회
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
	
	// 2. 새로운 게시물 등록
	@PostMapping("/register")
	String register(QnABoardDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.trace("register({}, {}) invoked.", dto, rttrs);
		
		try {
			Objects.requireNonNull(dto);
			if( this.service.register(dto) ) {		
				rttrs.addFlashAttribute("result", "true");
				rttrs.addFlashAttribute("postnNo", dto.getPostNo());
			} // if
			
			return "redirect:/board/qna/list";
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // register
	
	// 단순 등록화면 요청
	@GetMapping("/register")
	void register() {
		log.trace("register() invoked.");
		
	} // register

//	// 3. 특정 게시물 상세조회
//    @GetMapping(path={"/get" ,"modify"},  params = "postNo")
//    String get(@RequestParam Integer postNo, Model model) throws  ControllerException {
//        log.trace("get() invoked.");
//
//        try{
//        	Integer rc = this.service.updateReadcnt(postNo);
//        	model.addAttribute("_BOARD_", rc);
//        	
//            QnABoardVO vo = this.service.get(postNo);
//            model.addAttribute("__BOARD__", vo);
//            
//            return "board/qna/get";
//            
//        }catch (Exception e){
//            throw new ControllerException(e);
//        } // try-catch
//    } // get

	// 3. 특정 게시물 상세조회
    @GetMapping(path={"/get", "/modify"},  params = "postNo")
    void get(@RequestParam Integer postNo, Model model) throws  ControllerException {
        log.trace("get() invoked.");

        try{
        	Integer rc = this.service.updateReadcnt(postNo);
        	model.addAttribute("_BOARD_", rc);
    	
            QnABoardVO vo = this.service.get(postNo);
            model.addAttribute("__BOARD__", vo);
            
        }catch (Exception e){
            throw new ControllerException(e);
        } // try-catch
    } // get
    
//     4. 특정 게시물 업데이트(수정화면)
    @PostMapping("/modify")
    String modify(QnABoardDTO dto, Integer currPage, RedirectAttributes rttrs) throws ControllerException {
    	log.trace("modify({}, {}) invoked.", dto, currPage);
    	
    	try {
			Objects.requireNonNull(dto);
			
			rttrs.addAttribute("currPage", currPage);
			
			if( this.service.modify(dto) ) {		// if success
				rttrs.addFlashAttribute("result", "true");
				rttrs.addFlashAttribute("postNo", dto.getPostNo());
			} // if
			return "redirect:/board/qna/list";
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
    } // modify
    
    // 5. 특정 게시물 삭제(DELETE)
	@PostMapping("/remove")
	String remove(@RequestParam("postNo") Integer postNo, Integer currPage, RedirectAttributes rttrs) throws ControllerException {
		log.trace("remove({}, {}) invoked.", postNo, currPage);
    
    	try {
    		rttrs.addAttribute("currPage", currPage);
    		
			if( this.service.remove(postNo) ) {		// if success
				rttrs.addFlashAttribute("result", "true");
				rttrs.addFlashAttribute("postNo", postNo);
			} // if
			return "redirect:/board/qna/list";
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // remove
	
} // end class
