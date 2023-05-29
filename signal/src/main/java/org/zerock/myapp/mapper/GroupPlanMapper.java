package org.zerock.myapp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupPlanDTO;
import org.zerock.myapp.domain.GroupPlanVO;

public interface GroupPlanMapper {
	@Select("""
   		SELECT 
		    GP.planNo, GP.groupNo, GP.nickName, G.GROUPNAME as groupName, GP.startDate, GP.endDate, GP.duration
		FROM TBL_USER_GROUP UG, TBL_GROUPPLAN GP, TBL_GROUPS G
		WHERE 
			UG.GROUPNO = GP.GROUPNO
		    AND GP.GROUPNO = G.GROUPNO 
		    AND OUTCOME IN ('수락', '본인')
			AND GP.endDate > current_date
			AND UG.NICKNAME = #{nickName}     
		ORDER BY startDate ASC 
		OFFSET ( #{cri.currPage} - 1 ) * #{cri.amount} ROWS 
		FETCH NEXT #{cri.amount} ROWS ONLY
			""")
	public abstract List<GroupPlanVO> selectList(@Param("cri") Criteria cri, @Param("nickName") String nickName);

	public abstract Integer make(GroupPlanDTO dto);
	
	@Select("""
			SELECT *
			FROM tbl_groupplan
			WHERE planNo = #{planNo}
			""")
	public abstract GroupPlanVO select(Integer planNo);
	
	@Delete("DELETE FROM tbl_groupplan WHERE planNo = #{planNo}")
	public abstract Integer delete(Integer planNo);
	
	@Update("UPDATE tbl_groupplan SET groupName = #{groupName} WHERE planNo=#{planNo}")
	public abstract Integer update(@Param("groupName") String groupName, @Param("planNo") Integer planNo);
	
	// 요청시점에 총 게시물 개수 반환
	@Select("SELECT count(planno) FROM tbl_groupplan WHERE planno > 0 AND endDate > current_date")
	public abstract Integer getTotalAmount();
	
	// 사용자가 가입되어있는 동행이름 리스트 출력
	@Select("""
			SELECT G.GROUPNAME as groupName, U.GROUPNO as groupNo
			FROM TBL_USER_GROUP U, TBL_GROUPS G
			WHERE U.GROUPNO = G.GROUPNO AND U.OUTCOME IN ('수락', '본인') AND U.NICKNAME = #{nickName}
			""")
	public abstract List<Map<String, Integer>> groupNameList (String nickName);
	
	
	
//	public abstract 
}
