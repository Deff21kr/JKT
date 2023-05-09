package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;

@Value
public class GrouupBoardCommentVO {
	private Integer commentNo;
	private String content;
	private Timestamp regiDate;
	private Timestamp modifyDate;
} // end class
