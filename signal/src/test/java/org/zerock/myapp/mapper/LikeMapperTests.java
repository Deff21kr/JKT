package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.myapp.domain.LikeDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/**/root-*.xml"})
public class LikeMapperTests {

	@Setter(onMethod_ = {@Autowired})
	private LikeMapper mapper;
	
	@Before
	public void setup() {
		log.trace("setup() invoked.");
		
		assertNotNull(this.mapper);
		log.info("\t + this.mapper : {}", this.mapper);
		
	} // setup
	
	@Test(timeout = 1000 * 2)
	public void testInsert() throws Exception{
		log.trace("testInsert() invoked.");
		
		Integer postNo = 290;
		String nickName = "hyeonna";
		
		LikeDTO like = new LikeDTO();
		like.setPostNo(postNo);
		like.setNickName(nickName);
		log.info(like);
		
    	int result = 0;
    	result = mapper.insert(like);
    	log.info("결과: ", result);
		
	} // testInsert

} // end class
