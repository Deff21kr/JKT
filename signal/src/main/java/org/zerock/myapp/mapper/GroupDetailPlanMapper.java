package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.zerock.myapp.domain.GroupDetailPlanDTO;
import org.zerock.myapp.domain.GroupDetailPlanVO;

public interface GroupDetailPlanMapper {
	
	@Select("""
			SELECT *
			FROM TBL_GROUP_DETAILPLAN
			WHERE 
				PLANNO = #{planNo}
			ORDER BY
				planDay ASC, to_char(plantime, 'HH24:Mi') ASC
			""")
	public abstract List<GroupDetailPlanVO> selectList(@Param("planNo") Integer planNo);
	
	// 플랜계획 등록
	public abstract Integer insert(GroupDetailPlanDTO dto);
	
	// 특정 플랜계획 상세조회
	@Select("""
			SELECT * FROM TBL_GROUP_DETAILPLAN WHERE DETAILPLANNO = #{detailPlanNo}	 
			"""
			)
	public abstract GroupDetailPlanVO select(Integer detailPlanNo);
	
	// 특정 플랜계획 삭제
	@Delete("""
			DELETE FROM TBL_GROUP_DETAILPLAN
			WHERE DETAILPLANNO = #{detailPlanNo}
			""")
	public abstract Integer delete(Integer detailPlanNo);

	// 특정 플랜계획 업데이트
	@Update("""
			UPDATE TBL_GROUP_DETAILPLAN
			SET
				PLANTIME = #{planTime},
				PLACE = #{place},
				DETAILPLAN = #{detailPlan}
			WHERE
				DETAILPLANNO = #{detailPlanNo}
			""")
	public abstract Integer update(GroupDetailPlanDTO dto);
}
