package org.zerock.myapp.mapper;

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
		
		String startDate = "2023-05-02";
		Date startdate = Date.valueOf(startDate);
		

		String endDate = "2023-05-14";
		Date enddate = Date.valueOf(endDate);
		
		MyPlanDTO dto = new MyPlanDTO();
		dto.setPlanName("부산여행");
		dto.setStartDate(startdate);		
		dto.setEndDate(enddate);
		dto.setNickName("hyeondae");
		
		log.info("\t + dto: {}", dto);
		
		Integer affectedLines = this.mapper.make(dto);
		assertNotNull(affectedLines);
		log.info("\t + result : {}", affectedLines == 1);
	} // testMake
	
	@Test
	public void testSelectList() {
		log.trace("testSelectList() invoked");		
		Criteria cri = new Criteria();
		cri.setCurrPage(1);

		String nickName = "hyeondae";
		
		List<MyPlanVO> boards = this.mapper.selectList(cri, nickName);
//		
//		assertNotNull(boards);
//		log.info("\t boards: {}", boards);
		
	} // testSelectList

	@Test
	public void testSelect() {
		log.trace("testSelect() invoked");
		
		Integer planNo =11;
		MyPlanVO vo = this.mapper.select(planNo);
		
		assertNotNull(vo);
		log.info("\t + vo: {}", vo);
		
		
	} // testSelect
	
	@Test
	public void testDelete() {
		log.trace("testDelete() invoked");
		
		Integer planNo = 50;
		
		Integer affectedLines = this.mapper.delete(planNo);
		assertNotNull(affectedLines);
		log.info("affectedLines : {}", affectedLines);
		
	} // testDelete

	@Test
	public void testUpdate() {
		log.trace("testUpdate() invoked");
		
		String planName = "경주 여행";
		Integer planNo = 50;


		Integer affectedLines = this.mapper.update(planName, planNo);
		assertNotNull(affectedLines);
		log.info("affectedLines : {}", affectedLines);
	} // testUpdate

	
}
