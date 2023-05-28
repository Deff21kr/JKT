package org.zerock.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.zerock.myapp.domain.PinDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.service.PinService;
import org.zerock.myapp.service.UsersService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@SessionAttributes({"__PIN__"})
@Controller
@RequestMapping("/board/pin")

public class PinController {
	
	@Setter(onMethod_= @Autowired)
	private PinService pinService;
	
	@Setter(onMethod_ = @Autowired)
	private UsersService user;
	
	@PostMapping("/register")
	@ResponseBody
	public String insert(PinDTO pin, HttpServletRequest req) {
		log.trace("insert invoekd.");
		
//		로그인 체크
//		HttpSession session = req.getSession();
//		UsersVO vo = (UsersVO)session.getAttribute("userno");
//		if(vo == null) {
//			log.trace("로그인 실패!");
//			return "5";
//		} // if
		
		int result = pinService.insert(pin);
		log.trace(">>> 결과: " + pin);
		// insert의 반환타입이 int여서 String으로 변환하기 위해 빈문자열을 추가
		return result + ""; 
		
	} // insert
	
	@PostMapping("/remove")
	public String delete(PinDTO pin) throws ServiceException {
		log.trace("delete() invoked.");
		
		pinService.delete(pin.getPinNo());
		
		return "redirect:/user/mypage/" + pin.getNickName();
		
	} // delete
	
	@PostMapping("/list")
	public String select(@PathVariable("nickName") String nickName, Model model) {
		log.trace("select({}) invoked.", nickName);
		
		model.addAttribute("__PIN__", pinService.select(nickName));
		
		return "/user/mypage";
		
	} // select

	
} // end class
