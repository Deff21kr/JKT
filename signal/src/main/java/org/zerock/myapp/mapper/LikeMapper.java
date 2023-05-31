package org.zerock.myapp.mapper;


import org.zerock.myapp.domain.LikeDTO;

public interface LikeMapper {
	
	public abstract Integer insert(LikeDTO like) throws Exception;
	
	public abstract Integer delete(Integer likeNo);
	
	public abstract LikeDTO select(Integer postNo, String nickName);
	
	public abstract Integer checkLike(LikeDTO like);
	
} // end interface
