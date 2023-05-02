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
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ReviewBoardDTO;
import org.zerock.myapp.domain.ReviewBoardVO;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

// Spring Framework 구동
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/**/root-*.xml"})
public class ReviewBoardServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private ReviewBoardService service;
	
	@Before
	public void setup() { // 전처리
		log.trace("\n************************************** 			setup() "
				+ "\n************************************** ");
		
		Objects.requireNonNull(this.service);
		log.info("\tthis.service : {}" ,this.service);
	}
	
	@Test(timeout = 1000 * 5)
	public void testGetList() throws ServiceException {
		log.trace("\n************************************** 			testGetList() "
				+ "\n************************************** ");
		
		Criteria cri = new Criteria();
		cri.setCurrPage(1);
		
		List<ReviewBoardVO> list = this.service.getList(cri);
		
//		Objects.requireNonNull(list);
//		list.forEach(log::info);
		
	} //
	
	@Test(timeout = 1000 * 1)
	public void testRegister() throws ServiceException {
		log.trace("testRegister() invoked.");
		
		ReviewBoardDTO dto = new ReviewBoardDTO();
		dto.setTitle("영속성 끝 서비스 ㄲㄲ");
		dto.setContent("하면하지");
		dto.setNickName("Deff");
		
		log.info("\t+ dto: {}", dto);
		
//		---
		
		boolean isSuccess = this.service.register(dto);
		log.info("\t+ isSuccess: {}", isSuccess);
	} // testRegister
	
	
	@Test(timeout = 1000 * 1)
	public void testGet() throws ServiceException {
		log.trace("testGet() invoked.");
		
		int postno = 204;
		ReviewBoardVO vo = this.service.get(postno);
		
		assert vo != null;
		log.info("\t+ vo: {}", vo);
	} // testGet
	
	@Test(timeout = 1000 * 1)
	public void testModify() throws ServiceException {
		log.trace("testModify() invoked.");
		
		int postno = 205;
		ReviewBoardVO vo = this.service.get(postno);
		
		ReviewBoardDTO dto = vo.toDTO();
		dto.setContent("하자 좀");
		
		assertNotNull(dto);
		log.info("\t+ dto: {}", dto);
//		---
		
		boolean isSuccess = this.service.modify(dto);
		log.info("\t+ isSuccess: {}", isSuccess);
	} // testModify
	
	@Test(timeout = 1000 * 1)
	public void testRemove() throws ServiceException {
		log.trace("testGet() invoked.");
		
		int postno = 205;
		boolean isDeleted = this.service.remove(postno);		
		log.info("\t+ isDeleted: {}", isDeleted);
	} // testRemove
	
	
	@Test(timeout = 1000 * 1)
	public void testGetTotal() throws ServiceException {
		log.trace("testGetTotal() invoked.");
		
		int totalAmount = this.service.getTotal();		
		log.info("\t+ totalAmount: {}", totalAmount);
	} // testGetTotal
	
	
	
	
	
} // end class
