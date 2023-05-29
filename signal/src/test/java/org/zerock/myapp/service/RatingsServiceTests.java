package org.zerock.myapp.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.RatingsDTO;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

// Spring Framework 구동
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"file:src/main/webapp/WEB-INF/**/root-*.xml",
		"file:src/main/webapp/WEB-INF/**/servlet-*.xml"
})
public class RatingsServiceTests {
	@Setter(onMethod_ = @Autowired)
	private RatingsService ser;
	
	@Before
	public void setup() {
		log.trace("setup() invoked");
		
		assertNotNull(this.ser);
		log.info("\t + this.service : {}", this.ser);
	} // setup
	
	@Test
	public void getRated() throws ServiceException {
		String nickName = "강대수";
		List<RatingsDTO> vo = this.ser.selectRating(nickName);
		log.info("\t+ vo2222222222=========: {}", vo);
	}
	@Test
	public void getRated2() throws ServiceException {
		String nickName = "강대수";
		Double vo = this.ser.getRatedRating(nickName);
		log.info("\t+ vo2222222222=========: {}", vo);
	}
}
