package org.zerock.myapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.zerock.myapp.domain.PinDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.PinService;
import org.zerock.myapp.service.UsersService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@SessionAttributes({"__PIN__"})
@Controller
@RequestMapping("/board/group")

public class PinController {
	
	@Setter(onMethod_= @Autowired)
	private PinService pinService;
	
	@Setter(onMethod_ = @Autowired)
	private UsersService user;
	
	@PostMapping("/pin")
	@ResponseBody
	public Integer insert(PinDTO pin) throws ControllerException {
		log.trace("insert invoekd.");
		
		try {
			if(this.pinService.doPin(pin) == 1) {
				return 1;
			} else {
				return 0;
			} // if-else
		}catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
	} // insert

} // end class
