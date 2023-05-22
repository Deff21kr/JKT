package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class CommentCriteria {
	private Integer commentCurrPage = 1;
	
	private Integer commentAmount = 10;
	
	private Integer commentPagesPerPage =5;
} // end class
