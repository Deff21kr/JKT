package org.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
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
	public UsersDTO login(UsersDTO dto) {
		log.info("login({}) invoked.", dto);
		
		UsersVO vo = this.users.select (dto.getID() );
		
		
		this.encoder.matches(dto.getPassword(), vo.getPassword() );
		vo = this.mapper.selectUserIdPw(dto); // 아이디, 비밀번호 조회
		
		if(vo == null) {
			log.warn("아이디 비밀번호가 일치하지 않습니다.");
			return null;
		}
		
		UsersDTO login = vo.toDTO(); // VO를 DTO로 변환
		
		log.info("login : {}", login);
		
		return login;
	} // login


	
} // end class
