package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;


@Value
public class ApplicateGroupVO {
	private Integer appNo;
	private Integer outCome;
	private Timestamp appDate;
	private String appSatuts;
}
