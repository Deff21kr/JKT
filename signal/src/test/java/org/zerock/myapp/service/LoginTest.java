package org.zerock.myapp.service;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.DAOException;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

//JUnit4
//1) 디폴트 컨스트럭쳐를 가진다

@Log4j2
@NoArgsConstructor

//Spring Framework 구동
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/**/root-*.xml",
		"file:src/main/webapp/WEB-INF/**/servlet-*.xml"
		})
public class LoginTest {
	@Autowired private LoginService login;
	@Autowired private UsersService user;
	@Autowired private BCryptPasswordEncoder encode;
	
	@Before
	public void setup() { // 전처리
		log.trace("\n************************************** 			setup() "
				+ "\n************************************** ");
		
		Objects.requireNonNull(this.login);
		log.info("\tthis.service : {}" ,this.login);
		
		Objects.requireNonNull(this.user);
		log.info("\tthis.service : {}" ,this.user);
	}
	
	
	@Test
	public void testLogin2() throws DAOException, ServiceException {
		UsersVO vo  = this.user.get("1111");
//		UsersVO vo = this.user.select (dto.getID() );
		UsersDTO dto = vo.toDTO();
		
		this.encode.matches(dto.getPassword(), vo.getPassword() );
//		dto = this.login.login(dto); // 아이디, 비밀번호 조회
		
		
		log.info("login : {}", login);
	}
	
	
	

}
