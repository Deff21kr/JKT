package org.zerock.myapp.mapper;

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

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

// Spring Framework 구동
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/**/root-*.xml" })
public class OneOnOneBoardMapperTests {

	@Setter(onMethod_ = { @Autowired })
	private OneOnOneBoardMapper mapper;

	@Before
	public void setup() {
		log.trace("setup() invoked");

		assertNotNull(this.mapper);
		log.info("\t + this.mapper : {}", this.mapper);
	}

	
	@Test
	public void testSelectList() {
		log.trace("testSelectList() invoked");
		
        Criteria cri = new Criteria();
        cri.setCurrPage(20);
		
		List<OneOnOneBoardVO> boards = this.mapper.selectList(cri);
		Objects.requireNonNull(boards);
		boards.forEach(log::info);
	} // testSelectList
	
	@Test(timeout = 1000*5)
	public void testInsert() {
		log.trace("testInsert() invoked");
		
		OneOnOneBoardDTO dto = new OneOnOneBoardDTO();
		dto.setTitle("NEW_TITLE");
		dto.setContent("NEW_CONTENT");
		dto.setNickname("hyeondae");
		
		log.info("\t + dto : {}", dto);
//		---
		Integer affectedLines = this.mapper.insert(dto);
		assertNotNull(affectedLines);
		log.info("\t + result: {}", (affectedLines == 1));
		
	} // testInsert
	
	@Test(timeout = 1000*5)
	public void testSelect() {
		log.trace("testSelect() invoked");
	
		Integer postno = 15;
		OneOnOneBoardVO vo = this.mapper.select(postno);
		log.info("\t + postno : {}, vo, {}", postno, vo);
	} // testSelect
	
	@Test(timeout = 1000*5)
	public void testDelete() {
		log.trace("testDelete() invoked");
		
		Integer postno = 15;
		
		Integer affectedLines = this.mapper.delete(postno);
		log.info("postno: {}, affectedLines: {}", postno, affectedLines);
	} // testDelete
	
	@Test(timeout = 1000*5)
	public void testUpdate() {
		log.trace("testUpdate() invoked");
		
		Integer postno = 15;
		OneOnOneBoardVO vo = this.mapper.select(postno);

		assertNotNull(vo);
		
		// VO => DTO
		OneOnOneBoardDTO dto = vo.toDTO();
		dto.setTitle("UPDATE_TITLE");
		dto.setContent("UPDATE_CONTENT");

		log.info("\t + dto : {}", dto);
		
		Integer affectedLines = this.mapper.update(dto);
		log.info("affectedLines: {}, isSuccess= {}", affectedLines, (affectedLines == 1));

	} // testUpdate
	
	
	
	

}
