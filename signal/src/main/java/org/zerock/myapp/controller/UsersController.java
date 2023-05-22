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
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.UsersService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RequestMapping("/user")
@Controller
public class UsersController {
   
	@Setter (onMethod_=@Autowired)
	private UsersService service;
	
	// 1. 회원 목록 조회 (전부)
	@GetMapping("/list")//리턴타입이 보이드이므로 리퀘스트 맵핑이 uri
	void list(Model model) throws ControllerException {
		log.trace("list({}) ㄲㄲ",model);
		// model 상자 안에 담기만 하면 된다
		// 비즈니스 수행결과인 list 를 model 상자에 담자
		try {
			
			List<UsersVO> list;
			list = this.service.getList();
			
			// 리퀘스트 스콥에다가 공유속성을 생성
			model.addAttribute("__LIST__",list);
		} catch (Exception e) {
			throw new ControllerException(e);
		}

	} // list : 전체회원조회가 있어야 돼나??
	
	
	
	
	// 3. 특정회원 조회
	@GetMapping(path={"/get"}, params = "ID")
	void get(@RequestParam("ID") String ID ,Model model) 
				throws ControllerException {
		
		log.trace("get() 인보크");
		
		try {
			UsersVO vo = this.service.get(ID);
			model.addAttribute("__BOARD__",vo);
			
//			return "뷰이름";
		} catch(Exception e) {
			throw new ControllerException(e);
			
		}
		
	} // 특정 회원의 모든 게시물 조회
	
	
	@PostMapping(path="/mypage")
	String modify(UsersDTO dto,RedirectAttributes rttrs) 
			throws ControllerException {
		log.trace("modify({}) invoked.",dto);
		
		try {
			Objects.requireNonNull(dto);
			
			if( this.service.modify(dto) ) {
				rttrs.addAttribute("result","true");
				rttrs.addAttribute("userno",dto.getID());
			}
			
			return "redirect:/user/mypage";
		} catch (Exception e) {
			throw new ControllerException(e);
		}
	} // 회원정보수정
	
	@PostMapping(path="/mypage/remove")
	String remove(UsersDTO dto,RedirectAttributes rttrs) 
			throws ControllerException {
		log.trace("modify({}) invoked.",dto);
		
		try {
			Objects.requireNonNull(dto);
			
			if( this.service.remove(dto.getID()) ) {
				rttrs.addAttribute("result","true");
				rttrs.addAttribute("userno",dto.getID());
			}
			
			return "/mainpage";
		} catch (Exception e) {
			throw new ControllerException(e);
		}
	} // 탈퇴?
	
//	@GetMapping(path={"/mypage/group/list"}, params = "ID")
//	String myGroupList() {
//		
//		return "redirect:mypage";
//		
//	}
	
	@GetMapping(path={"/mypage"})
	String myGroupList() {
		
		return "user/mypage";
		
	}
	
//	@PostMapping(path={"/mypage/group/{동행명}/evaluate"}, params = "ID")
//	void partnerEvaluate() {
//		
//	}
	
	// 프로필 수정
	@PostMapping("/edit")
	String profilModify(UsersDTO dto, RedirectAttributes rttrs, Model model,String ID,String MBTI,
	String likeArea) throws ControllerException {
		
		try {
			this.service.profileEdit(dto);
			log.info("\t+ dto: {}", dto);
			
		} catch(Exception e) {
			throw new ControllerException(e);
		}
		return "redirect:/user/mypage";
	}

	// 프로필 수정
		@GetMapping("/edit")
		void myPageModify() throws ControllerException {
			try {
				
			} catch(Exception e) {
				throw new ControllerException(e);
			}
		}
	
	
   
} // end class