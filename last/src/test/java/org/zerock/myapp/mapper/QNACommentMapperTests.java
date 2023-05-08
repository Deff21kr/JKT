package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.myapp.domain.QNACommentDTO;
import org.zerock.myapp.domain.QNACommentVO;
import org.zerock.myapp.service.QnABoardService;
import org.zerock.myapp.service.QNACommentService;
import org.zerock.myapp.service.QNACommentServiceImpl;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations= { "file:src/main/webapp/WEB-INF/**/root-*.xml" })
@Log4j2
public class QNACommentMapperTests {
	
	
	@Setter(onMethod_ = @Autowired)
	private QNACommentService ser;
	
	
	@Autowired
	private QNACommentMapper dao;
	
	@Before
	public void setup() {
		log.trace("\t+ before 호추루루루루루");
		
		Objects.requireNonNull(this.ser);
		log.info("\t+ type: {}, this.service: {}", this.ser.getClass().getName(), this.ser);
	}
	
	// 댓글 생성
	@Test
	public void testCreate() {
	    IntStream.rangeClosed(1, 3).forEach(i -> {
	        QNACommentDTO dto = new QNACommentDTO();
	        
	        dto.setPostNo(1);	// 나중에 추가할거임~
	        dto.setContent("네번째 댓글테스트"+i);
	        dto.setNickName("홍기서");
	        
	        this.dao.insert(dto);

	    });
	} // testCreate
	
	
	// 댓글 삭제
	@Test
	public void testDelete() {
		Integer commentno = 30;		
		Integer affectLines = dao.deletecomment(commentno);
		
		log.info("\t+ affectLines: {}, commentNo", affectLines, commentno);
	}
	
	// 댓글 조회
	@Test
	public void testGetList(Integer postNo) {
		List<QNACommentVO> list = this.dao.selectList(postNo); 
		assertNotNull(list);
		
		list.forEach(log::info);
	}
	
	@Test
	public void testModify() {
		log.trace("testUpdate() invoked.");
		
		Integer commentno = 7;
		QNACommentVO vo = this.dao.select(commentno);
		
		assertNotNull(vo);
		
		// VO => DTO
		
		QNACommentDTO dto = vo.toDTO();
		dto.setContent("댓글수정테스트으으으");
		dto.setNickName("홍기숴어어");
		
		log.info("\t dto: {}", dto);
		
		Integer affectLines = this.dao.update(dto);
		log.info("\t affectLines: {}, isSuccess: {}", affectLines, (affectLines == 1));
	}
	
	
	
	
}
