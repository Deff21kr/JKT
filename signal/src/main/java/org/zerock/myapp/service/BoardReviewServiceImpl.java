package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ReviewDTO;
import org.zerock.myapp.domain.ReviewVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.ReviewMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service("ReviewService")
public class BoardReviewServiceImpl implements BoardReviewService, InitializingBean, DisposableBean {
	
	@Override
	public void destroy() throws Exception {
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

	@Override
	public List<ReviewVO> getList(Criteria cri) throws ServiceException {
		
		
		return null;
		
	}

	@Override
	public Boolean register(ReviewDTO dto) throws ServiceException {
		
		
		return null;
		
	}

	@Override
	public ReviewVO get(Integer postno) throws ServiceException {
		
		
		return null;
	}

	@Override
	public Boolean modify(ReviewDTO dto) throws ServiceException {
		
		
		return null;
	}

	@Override
	public Boolean remove(Integer postno) throws ServiceException {
		
		
		return null;
	}

	@Override
	public Integer getTotal() throws ServiceException {
		
		
		return null;
	}
	
}
