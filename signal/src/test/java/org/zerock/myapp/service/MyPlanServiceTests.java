package org.zerock.myapp.service;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.DetailPlanDTO;
import org.zerock.myapp.domain.DetailPlanVO;
import org.zerock.myapp.domain.JoinMyPlanDTO;
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
	
	@Test
	public void testRemove() throws ServiceException {
		log.trace("testRemove() invoked");
		
		Integer planNo = 55;
		
		Boolean result = this.service.remove(planNo);
		log.info("result : {}", result);
	} // testRemove
	
	@Test
	public void testModify() throws ServiceException {
		log.trace("testModify() invoked");
		
		String planName = "경주 여행";
		Integer planNo = 55;
		
		Boolean result = this.service.modify(planName, planNo);
		assertNotNull(result);
		
		log.info("result : {}", result);
		
	} // testModify
	
	
//	==============DETAIL PLAN TEST SERVICE =================
	@Test
	public void testGetDetailPlanList() throws ServiceException {
		log.trace("testGetDetailPlanList() invoked");
		
		Integer planNo = 39;
		Integer plannerType = 0;
		
		List<DetailPlanVO> vo = this.service.getDetailPlanList(planNo);
		assertNotNull(vo);
		log.info("\t + vo : {}", vo);
		
	} // testGetDetailPlanList
	
	@Test
	public void testRegisterDetailPlan() throws ServiceException {
		log.trace("testRegisterDetailPlan() invoked");

		java.util.Date date = new java.util.Date();
		
		
		DetailPlanDTO dto = new DetailPlanDTO();
		dto.setPlanNo(39);
		dto.setPlanDay(2);
		dto.setPlace("강원도");
		dto.setPlanTime(date);
		dto.setDetailPlan("뭐 할까요?");
		
		assertNotNull(dto);

		
		Boolean result = this.service.registerDetailPlan(dto);
		log.info("\t + result : {}", result);
	} // testRegisterDetailPlan
	
	@Test
	public void testGetDetailPlan() throws ServiceException {
		log.trace("testGetDetailPlan() invoked");
		
		Integer detailPlanNo = 10;
		
		DetailPlanVO vo = this.service.getDetailPlan(detailPlanNo);
		assertNotNull(vo);
		log.info("\t + vo: {}", vo);
		
	} // testGetDetailPlan
	
	
	@Test
	public void testModifyDetailPlan() throws ServiceException {
		log.trace("testModifyDetailPlan invoked");
		
		java.util.Date date = new java.util.Date();

		
		DetailPlanDTO dto = new DetailPlanDTO();
		dto.setDetailPlanNo(10);
		dto.setDetailPlan("수정 완료");
		dto.setPlace("일산");
		dto.setPlanTime(date);
		
		assertNotNull(dto);
		log.info("\t + dto : {}", dto);
		Boolean result = this.service.modifyDetailPlan(dto);
		log.info("\t +result : {}", result);
		
	} // testModifyDetailPlan
	
	
	@Test
	public void testRemoveDetailPlan() throws ServiceException {
		log.trace("removeDetailPlan() invoked");
		
		Integer detailPlanNo = 6;
		
		Boolean result = this.service.removeDetailPlan(detailPlanNo);
		log.info("\t + result = {}", result);
		
	} // removeDetailPlan
	
	@Test
	public void testJoinList() throws ServiceException {
		log.trace("testJoinList() invoked");
		
		String nickName = "hyeondae";
		Integer plannerType = 0;
		
		List<JoinMyPlanDTO> dto = this.service.joinList(nickName);
		assertNotNull(dto);
		
		log.info("dto : {}", dto);
		
	} // testJoinList
}
