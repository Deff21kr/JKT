package org.zerock.myapp.domain;


import lombok.Value;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Value
public class BoardAttachVO {

	//컬럼 순서 지키기
	private String fileName;	// 파일이름
	private String uploadPath;	// 업로드한 경로
	private String uuid;		// 고유번호
	
	private Integer postNo;
	
	
	
	public AttachFileDTO toDTO() {
		log.trace("toDTO() invoked");

		AttachFileDTO dto = new AttachFileDTO();
		dto.setFileName(fileName);
		dto.setUploadPath(uploadPath);
		dto.setUuid(uuid);
		dto.setPostNo(postNo);
		
		log.info("\t+ dto: {}");

		return dto;
		
	}//todDTO
	
}//end class
