package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.PinDTO;
import org.zerock.myapp.exception.ServiceException;

public interface PinService {
	
	public Integer insert(PinDTO pin);
	
	public Boolean delete(Integer pinNo) throws ServiceException;
	
	public List<PinDTO> select(String nickName);
	
} // end interface
