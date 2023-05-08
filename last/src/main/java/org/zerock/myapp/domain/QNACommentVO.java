package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Value
@Log4j2
public class QNACommentVO {
	private Integer commentNo;
	private String content;
	private Timestamp regiDate;
	private Timestamp modifyDate;
	private String nickName;
	private Integer postNo;
	
	public QNACommentDTO toDTO() {
		log.trace("toDTO() invoked.");
		
		QNACommentDTO dto = new QNACommentDTO();
		dto.setCommentNo(this.commentNo);
		dto.setContent(this.content);
		
		return dto;
	}
}	
