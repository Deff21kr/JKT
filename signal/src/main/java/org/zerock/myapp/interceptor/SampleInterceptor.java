package org.zerock.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

//@Component
public class SampleInterceptor implements HandlerInterceptor {

	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {	// 전처리 - HTTP request 가 Request Mapping Table대로, 원래의 컨트롤러 핸들러 메소드로 가기전 가로챔
		log.trace("==============================================================");
		log.trace("preHandler(req, res, {}) invoked.", handler);
		log.trace("==============================================================");

		return true;	// 여기서 반환하는 boolean 값으로, 가로챈 HTTP request를 핸들러로 넘길지 말지를 결정
	} // preHandle
	

	// *주의사항*: 컨트롤러의 핸들러 메소드에서 예외가 발생하면, 사후처리는 수행될 수 없습니다 =>? 모델과뷰가 생성될 수 없기 때문
//	@Override
//	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,	ModelAndView modelAndView)
//			throws Exception {	// 후처리 - Controller's Handler method 호출이 완료된 직후 가로챔
//		log.trace("==============================================================");
//		log.trace("postHandler(req, res, {}, {}) invoked.", handler, modelAndView);
//		log.trace("==============================================================");
//		
//	} // postHandle
	

	// *주의사항*: 컨트롤러의 핸들러 메소드의 예외처리 방법과 아래 완료처리의 Exception 매개변수는 공존할 수 없음.
//	@Override
//	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception e)
//			throws Exception {	// 완료처리 - View 가 구동되어서 HTTP response 생성/전송이 완료된 직후 가로챔
//		log.trace("==============================================================");
//		log.trace("afterCompletion(req, res, {}, {}) invoked.", handler, e);
//		log.trace("==============================================================");
//		
//	} // afterCompletion
	

} // end class
