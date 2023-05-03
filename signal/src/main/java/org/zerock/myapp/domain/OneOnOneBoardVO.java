package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Value
public class OneOnOneBoardVO {

	private Integer postno;
	private String nickname;
	private String title;
	private String content;
	private Timestamp regidate;
	private Timestamp modifydate;
	private Integer readcnt;
	private Integer reproot;
	private Integer repstep;
	private Integer repindent;
	
	public OneOnOneBoardDTO toDTO() {
		log.trace("toDTO() invoked");
		
		OneOnOneBoardDTO dto = new OneOnOneBoardDTO();
		dto.setPostno(postno);
		dto.setNickname(nickname);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setReadcnt(readcnt);
		dto.setReproot(reproot);
		dto.setRepstep(repstep);
		dto.setRepindent(repindent);
		
		log.info("\t + dto : {}", dto);
		
		return dto;
		
	}
	

}
