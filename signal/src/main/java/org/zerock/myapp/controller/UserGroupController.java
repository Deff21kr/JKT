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
@RequestMapping("/user")
@Controller
public class UserGroupController {
	@Setter (onMethod_=@Autowired) private UserGroupService service;
	@Setter (onMethod_=@Autowired) private GroupService group;
	@Setter (onMethod_=@Autowired) private GroupBoardService board;
	@Setter (onMethod_=@Autowired) private UsersService user;
	
	@GetMapping("/mygroup")
	public void uesrGroup(Model model,HttpServletRequest req) throws ControllerException {
		try {
			log.trace("동행({} ) invoked.",model);
			
			HttpSession session = req.getSession();
			UsersVO vo = (UsersVO)session.getAttribute("__AUTH__"); 
			log.info("유저 : {}",vo);
			List<UserGroupDTO> list =this.service.getList(vo.getNickName());
			log.info("조인 : {}",list);
			model.addAttribute("__GROUP__",list);
//			return "/user/mygroup";
		} catch (Exception e) {
			throw new ControllerException(e);
		}
	}
	
//	@PostMapping("/mygroup")
//	public void uesrGroup(Model model,UsersDTO dto) throws ControllerException {
//		try {
//			UsersVO vo = this.user.get(dto.getID());
//			
//			List<UserGroupDTO> list =this.service.getList(vo.getNickName());
//			model.addAttribute("__GROUP__",list);
//			
//		} catch (Exception e) {
//			throw new ControllerException(e);
//		}
//		
//	}

}
