package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class ReviewDTO {
	private Integer postno;
	private Integer userno;
	private String nickname;
	private String title;
	private String content;
	private Integer views;
	private Integer likes;
}
