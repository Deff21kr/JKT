package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class OneOnOneBoardDTO {

	private Integer postNo;
	private String nickName;
	private String title;
	private String content;
	private Integer repRoot;
	private Integer repStep;
	private Integer repIndent;

}
