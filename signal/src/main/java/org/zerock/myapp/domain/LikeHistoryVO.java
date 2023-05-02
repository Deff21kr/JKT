package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class LikeHistoryVO {
	private Integer userActNo;
	private String title;
	private Integer value;
}
