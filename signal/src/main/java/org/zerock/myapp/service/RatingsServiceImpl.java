package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.RatingsDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.RatingsMapper;

import lombok.Setter;

@Service("RatingsService")
public class RatingsServiceImpl implements RatingsService{
	
	@Setter(onMethod_ = @Autowired)
	private RatingsMapper rat;
	
	@Override
	public Integer setRaterRating(String nickName,String setNickName ,Integer rating) throws ServiceException {
		try {
			return this.rat.setRaterUserNickName(nickName,setNickName ,rating);
		}catch(Exception e) {
			throw new ServiceException(e);
		}
		
	}

//	@Override
//	public Double getRatedRating(String ratedUserNickName) throws ServiceException {
//		try {
//			if(this.rat.getRatedRating(ratedUserNickName) != null){
//			return this.rat.getRatedRating(ratedUserNickName);
//			} else {
//				return null;
//			}
//		}catch(Exception e) {
//			throw new ServiceException(e);
//		}
//	}
	
	@Override
	public RatingsDTO getRatedRating(String ratedUserNickName) throws ServiceException {
	    try {
	    	RatingsDTO ratedRating = this.rat.getRatedUserNickName(ratedUserNickName);
	        return ratedRating;
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
