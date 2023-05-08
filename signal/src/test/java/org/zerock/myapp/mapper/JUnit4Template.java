package org.zerock.myapp.mapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

// JUnit4
// 1) 디폴트 컨스트럭쳐를 가진다

@Log4j2
@NoArgsConstructor

// Spring Framework 구동
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/**/root-*.xml"})
public class JUnit4Template {
	
	
	@Before
	public void setup() { // 전처리
		log.trace("before invoked");
	}
	
	// 테스트 메소드 이름은 반드시 접두사 test를 붙여야 함
	@Test
	public void testCXXX() {
		log.trace("222 invoked");	
		
	}
	
	@Test
	public void testBXXX() {
		log.trace("333 invoked");
	}
	
	
	
	@Test
	public void testAXXX() {
		log.trace("111 invoked");
	}

	
	
	
	@After
	public void tearDown() {
		log.trace("after invoked");
	}
	
	
}	// end class
