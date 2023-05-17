package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.UserGroupDTO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.exception.ServiceException;

public interface UserGroupService {
	
		// 1. 내 동행에 신청한 유저 보여줌
		public abstract List<UserGroupDTO> getList(String nickName) throws ServiceException;
		
		public abstract Boolean register(String ID,Integer groupNo) throws ServiceException;
		
		public abstract Boolean registerDefault(String ID,Integer groupNo) throws ServiceException;
		
		// 3. 특정 게시물 상세 조회 기능 수행 (READ)
		public abstract UserGroupDTO get(Integer appNo) throws ServiceException;
//		
		// 4. GroupMapper 에서
		public abstract Boolean modify(UserGroupDTO dto) throws ServiceException;
//		
//		// 5. 특정 게시물 삭제 기능 수행 (DELETE)
//		public abstract Boolean remove(Integer appNo) throws ServiceException;
//		
//		// 6. 총 게시물 동행 반환
//		public abstract Integer getTotal() throws ServiceException;

}
