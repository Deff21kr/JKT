package org.zerock.myapp.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.GroupBoardVO;
import org.zerock.myapp.domain.UserGroupDTO;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/**/root-*.xml"})

public class UserGroupSericeTests {
	@Setter(onMethod_= { @Autowired })
	private UserGroupService service;
	@Autowired
	private GroupBoardService board;
	
	@Before
	public void setup() {		// 전처리(Optional)
		log.trace("setup() invoked.");
		
		assertNotNull(this.service);
		log.info("\t+ this.service : {}", this.service);
	} // setup
	@Test
	public void test() throws ServiceException {
		this.service.getList("123123");
	}
	@Test(timeout = 1000 * 2)
	public void testUpdate() throws ServiceException {
		log.trace("testUpdate() invoked.");
		UserGroupDTO dto =this.service.get(41);
		dto.setOutCome("거절");
		
		this.service.modify(dto);
//		log.info("dto: {} ",dto);
	} // testGetList	
	
	
	
} // end class
