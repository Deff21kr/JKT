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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.QnABoardDTO;
import org.zerock.myapp.domain.QnABoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.domain.QNACommentDTO;
import org.zerock.myapp.domain.QNACommentVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.QNACommentService;
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
	
	@Setter(onMethod_ = @Autowired)
	private QNACommentService commentService;
	
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
		
		List<QNACommentVO> commentList = this.commentService.getList();
        model.addAttribute("__COMMENT_LIST__", commentList);
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
				rttrs.addFlashAttribute("postNo", dto.getPostNo());
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
    @GetMapping(path={"/qnaView", "/qnaEdit"},  params = "postNo")
    void get(@RequestParam Integer postNo, Model model) throws  ControllerException {
        log.trace("get() invoked.");

        try{
            QnABoardVO vo = this.service.get(postNo);
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
				rttrs.addFlashAttribute("postNo", dto.getPostNo());
			} // if
			return "redirect:/board/qnaList";
			
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
			return "redirect:/board/qnaList";
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // remove
	
//	-------------- 댓글 C/R/U/D ------------------------
	
//	// 댓글 목록 조회
//		@ResponseBody
//		@GetMapping("/comments")
//		List<QNACommentVO> comments(@RequestParam("postNo") Integer postno, Model model) throws ControllerException {
//		    log.trace("comments({}) invoked.", postno);
//	    
//	    try {
//	        List<QNACommentVO> commentList = this.commentService.getList(postno);
//	        model.addAttribute("__COMMENT_LIST__", commentList);
//	        return commentList;
//	    } catch (Exception e) {
//	        throw new ControllerException(e);
//				
//			}
//		} // comments
		
		// 댓글 등록
		@PostMapping("/comments/insert")
		String addComment(QNACommentDTO dto, RedirectAttributes rttrs) throws ControllerException {
		    log.trace("addComment({}) invoked.", dto);
		    
		    try {
		    	dto.setNickName("화가 줜나난 홍기서");
//		    	dto.setPostNo(304);
		        commentService.insert(dto);
		        rttrs.addAttribute("postNo", dto.getPostNo());
		        return "redirect:/board/qnaView";
		    } catch (Exception e) {
		        throw new ControllerException(e);
		    }
		} // addComment

		
		// 댓글 수정
		@PostMapping("/comments/edit")
		String editComment(QNACommentDTO dto, RedirectAttributes rttrs) throws ControllerException {
			log.trace("editComment({}) invoked.", dto);
			
			try {
				commentService.update(dto);
				rttrs.addAttribute("postNo", dto.getPostNo());
				return "redirect:/board/comments";
			} catch (Exception e) {
				throw new ControllerException(e);
			}
		} // editComment
		
		// 댓글 삭제
		@PostMapping("/comments/delete")
		String deleteComment(@RequestParam("commentNo") Integer commentNo, @RequestParam("postNo") Integer postNo, RedirectAttributes rttrs) throws ControllerException {
			log.trace("deleteComment({}) invoked.", commentNo);
			
			try {
				commentService.deleteComment(commentNo);
				rttrs.addAttribute("postNo", postNo);
				return "redirect:/board/qnaView";
			} catch (Exception e) {
				throw new ControllerException(e);
			}
		} // deleteComment
	
	
	
} // end class