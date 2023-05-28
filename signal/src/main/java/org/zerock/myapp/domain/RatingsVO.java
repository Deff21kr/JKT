package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class RatingsVO {
	private Integer ID;
	private String raterUserNickName;
	private String ratedUserNickName;
	private Integer rating;
	private Double ratedRating;
	
public RatingsDTO toDTO() {
		
		RatingsDTO dto = new RatingsDTO();
		dto.setRatedRating(ratedRating);
		dto.setRatedUserNickName(ratedUserNickName);
		dto.setRaterUserNickName(raterUserNickName);
		dto.setRating(rating);
		return dto;
		
		
	}
}
