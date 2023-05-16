package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Data;


@Data
public class UserGroupDTO {
	private Integer appNo;
	private String ID;
	private Integer groupNo;
	private String groupName;
	private String area;
	private String recruitStatus;
	private String outCome;
	private Integer memberNum;
	private Integer currentMember;
	private String postNo;
	
	
}
