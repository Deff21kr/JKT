package org.zerock.myapp.security;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
			log.error("Access Denied Handler");
			log.error("Redirect....");

			res.sendRedirect("/accessError");
	}

}
