package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Value
@Log4j2
public class QnACommentVO {
	
	private Integer commentNo;
	private String content;
	private Timestamp regiDate;
	private Timestamp modifyDate;
	private String nickName;
	private Integer postNo;
	
	public QnACommentDTO toDTO() {
		log.trace("toDTO() invoked.");
		
		QnACommentDTO dto = new QnACommentDTO();
		dto.setCommentNo(this.commentNo);
		dto.setContent(this.content);
		
		return dto;
	} // QNACommentDTO
	
} // end class
