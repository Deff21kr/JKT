package org.zerock.myapp.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.CommentCriteria;
import org.zerock.myapp.domain.CommentPageDTO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.domain.QnABoardDTO;
import org.zerock.myapp.domain.QnABoardVO;
import org.zerock.myapp.domain.QnACommentDTO;
import org.zerock.myapp.domain.QnACommentVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.QnABoardService;
import org.zerock.myapp.service.QnACommentService;

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
	
	@Setter(onMethod_ = @Autowired)
	private QnACommentService commentService;
	
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

//	 3. 특정 게시물 상세조회
    @GetMapping(path={"/get*", "/modify*"},  params = "postNo")
    void get(@RequestParam Integer postNo, Model model, Criteria cri, Integer currPage, RedirectAttributes rttrs, CommentCriteria commentCri) throws  ControllerException {
        log.trace("get() invoked.");

        try{
        	Integer rc = this.service.updateReadcnt(postNo);
        	model.addAttribute("_BOARD_", rc);
    	
            QnABoardVO vo = this.service.get(postNo);
            model.addAttribute("__BOARD__", vo);
            
            List<QnACommentVO> commentList = this.commentService.selectList(commentCri, postNo);
            model.addAttribute("__COMMENT_LIST__", commentList);
            log.info("\t+ 댓글 조회된다아아아아");
            
            CommentPageDTO pageDTO = new CommentPageDTO(this.commentService.getCommentTotal(postNo), commentCri);
    		model.addAttribute("__commentPage__", pageDTO);
    		rttrs.addAttribute("currPage", currPage);
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
    
//    @GetMapping(path={"/modify"},  params = "postNo")
//    void get(@RequestParam Integer postNo, Model model) throws  ControllerException {
//        log.trace("get() invoked.");
//
//        try{
//        	Integer rc = this.service.updateReadcnt(postNo);
//        	model.addAttribute("_BOARD_", rc);
//    	
//            QnABoardVO vo = this.service.get(postNo);
//            model.addAttribute("__BOARD__", vo);
//            
//        }catch (Exception e){
//            throw new ControllerException(e);
//        } // try-catch
//    } // modify
    
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
	
	// ----------- 댓글 C/U/D ----------
	
//	 @PostMapping(path={"/get*", "/reply"},  params = "postNo")
//	 void readReply(Model model, RedirectAttributes rttrs, Criteria cri) throws ServiceException {
//		   List<QnACommentVO> commentList = this.commentService.getList(cri);
//           model.addAttribute("__COMMENT_LIST__", commentList);
//           log.info("\t+ 댓글 조회된다아아아아");
//           
//           PageDTO pageDTO = new PageDTO(cri, this.service.getTotal());
//   			model.addAttribute("pageMaker", pageDTO);
//	}
	 
	// 댓글 등록
//	@RequestMapping(value = "/qnaReply", method= {RequestMethod.POST})
	@PostMapping("/qnaReply")
	String insert(
			@ModelAttribute QnACommentDTO dto,
			@ModelAttribute CommentCriteria commentCri, 
			Criteria cri, 
			RedirectAttributes rttrs,
			@RequestParam("currPage") Integer currPage) 
					throws ControllerException {
	    log.trace("addComment({}) invoked.", dto);
	    try {
	    	rttrs.addAttribute("currPage", currPage);
	    	this.commentService.insert(dto);
	        rttrs.addAttribute("postNo", dto.getPostNo());
	        rttrs.addAttribute("commentCurrPage", commentCri.getCommentCurrPage());
	        return "redirect:/board/qna/get?";
	    } catch (Exception e) {
	        throw new ControllerException(e);
	    }
	} // addComment


//			@GetMapping("/qnaReply")
//			String Getinsert(@ModelAttribute QnACommentDTO dto, Criteria cri,RedirectAttributes rttrs) throws ControllerException {
//			    log.trace("addComment({}) invoked.", dto);
//			    try {
//			    	commentService.insert(dto);
//			        rttrs.addAttribute("postNo", dto.getPostNo());
//			        return "redirect:/board/qna/get?currPage="+cri.getCurrPage();
//			    } catch (Exception e) {
//			        throw new ControllerException(e);
//			    }
//			} // addComment
			
			
			// 댓글 수정
			@PostMapping("/edit")
			String editComment(QnACommentDTO dto, RedirectAttributes rttrs, Criteria cri) throws ControllerException {
				log.trace("editComment({}) invoked.", dto);
				
				try {
					this.commentService.update(dto);
					rttrs.addAttribute("postNo", dto.getPostNo());
					return "redirect:/board/qna/get?currPage="+cri.getCurrPage();
				} catch (Exception e) {
					throw new ControllerException(e);
				}
			} // editComment
			
			// 댓글 삭제
			@PostMapping("/delete")
			String deleteComment(@RequestParam(value = "commentNo", required=false) Integer commentNo, Integer postNo,RedirectAttributes rttrs) throws ControllerException {
				log.trace("deleteComment({}) invoked.", commentNo);
				
				try {
					this.commentService.delete(commentNo);
					rttrs.addAttribute("postNo", postNo);
					return "redirect:/board/qna/get";
				} catch (Exception e) {
					throw new ControllerException(e);
				}
			} // deleteComment
} // end class
