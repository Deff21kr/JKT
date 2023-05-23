package org.zerock.myapp.controller;

import java.util.List;
import java.util.Objects;

import org.apache.ibatis.annotations.Param;
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
import org.zerock.myapp.domain.GroupBoardCriteria;
import org.zerock.myapp.domain.GroupBoardDTO;
import org.zerock.myapp.domain.GroupBoardPageDTO;
import org.zerock.myapp.domain.GroupBoardVO;
import org.zerock.myapp.domain.GroupsDTO;
import org.zerock.myapp.domain.QnACommentDTO;
import org.zerock.myapp.domain.QnACommentVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.GroupBoardService;
import org.zerock.myapp.service.GroupService;
import org.zerock.myapp.service.QnABoardService;
import org.zerock.myapp.service.QnACommentService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Controller
@RequestMapping("/board/group")
public class GroupBoardController {
	
	@Setter(onMethod_ = @Autowired)
	private GroupBoardService service;
	@Setter(onMethod_ = @Autowired)
	private GroupService group;
	@Setter(onMethod_ = @Autowired)
	private QnACommentService commentService;
	
//	// 1. 게시판 목록 조회
	@GetMapping("/list")
	void list(GroupBoardCriteria cri, Model model) throws ControllerException {
		log.trace("list({}, {}) invoked.", cri, model);
		
		try {
		List<GroupBoardVO> list = this.service.getList(cri);
		// Request Scope  공유속성 생성
		model.addAttribute("__LIST__", list);
		
		GroupBoardPageDTO pageDTO = new GroupBoardPageDTO(cri, this.service.getTotal());
		model.addAttribute("pageMaker", pageDTO);
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // list
	
	// 2. 조건검색조회
	@GetMapping("/searchList")
	void searchList(GroupBoardCriteria cri, Model model) throws ControllerException {
		log.trace("list({}, {}) invoked.", cri, model);
		
		try {
		List<GroupBoardVO> list = this.service.getSearchList(cri);
		// Request Scope  공유속성 생성
		model.addAttribute("__SEARCH__", list);
		
		GroupBoardPageDTO pageDTO = new GroupBoardPageDTO(cri, this.service.getSearchTotal(cri));
		log.info("********************************************************* 검색된 게시물 수: {})", this.service.getSearchTotal(cri));
		model.addAttribute("searchPageMaker", pageDTO);
		
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // list
	
 
    
	
	
	// 2. 새로운 게시물 등록
	@PostMapping("/register")
	String register(GroupBoardDTO dto,GroupsDTO dh, RedirectAttributes rttrs) throws ControllerException {
		log.trace("register({}, {}) invoked.", dto, rttrs);
		
		try {
			Objects.requireNonNull(dto);
			// 게시글에서 지역,날짜,인원,멤버수,글번호 등을 뽑아내어 
			// 새로운 게시글이 등록될때 동행역시 생성
			
			if( this.service.register(dto) & this.group.register(dh,dto)) {		
				rttrs.addFlashAttribute("result", "true");
				rttrs.addFlashAttribute("postno", dto.getPostNo());
			    UsersVO one = this.user.getByNick(dto.getNickName());
			    this.mapping.registerDefault(one.getID(), dh.getGroupNo());
				
				
				return "redirect:/board/group/list";
			} else {
				return null;
			}
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // register
	
	// 단순 등록화면 요청
	@GetMapping("/register")
	void register() {
		log.trace("register() invoked.");
		
	} // register
	
	// 3. 특정 게시물 상세조회
    @GetMapping(path={"/get", "/modify"},  params = "postNo")
    void get(@RequestParam Integer postNo, Model model, CommentCriteria commentCri, @Param("currPage") Integer currPage, RedirectAttributes rttrs) throws  ControllerException {
        log.trace("get() invoked.");

        try{
            GroupBoardVO vo = this.service.get(postNo);
            model.addAttribute("__BOARD__", vo);
            
            List<QnACommentVO> commentList = this.commentService.selectList(commentCri, postNo);
            model.addAttribute("__COMMENT_LIST__", commentList);
            log.info("\t+ 댓글 조회된다아아아아");
            
            CommentPageDTO pageDTO = new CommentPageDTO(this.commentService.getCommentTotal(postNo), commentCri);
    		model.addAttribute("__commentPage__", pageDTO);
        }catch (Exception e){
            throw new ControllerException(e);
        } // try-catch
    } // get
	
    // 4. 특정 게시물 업데이트(수정화면)
    @PostMapping("/modify")
    String modify(GroupBoardDTO dto, Integer currPage, RedirectAttributes rttrs) throws ControllerException {
    	log.trace("modify({}, {}) invoked.", dto, currPage);
    	
    	try {
			Objects.requireNonNull(dto);
			
			rttrs.addAttribute("currPage", currPage);
			
			if( this.service.modify(dto) ) {		// if success
				rttrs.addFlashAttribute("result", "true");
				rttrs.addFlashAttribute("postno", dto.getPostNo());
			} // if
			return "redirect:/board/group/list";
			
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
			return "redirect:/board/group/list";
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // remove
	
	// 댓글 등록
//		@RequestMapping(value = "/qnaReply", method= {RequestMethod.POST})
		@PostMapping("/qnaReply")
		String insert(@ModelAttribute QnACommentDTO dto, Criteria cri,RedirectAttributes rttrs, @RequestParam("currPage") Integer currPage, CommentCriteria commentCri) throws ControllerException {
		    log.trace("addComment({}) invoked.", dto);
		    try {
		    	commentService.insert(dto);
		    	rttrs.addAttribute("currPage", currPage);
		        rttrs.addAttribute("postNo", dto.getPostNo());
		        rttrs.addAttribute("commentCurrPage", commentCri.getCommentCurrPage());
		        return "redirect:/board/group/get?";
		    } catch (Exception e) {
		        throw new ControllerException(e);
		    }
		} // addComment

		
		// 댓글 수정
		@PostMapping("/edit")
		String editComment(QnACommentDTO dto, RedirectAttributes rttrs) throws ControllerException {
			log.trace("editComment({}) invoked.", dto);
			
			try {
				commentService.update(dto);
				rttrs.addAttribute("postNo", dto.getPostNo());
				return "redirect:/board/group/get";
			} catch (Exception e) {
				throw new ControllerException(e);
			}
		} // editComment
		
		// 댓글 삭제
		@PostMapping("/delete")
		String deleteComment(@RequestParam(value = "commentNo", required=false) Integer commentNo, Integer postNo,RedirectAttributes rttrs) throws ControllerException {
			log.trace("deleteComment({}) invoked.", commentNo);
			
			try {
				commentService.delete(commentNo);
				rttrs.addAttribute("postNo", postNo);
				return "redirect:/board/group/get";
			} catch (Exception e) {
				throw new ControllerException(e);
			}
		} // deleteComment
		
		
	

	
	
	
	
	
	
} // end class
