package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class LikeVO {
	private Integer LikeNo;
	private Integer postNo;
	private String nickName;
	
	public LikeDTO toDTO() {
		LikeDTO dto = new LikeDTO();
		
		dto.setLikeNo(LikeNo);
		dto.setPostNo(postNo);
		dto.setNickName(nickName);
	
		return dto;
	}
	
} // end class
