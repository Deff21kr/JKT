package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.RatingsDTO;

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
public class RatingsMapperTests {
	@Setter(onMethod_ = @Autowired)
	private RatingsMapper map;
	
	@Before
	public void setup() {
		log.trace("setup() 인보크");

		assertNotNull(this.map);
		log.info("\tthis.mapper : {}", this.map);
		
//		assertNotNull(this.authMapper);
//		log.info("\tthis.authMapper : {}", this.authMapper);
	
	}
	
	@Test(timeout = 1000*10)
	public void testRating() {
		log.trace("점수 주고받고 조회까지 키야아아아아앙");
		String nickName2 = "강대수";
		String nickName = "한글";
		Integer rating = 1;
//		Integer vo = this.map.setRaterUserNickName(nickName, nickName2,rating);
		
		List<RatingsDTO> dto = this.map.selectRating(nickName);
		
//		log.info("\t+ 점수우우무너ㅜ엄ㄴ웜눠웜누: ({}, {})", vo, dto);
	}
}
