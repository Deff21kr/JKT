package org.zerock.myapp.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Value;

@Value
public class UsersVO {
	
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
	
	
	public UsersDTO toDTO() {
		
		UsersDTO dto = new UsersDTO();
		
		dto.setID(ID);
		dto.setNickName(nickName);
		dto.setName(name);
		dto.setPassword(password);
		dto.setGender(gender);
		dto.setEMail(EMail);
		dto.setPhoneNumber(phoneNumber);
		dto.setBirthDate(birthDate);
		dto.setMBTI(MBTI);
		dto.setLikeArea(likeArea);
		dto.setUno(uno);
		dto.setEnabled(enabled);
		dto.setRememberMe(dto.getRememberMe());
		return dto;
	}
	

}
