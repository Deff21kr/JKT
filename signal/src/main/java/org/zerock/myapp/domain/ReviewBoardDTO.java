package org.zerock.myapp.domain;

import java.util.List;

import lombok.Data;

@Data
public class ReviewBoardDTO {
	//클라이언트로부터의 입력 데이터를 저장하고, 서버나 다른 계층으로 데이터를 전달
	
	
	private Integer postNo;
	private String nickName;
	private String title;
	private String content;
	private String area;
	
	private List<AttachFileDTO> attachList;

	
}//end class
