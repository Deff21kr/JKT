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
import org.zerock.myapp.domain.ReviewBoardDTO;
import org.zerock.myapp.domain.ReviewBoardVO;

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
	private ReviewBoardMapper mapper;
	
	@Before
	public void setup() { // 전처리
		log.trace("before invoked");
		
		Objects.requireNonNull(this.mapper);
		log.info("\tthis.mapper : {}", this.mapper);
	}
	
	// 테스트 메소드 이름은 반드시 접두사 test를 붙여야 함
	@Test
	public void testSelectList() {
		log.trace("testSelectList invoked");
		Criteria cri = new Criteria();
		cri.setCurrPage(1);
		
		List<ReviewBoardVO> boards = this.mapper.selectList(cri);
		
		Objects.requireNonNull(boards);
		boards.forEach(log::info);
		
	}
	
	@Test
	public void testInsert() {
		log.trace("testInsert invoked");
		
		ReviewBoardDTO dto = new ReviewBoardDTO();
		dto.setTitle("후기게시판 제목");
		dto.setContent("Like나 view는 낫널 넣을꺼면 디폴트값 0으로 해놓자");
		dto.setNickName("Deff");
//		dto.setViews(22);
//		dto.setLikes(11);
		
		log.info("\t+ dto: {}", dto);

////    ----------------------------

		Integer aff = this.mapper.insert(dto);
		assert aff != null;
		log.info("\t+ aff: {} \n\tdto : {} ", (aff == 1), dto);
	}
	
	@Test
	public void testSelect() {
		log.trace("testSelect invoked");
		Integer postno =203;
		ReviewBoardVO vo = this.mapper.select(postno);
		
		log.info("\n\t+ postno: {}, \nvo: {}", postno, vo);
	}
	
	@Test
	public void testDelete() {
		log.trace("testDelete invoked");
		Integer postno =202;
		Integer affectedLines = this.mapper.delete(postno);
		log.info("\n\tpostno: {}, \n\taffectedLines: {}, \n\tisSuccess: {}", postno, affectedLines, (affectedLines == 1));
	}
	
	@Test
	public void testUpdate() {
		log.trace("testUpdate() invoked.");
		
		Integer postno = 203;
		ReviewBoardVO vo = this.mapper.select(postno);
		
		assertNotNull(vo);
		
		// VO => DTO
		ReviewBoardDTO dto = vo.toDTO();
		
		dto.setTitle("후기 제목");
		dto.setContent("modifydate : current_date 빼기 얘떔에 등록할때 같이 찍힘");
		
		log.info("\t+ dto: {}", dto);	
//		---
		this.mapper.update(dto);
		Integer affectedLines = this.mapper.update(dto);
		log.info("\t+ dto: {}", dto);
		log.info("affectedLines: {}, isSuccess: {}", affectedLines, (affectedLines == 1));
	} // testUpdate
	
	
}	// end class
