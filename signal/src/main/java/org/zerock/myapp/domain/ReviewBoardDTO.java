package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class ReviewBoardDTO {
	private Integer postno;
	private String nickName;
	private String title;
	private String content;
	private Integer views;
	private Integer likes;
}
