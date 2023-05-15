package org.zerock.myapp.service;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.MyPlanDTO;
import org.zerock.myapp.domain.MyPlanVO;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/**/root-*.xml")
public class MyPlanServiceTests {

	@Setter(onMethod_ = @Autowired)
	private MyPlanService service;
	
	@Before
	public void setup() {
		log.trace("setup() invoked");
		
		assertNotNull(this.service);
		log.info("\t + this.service: {}", this.service);
	} // setup
	
	@Test
	public void testSelectList() throws ServiceException {
		log.trace("testSelectList() invoked");
		
		Criteria cri = new Criteria();
		cri.setCurrPage(1);
		
		String nickName = "hyeondae";
		
		List<MyPlanVO> vo = this.service.getList(cri, nickName);
		assertNotNull(vo);
		
		log.info("\t + vo : {}", vo);
		
	} // testSelectList
	
	@Test
	public void testGenerate() throws ServiceException {
		log.trace("testGenerate() invoked");
		
		String startDate = "2023-05-16";
		Date startdate = Date.valueOf(startDate);
		

		String endDate = "2023-05-20";
		Date enddate = Date.valueOf(endDate);
		
		MyPlanDTO dto = new MyPlanDTO();
		
		dto.setPlanName("서울여행");
		dto.setStartDate(startdate);
		dto.setEndDate(enddate);	
		dto.setNickName("hyeondae");
		
		assertNotNull(dto);
		log.info("\t + dto : {}", dto);
		
		Boolean result = this.service.generate(dto);
		log.info("\t result: {}", result);
		
	} // testGenerate
	
	@Test
	public void testGet() throws ServiceException {
		log.trace("testGet() invoked");
		
		Integer planNo = 12;
		
		MyPlanVO vo = this.service.get(planNo);
		assertNotNull(vo);
		
		log.info("\t + vo: {}", vo);
		
	} // testGet
	
	
	
}
