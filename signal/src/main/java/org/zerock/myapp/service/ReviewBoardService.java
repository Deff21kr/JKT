package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.AttachFileDTO;
import org.zerock.myapp.domain.ReviewBoardCriteria;
import org.zerock.myapp.domain.ReviewBoardDTO;
import org.zerock.myapp.domain.ReviewBoardVO;
import org.zerock.myapp.domain.ReviewBoardCriteria;
import org.zerock.myapp.exception.ServiceException;

public interface ReviewBoardService {
	

	// 1. 게시판 목록 및 반환 
	public abstract List<ReviewBoardVO> getList(ReviewBoardCriteria cri) throws ServiceException;

	// 2. 게시물 등록 
	public abstract Boolean register(ReviewBoardDTO dto) throws ServiceException;

	// 3. 게시물 조회 
	public abstract ReviewBoardVO get(Integer postNo) throws ServiceException;

	// 4. 게시물 갱신 (업데이트)
	public abstract Boolean modify(ReviewBoardDTO dto) throws ServiceException;

	// 5. 게시물 삭제 
	public abstract Boolean remove(Integer postNo) throws ServiceException;

	// 6. 게시물 총 개수 반환 
	public abstract Integer getTotal() throws ServiceException;
	
	// 7. 첨부파일 조회 (JSON -> 화면)
	public List<AttachFileDTO> getAttachList(Integer postNo);
	
	// 8. 게시물 등록 + 첨부파일
	public Boolean registerWithFile(ReviewBoardDTO dto) throws ServiceException;
	
	// 9. 조회수
	public abstract Integer updateReadcnt(Integer postNo) throws ServiceException;

}//end interface
