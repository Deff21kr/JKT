package org.zerock.myapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.UsersMapper;
import org.zerock.myapp.persistence.LoginDAO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
@NoArgsConstructor
@Log4j2

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDAO mapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UsersMapper users;
	

	@Override
	public UsersVO authenticate(UsersDTO dto) throws ServiceException {
		log.info("login({}) invoked.", dto);
		
		UsersVO vo = this.users.select(dto.getID());
		boolean is =this.encoder.matches(dto.getPassword(), vo.getPassword() );
		log.info("\n\nlogin : {}\nvo:{}",is,vo);
		
		
		try {	
			if(is) {
				log.info("\nvo: {}\ndto : {}",vo,dto);
				return this.mapper.selectUserIdPw(this.users.select(dto.getID()).toDTO());
			} else {
				log.warn("아이디 비밀번호가 일치하지 않습니다.");
				return null;
			}
				
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	
	
	} // login
	
	
	
	
	
} // end class
