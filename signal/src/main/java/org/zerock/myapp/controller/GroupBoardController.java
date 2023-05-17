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
import org.zerock.myapp.domain.GroupBoardCriteria;
import org.zerock.myapp.domain.GroupBoardDTO;
import org.zerock.myapp.domain.GroupBoardPageDTO;
import org.zerock.myapp.domain.GroupBoardVO;
import org.zerock.myapp.domain.GroupsDTO;
import org.zerock.myapp.domain.UserGroupDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.GroupBoardService;
import org.zerock.myapp.service.GroupService;
import org.zerock.myapp.service.UserGroupService;
import org.zerock.myapp.service.UsersService;

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
	private UserGroupService mapping;
	@Setter(onMethod_ = @Autowired)
	private UsersService user;
	
	// 1. 게시판 목록 조회
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
	
	
	// 2. 새로운 게시물 등록
	@PostMapping("/register")
	String register(GroupBoardDTO dto,GroupsDTO dh, RedirectAttributes rttrs) throws ControllerException {
		log.trace("register({}, {}) invoked.", dto, rttrs);
		
		try {
			Objects.requireNonNull(dto);
			// 게시글에서 지역,날짜,인원,멤버수,글번호 등을 뽑아내어 
			// 새로운 게시글이 등록될때 동행역시 생성
			
			if( this.service.register(dto) & this.group.register(dh,dto)) {	
				
				UsersVO one = this.user.getByNick(dto.getNickName());
				this.mapping.registerDefault(one.getID(), dh.getGroupNo());
				
				rttrs.addFlashAttribute("result", "true");
				rttrs.addFlashAttribute("postno", dto.getPostNo());
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
    void get(@RequestParam Integer postNo, Model model) throws  ControllerException {
        log.trace("get() invoked.");

        try{
            GroupBoardVO vo = this.service.get(postNo);
            model.addAttribute("__BOARD__", vo);
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
				rttrs.addFlashAttribute("postNo", dto.getPostNo());
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
	
	
	// 7. 카테고리별 검색 기능
	@GetMapping("/search")
	void search(GroupBoardCriteria cri, Model model) throws ControllerException {
	    log.trace("search({}, {}) invoked.", cri, model);

	    try {
	        List<GroupBoardVO> list = this.service.searchList(cri);
	        // Request Scope에 공유속성 생성
	        model.addAttribute("__SEARCH__", list);

	        GroupBoardPageDTO pageDTO = new GroupBoardPageDTO(cri, this.service.getTotal());
	        model.addAttribute("pageMaker", pageDTO);
	    } catch (Exception e) {
	        throw new ControllerException(e);
	    } // try-catch
	} // search
	
	
	
	
	
	
} // end class
