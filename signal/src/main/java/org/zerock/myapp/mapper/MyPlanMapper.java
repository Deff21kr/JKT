package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.MyPlanDTO;
import org.zerock.myapp.domain.MyPlanVO;

public interface MyPlanMapper {
	
//		@Select("SELECT * "
//				+ "FROM tbl_myplan "
//				+ "WHERE nickName = #{nickName} "
//				+ "ORDER BY startDate ASC "
//				+ "OFFSET ( #{currPage} - 1 ) * #{amount} ROWS "
//				+ "FETCH NEXT #{amount} ROWS ONLY" )
	public abstract List<MyPlanVO> selectList(@Param("cri") Criteria cri, @Param("nickName") String nickName);

	public abstract Integer make(MyPlanDTO dto);
	
	public abstract MyPlanVO select(Integer planNo);
	
	@Delete("DELETE FROM tbl_myplan WHERE planNo = #{planNo}")
	public abstract Integer delete(Integer planNo);
	
	@Update("UPDATE tbl_myplan SET planName = #{planName} WHERE planNo=#{planNo}")
	public abstract Integer update(@Param("planName") String planName, @Param("planNo") Integer planNo);
	
	// 요청시점에 총 게시물 개수 반환
	@Select("SELECT count(planno) FROM tbl_myplan WHERE planno > 0 AND endDate > current_date")
	public abstract Integer getTotalAmount();
	
//	public abstract 
}
