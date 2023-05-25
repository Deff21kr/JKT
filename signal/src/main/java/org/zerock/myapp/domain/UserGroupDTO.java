package org.zerock.myapp.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class UserGroupDTO {
	private Integer appNo;
	private String nickName;
	private Integer groupNo;
	private String groupName;
	private String area;
	private String recruitStatus;
	private String outCome;
	private Integer memberNum;
	private Integer currentMember;
	private String postNo;
	
    private String title;
    private String content;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    private String writer;
	
	
}
