package org.zerock.myapp.mapper;

import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ReviewVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

// JUnit4
// 1) 디폴트 컨스트럭쳐를 가진다

@Log4j2
@NoArgsConstructor

// Spring Framework 구동
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/**/root-*.xml"})
public class ReviewBoardMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private ReviewMapper mapper;
	
	@Before
	public void setup() { // 전처리
		log.trace("before invoked");
		
		Objects.requireNonNull(this.mapper);
		log.info("\tthis.mapper : {}", this.mapper);
	}
	
	// 테스트 메소드 이름은 반드시 접두사 test를 붙여야 함
	@Test
	public void testSelectList() {
		log.trace("222 invoked");
		Criteria cri = new Criteria();
		cri.setCurrPage(20);
		
		List<ReviewVO> boards = this.mapper.selectList(cri);
		
		Objects.requireNonNull(boards);
		boards.forEach(log::info);
		
		
	}
	
	
}	// end class
