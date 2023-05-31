package org.zerock.myapp.mapper;


import org.apache.ibatis.annotations.Param;
import org.zerock.myapp.domain.LikeDTO;

public interface LikeMapper {
	
	public abstract Integer insert(LikeDTO like) throws Exception;
	
	public abstract Integer delete(Integer likeNo);
	
	public abstract LikeDTO select(@Param("postNo") Integer postNo, @Param("nickName") String nickName);
	
	public abstract Integer checkLike(LikeDTO like);
	
} // end interface
