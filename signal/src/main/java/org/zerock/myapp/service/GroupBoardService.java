package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupBoardDTO;
import org.zerock.myapp.domain.GroupBoardVO;
import org.zerock.myapp.exception.ServiceException;

public interface GroupBoardService {
	
	// 1. 게시판 목록을 얻어 반환해주는 기능 수행
	public abstract List<GroupBoardVO> getList(Criteria cri) throws ServiceException;
	
	// 2. 새로운 게시물 등록 기능 수행 (CREATE)
	public abstract Boolean register(GroupBoardDTO dto) throws ServiceException;
	
	// 3. 특정 게시물 상세 조회 기능 수행 (READ)
	public abstract GroupBoardVO get(Integer postno) throws ServiceException;
	
	// 4. 특정 게시물 업데이트 기능 수행 (UPDATE)
	public abstract Boolean modify(GroupBoardDTO dto) throws ServiceException;
	
	// 5. 특정 게시물 삭제 기능 수행 (DELETE)
	public abstract Boolean remove(Integer postno) throws ServiceException;

	// 6. 총 게시물 갯수 반환
	public abstract Integer getTotal() throws ServiceException;
} // end interface
