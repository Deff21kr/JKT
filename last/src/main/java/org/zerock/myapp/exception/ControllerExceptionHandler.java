package org.zerock.myapp.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@ControllerAdvice	// For All packages
@ControllerAdvice(basePackages= { "org.zerock.myapp.controller" }) // For one package
public class ControllerExceptionHandler {

	
	// 1. Exception 을 처리하는 예외처리 메소드
	@ExceptionHandler(Exception.class)
	String handleException(Exception e, Model model) {
		log.trace("handleException({}) invoked.", e);
		
		log.error("\t+ 1. Exception Type: {}", e.getClass().getName());
		log.error("\t+ 2. Exception Mesg: {}", e.getMessage());
		
		// 공유영역중, Request Scope의 공유속성으로 바인딩
		model.addAttribute("__EXCEPTION__", e);
		
		// 컨트롤러의 핸들러 메소드와 동일한 리턴타입(즉, 뷰의 이름)
		return "errorPage";	
	} // handleException

	
	// 2. ControllerException 을 처리하는 예외처리 메소드
	@ExceptionHandler(ControllerException.class)
	String handleControllerException(Exception e, Model model) {
		log.trace("handleControllerException({}) invoked.", e);
		
		log.error("\t+ 1. Exception Type: {}", e.getClass().getName());
		log.error("\t+ 2. Exception Mesg: {}", e.getMessage());
		
		// 공유영역중, Request Scope의 공유속성으로 바인딩
		model.addAttribute("__EXCEPTION__", e);
		
		// 컨트롤러의 핸들러 메소드와 동일한 리턴타입(즉, 뷰의 이름)
		return "errorPage";	
	} // handleControllerException
	
} // end class
