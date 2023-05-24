package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupPlanDTO;
import org.zerock.myapp.domain.GroupPlanVO;
import org.zerock.myapp.domain.GroupsDTO;

public interface GroupPlanMapper {
	@Select("""
   		SELECT *
		FROM tbl_groupplan gp,(
			SELECT *
			FROM TBL_USER_GROUP U, TBL_GROUPS G, TBL_USERS US
			WHERE G.GROUPNO=U.GROUPNO AND U.ID=US.ID AND U.OUTCOME IN ('수락', '본인') AND US.NICKNAME = #{nickName};
		) as a
		WHERE groupNo = #{groupNo} AND endDate > current_date AND NICK(S)    
		ORDER BY startDate ASC 
		OFFSET ( #{cri.currPage} - 1 ) * #{cri.amount} ROWS 
		FETCH NEXT #{cri.amount} ROWS ONLY
			""")
	public abstract List<GroupPlanVO> selectList(@Param("cri") Criteria cri, @Param("groupNo") Integer groupNo, @Param("nickName") String nickName);

	public abstract Integer make(GroupPlanDTO dto);
	
	@Select("""
			SELECT *
			FROM tbl_groupplan
			WHERE planNo = #{planNo}
			""")
	public abstract GroupPlanVO select(Integer planNo);
	
	@Delete("DELETE FROM tbl_groupplan WHERE planNo = #{planNo}")
	public abstract Integer delete(Integer planNo);
	
	@Update("UPDATE tbl_groupplan SET planName = #{planName} WHERE planNo=#{planNo}")
	public abstract Integer update(@Param("planName") String planName, @Param("planNo") Integer planNo);
	
	// 요청시점에 총 게시물 개수 반환
	@Select("SELECT count(planno) FROM tbl_groupplan WHERE planno > 0 AND endDate > current_date")
	public abstract Integer getTotalAmount();
	
	// 사용자가 가입되어있는 동행이름 리스트 출력
	@Select("""
			SELECT G.GROUPNAME 
			FROM TBL_USER_GROUP U, TBL_GROUPS G
			WHERE U.GROUPNO = G.GROUPNO AND U.OUTCOME IN ('수락', '본인') AND U.ID = #{id}
			""")
	public abstract List<String> groupNameList (String id);
	
	
	
//	public abstract 
}
