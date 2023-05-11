package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class GroupsDTO {
	
	private Integer groupNo;
	private String groupName;
	private String recruitStatus;
	private Integer maxMember;
	private Integer CurrentMember;
	private String Area;
	private Integer postNo;

}
