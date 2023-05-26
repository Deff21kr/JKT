package org.zerock.myapp.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Value;

@Data
public class JoinGroupPlanDTO {
	
	private Integer planNo;
	private Integer groupNo;
	private String planName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	private String nickName;
	private Integer duration;
	
	private Integer detailPlanNo;
	private Integer planDay;
	@DateTimeFormat(pattern = "HH24:Mi")
	private Date planTime;
	private String place;
	private String detailPlan;

}
