package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Value
public class QnABoardVO {	
	private Integer postno;
	private String nickname;
	private String title;
	private String content;
	private Timestamp regidate;
	private Timestamp modifydate;
	private Integer readcnt;
	
	public QnABoardDTO toDTO() {
		log.trace("toDTO() invoked.");
		
		QnABoardDTO dto = new QnABoardDTO();
		dto.setPostno(postno);
		dto.setNickname(nickname);
		dto.setTitle(title);
		dto.setContent(content);
		
		log.info("\t + dto:{}", dto);
		
		return dto;
	} // toDTO

} // end class







