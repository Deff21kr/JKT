package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.JoinMyPlanDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/**/root-*.xml"})
public class JoinMyPlanMapperTests {
	
	@Setter(onMethod_ = {@Autowired})
	private JoinMyPlanMapper mapper;
	
	@Before
	public void setup() {
		log.trace("setup invoked");
		
		assertNotNull(this.mapper);
		log.info("\t + this.mapper: {}", this.mapper);
	} // setup

	@Test
	public void testSelectList() {
		log.trace("testSelectList()  invoked");
		
		String nickName = "hyeondae";
	
		List<JoinMyPlanDTO> vo = this.mapper.selectList(nickName);
		assertNotNull(vo);
		
		log.info("\t + vo : {}", vo);
		
		
	} // testSelectList
	
	
} // end class
