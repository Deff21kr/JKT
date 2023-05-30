package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;

@Value
public class GroupBoardVO {
	private Integer postNo;
	private String title;
	private String content;
	private String area;
	private Integer memberNum;
	private String recruitStatus;
	private Timestamp startDate;
	private Timestamp endDate;
	private Timestamp regiDate;
	private Timestamp modifyDate;
	private String nickName;
	private String groupName;
	private String boardName;
	private Integer readCnt;
	
}
