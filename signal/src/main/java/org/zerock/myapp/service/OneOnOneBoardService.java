package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.OneOnOneBoardDTO;
import org.zerock.myapp.domain.OneOnOneBoardVO;
import org.zerock.myapp.exception.ServiceException;

public interface OneOnOneBoardService {
	
	// 1. 게시판 목록을 얻어 반환해주는 기능 수행
	public abstract List<OneOnOneBoardVO> getList(Criteria cri) throws ServiceException;
	
	// 2. 새로운 게시글 등록 기능 수행( Create )
	public abstract Boolean register(OneOnOneBoardDTO dto) throws ServiceException;
	
	// 3. 특정 게시물 상세조회( READ )
	public abstract OneOnOneBoardVO get(Integer postNo) throws ServiceException;
	
	// 4. 특정 게시물 업데이트 ( UPDATE )
	public abstract Boolean modify(OneOnOneBoardDTO dto) throws ServiceException;
	
	// 5. 특정 게시물 삭제 ( DELETE )
	public abstract Boolean remove(Integer postNo) throws ServiceException;
	
	// 6. 총 게시물 개수 반환
	public abstract Integer getTotal() throws ServiceException;	
	
	// 7. 답변글 달기
	public abstract Boolean reply(OneOnOneBoardDTO dto) throws ServiceException;

}
