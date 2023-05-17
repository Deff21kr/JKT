package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.QnABoardDTO;
import org.zerock.myapp.domain.QnABoardVO;
import org.zerock.myapp.domain.UserGroupDTO;
import org.zerock.myapp.exception.DAOException;

public interface UserGroupMapper {
	
		@Select("""
				SELECT  /*+ index_desc(tbl_user_group) */  
					a.appno, b.groupname, a.id,b.area, b.recruitstatus,a.outCome,b.membernum,b.currentmember,b.groupno
                FROM TBL_USER_GROUP a
                INNER JOIN tbl_groups b ON a.groupNo = b.groupNo
                where b.postno IN (select c.postno from tbl_groups c , tbl_groupboard d where c.postno=d.postno and d.nickname=#{nickName})
				OFFSET (#{currPage} -1) * #{amount} ROWS
				FETCH NEXT #{amount} ROWS ONLY
				ORDER BY a.appno desc
				""")
		public abstract List<UserGroupDTO> selectList(String nickName,Criteria cri) throws DAOException;;
		
		// 2. 신청시 생성
		public abstract Integer insert(String ID,Integer groupNo) throws DAOException;
		
		// 2. 글 등록시 본인 자동등록
		public abstract Integer insertDefault(String ID,Integer groupNo) throws DAOException;
	
		// 3. 상세조회
		public abstract UserGroupDTO select(Integer appNo) throws DAOException;
	   
		// 4. 거절시 삭제
		public abstract Integer delete(Integer appNo) throws DAOException;
		
		// 5. 수락및 거절 수락시 currentMember +1 
		public abstract Integer update(UserGroupDTO dto) throws DAOException;;
		
		@Select("SELECT count(appno) FROM tbl_user_group WHERE appno > 0")
		public abstract Integer getTotalAmount();


}
