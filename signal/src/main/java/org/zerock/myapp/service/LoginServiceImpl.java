package org.zerock.myapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.DAOException;
import org.zerock.myapp.mapper.LoginMapper;
import org.zerock.myapp.mapper.UsersMapper;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	
	@Setter(onMethod_ = @Autowired)
	private LoginMapper mapper;
	
	@Setter (onMethod_ = @Autowired)
	private BCryptPasswordEncoder encoder;
	
	@Setter (onMethod_ = @Autowired)
	private UsersMapper users;

	@Override
	public UsersVO login(UsersDTO dto) throws DAOException {
		log.info("login({}) invoked.", dto);
		
		UsersVO vo = this.users.select (dto.getID() );
		
		
		boolean is =this.encoder.matches(dto.getPassword(), vo.getPassword() );
		log.info("\n\nlogin : {}\nvo:{}",is,vo);
		
		if(is != true) {
			log.warn("아이디 비밀번호가 일치하지 않습니다.");
			return null;
		} else {
			return vo;
		}
		
		
		
	
	} // login
	
	
	
} // end class
