package org.zerock.myapp.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Value;

@Value
public class GroupDetailPlanVO {

	private Integer detailPlanNo;
	private Integer planNo;
	private Integer planDay;
	@DateTimeFormat(pattern = "HH24:Mi")
	private Date planTime;
	private String place;
	private String detailPlan;

}
