package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class PinVO {
	private Integer pinNo;
	private Integer postNo;
	private String nickName;
	
	public PinDTO toDTO() {
		PinDTO dto = new PinDTO();
		
		dto.setNickName(nickName);
		dto.setPinNo(pinNo);
		dto.setPostNo(postNo);
		
		return dto;
	}
	
}
