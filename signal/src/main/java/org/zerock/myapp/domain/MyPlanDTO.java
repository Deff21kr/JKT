package org.zerock.myapp.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MyPlanDTO {

	private Integer planNo;
	private String planName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private String nickName;
	private Integer  duration;
//	private Integer duration;
	
	
	
}
