package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class PinHistoryVO {
	private Integer userActNo;
	private String title;
	private Integer pin;
}
