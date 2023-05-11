package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class QNACommentDTO {
	private Integer commentno;
	private String content;
	private Integer postno;
	private String nickname;
}
