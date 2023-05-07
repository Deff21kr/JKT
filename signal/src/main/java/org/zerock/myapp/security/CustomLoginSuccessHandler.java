package org.zerock.myapp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res,
			Authentication auth) throws IOException, ServletException {
		log.trace("Login Success");
		
		List<String> roleNames = new ArrayList<>();
		auth.getAuthorities().forEach( authority-> {
			roleNames.add(authority.getAuthority());
		});
		
		log.info("Role Names : {} ",roleNames);
		
		if(roleNames.contains("ROLE_ADMIN")) {
			res.sendRedirect("/sample/admin");
			return;
		}
		
		if(roleNames.contains("ROLE_MEMBER")) {
			res.sendRedirect("/sample/member");
			return;
		}
		res.sendRedirect("/");
		
	} // onAuthenticationSuccess
	
	

}
