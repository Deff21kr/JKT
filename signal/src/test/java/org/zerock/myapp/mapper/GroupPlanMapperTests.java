package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupPlanDTO;
import org.zerock.myapp.domain.GroupPlanVO;
import org.zerock.myapp.domain.GroupsDTO;
import org.zerock.myapp.domain.MyPlanVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/**/root-*.xml")
public class GroupPlanMapperTests {
	
	@Setter(onMethod_ = {@Autowired})
	private GroupPlanMapper mapper;

	@Test
	public void setup() {
		log.trace("setup invoked");
		
		assertNotNull(this.mapper);
		log.info("\t + this.mapper : {}", this.mapper);
	} //
	
	@Test
	public void testSelectList() {
		log.trace("셀렉트 리스트 인보크");
	
		Criteria cri = new Criteria();
		cri.setCurrPage(1);

		String nickName = "hyeondae";
		
		List<GroupPlanVO> vo =this.mapper.selectList(cri, nickName);
		assertNotNull(vo);
		
		log.info("\t + vo : {}", vo);
	} // testSelectList
	
	@Test
	public void testMake () {
		log.trace("testMake() invoked");
		
		String startDate = "2023-05-02";
		Date startdate = Date.valueOf(startDate);
		

		String endDate = "2023-05-14";
		Date enddate = Date.valueOf(endDate);
		
		GroupPlanDTO dto = new GroupPlanDTO();
		dto.setGroupNo(50);
		dto.setNickName("hyeondae");
		dto.setStartDate(startdate);
		dto.setEndDate(enddate);
		
		
		Integer affectedLines = this.mapper.make(dto);
		assertNotNull(affectedLines);
		log.info("\t result : {}", affectedLines == 1);
		
	} // testMake
	

	@Test
	public void testSelect() {
		log.trace("testSelect() invoked");
		
		Integer planNo =5;
		GroupPlanVO vo = this.mapper.select(planNo);
		
		assertNotNull(vo);
		log.info("\t + vo: {}", vo);
		
		
	} // testSelect
	
	@Test
	public void testDelete() {
		log.trace("testDelete() invoked");
		
		Integer planNo = 5;
		
		Integer affectedLines = this.mapper.delete(planNo);
		assertNotNull(affectedLines);
		log.info("affectedLines : {}", affectedLines);
		
	} // testDelete

	@Test
	public void testUpdate() {
		log.trace("testUpdate() invoked");
		
		String planName = "경주 여행";
		Integer planNo = 5;


		Integer affectedLines = this.mapper.update(planName, planNo);
		assertNotNull(affectedLines);
		log.info("affectedLines : {}", affectedLines);
	} // testUpdate

	
	@Test
	public void testGroupNameList() {
		log.trace("testgroupNameList invoked");
		
		String nickName = "hyeondae";
		List<Map<String, Integer>> dto = this.mapper.groupNameList(nickName);
		assertNotNull(dto);
		
		log.info("\t + nickName : {}", nickName);
	} // testgroupNameList
}
