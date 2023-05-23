package org.zerock.myapp.domain;

import java.util.Date;

import lombok.Value;

@Value
public class GroupPlanVO {

	private Integer planNo;
	private Integer groupNo;
	private String nickName;
	private String planName;
	private Date startDate;
	private Date endDate;
	private Integer duration;
}
