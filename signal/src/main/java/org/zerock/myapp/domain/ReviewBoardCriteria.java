package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class ReviewBoardCriteria {
	
	private Integer currPage = 1;
	private Integer amount = 9;
	private Integer pagesPerPage = 5;

} // end class
