package org.zerock.myapp.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Value;

@Value
public class MyPlanVO {

	private Integer planNo;
	private String planName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	private String nickName;
	private Integer duration;
}
