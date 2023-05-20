package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.JoinMyPlanDTO;

public interface JoinMyPlanMapper {
	
	
	@Select("""
			SELECT *
				FROM TBL_MYPLAN MY, TBL_DETAILPLAN DE
				WHERE MY.PLANNO = DE.PLANNO AND to_date(ENDDATE,'yyyy-MM-dd') >= to_date(CURRENT_DATE ,'yyyy-MM-dd')AND NICKNAME = #{nickName}
				ORDER BY MY.PLANNO ,PLANDAY, TO_CHAR(PLANTIME, 'HH24:MI')
			""")
	public abstract List<JoinMyPlanDTO> selectList(@Param("nickName") String nickName);

}
