package org.zerock.myapp.mapper;

import java.util.List;

import org.zerock.myapp.domain.AttachFileDTO;
import org.zerock.myapp.domain.BoardAttachVO;

//첨부파일 수정은 없음. 오로지 추가와 삭제만 존재
public interface BoardAttachMapper {
	
	//1.첨부파일 추가
	public abstract Integer insert(AttachFileDTO dto);
	
	//2.첨부파일 삭제
	public abstract Integer delete(String uuid);
	
	//3.특정게시물 번호로, 첨부파일 찾기
	public List<AttachFileDTO> findByPostNo(Integer postNo);
	
}//end class
