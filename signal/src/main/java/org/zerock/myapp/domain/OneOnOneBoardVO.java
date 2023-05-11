package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Value
public class OneOnOneBoardVO {

	private Integer postNo;
	private String nickName;
	private String title;
	private String content;
	private Timestamp regiDate;
	private Timestamp modifyDate;
	private Integer repRoot;
	private Integer repStep;
	private Integer repIndent;
	
	public OneOnOneBoardDTO toDTO() {
		log.trace("toDTO() invoked");
		
		OneOnOneBoardDTO dto = new OneOnOneBoardDTO();
		dto.setPostNo(postNo);
		dto.setNickName(nickName);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setRepRoot(repRoot);
		dto.setRepStep(repStep);
		dto.setRepIndent(repIndent);
		
		log.info("\t + dto : {}", dto);
		
		return dto;
		
	}
	

}
