package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Value
public class QnABoardVO {	// POJO, 자바빈즈 클래스여야 함
	private Integer postno;
	private String title;
	private String content;
	private String nickname;
	private Timestamp regidate;
	private Timestamp modifydate;
	
	public QnABoardDTO toDTO() {
		log.trace("toDTO() invoked.");
		
		QnABoardDTO dto = new QnABoardDTO();
		dto.setPostno(postno);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setNickname(nickname);
		
		log.info("\t + dto:{}", dto);
		
		return dto;
	} // toDTO

} // end class







