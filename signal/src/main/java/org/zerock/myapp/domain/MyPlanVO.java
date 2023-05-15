package org.zerock.myapp.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Value;

@Value
public class MyPlanVO {

	private Integer planNo;
	private String planName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private String nickName;
	private Integer duration;
}
