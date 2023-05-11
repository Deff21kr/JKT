package org.zerock.myapp.domain;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class GroupBoardDTO {
	
	private Integer postNo;
	private String title;
	private String nickName;
	private String content;
	private String area;
	private Integer memberNum;
	private String recruitstatus;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate endDate;

} // end class
