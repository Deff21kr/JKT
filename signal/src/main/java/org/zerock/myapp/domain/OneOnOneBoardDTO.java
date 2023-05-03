package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class OneOnOneBoardDTO {

	private Integer postno;
	private String nickname;
	private String title;
	private String content;
	private Integer readcnt;
	private Integer reproot;
	private Integer repstep;
	private Integer repindent;

}
