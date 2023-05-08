package org.zerock.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.myapp.domain.UsersVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Component
public class AuthInterceptor implements HandlerInterceptor {
	
	
	// HTTP request 가 Request Mapping 대로, Controller의 Handler에 위임되기 직전에 가로채서
	// 이 요청을 보낸 웹 브라우저가 인증을 완료한 상태인지 체크
	//	NO인증이면 로그인창으로 밀어버림 => 인터셉터의 역할
	@Override
	   public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
	         throws Exception {   
		// 전처리 - HTTP request가 Request Mapping Table대로, 원래의 컨트롤러 핸들러 메소드로 가기전 가로챔
	      log.trace("==============================================================");
	      log.trace("preHandle(req, res, {}) invoked.", handler);
	      log.trace("==============================================================");
	      
//	      HttpSession session =  req.getSession(); // 웹 브라우저에 할당되어있는 세션 획득
	      
	      // 요청을 보낸 브라우저에 대한 세션이 있으면 있는걸 반환
	      // 없으면 null 을 반환
	      HttpSession session =  req.getSession(false); // 없으면 만들라가 트루, fasle는 없으면 널 반환
	      // 매개변수 있는 세션과 비교 : 없으면 널을 반환
	      
//	      일단 세션이 있으면 아래에서 인증정보
	      if(session == null) { // 아예 세션 자체가 없으면
	    	  log.info("세션없은 null 임");
	    	 res.sendRedirect("/user/login");
	    	return false;
	    	
	      } 
	      
//	      =======================================
	      
	      String sessionId = session.getId();
    	  log.info("\n1. sessionId : {} ",sessionId);
	      
	      UsersVO vo = (UsersVO) session.getAttribute("__AUTH__");
	      if(vo != null) { // 인증된 웹브라우저
    		  log.info("\n*******************************************************"
    		  		+ "\n******************** 인증완료 *************************"
    		  		+ "\n*******************************************************");
    		  return true;
    	  } else {	// 세션은 있으나 인증이 안된 웹브라우저
    		  log.info("NO authentication found. Redirect to Login.");
	    	  
	    	  res.sendRedirect("/user/login");
	    	  return false; // 원래 요청을 처리하지 못하도록 함
    	  }
    	  
	   }

	// 목적 : 로그인 컨트롤러가 인증정보를 찾아내면 로그인 성공
	//			이 인증정보(Credential) UserVO 객체를 현재의 웹 브라우저 만이 열 수 있는 금고상자(세션)에 넣어주자
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		  log.info("\n*******************************************************"
  		  		+ "\n\t\t postHandle(req,res,{} ,{})"
  		  		+ "\n*******************************************************",handler,modelAndView);
		  
// ****** // Step1. 컨트롤러의 핸들러가 반환한 Model과 View의 이름을 획득
		  ModelMap returnedModelMap = modelAndView.getModelMap();
		  log.info("\treturnedModelMap : {}" , returnedModelMap);

		  
// ****** // Step2. 인증정보 (UserVO, Credential) 가 있다면, SessionScope 에 Binding 금고상자안에 넣음
		  UsersVO vo =(UsersVO) returnedModelMap.get("__AUTH__");
		  log.info("\tvo : {}" , vo);
		  if( vo != null) {
			  // 정상적인 로그인 성공이라면
			  
			  // Step3. 세션(금고) 안에 Credential(인증정보)로, UserVO 객체를 바인딩(공유속성)
			  HttpSession session = req.getSession(false);
			  log.info("\tsessionId : {}" , session.getId());
			  session.setAttribute("__AUTH__", vo);	// 인증정보(Creential) 바인딩
			  log.info("\n******************************************************"
			  		+  "\n\t\tBinding Credential into Session Success!"
			  		+  "\n******************************************************");
			  
		  }
		  
		  
	} // postHandle
	
	
	
	

} // end class
