package org.zerock.myapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.exception.ServiceException;

public interface UsersService {
	
	
	// 1. 유저 목록 반환
	public abstract List<UsersVO> getList() throws ServiceException;
	
	// 2. 회원가입
	public abstract Boolean register(UsersDTO dto) throws ServiceException;
	
	// 3. 특정 회원 조회
	public abstract UsersVO get(String ID) throws ServiceException;
	
	// 3-1. 특정 회원 조회
	public abstract UsersVO getByNick(String nickName) throws ServiceException;
	
	// 4. 회원정보수정
	public abstract Boolean modify(UsersDTO dto) throws ServiceException;
	
	// 5. 탈퇴
	public abstract Boolean remove(String ID) throws ServiceException;
	
	public abstract Integer checkIdService(String ID) throws ServiceException;
	
	public abstract Integer checkNickNameService(String nickName) throws ServiceException;
	
	// 6. 회원 프로필 수정
	public abstract Boolean profileEdit(UsersDTO dto) throws ServiceException;
	
	// 7. 마이프로필 글쓴내역보기
	public abstract List<UsersVO> selectWriteList(String nickName) throws ControllerException;
} // end interface
