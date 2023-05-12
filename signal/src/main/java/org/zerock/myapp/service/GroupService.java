package org.zerock.myapp.service;

import org.zerock.myapp.domain.GroupBoardDTO;
import org.zerock.myapp.domain.GroupsDTO;
import org.zerock.myapp.exception.ServiceException;

public interface GroupService {
	
	public abstract Boolean register(GroupsDTO dto,GroupBoardDTO bo) throws ServiceException;

}
