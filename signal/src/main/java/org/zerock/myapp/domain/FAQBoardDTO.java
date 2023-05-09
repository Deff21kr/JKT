package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class FAQBoardDTO {
	private Integer postNo;
	private Integer managerId;
	private String title;
	private Timestamp rbpostRegiDate;
}
