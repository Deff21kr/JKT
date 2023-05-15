package org.zerock.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.MyPlanDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.MyPlanMapper;

import lombok.Setter;

@Service("MyPlanService")
public class MyPlanServiceImpl implements MyPlanService {

	@Setter(onMethod_ = {@Autowired})
	private MyPlanMapper mapper;
	
	@Override
	public Boolean generate(MyPlanDTO dto) throws ServiceException {
		
		try {
			
			return (this.mapper.make(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		

	} // generate

}
