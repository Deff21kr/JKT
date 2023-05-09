package org.zerock.myapp.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.DAOException;
import org.zerock.myapp.service.LoginService;
import org.zerock.myapp.service.UsersService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@SessionAttributes({"__AUTH__"})
@NoArgsConstructor
@Log4j2

@RequestMapping("/common")
@Controller
public class CommonController {
	//공통의 기능
	
	@Setter(onMethod_ = @Autowired)
	private UsersService service;
	
	@Setter(onMethod_ = @Autowired)
	private LoginService login;
	
	
	// 2. 회원가입						필수 파라미터 등록!!
		@PostMapping(path="/register"
//					,params= {"ID","nickName","name",
//							"password", "gender", "EMail",
//							"phoneNumber", "birthDate","MBTI","likeArea"}
				)	//rttrs 1회성 전달 메소드  
		String register(UsersDTO dto,RedirectAttributes rttrs ) throws ControllerException  {
			log.trace("register( {}, {} ) ㄲㄲ",dto,rttrs);
			try {
				Objects.requireNonNull(dto);		// dto가 제대로 수집되어 널이 아니라면
				if (this.service.register(dto)) {	// if Success
					rttrs.addAttribute("result", "true");
					rttrs.addAttribute("uno",dto.getUno());
				}
				return "redirect:/mainpage";
			} catch(Exception e) {
				throw new ControllerException(e);
			}
		
		} // 회원가입창으로 이동
		@GetMapping("/register" )
		String register() {
			log.trace("join() invoked.");
			return "/user/register"; 
			
		}
		
//		============================================================
		
		@GetMapping("/login")
		public String login() {
			return "loginpage"; // 로그인 폼 페이지로 이동
		} // login
		
		@PostMapping("/login22")
		public String login(@RequestParam("ID") String id,
							@RequestParam("password") String password,
							HttpServletRequest request,
							Model model) throws DAOException {
			log.trace("login({},{}) invoked.", id, password);
			
			UsersDTO dto = new UsersDTO();
			dto.setID(id);
			dto.setPassword(password);
			
			UsersVO login = this.login.login(dto); // 로그인 서비스 실행
			
			if(login == null) {
				model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "loginpage"; // 로그인 실패시 로그인 폼 페이지로 이동
			} // if
			
			// 로그인 성공시, 세션에 로그인 정보를 저장
			HttpSession session = request.getSession();
			session.setAttribute("login", login);
			
			return "mainpage"; // 메인 페이지로 이동
		} // login
		
		@PostMapping("/login")
		String loginPost(
				UsersDTO dto,
				Model model,
				RedirectAttributes rttrs) throws ControllerException {
			log.trace("loginPost({}) invoked.", dto);

			try {
				UsersVO vo = this.login.login(dto);

				if (vo != null) { // 로그인을 성공했다면 (why? 영속성까지 들어가서 객체를 반환한다는건 데이터가 맞게 들어갔다는 뜻)
					model.addAttribute("__AUTH__", vo);	// Request Scope
					
					return "mainpage"; // 인증 성공시 매인페이지로!!
				} else { // 로그인 실패
						rttrs.addAttribute("__RESULT__","실패");
						
						return "redirect:/login"; // 다시 로그인 페이지로
				}
			} catch (Exception e) {
				throw new ControllerException(e);
			}

		} // method
		

}
