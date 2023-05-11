package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Value
@Log4j2
public class QNACommentVO {
	private Integer commentno;
	private String content;
	private Timestamp regidate;
	private Timestamp modifydate;
	private String nickname;
	private Integer postno;
	
	public QNACommentDTO toDTO() {
		log.trace("toDTO() invoked.");
		
		QNACommentDTO dto = new QNACommentDTO();
		dto.setCommentno(this.commentno);
		dto.setContent(this.content);
		
		return dto;
	}
}	
