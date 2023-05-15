package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.QnABoardDTO;
import org.zerock.myapp.domain.QnABoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.exception.ServiceException;

public interface QnABoardService {
	
	// 1. 게시판 목록을 얻어 반환해주는 기능 수행
	public abstract List<QnABoardVO> getList(Criteria cri) throws ServiceException;
	
	// 2. 새로운 게시물 등록 기능 수행 (CREATE)
	public abstract Boolean register(QnABoardDTO dto) throws ServiceException;
	
	// 3. 특정 게시물 상세 조회 기능 수행 (READ)
	public abstract QnABoardVO get(Integer postNo) throws ServiceException;
	
	// 4. 특정 게시물 업데이트 기능 수행 (UPDATE)
	public abstract Boolean modify(QnABoardDTO dto) throws ServiceException;
	
	// 5. 특정 게시물 삭제 기능 수행 (DELETE)
	public abstract Boolean remove(Integer postNo) throws ServiceException;

	// 6. 총 게시물 갯수 반환
	public abstract Integer getTotal() throws ServiceException;
	
	// 7. 조회수
	public abstract Integer updateReadcnt(Integer postNo) throws ServiceException;
	
} // end interface
