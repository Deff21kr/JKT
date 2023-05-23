package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Data;

@Data
public class GroupPlanDTO {

	private Integer planNo;
	private Integer groupNo;
	private String nickName;
	private String planName;
	private Date startDate;
	private Date endDate;
	private Integer duration;
}
