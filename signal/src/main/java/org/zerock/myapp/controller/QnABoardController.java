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
import org.zerock.myapp.domain.QnABoardDTO;
import org.zerock.myapp.domain.QnABoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.QnABoardService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Controller
@RequestMapping("/board")
public class QnABoardController {
	
	@Setter(onMethod_ = @Autowired)
	private QnABoardService service;
	
	// 1. 게시판 목록 조회
	@GetMapping("/qnaList")
	void list(Criteria cri, Model model) throws ControllerException {
		log.trace("list({}, {}) invoked.", cri, model);
		
		try {
		List<QnABoardVO> list = this.service.getList(cri);
		// Request Scope  공유속성 생성
		model.addAttribute("__LIST__", list);
		
		PageDTO pageDTO = new PageDTO(cri, this.service.getTotal());
		model.addAttribute("pageMaker", pageDTO);
		
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // list
	
	
	// 2. 새로운 게시물 등록
	@PostMapping("/qnaWrite")
	String register(QnABoardDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.trace("register({}, {}) invoked.", dto, rttrs);
		
		try {
			Objects.requireNonNull(dto);
			
			if( this.service.register(dto) ) {		
				rttrs.addFlashAttribute("result", "true");
				rttrs.addFlashAttribute("postno", dto.getPostno());
			} // if
			
			return "redirect:/board/qnaList";
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // register
	
	// 단순 등록화면 요청
	@GetMapping("/qnaWrite")
	void register() {
		log.trace("register() invoked.");
		
	} // register
	
	// 3. 특정 게시물 상세조회
    @GetMapping(path={"/qnaView", "/qnaEdit"},  params = "postno")
    void get(@RequestParam Integer postno, Model model
//    		HttpServletRequest req, HttpServletResponse res 
    		) throws  ControllerException {
        log.trace("get() invoked.");

        try{
        	Integer rc = this.service.updateReadcnt(postno);
        	model.addAttribute("_BOARD_", rc);
        	
//// ========= 조회수 중복방지 =================================
//        	
//        	Cookie oldCookie = null;
//        	Cookie[] cookies = req.getCookies();
//        	if(cookies != null) {
//        		for(Cookie cookie : cookies) {
//        			if(cookie.getName().equals("postno")) {
//        				oldCookie = cookie;
//        			} // if
//        		} // enhanced for
//        	} // if
//        	
//        	if(oldCookie != null) {
//        		if(!oldCookie.getValue().contains("[" + postno.toString() + "]")) {
//        			service.get(postno);
//        			oldCookie.setValue(oldCookie.getValue() + "_[" + postno + "]");
//        			oldCookie.setPath("/");
//        			oldCookie.setMaxAge(60 * 60 * 24);
//        			res.addCookie(oldCookie);
//        			log.info(">>>>> check oldCookie");
//        		}
//        	} else {
//        		service.get(postno);
//        		Cookie newCookie = new Cookie("postno", "_[" + postno + "]" );
//        		newCookie.setPath("/");
//        		newCookie.setMaxAge(60 * 60 * 24);
//        		res.addCookie(newCookie);
//        		log.info(">>>>> check newCookie");
//        	} 
        	
// =====================================================        	
            QnABoardVO vo = this.service.get(postno);
            model.addAttribute("__BOARD__", vo);
            
        }catch (Exception e){
            throw new ControllerException(e);
        } // try-catch
    } // get
	
    // 4. 특정 게시물 업데이트(수정화면)
    @PostMapping("/qnaEdit")
    String modify(QnABoardDTO dto, Integer currPage, RedirectAttributes rttrs) throws ControllerException {
    	log.trace("modify({}, {}) invoked.", dto, currPage);
    	
    	try {
			Objects.requireNonNull(dto);
			
			rttrs.addAttribute("currPage", currPage);
			
			if( this.service.modify(dto) ) {		// if success
				rttrs.addFlashAttribute("result", "true");
				rttrs.addFlashAttribute("postno", dto.getPostno());
			} // if
			return "redirect:/board/qnaList";
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
    } // modify
    
    // 5. 특정 게시물 삭제(DELETE)
	@PostMapping("/remove")
	String remove(@RequestParam("postno") Integer postno, Integer currPage, RedirectAttributes rttrs) throws ControllerException {
		log.trace("remove({}, {}) invoked.", postno, currPage);
    
    	try {
    		rttrs.addAttribute("currPage", currPage);
    		
			if( this.service.remove(postno) ) {		// if success
				rttrs.addFlashAttribute("result", "true");
				rttrs.addFlashAttribute("postno", postno);
			} // if
			return "redirect:/board/qnaList";
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // remove
	
} // end class
