package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.QnABoardDTO;
import org.zerock.myapp.domain.QnABoardVO;
import org.zerock.myapp.domain.UserGroupDTO;
import org.zerock.myapp.exception.DAOException;

public interface UserGroupMapper {
	
		@Select("""
				SELECT  /*+ index_desc(tbl_user_group) */  
						a.appno, a.id, b.groupname,b.area, b.recruitstatus,b.membernum,b.currentmember,b.postno
				FROM TBL_USER_GROUP a
				INNER JOIN tbl_groups b ON a.groupNo = b.groupNo
				WHERE a.groupNo = #{groupNo}
	
				""")
		public abstract List<UserGroupDTO> selectList(Integer groupNo) throws DAOException;;
		
		// 2. 신청시 생성
		public abstract Integer insert(UserGroupDTO dto) throws DAOException;;
	
		// 3. 상세조회
		public abstract UserGroupDTO select(Integer appNo) throws DAOException;
	   
		// 4. 거절시 삭제
		public abstract Integer delete(Integer appNo) throws DAOException;
		
		// 5. 수락및 거절 수락시 currentMember +1 
		public abstract Integer update(UserGroupDTO dto) throws DAOException;;


}
