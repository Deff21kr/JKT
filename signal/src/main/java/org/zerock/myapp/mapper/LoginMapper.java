package org.zerock.myapp.mapper;

import java.util.Date;

import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.DAOException;

public interface LoginMapper {
	
	public abstract UsersVO selectUserIdPw(UsersDTO dto) throws DAOException;;
	
	public abstract Integer updateUserWithRememberMe(
			String id,
			String rememberMeCookie,
			Date rememberMeMaAge) throws DAOException;;

	public abstract UsersVO selectUserByRememberMe(String rememberMe) throws DAOException; 
	
	
} // end interface
