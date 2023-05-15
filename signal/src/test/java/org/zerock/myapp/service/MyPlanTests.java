package org.zerock.myapp.service;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.MyPlanDTO;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/**/root-*.xml")
public class MyPlanTests {

	@Setter(onMethod_ = @Autowired)
	private MyPlanService service;
	
	@Before
	public void setup() {
		log.trace("setup() invoked");
		
		assertNotNull(this.service);
		log.info("\t + this.service: {}", this.service);
	} // setup
	
	@Test
	public void testGenerate() throws ServiceException {
		log.trace("testGenerate() invoked");
		
		String startDate = "2022-05-25";
		Date date = Date.valueOf(startDate);
		
		MyPlanDTO dto = new MyPlanDTO();
		
		dto.setPlanName("플래너명");
		dto.setUserNo(2);
		dto.setStartDate(date);
		dto.setEndDate(date);		
		
		assertNotNull(dto);
		log.info("\t + dto : {}", dto);
		
		Boolean result = this.service.generate(dto);
		log.info("\t result: {}", result);
		
	} // testGenerate
	
}
