package org.zerock.myapp.service;


import org.zerock.myapp.domain.PinDTO;
import org.zerock.myapp.exception.ServiceException;

public interface PinService {
	
	public abstract Integer doPin(PinDTO pin) throws ServiceException;
	
//	public abstract Boolean delete(Integer pinNo) throws ServiceException;
	
//	public abstract List<PinDTO> select(String nickName);
//	
//	public abstract Integer checkPin(PinDTO pin);
	
} // end interface
