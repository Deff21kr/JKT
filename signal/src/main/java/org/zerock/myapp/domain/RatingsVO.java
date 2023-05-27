package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class RatingsVO {
	private Integer ID;
	private String raterUserID;
	private String ratedUserID;
	private Integer rating;
}
