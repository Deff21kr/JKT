package org.zerock.myapp.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Value
public class ReviewBoardVO {
	//반드시 컬럼의 순서대로 선언할 것 (확인 필요)
	private Integer postNo;	
	private String nickName;
	private String title;
	private String content;
	
	private Integer readCnt;
	private Timestamp regiDate;
	private Timestamp modifyDate;
	private String area;
	
//	private Integer replyCnt;
	private List<AttachFileDTO> attachList;

	
	
	public ReviewBoardDTO toDTO() {
		log.trace("toDTO() invoked");

		ReviewBoardDTO dto = new ReviewBoardDTO();
		dto.setPostNo(postNo);
		dto.setNickName(nickName);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setAttachList(attachList);
		dto.setArea(area);
		log.info("\t+ dto: {}");

		return dto;
		
	}//todDTO

}//end class
