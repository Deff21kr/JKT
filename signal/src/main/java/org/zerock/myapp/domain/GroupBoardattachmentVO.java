package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;

@Value
public class GroupBoardattachmentVO {
	private Integer fileNo;
	private String fileName;
	private String fileXtension;
	private String fileSize;
	private Timestamp fileDate;
}
