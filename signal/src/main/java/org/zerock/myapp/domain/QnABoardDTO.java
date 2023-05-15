package org.zerock.myapp.domain;


import lombok.Data;

@Data			
public class QnABoardDTO {
	
	private Integer postNo;
	private String nickName;
	private String title;
	private String content;
	private Integer readCnt;
	
} // end class
