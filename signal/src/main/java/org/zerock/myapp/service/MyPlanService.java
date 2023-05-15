package org.zerock.myapp.service;

import org.zerock.myapp.domain.MyPlanDTO;
import org.zerock.myapp.exception.ServiceException;

public interface MyPlanService {
	
	public abstract Boolean generate(MyPlanDTO dto) throws ServiceException;

}
