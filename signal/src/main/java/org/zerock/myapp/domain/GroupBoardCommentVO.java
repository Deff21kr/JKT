package org.zerock.myapp.domain;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;
import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Value
@Log4j2
public class GroupBoardCommentVO {
	
	private Integer commentNo;
	private String content;
	private Timestamp modifyDate;
	private Integer postNo;
	private String nickName;
	private Timestamp regiDate;
	
	
	public GroupBoardCommentDTO toDTO() {
		log.trace("toDTO() invoked.");
		
		GroupBoardCommentDTO dto = new GroupBoardCommentDTO();
		dto.setCommentNo(commentNo);
		dto.setContent(content);
		return dto;
	} // QNACommentDTO
	
} // end class
