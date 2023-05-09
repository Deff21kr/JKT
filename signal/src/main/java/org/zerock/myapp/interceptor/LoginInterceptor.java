package org.zerock.myapp.interceptor;

import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.mapper.LoginMapper;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private LoginMapper dao;

	/*
	 * 로그인 인터셉터의 목적은 무엇일까? 목적 : 전처리는 이미 사용자가 로그인창에서 아이디, 암호를 입력,전송 했다는 것은 분명한 사실이기에
	 * 로그인 요청을 먼저 가로채서 전처리에서 로그인 요청을 보낸 웹브라우저에 대응되는 세션객체가 있다면 무조건 파괴시키는 동작이 피룡
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		// 전처리 - HTTP request가 Request Mapping Table대로, 원래의 컨트롤러 핸들러 메소드로 가기전 가로챔
		log.trace("==============================================================");
		log.trace("preHandle(req, res, {}) invoked.", handler);
		log.trace("==============================================================");

// ****** // Step1. 로그인 요청을 보낸 웹브라우저에 대응되는 세션객체 획득
		HttpSession session = req.getSession(false);

		if (session != null) {
			log.info("\t sessionId : {}", session.getId());

// ****** // Step2. 기존 세션은 무조건 파괴시켜서 새로운 credential은 새로운 객체에 저장
			session.invalidate();
			log.info("Session Destroyed?? {}", session.getId());

		} 
		return true; // 인증로직은 기존대로 컨트롤러의 핸들러가 처리하도록 해줘야함
	}
	
	

/*
 * *** 후처리 ***
 * 목적 : 자동로그인 처리를 위한 로직 구현
 * 			인증성공 이후에 rememberMe 옵션이 true이면 자동로그인 가능하도록 처리
 */
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		log.info("\n*******************************************************"
  		  		+ "\n\t\t postHandle(req,res,{} ,{})"
  		  		+ "\n*******************************************************",handler,modelAndView);

		// Step1. 인증성공 여부에 따라 자동 로그인 처리를 수행
		//			세션에 Credential(인증정보) 유무 확인		-- 조건(1)
		//			rememberMe 옵션의 on,off 확인				-- 조건(2)
		boolean authencated = isAuthenticated(req);				// 조건(1) 확인
		boolean rememberMe = checkRememberMeOption(req);		// 조건(2) 확인
		log.info("\tRemember-Me condition : 인증 : {} , 옵션체크 :  {}",authencated,rememberMe);
		// 자동록인 처리조건을 만족한다면..
		if(authencated && rememberMe) {	
			
			// Step2. 자동로그인설정을 요청한 웹브라우저의 이름인 세션ID를 쿠키값으로 생성
			HttpSession session = req.getSession(false);
			String sessionId = session.getId();			// 쿠키값으로 사용
			
			Cookie rememberMeCookie = new Cookie("__REMEMBER_ME__",sessionId);
			
			final int maxAge =1*60*60*24*7*52;
			rememberMeCookie.setMaxAge(maxAge); // 0: 무한 , 1: 부터 초
			rememberMeCookie.setPath("/");
			
			// Step4. 응답메시지의 Set-Cookie 헤더에 값으로 설정
			res.addCookie(rememberMeCookie);
			// 쿠키는 작은 문자열 데이터
			
			// Step5. 
			long currDateTime = System.currentTimeMillis();
			long expireDateTime = currDateTime + (maxAge *1000L);
			
			Timestamp rememberMeCookieMaxAge = new Timestamp(expireDateTime);
			
			int affectedLines = this.dao.updateUserWithRememberMe(
					getUserId(req), 
					rememberMeCookie.getValue(), 
					rememberMeCookieMaxAge);
			
			boolean isUpdated = (affectedLines == 1);
			
			log.info("\n\n\tisupdated : {} \n\n",isUpdated);
		} // if
		

		
	} // postHandle
	
	// 인증정보로부터 사용자 아이디 획득
	private String getUserId(HttpServletRequest req) {
		log.trace("\tgetUserId(req) invoked");
		
		HttpSession session = req.getSession(false);
		
		UsersVO vo = (UsersVO)session.getAttribute("__AUTH__");
		
		log.info("\tvo : {}",vo);
		return vo.getID();
		
		
	}
	
	
	// 조건1 : 인증성공여부 반환
	private boolean isAuthenticated(HttpServletRequest req) {
		log.trace("\tisAuthenticated(req) invoked");
		
		HttpSession session = req.getSession(false);
			Object obj = session.getAttribute("__AUTH__");
			
			log.info("\n\nobj:{}\n\n",obj);
			return obj !=null;

	}
	
	// 조건2 :자동로그인 옵션이 on/off 반환
	private boolean checkRememberMeOption(HttpServletRequest req) {
		log.trace("\tcheckRememberMeOption(req) invoked");
		
		String remeberMe = req.getParameter("rememberMe"); // 체크박스 체크시 "on" 문자열 반환 언체크는 반환 X
		return remeberMe != null;
	}
	
	
	
	
	
	

} // end class
