package org.zerock.myapp.mapper;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.DAOException;
import org.zerock.myapp.persistence.LoginDAO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

// JUnit4
// 1) 디폴트 컨스트럭쳐를 가진다

@Log4j2
@NoArgsConstructor

// Spring Framework 구동
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/**/root-*.xml"})
public class RememberMeTest {
	@Setter(onMethod_ = @Autowired)
	private LoginDAO login;
	@Setter(onMethod_ = @Autowired)
	private UsersMapper user;
	
	
	
	@Before
	public void setup() { // 전처리
		log.trace("before invoked");
	}
	@Test
	public void testUpdateUserWithRememberMe() throws DAOException {	// 단위테스트 
		log.trace("testUpdateUserWithRememberMe() invoked.");
		
		String ID = "123123";
		String rememberMeCookie = "1234567890ABCDEFG";
		
		long oneWeek = 1000 * 60 * 60 * 24 * 7;	// in milliseconds
		long now = System.currentTimeMillis();	// in milliseconds		
		Timestamp rememberMeMaxAge = new Timestamp(now + oneWeek);
		
		int affectedLines = 
			this.login.
				updateUserWithRememberMe(
					ID, 
					rememberMeCookie, 
					rememberMeMaxAge
				);
		
		log.info("\t+ affectedLines: {}", affectedLines);
	} // testUpdateUserWithRememberMe
	
	@Test
	public void testUpdateUserWithRememberMe22() throws DAOException {	// 단위테스트 
		log.trace("testUpdateUserWithRememberMe() invoked.");
		
		String ID = "123123";
		String rememberMeCookie = "1234567890ABCDEFG";
		
		long oneWeek = 1000 * 60 * 60 * 24 * 7;	// in milliseconds
		long now = System.currentTimeMillis();	// in milliseconds		
		Timestamp rememberMeMaxAge = new Timestamp(now + oneWeek);
		
		UsersVO affectedLines = 
			this.login.
				selectUserByRememberMe(rememberMeCookie
				
				);
		
		log.info("\t+ affectedLines: {}", affectedLines);
	} // testUpdateUserWithRememberMe

	
	
}	// end class
