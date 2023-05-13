package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Value
public class QnABoardVO {	
	private Integer postNo;
	private String nickName;
	private String title;
	private String content;
	private Timestamp regiDate;
	private Timestamp modifyDate;
	private Integer readCnt;
	
	public QnABoardDTO toDTO() {
		log.trace("toDTO() invoked.");
		
		QnABoardDTO dto = new QnABoardDTO();
		dto.setPostNo(postNo);
		dto.setNickName(nickName);
		dto.setTitle(title);
		dto.setContent(content);
		
		log.info("\t + dto:{}", dto);
		
		return dto;
	} // toDTO

} // end class







