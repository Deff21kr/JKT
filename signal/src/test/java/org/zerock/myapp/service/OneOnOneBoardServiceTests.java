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
import org.zerock.myapp.domain.OneOnOneBoardDTO;
import org.zerock.myapp.domain.OneOnOneBoardVO;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/**/root-*.xml" })
public class OneOnOneBoardServiceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private OneOnOneBoardService service;
	
	@Before
	public void setup() {
		log.trace("setup() invoked");
		
		assertNotNull(this.service);
		log.info("\t + this.service : {}", this.service);
	} // setup

	@Test
	public void testGetList() throws ServiceException {
		log.trace("testGetList() invoked");
		
		Criteria cri = new Criteria();
		cri.setCurrPage(1);

		List<OneOnOneBoardVO> list = this.service.getList(cri);
		
		Objects.requireNonNull(list);
		list.forEach(log::info);
	} // testGetList
	
	@Test
	public void testRegister() throws ServiceException {
		log.trace("testRegister() invoked");
		
		OneOnOneBoardDTO dto = new OneOnOneBoardDTO();
		dto.setNickName("hyeondaeA");
		dto.setTitle("New_titleA");
		dto.setContent("New_contentA");
		
		assertNotNull(dto);
		log.info("\t + dto: {}", dto);
		
//		---
		
		boolean isSuccess = this.service.register(dto);
		log.info("\t + isSuccess : {}", isSuccess);
	} // testRegister
	
	@Test
	public void testGet() throws ServiceException {
		log.trace("testGet() invoked");
		
		Integer postno = 115;
		
		OneOnOneBoardVO vo = this.service.get(postno);
		assertNotNull(vo);
		log.info("\t + vo : {}", vo);
		
	} // testGet
	
	@Test
	public void testModify() throws ServiceException {
		log.trace("testModify() invoked");
		
		Integer postno = 115;
		OneOnOneBoardVO vo = this.service.get(postno);
		
		assertNotNull(vo);
		log.info("\t + vo: {}", vo);
		
		OneOnOneBoardDTO dto = vo.toDTO();
		dto.setTitle("Modify_Title");
		dto.setContent("Modify_content");
		
		assertNotNull(dto);
		log.info("\t + dto : {}", dto);
		
		Boolean isSuccess = this.service.modify(dto);
		log.info("\t + isSuccess : {}", isSuccess);
	} // testModify
	
	@Test
	public void testRemove() throws ServiceException {
		log.trace("testRemove() invoked");
		
		Integer postno = 3;
		Boolean isDeleted = this.service.remove(postno);
		log.info("\t + isDeleted : {}", isDeleted);
		
	} // testRemove
	
	
	@Test
	public void testReply() throws ServiceException {
		log.trace("testReply() invoked");
	
		OneOnOneBoardDTO dto = new OneOnOneBoardDTO();
		dto.setNickName("hyeondaeTTT2-1");
		dto.setTitle("serviceTestTitle2-1");
		dto.setContent("plz two-one clear");
		dto.setRepRoot(116);
		dto.setRepStep(1);
		dto.setRepIndent(0);
		
		this.service.reply(dto);
		
	} // testReply
	
	
	
	

}
