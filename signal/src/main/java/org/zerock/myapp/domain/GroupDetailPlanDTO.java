package org.zerock.myapp.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class GroupDetailPlanDTO {

	private Integer detailPlanNo;
	private Integer planNo;
	private Integer planDay;
	@DateTimeFormat(pattern = "HH:mm")
	private Date planTime;
	private String place;
	private String detailPlan;
}
