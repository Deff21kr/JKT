package org.zerock.myapp.service;


import org.zerock.myapp.domain.LikeDTO;
import org.zerock.myapp.exception.ServiceException;

public interface LikeService {
	
	public abstract Integer doLike(LikeDTO like) throws ServiceException;
	
} // end interface
