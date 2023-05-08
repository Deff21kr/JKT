package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class CommentHistoryVO {
	private Integer userActNo;
	private Integer userNo;
	private Integer postNo;
	private String title;
	private String content;
}
