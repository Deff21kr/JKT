package org.zerock.myapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.mapper.UsersMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomUserDetailsService implements UserDetailsService {
	
	@Setter(onMethod_ = @Autowired)
	private UsersMapper dao;
	
	@Override
	public UserDetails loadUserByUsername(String id) 
			throws UsernameNotFoundException 
	{
		log.trace("\n************************************************"
				+ "\n\t\tloadUserByUserName : {}"
				+ "\n************************************************",id);
		// userName means userid
		UsersDTO vo = dao.select(id);
		
		log.info("\n************************************************"
				+ "\n\tqueried by user mapper : {}"
				+ "\n************************************************",vo);
		
		return vo==null ? null : new CustomUser(vo);
	}
	
}
