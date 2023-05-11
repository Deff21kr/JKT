package org.zerock.myapp.service;

import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;

public interface LoginService {
	
	public abstract UsersVO authenticate(UsersDTO dto) throws ServiceException ;
	
} // end interface
