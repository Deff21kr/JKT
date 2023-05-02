package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Value
public class ReviewBoardVO {
	
	private Integer postno;
	private String nickName;
	private String title;
	private String content;
	private Integer views;
	private Integer likes;
	private Timestamp regidate;
	private Timestamp modifydate;

	public ReviewBoardDTO toDTO() {
		log.trace("toDTO() invoked");

	ReviewBoardDTO dto = new ReviewBoardDTO();
	dto.setPostno(postno);
	dto.setNickName(nickName);
	dto.setTitle(title);
	dto.setContent(content);
	dto.setViews(views);
	dto.setLikes(likes);

	log.info("\t+ dto: {}");

	return dto;
	}

}
