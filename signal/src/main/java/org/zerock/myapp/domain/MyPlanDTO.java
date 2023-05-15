package org.zerock.myapp.domain;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MyPlanDTO {

	private Integer planNo;
	private Integer userNo;
	private String planName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.sql.Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.sql.Date endDate;
	
}
