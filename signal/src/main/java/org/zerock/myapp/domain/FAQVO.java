package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;

@Value
public class FAQVO {
	private Integer postNo;
	private Integer managerId;
	private String title;
	private Timestamp rbpostRegiDate;
}
