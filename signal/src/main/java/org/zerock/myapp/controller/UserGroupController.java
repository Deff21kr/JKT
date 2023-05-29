package org.zerock.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupsDTO;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.domain.QnABoardVO;
import org.zerock.myapp.domain.UserGroupDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.GroupBoardService;
import org.zerock.myapp.service.GroupService;
import org.zerock.myapp.service.QnACommentService;
import org.zerock.myapp.service.UserGroupService;
import org.zerock.myapp.service.UsersService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@RequestMapping("/user")
@Controller
public class UserGroupController {
	@Setter (onMethod_=@Autowired) private UserGroupService service;
	@Setter (onMethod_=@Autowired) private GroupService group;
	@Setter (onMethod_=@Autowired) private GroupBoardService board;
	@Setter (onMethod_=@Autowired) private UsersService user;
	@Setter (onMethod_=@Autowired) private QnACommentService commentService;
	
	
	@GetMapping("/mygroup")
	public String uesrGroup(Model model,HttpServletRequest req,Criteria cri) throws ControllerException {
		try {
			log.trace("동행({} ) invoked.",model);
			HttpSession session = req.getSession();
			UsersVO vo = (UsersVO)session.getAttribute("__AUTH__"); 
			log.info("유저 : {}",vo);
			
			List<UserGroupDTO> list =this.service.getList(vo.getNickName(),cri);
			log.info("조인 : {}",list);
			model.addAttribute("__GROUP__",list);
			
			PageDTO pageDTO = new PageDTO(cri,this.service.getTotal(vo.getNickName()));
			model.addAttribute("pageMaker",pageDTO);
			
			return "/user/mygroup";
		} catch (Exception e) {
			throw new ControllerException(e);
		}
	}
	
//	// 내가 신청한 동행 조회
//	@GetMapping("/myapp")
//	public String userApp(Model model,HttpServletRequest req,Criteria cri) throws ControllerException {
//		
//		try {
//			log.trace("동행({} ) invoked.",model);
//			HttpSession session = req.getSession();
//			UsersVO vo = (UsersVO)session.getAttribute("__AUTH__"); 
//			log.info("\n\nvo : {}",vo);
//			List<UserGroupDTO> list = this.service.getMyAppList( vo.getNickName(),cri );
//			log.info("\n\nlist : {}",list);
//			// Request Scope  공유속성 생성
//			model.addAttribute("__APPLIST__", list);
//			
////			PageDTO pageDTO = new PageDTO(cri, this.service.getTotal());
////			model.addAttribute("pageMaker", pageDTO);
//			
//			return "redirect:/user/mypage";
//		} catch (Exception e) {
//			throw new ControllerException(e);
//		}
//	}
	
	
	@PostMapping("/mygroup")
	public String uesrGroup(
			@RequestParam("appNo") int appNo, 
			@RequestParam("outCome") String outCome,
			UserGroupDTO dto) throws ControllerException {
		try {
			log.trace("동행({}) invoked.",dto);
			
			dto.setOutCome(outCome);
			this.service.modify(dto);
			
			return "redirect:/user/mygroup";
		} catch (Exception e) {
			throw new ControllerException(e);
		}
	}
	
	@PostMapping("/mygroup/register")
	@ResponseBody
	public int register(@RequestParam("nickName") String nickName,@RequestParam("postNo") Integer postNo,Integer currPage,
			RedirectAttributes rttrs) throws ControllerException {
		try {
			log.trace("유저ID ({} ) invoked.",nickName);
			log.trace("유저nickName ({} ) invoked.",nickName);
			log.trace("postNo ({} ) invoked.",postNo);
			log.trace("currPage ({} ) invoked.",currPage);
			GroupsDTO dto = this.group.getPost(postNo);
			log.info("dto ({} ) invoked.",dto);
			
			rttrs.addAttribute("currPage", currPage);
			rttrs.addAttribute("postNo",postNo);
			
			if(this.service.groupCheckIDService(nickName, postNo)==0) {
				this.service.register(nickName,dto.getGroupNo());
				return 0;
			}else {
				return 1;
			}
		} catch (Exception e) {
			throw new ControllerException(e);
		}
	}
	
	
	
	

}
