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
	
	@Autowired private UsersMapper users;
	

	@Override
	public UsersVO authenticate(UsersDTO dto) throws ServiceException {
		log.info("login({}) invoked.", dto);
		
			
		try {
	        UsersVO vo = this.users.select(dto.getID());

	        if (vo == null) {
	            log.warn("사용자를 찾을 수 없습니다.");
	            return null;
	        }

	        boolean isMatched = this.encoder.matches(dto.getPassword(), vo.getPassword());

	        if (isMatched) {
	            log.info("비밀번호가 일치합니다. 사용자 정보를 반환합니다.");
	            return this.mapper.selectUserIdPw(vo.toDTO());
	        } else {
	            log.warn("비밀번호가 일치하지 않습니다.");
	            return null;
	        }
	    } catch (Exception e) {
	        throw new ServiceException(e);
	    }
		
		
	
	} // login
	
	
	
	
	
} // end class
