package org.zerock.myapp.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UsersDTO {
	private String ID;
	private String nickName;
	private String name;
	private String password;
	private String gender;
	private String EMail;
	private String phoneNumber;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate birthDate;
	private Timestamp userRegiDate;
	private Timestamp withDrawal;
	private String MBTI;
	private String likeArea;
	private Integer uno;
	private Boolean enabled;
	private Boolean rememberMe;
	private String statusMessage;
	private String boardName;
	private String title;
	private String content;
	private Timestamp regiDate;
	private Double rating;
	private String fileName;
	private Integer postNo;
	

}
