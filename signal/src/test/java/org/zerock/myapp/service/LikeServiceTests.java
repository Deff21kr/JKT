package org.zerock.myapp.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.LikeDTO;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/**/root-*.xml"})

public class LikeServiceTests {
	
	@Setter(onMethod_= { @Autowired })
	private LikeService service;
	
	@Before
	public void setup() {		// 전처리(Optional)
		log.trace("setup() invoked.");
		
		assertNotNull(this.service);
		log.info("\t+ this.service : {}", this.service);
	} // setup
	
	@Test
	public void testdoLike() throws ServiceException {
		log.trace("testdoLike() invoked.");
		
		Integer postNo = 276;
		String nickName = "hyeonna";
		
		LikeDTO dto = new LikeDTO();
		dto.setPostNo(postNo);
		dto.setNickName(nickName);
		log.info(dto);
		
		int result = this.service.doLike(dto);
		log.info(">>> 결과: " + result);
		
	} // testdoLike
	
} // end class
