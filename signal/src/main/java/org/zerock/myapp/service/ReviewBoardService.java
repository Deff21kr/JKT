package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ReviewBoardDTO;
import org.zerock.myapp.domain.ReviewBoardVO;
import org.zerock.myapp.exception.ServiceException;

public interface ReviewBoardService {
	

	// 1. 게시판 목록 및 반환 
	public abstract List<ReviewBoardVO> getList(Criteria cri) throws ServiceException;

	// 2. 게시물 등록 
	public abstract Boolean register(ReviewBoardDTO dto) throws ServiceException;

	// 3. 게시물 조회 
	public abstract ReviewBoardVO get(Integer postno) throws ServiceException;

	// 4. 게시물 갱신 (업데이트)
	public abstract Boolean modify(ReviewBoardDTO dto) throws ServiceException;

	// 5. 게시물 삭제 
	public abstract Boolean remove(Integer postno) throws ServiceException;

	// 6. 게시물 반환 
	public abstract Integer getTotal() throws ServiceException;

}
