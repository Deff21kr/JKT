package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Value;

@Value
public class DetailPlanVO {

	private Integer detailPlanNo;
	private Integer planNo;
	private String planDay;
	private Date planTime;
	private String place;
	private String detailPlan;
	
}
