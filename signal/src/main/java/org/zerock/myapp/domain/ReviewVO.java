package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Value
public class ReviewVO {
	
	private Integer postno;
	private Integer userno;
	private String nickname;
	private String title;
	private String content;
	private Integer views;
	private Integer likes;
	private Timestamp regidate;
	private Timestamp modifydate;

	public ReviewDTO toDTO() {
		log.trace("toDTO() invoked");

	ReviewDTO dto = new ReviewDTO();
	dto.setPostno(postno);
	dto.setUserno(userno);
	dto.setNickname(nickname);
	dto.setTitle(title);
	dto.setContent(content);
	dto.setViews(views);
	dto.setLikes(likes);

	log.info("\t+ dto: {}");

	return dto;
	}

}
