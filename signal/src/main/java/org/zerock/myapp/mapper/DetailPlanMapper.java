package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.DetailPlanDTO;
import org.zerock.myapp.domain.DetailPlanVO;
import org.zerock.myapp.domain.OneOnOneBoardDTO;
import org.zerock.myapp.domain.OneOnOneBoardVO;

public interface DetailPlanMapper {
	
	@Select("""
			SELECT *
			FROM TBL_DETAILPLAN
			WHERE 
				PLANNO = #{planNo}
			ORDER BY
				planDay ASC, to_char(plantime, 'HH24:Mi') ASC
			""")
	public abstract List<DetailPlanVO> selectList(@Param("planNo") Integer planNo);
	
	// 플랜계획 등록
	public abstract Integer insert(DetailPlanDTO dto);
	
	// 특정 플랜계획 상세조회
	@Select("""
			SELECT * FROM TBL_DETAILPLAN WHERE DETAILPLANNO = #{detailPlanNo}	 
			"""
			)
	public abstract DetailPlanVO select(Integer detailPlanNo);
	
	// 특정 플랜계획 삭제
	@Delete("""
			DELETE FROM TBL_DETAILPLAN
			WHERE DETAILPLANNO = #{detailPlanNo}
			""")
	public abstract Integer delete(Integer detailPlanNo);

	// 특정 플랜계획 업데이트
	@Update("""
			UPDATE TBL_DETAILPLAN
			SET
				PLANTIME = #{planTime},
				PLACE = #{place},
				DETAILPLAN = #{detailPlan}
			WHERE
				DETAILPLANNO = #{detailPlanNo}
			""")
	public abstract Integer update(DetailPlanDTO dto);
}
