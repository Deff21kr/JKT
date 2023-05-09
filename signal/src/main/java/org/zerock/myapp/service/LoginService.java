package org.zerock.myapp.service;

import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.DAOException;

public interface LoginService {
	
	public abstract UsersVO login(UsersDTO dto) throws DAOException ;
	
//	public abstract UserVO authenticate(UserDTO dto) throws ServiceException;
	
} // end interface
