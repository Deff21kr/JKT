//package org.zerock.myapp.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.zerock.myapp.domain.UserDTO;
//import org.zerock.myapp.domain.UserVO;
//import org.zerock.myapp.domain.UsersDTO;
//import org.zerock.myapp.exception.ControllerException;
//import org.zerock.myapp.exception.DAOException;
//import org.zerock.myapp.service.LoginService;
//import org.zerock.myapp.service.UsersService;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//
//@Controller
//@Log4j2
//@RequiredArgsConstructor
//public class LoginController {
//	@Autowired
//	private LoginService service;
//	@Autowired
//	private UsersService user;
//	
//	@GetMapping("/loginpost")
//	public String login() {
//		return "loginpage"; // 로그인 폼 페이지로 이동
//	} // login
//	
//	@PostMapping("/loginpost")
//	public String login(@RequestParam("ID") String id,
//						@RequestParam("password") String password,
//						HttpServletRequest request,
//						Model model) throws DAOException {
//		log.trace("login({},{}) invoked.", id, password);
//		
//		UsersDTO dto = new UsersDTO();
//		dto.setID(id);
//		dto.setPassword(password);
//		
//		UsersDTO login = this.service.login(dto); // 로그인 서비스 실행
//		
//		if(login == null) {
//			model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
//		return "loginpage"; // 로그인 실패시 로그인 폼 페이지로 이동
//		} // if
//		
//		// 로그인 성공시, 세션에 로그인 정보를 저장
//		HttpSession session = request.getSession();
//		session.setAttribute("login", login);
//		
//		return "mainpage"; // 메인 페이지로 이동
//	} // login
//	
//	@GetMapping("/logout")
//	public String logout(HttpServletRequest request) {
//		log.info("logout()...");
//		
//		// 세션에서 로그인 정보를 삭제
//		HttpSession session = request.getSession();
//		session.invalidate();
//		
//		return "mainpage"; // 메인 페이지로 이동
//	} // logout
//	
//	
//	@PostMapping("/loginPost")
//	String loginPost(
//			UserDTO dto,
//			Model model,
//			RedirectAttributes rttrs) throws ControllerException {
//		log.trace("loginPost({}) invoked.", dto);
//
//		try {
//			UserVO vo = this.service.authenticate(dto);
//
//			if (vo != null) { // 로그인을 성공했다면 (why? 영속성까지 들어가서 객체를 반환한다는건 데이터가 맞게 들어갔다는 뜻)
//				model.addAttribute("__AUTH__", vo);	// Request Scope
//				
//				return "redirect:/board/list"; // 인증 성공시 매인페이지로!!
//			} else { // 로그인 실패
//					rttrs.addAttribute("__RESULT__","실패이샛기야");
//					
//					return "redirect:/user/login"; // 다시 로그인 페이지로
//			}
//		} catch (Exception e) {
//			throw new ControllerException(e);
//		}
//
//	} // method
//	
//	
//	
//} // end class
