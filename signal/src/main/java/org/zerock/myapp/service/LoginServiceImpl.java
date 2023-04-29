package org.zerock.myapp.service;

import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.mapper.LoginMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	
	private final LoginMapper mapper;

	@Override
	public UsersDTO login(UsersDTO dto) {
		log.info("login({})", dto);
		
		UsersVO vo = mapper.selectUserIdPw(dto); // 아이디, 비밀번호 조회
		
		if(vo == null) {
			log.warn("vo is null...");
			return null;
		}
		
		UsersDTO login = vo.toDTO(); // VO를 DTO로 변환
		
		log.info("login : {}", login);
		
		return login;
	}


	
}
