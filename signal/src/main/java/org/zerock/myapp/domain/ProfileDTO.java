package org.zerock.myapp.domain;

import lombok.Data;

//첨부파일의 정보들을 저장 
//							-> 브라우저 콘솔창에서 json형태로 확인 가능


@Data
public class ProfileDTO {
	
	private String fileName;		// 원본파일명
	private String uploadPath;		// 업로드경로
	private String uuid;			// UUID 값
	private Integer nickName;			// 첨부파일이 등록된 유저 번호 FK
	
//	private boolean image;	//이미지 여부	
	
}//end class
