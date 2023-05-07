package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.AuthDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;

public interface AuthService {
	
		public abstract List<AuthDTO> getList() throws ServiceException;
		
		public abstract Boolean register(AuthDTO dto) throws ServiceException;
		
		public abstract AuthDTO get(String userid) throws ServiceException;
		
		public abstract Boolean modify(AuthDTO dto) throws ServiceException;
		
		public abstract Boolean remove(String userid) throws ServiceException;
		
		

}
