package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Value
@Log4j2
public class ReviewCommentVO {
	
	private Integer commentNo;
	private String content;
	private Timestamp regiDate;
	private Timestamp modifyDate;
	private Integer postNo;
	private String nickName;
	
	
	public ReviewCommentDTO toDTO() {
		log.trace("toDTO() invoked.");
		
		ReviewCommentDTO dto = new ReviewCommentDTO();
		dto.setCommentNo(this.commentNo);
		dto.setContent(this.content);
		
		return dto;
	} // ReviewCommentDTO
	
} // end class
