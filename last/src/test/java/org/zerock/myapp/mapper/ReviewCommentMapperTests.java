package org.zerock.myapp.mapper;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.myapp.domain.ReviewCommentVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations= { "file:src/main/webapp/WEB-INF/**/root-*.xml" })
@Log4j2
public class ReviewCommentMapperTests {
	
	
	@Setter(onMethod_ = @Autowired)
	private QNACommentMapper map;
	
	
	@Test
	public void testCreate() {
	    IntStream.rangeClosed(1, 10).forEach(i -> {
	        ReviewCommentVO vo = new ReviewCommentVO();

	        vo.setPostNo(95);
	        vo.setContent("댓글테스트" + i);
	        
	        
	        
	    });
	}
	
	@Test
	public void testDelete() {
	        
	        
	    }
	}

