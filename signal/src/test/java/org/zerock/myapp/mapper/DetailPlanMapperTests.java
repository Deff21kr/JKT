package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.DetailPlanDTO;
import org.zerock.myapp.domain.DetailPlanVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/**/root-*.xml"})
public class DetailPlanMapperTests {

	@Setter(onMethod_ = {@Autowired})
	private DetailPlanMapper mapper;
	
	@Before
	public void setUp() {
		log.trace("setUp() invoked");

		assertNotNull(this.mapper);
		log.info("\t + this.mapper : {}",this.mapper);
	} //setUp

	@Test
	public void testSelectList() {
		log.trace("testSelectList() invoked");
		
		Integer planNo =39;
		
		List<DetailPlanVO> vo = this.mapper.selectList(planNo);
		assertNotNull(vo);
		
		log.info("\t + vo : {}", vo);
	} // testSelectList
	
	@Test
	public void testInsert() {
		log.trace("testInsert() invoked");
		

		// LocalDateTime 생성
		LocalDateTime localDateTime = LocalDateTime.now();

		// LocalDateTime을 Date로 변환
		ZoneId zoneId = ZoneId.systemDefault();
		Date date = Date.from(localDateTime.atZone(zoneId).toInstant());
		
		DetailPlanDTO dto = new DetailPlanDTO();
		
		dto.setPlanNo(39);
		dto.setPlanDay(1);
		dto.setPlanTime(date);
		dto.setPlace("인천");
		dto.setDetailPlan("어디갈까요?");

		log.info("\t dto : {}", dto);
		Integer affectedLines = this.mapper.insert(dto);
	
		assertNotNull(affectedLines);
		log.info("\t result : {}", affectedLines == 1);
		
	}// testInsert
	
	@Test
	public void testSelect() {
		log.trace("testSelect() invoked");
	
		Integer detailPlanNo = 46;
		
		DetailPlanVO vo = this.mapper.select(detailPlanNo);
		assertNotNull(vo);
		log.info("\t + vo : {}", vo);
		
	} // testSelect
	
	@Test
	public void testUpdate() {
		log.trace("testUpdate() invoked");
		
		// LocalDateTime 생성
		LocalDateTime localDateTime = LocalDateTime.now();

		// LocalDateTime을 Date로 변환
		ZoneId zoneId = ZoneId.systemDefault();
		Date date = Date.from(localDateTime.atZone(zoneId).toInstant());
		
		Integer detailPlanNo = 46;
		

		DetailPlanDTO dto = new DetailPlanDTO();
		dto.setPlace("뉴서울");
		dto.setDetailPlan("새로운 계획");
		dto.setPlanTime(date);
		dto.setDetailPlanNo(detailPlanNo);
		
		log.info("\t dto : {}", dto);
		Integer affectedLines = this.mapper.update(dto);

		assertNotNull(affectedLines);
		log.info("\t + result :{}", affectedLines == 1);

		
	} // testUpdate
	
	
	@Test
	public void testDelete() {
		log.trace("testDelete() invoked");
		
		Integer detailPlanNo = 3;
		
		Integer affectedLines = this.mapper.delete(detailPlanNo);
		assertNotNull(affectedLines);
		log.info("\t + result :{}", affectedLines == 1);
	} // testDelete
	
	
}
