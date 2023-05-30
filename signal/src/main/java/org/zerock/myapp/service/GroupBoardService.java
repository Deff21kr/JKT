package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.GroupBoardCriteria;
import org.zerock.myapp.domain.GroupBoardDTO;
import org.zerock.myapp.domain.GroupBoardVO;
import org.zerock.myapp.exception.ServiceException;

public interface GroupBoardService {
	
	// 1. 게시판 목록을 얻어 반환해주는 기능 수행
	public abstract List<GroupBoardVO> getList(GroupBoardCriteria cri) throws ServiceException;
	
	// 2. 조건검색조회
	public abstract List<GroupBoardVO> getSearchList(GroupBoardCriteria cri) throws ServiceException;
	
	// 3. 새로운 게시물 등록 기능 수행 (CREATE)
	public abstract Boolean register(GroupBoardDTO dto) throws ServiceException;
	
	// 4. 특정 게시물 상세 조회 기능 수행 (READ)
	public abstract GroupBoardVO get(Integer postno) throws ServiceException;
	
	// 5. 특정 게시물 업데이트 기능 수행 (UPDATE)
	public abstract Boolean modify(GroupBoardDTO dto) throws ServiceException;
	
	// 6. 특정 게시물 삭제 기능 수행 (DELETE)
	public abstract Boolean remove(Integer postno) throws ServiceException;

	// 7. 총 게시물 갯수 반환
	public abstract Integer getTotal() throws ServiceException;
	
	// 8. 검색 조회된 게시물 갯수 반환
	public abstract Integer getSearchTotal(GroupBoardCriteria cri) throws ServiceException;
	
	// 9. 조회수 증가
	public abstract Integer plusReadcnt(Integer postNo) throws ServiceException;


} // end interface
