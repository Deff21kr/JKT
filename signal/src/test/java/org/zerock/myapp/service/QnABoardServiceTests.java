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
import org.zerock.myapp.domain.QnABoardDTO;
import org.zerock.myapp.domain.QnABoardVO;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/**/root-*.xml"})

public class QnABoardServiceTests {
	@Setter(onMethod_= { @Autowired })
	private QnABoardService service;
	
	
	@Before
	public void setup() {		// 전처리(Optional)
		log.trace("setup() invoked.");
		
		assertNotNull(this.service);
		log.info("\t+ this.service : {}", this.service);
	} // setup
	
	@Test(timeout = 1000 * 2)
	public void testGetList() throws ServiceException {
		log.trace("testGetList() invoked.");
		
		Criteria cri = new Criteria();
		cri.setCurrPage(20);
		
		List<QnABoardVO> list  = this.service.getList(cri);
		
		Objects.requireNonNull(list);
		list.forEach(log::info);
			
	} // testGetList	
	
	
	@Test(timeout = 1000 * 2)
	public void testRegister() throws ServiceException {
		log.trace("testRegister() invoked.");
		
		QnABoardDTO dto = new QnABoardDTO();
		dto.setNickName("NEW_NICKNAME");
        dto.setTitle("NEW_TITLE");
        dto.setContent("NEW_CONTENT");
		log.info("\t+ dto : {}", dto);
		
		boolean isSuccess = this.service.register(dto);
		log.info("\t+ isSuccess : {}", isSuccess);
	} // testRegister	
	
	
	@Test(timeout = 1000 * 2)
	public void testGet() throws ServiceException {
		log.trace("testGet() invoked.");
		
		int postno = 100;
		QnABoardVO vo  = this.service.get(postno);
		
		assert vo != null;
		log.info("\t+ vo : {}", vo);
			
	} // testGet
	
	
	@Test(timeout = 1000 * 2)
	public void testModify() throws ServiceException {
		log.trace("testModify() invoked.");
		
		int postno = 88;
		QnABoardVO vo = this.service.get(postno);
		
		QnABoardDTO dto = vo.toDTO();
		dto.setContent("MODIFIED_CONTENT");
		
		assertNotNull(dto);
		log.info("\t+ dto : {}", dto);
		
		boolean isSuccess = this.service.modify(dto);
		log.info("\t+ isSuccess : {}", isSuccess);
	} // testModify	
	
	
	@Test(timeout = 1000 * 2)
	public void testRemove() throws ServiceException {
		log.trace("testRemove() invoked.");
		
		int postno = 100;
		boolean isDeleted = this.service.remove(postno);
		log.info("\t+ isDeleted : {}", isDeleted);
	} // testRemove	
	
	@Test(timeout = 1000 * 2)
	public void testGetTotal() throws ServiceException {
		log.trace("testGetTotal() invoked.");
		
		int totalAmount = this.service.getTotal();
		log.info("\t + totalAmount: {}", totalAmount );
	} // testGetTotal
	
	@Test(timeout = 1000 * 2)
	public void testUpdateReadcnt() throws ServiceException {
		log.trace("testUpdateReadcnt() invoked.");
		
		int postno = 100;
		Integer rc  = this.service.updateReadcnt(postno);
		
		assert rc != null;
		log.info("\t+ vo : {}", rc);
		
	} // testUpdateReadcnt
	
} // end class
