package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;

@Value
public class GroupBoardVO {
	private Integer postNo;
	private Integer userNo;
	private String title;
	private String area;
	private Integer memberNum;
	private String recruitStatus;
	private Timestamp startDate;
	private Timestamp endDate;
	private Integer views;
	private Integer pins;
	private Timestamp regiDate;
	private Timestamp modifyDate;
	private String nickName;
	
	
}
