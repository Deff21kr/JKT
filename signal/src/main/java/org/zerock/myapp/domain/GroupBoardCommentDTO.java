package org.zerock.myapp.domain;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

@Data
public class GroupBoardCommentDTO {
	
	private Integer commentNo;
	private String content;
	private Timestamp modifyDate;
	private Integer postNo;
	private String nickName;
	private Date regiDate;
	
} // end class
