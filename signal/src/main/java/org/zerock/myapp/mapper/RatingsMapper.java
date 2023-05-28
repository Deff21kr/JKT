package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.RatingsDTO;

public interface RatingsMapper {
//	// 점수 단순 조회
	@Select("""
			select *
			from tbl_ratings
			where ratedUserNickName = #{ratedUserNickName}
			""")
	public abstract List<RatingsDTO> selectRating(String nickName);
	
	// 점수를 주자
	public abstract Integer setRaterUserNickName(@Param("ratedUserNickName")String nickName,@Param("raterUserNickName")String setNickName,@Param("rating") Integer rating);
	
	// 점수를 받고 조회하기
	@Select("""
			select avg(rating)
			from tbl_ratings
			where ratedUserNickName = #{ratedUserNickName}
			""")
	public abstract Double getRatedUserNickName(@Param("ratedUserNickName")String ratedUserNickName);
}