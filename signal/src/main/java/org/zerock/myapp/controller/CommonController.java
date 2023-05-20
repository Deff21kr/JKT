package org.zerock.myapp.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.LoginService;
import org.zerock.myapp.service.UsersService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@SessionAttributes({ "__AUTH__" })

@RequestMapping("/common")
@Controller
public class CommonController {
	// 공통의 기능
	@Autowired
	private UsersService service;
	@Autowired
	private LoginService login;

	@GetMapping("/loginPost")
	void login(Model model) {
//		model.addAttribute("__RESULT__", "실패");
	}

	@PostMapping("/loginPost")
	String login(UsersDTO dto, Model model, HttpServletRequest req, RedirectAttributes rttrs)
			throws ControllerException {
		log.trace("loginPost(\n\n{},\n{},\n{}\n) invoked.", dto, model, rttrs);

		try {
//			UsersVO vo = this.login.authenticate(this.service.get(dto.getID()).toDTO());
			UsersVO vo = this.login.authenticate(dto);
			log.info("\tvo: {}", vo);

			if (vo != null) { // 로그인을 성공했다면 (why? 영속성까지 들어가서 객체를 반환한다는건 데이터가 맞게 들어갔다는 뜻)
				model.addAttribute("__AUTH__", vo); // Request Scope
				String re = (String) req.getSession().getAttribute("redirectUri");
//				log.info("\n\ncurrPage : {}" ,sign.getCurrPage());
//				log.info("\n\npostNo : {}" ,sign.getPostNo());
//				if(currPage != null & postNo != null ) {
//					return re+currPage+postNo;
//				}
				log.info("\n\n&&& re &&&& : {} \n\n", re);
				log.info("\ndto : {} ,model : {}", dto, model);

				if (re != null) {
					return "redirect:" + re;
				} else {
					return "redirect:/";
				}

			} else { // 로그인 실패
				rttrs.addAttribute("__RESULT__", "실패");
				return "redirect:/common/loginPost"; // 다시 로그인 페이지로
			}
		} catch (Exception e) {
			throw new ControllerException(e);
		}

	} // method

	// 2. 회원가입 필수 파라미터 등록!!
	@PostMapping(path = "/register") // rttrs 1회성 전달 메소드
	String register(UsersDTO dto, RedirectAttributes rttrs, Model model) throws ControllerException {
		log.trace("register( {}, {} ) ㄲㄲ", dto, rttrs);
		try {
			String tempPassword = dto.getPassword();
			Objects.requireNonNull(dto); // dto가 제대로 수집되어 널이 아니라면
			if (this.service.register(dto)) { // if Success
				rttrs.addAttribute("result", "true");
				dto.setPassword(tempPassword);
				UsersVO vo = this.login.authenticate(dto);
				log.info("\tvo: {}", vo);

				if (vo != null) { // 로그인을 성공했다면 (why? 영속성까지 들어가서 객체를 반환한다는건 데이터가 맞게 들어갔다는 뜻)
					model.addAttribute("__AUTH__", vo); // Request Scope
					log.info("\ndto : {} ,model : {}", dto, model);
					return "redirect:/"; // 회원가입 & 로그인성공 = > 매인
				} else {
					rttrs.addAttribute("__RESULT__", "비회원");
					return "redirect:/common/loginPost"; // 회원가입만 성공 로그인 실패 => 로그인
				}

			}
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		return "redirect:/common/register";	//회원가입 실패

	} // 회원가입창으로 이동

	@GetMapping("/register")
	String register() {
		log.trace("join() invoked.");
		return "/common/register";

	}

	@GetMapping("idCheck")
	@ResponseBody
	public Integer idCheck(@RequestParam("ID") String ID) throws ControllerException {
		log.info("중복체크 : {}", ID);

		try {
			Objects.requireNonNull(ID); // dto가 제대로 수집되어 널이 아니라면
			log.info("\n\nidididid : {}",ResponseEntity.ok(this.service.checkIdService(ID)));
			return  this.service.checkIdService(ID);
			
		} catch (Exception e) {
			throw new ControllerException(e);
		}

	}
	@GetMapping("nickNameCheck")
	@ResponseBody
	public Integer nickNameCheck(@RequestParam("nickName") String nickName) throws ControllerException {
		log.info("중복체크 : {}", nickName);

		try {
			Objects.requireNonNull(nickName); // dto가 제대로 수집되어 널이 아니라면

			return this.service.checkNickNameService(nickName);
		} catch (Exception e) {
			throw new ControllerException(e);
		}

	}

//		============================================================

}
