package org.zerock.myapp.persistence;

import java.util.Date;

import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.DAOException;

public interface LoginDAO {
	
	public abstract UsersVO selectUserIdPw(UsersDTO dto) throws DAOException;;
	
	public abstract Integer updateUserWithRememberMe(
			String ID,
			String rememberMeCookie,
			Date rememberMeMaxAge) throws DAOException;;

	public abstract UsersVO selectUserByRememberMe(String rememberMe) throws DAOException; 
	
	
} // end interface
