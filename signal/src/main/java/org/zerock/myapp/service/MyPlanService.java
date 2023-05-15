package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.MyPlanDTO;
import org.zerock.myapp.domain.MyPlanVO;
import org.zerock.myapp.exception.ServiceException;

public interface MyPlanService {
	
	public abstract List<MyPlanVO> getList(Criteria cri, String nickName) throws ServiceException;
	
	public abstract Boolean generate(MyPlanDTO dto) throws ServiceException;

	public abstract MyPlanVO get(Integer planNo) throws ServiceException;
}
