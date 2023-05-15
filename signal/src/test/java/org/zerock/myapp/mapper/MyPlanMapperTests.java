package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.MyPlanDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/**/root-*.xml"})
public class MyPlanMapperTests {
	
	@Setter(onMethod_ = {@Autowired})
	private MyPlanMapper mapper;
	
	@Before
	public void setup() {
		log.trace("setup() invoked");
	
		assertNotNull(this.mapper);
		log.info("\t + mapper : {}", this.mapper);
	} // setup
	
	@Test
	public void testMake() {
		log.trace("testMake() invoked");
		
		String startDate = "2022-05-22";
		Date date = Date.valueOf(startDate);
		
		MyPlanDTO dto = new MyPlanDTO();
		dto.setPlanName("플래너명");
		dto.setUserNo(1);
		dto.setStartDate(date);
		dto.setEndDate(date);

		log.info("\t + dto: {}", dto);
		
		Integer affectedLines = this.mapper.make(dto);
		assertNotNull(affectedLines);
		log.info("\t + result : {}", affectedLines == 1);
	} // testMake
	
}
