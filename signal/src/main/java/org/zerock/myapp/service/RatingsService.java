package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.RatingsDTO;
import org.zerock.myapp.exception.ServiceException;

public interface RatingsService {
	// 점수 단순 조회
	public abstract List<RatingsDTO> selectRating(String nickName) throws ServiceException;
	
	// 점수를 주기
	public abstract Integer setRaterRating(String nickName,String setNickName ,Integer rating) throws ServiceException;
	
	// 점수 받기(조회)
	public abstract RatingsDTO getRatedRating(String nickName) throws ServiceException;
	
}
