package org.zerock.myapp.service;

import org.zerock.myapp.domain.UsersDTO;

public interface LoginService {
	
	public abstract UsersDTO login(UsersDTO dto);
	
} // end interface
