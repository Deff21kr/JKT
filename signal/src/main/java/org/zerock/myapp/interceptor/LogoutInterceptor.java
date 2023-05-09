package org.zerock.myapp.interceptor;

import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.mapper.UsersMapper;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Component
public class LogoutInterceptor implements HandlerInterceptor {
	@Resource private UsersMapper dao;
	
/*
 * 로그아웃 요청을 보낸 웹 브라우저가 가지고 있는, 세션객체 금고상자를 파괴하는 작업을
 * 아래의 전처리에서 수행하자!! 그러므로 이 요청은 컨트롤러의 핸들러 즉, 뒤로 보내지 않음
 * 세션트래킹시 주의사항!
 * 인증된 사용자가(로그인한) 명시적으로 "로그아웃"을 요구하지 않는 한,
 * 금고상자(세션객체,HttpSession) 자체를 파괴시켜서는 안된다.
 */
	@Override
	   public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
	         throws Exception {   
		// 전처리 - HTTP request가 Request Mapping Table대로, 원래의 컨트롤러 핸들러 메소드로 가기전 가로챔
	      log.trace("==============================================================");
	      log.trace("preHandle(req, res, {}) invoked.", handler);
	      log.trace("==============================================================");
	     
	      // Step1. 현재 로그아웃 요청을 보낸 웹브라우저에 대응하는 세션객체 획득
	      HttpSession session = req.getSession(false);
	      UserVO userVO = (UserVO) session.getAttribute("__AUTH__");
	      if( session != null ) {
	    	  // 세션이 널이 아니면 로그아웃 처리
	    	  
	    	  session.invalidate();
	      }
	      
	      // 자동로그인과 관련된 로직이 추가 되어야함
	      // 왜? 자동로그인 설정된 웹브라우저가 명시적으로 로그아웃을 요청했으니,
	      // 자동로그인설정 해제시켜줘야 함.
	      
	      // Step.2 현재 로그아웃요청한 웹브라우저가 자동로그인설정 대상인지 확인
	      Cookie rememberMeCookie = WebUtils.getCookie(req, "__REMEMBER_ME__");
	      if(rememberMeCookie != null ) {
	    	  // Step.3 웹브라우저가 보관하는 자동로그인 쿠키(rememberMeCookie)를 파괴
	    	  rememberMeCookie.setMaxAge(1);
	    	  rememberMeCookie.setPath("/");
	    	  
	    	  res.addCookie(rememberMeCookie); // 쿠킼의 설정이 바뀌었으므로 다시 준다
	    	  
	    	  log.info("\t Remember-Me Cookie Destroyed.");
	    	  
	    	  // Step.4 tbl_user 테이블에 설정된 자동로그인 쿠키값과 만료일시 컬럼의 값을 null로 변경
	    	  Objects.requireNonNull(this.dao);
	    	  Objects.requireNonNull(userVO);
	    	  this.dao.updateUserWithRememberMe(userVO.getUserid(), null, null);
	    	  log.info("\tRemember-Me Released Successfully. ");
	      }
	      
	      res.sendRedirect("/user/login");
	      return false; // 이미 false 가 결정됨 왜냐면 뒤로 보내지 않을거니깐!! 즉 여기서 로그아웃!
    	  
	   } // preHandle
	

} // end class
