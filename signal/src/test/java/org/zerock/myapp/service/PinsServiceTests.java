package org.zerock.myapp.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.PinDTO;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/**/root-*.xml"})

public class PinsServiceTests {
	
	@Setter(onMethod_= { @Autowired })
	private PinService service;
	
	@Before
	public void setup() {		// 전처리(Optional)
		log.trace("setup() invoked.");
		
		assertNotNull(this.service);
		log.info("\t+ this.service : {}", this.service);
	} // setup
	
	@Test
	public void testdoPin() throws ServiceException {
		log.trace("testdoPin() invoked.");
		
		Integer postNo = 256;
		String nickName = "123123";
		
		PinDTO dto = new PinDTO();
		dto.setPostNo(postNo);
		dto.setNickName(nickName);
		log.info(dto);
		
		int result = this.service.doPin(dto);
		log.info(">>> 결과: " + result);
	} // testdoPin
	
//	@Test(timeout = 1000 * 2) 
//	public void testDelete() throws ServiceException {
//		log.trace("testDelete() invoked.");
//		
//		Integer pinNo = 8;
//		boolean isDeleted = service.delete(pinNo);
//		log.info(">>> isDeleted : {}", isDeleted);
//	} // testDelete
//	
//	@Test(timeout = 1000 * 2) 
//	public void testSelect() throws ServiceException {
//		log.trace("testSelect() invoked.");
//		
//		String nickName = "Hyeonna";
//		List<PinDTO> list = service.select(nickName);
//		log.info(">>> List: " + list);
//		
//	} // testSelect
	
} // end class
