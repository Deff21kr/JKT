package org.zerock.myapp.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2

@Getter
public class CustomUser extends User {

	private static final long serialVersionUID = 1L;
	private UsersDTO users;

	public CustomUser(String username, String password,
					Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	} //constructor
	
	public CustomUser (UsersDTO vo) {
		super(vo.getID(), vo.getPassword(), vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()) );
		
		this.users=vo;
	} //constructor


	

}
