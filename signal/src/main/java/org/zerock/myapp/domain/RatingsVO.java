package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class RatingsVO {
	private Integer ID;
	private String raterUserNickName;
	private String ratedUserNickName;
	private Integer rating;
}
