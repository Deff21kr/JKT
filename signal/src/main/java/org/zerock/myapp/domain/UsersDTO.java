package org.zerock.myapp.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UsersDTO {
	private Integer userno;
	private String ID;
	private String nickName;
	private String Name;
	private String password;
	private String gender;
	private String EMail;
	private String phoneNumber;
	private LocalDate birthDate;
	private String MBTI;
	private String likeArea;

}
