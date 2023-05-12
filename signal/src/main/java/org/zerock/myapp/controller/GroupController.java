package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.GroupsDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.GroupService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RequestMapping("/board/group")
@Controller
public class GroupController {
	
	@Autowired
	private GroupService group;
/*
 * ,groupName,recruitStatus,maxMember,,area,postNo
 */
//	@PostMapping("/register")
//	public void group(GroupsDTO dto) throws ServiceException {
//		this.group.register(dto);
//	}

}
