//package org.zerock.myapp.service;
//
//import java.util.List;
//
//import org.zerock.myapp.domain.UsersDTO;
//import org.zerock.myapp.domain.UsersVO;
//import org.zerock.myapp.exception.ServiceException;
//
//public interface UsersService {
//	
//	
//	// 1. 게시판 목록을 얻어 반환해주는 기능 수행
//	public abstract List<UsersVO> getList() throws ServiceException;
//	
//	// 2. 새로운 게시물 등록 기능 수행 Create
//	public abstract Boolean register(UsersDTO dto) throws ServiceException;
//	
//	// 3. 특정 게시물 상세조회 Read
//	public abstract UsersVO get(String id) throws ServiceException;
//	
//	// 4. 특정 게시물 업데이트 Update
//	public abstract Boolean modify(UsersDTO dto) throws ServiceException;
//	
//	// 5. 특정 게시물 삭제 Delete
//	public abstract Boolean remove(String id) throws ServiceException;
//	
//	
//} // end interface
