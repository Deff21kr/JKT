package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Data;


@Data
public class UserGroupDTO {
	private Integer appNo;
	private String ID;
	private Integer groupNo;
	private String outCome;
	private Timestamp appDate;
}
