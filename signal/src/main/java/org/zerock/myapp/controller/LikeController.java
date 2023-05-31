package org.zerock.myapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.zerock.myapp.domain.LikeDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.LikeService;
import org.zerock.myapp.service.UsersService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@SessionAttributes({"__LIKE__"})
@Controller
@RequestMapping("/board/review")

public class LikeController {
	
	@Setter(onMethod_= @Autowired)
	private LikeService likeService;
	
	@Setter(onMethod_ = @Autowired)
	private UsersService user;
	
	@PostMapping("/like")
	@ResponseBody
	public Integer insert(LikeDTO like) throws ControllerException {
		log.trace("insert invoked.");
		
		try {
			if(this.likeService.doLike(like) == 1) {
				return 1;
			} else {
				return 0;
			} // if-else
		}catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // insert

} // end class
