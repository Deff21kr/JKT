package org.zerock.myapp.mapper;

import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;

public interface LoginMapper {
	
	public abstract UsersVO selectUserIdPw(UsersDTO dto);
	
} // end interface
