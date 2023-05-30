package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class ReviewCommentDTO {
	
	private Integer commentNo;
	private String content;
	private Integer postNo;
	private String nickName;
	
} // end class
