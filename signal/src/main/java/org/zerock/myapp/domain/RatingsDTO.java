package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class RatingsDTO {
	private String raterUserNickName;
	private String ratedUserNickName;
	private Integer rating;
	private Double ratedRating;
	
}
