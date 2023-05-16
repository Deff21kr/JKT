package org.zerock.myapp.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class GroupBoardCriteria {
	private Integer currPage = 1;
	private Integer amount = 16;
	private Integer pagesPerPage = 5;
	private String area;
	private Integer memberNum;
	private String recruitStatus;
	@DateTimeFormat(pattern="yyyy-MM-dd")private LocalDate startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")private LocalDate endDate;

} // end class
