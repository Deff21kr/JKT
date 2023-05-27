package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.RatingsDTO;
import org.zerock.myapp.exception.ServiceException;

import lombok.Setter;

@Service("Ratings")
public class RatingsServiceImpl implements RatingsService{
	
	@Setter(onMethod_ = @Autowired)
	private RatingsService rat;
	
	@Override
	public Integer setRaterRating(String nickName, Integer rating) throws ServiceException {
		try {
			return this.rat.setRaterRating(nickName, rating);
		}catch(Exception e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public Double getRatedRating(String nickName) throws ServiceException {
		try {
			return this.rat.getRatedRating(nickName);
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<RatingsDTO> selectRating(String nickName) throws ServiceException {
		try {
			return this.rat.selectRating(nickName);
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}
		
}
