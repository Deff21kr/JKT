package org.zerock.myapp.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.MyPlanDTO;
import org.zerock.myapp.domain.MyPlanVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.MyPlanMapper;

import lombok.Setter;

@Service("MyPlanService")
public class MyPlanServiceImpl implements MyPlanService {

	@Setter(onMethod_ = {@Autowired})
	private MyPlanMapper mapper;
	

	@Override
	public List<MyPlanVO> getList(Criteria cri, String nickName) throws ServiceException {
		this.mapper.selectList(cri, nickName);
		
		try {
			
			return this.mapper.selectList(cri, nickName);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getList
	
//	@Override
//	public Boolean generate(MyPlanDTO dto) throws ServiceException {
//		
//		try {
//			
//			return (this.mapper.make(dto) == 1);
//		} catch (Exception e) {
//			throw new ServiceException(e);
//		} // try-catch
//	} // generate
	
	@Override
	public Boolean generate(MyPlanDTO dto) throws ServiceException {
	    try {
	        // startDate와 endDate를 가져옴
	        LocalDate startDate = dto.getStartDate();
	        LocalDate endDate = dto.getEndDate();
	        
	        // duration 계산
//	        Duration duration = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay());
	        
	        // dto에 duration 설정
//	        dto.setDuration(duration);
	        
	        return (this.mapper.make(dto) == 1);
	    } catch (Exception e) {
	        throw new ServiceException(e);
	    }
	}

	@Override
	public MyPlanVO get(Integer planNo) throws ServiceException {
		try {
			return this.mapper.select(planNo);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	}


}
