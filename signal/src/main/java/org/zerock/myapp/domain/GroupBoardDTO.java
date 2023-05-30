package org.zerock.myapp.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class GroupBoardDTO {
	
	private Integer postNo;
	private String title;
	private String content;
	private String area;
	private Integer memberNum;
	private String recruitStatus;
	@DateTimeFormat(pattern="yyyy-MM-dd")private LocalDate startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")private LocalDate endDate;
	private String nickName;
	private String groupName;
	private String boardName;
	private Integer readCnt;

} // end class
