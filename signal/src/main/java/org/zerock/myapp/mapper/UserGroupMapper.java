package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.UserGroupDTO;
import org.zerock.myapp.exception.DAOException;

public interface UserGroupMapper {
		// 나에게 동행을 신청한 유저_그룹 리스트
		@Select("""
				SELECT  
					a.appno, b.groupname, a.nickname,b.area, b.recruitstatus,a.outCome,b.membernum,b.currentmember,b.groupno
                FROM TBL_USER_GROUP a
                INNER JOIN tbl_groups b ON a.groupNo = b.groupNo
                where b.postno IN (select c.postno from tbl_groups c , tbl_groupboard d where c.postno=d.postno and d.nickname=#{nickName})
                ORDER BY b.groupno desc, a.appno desc
				OFFSET (#{cri.currPage} -1) * #{cri.amount} ROWS
				FETCH NEXT #{cri.amount} ROWS ONLY
				""")
		public abstract List<UserGroupDTO> selectList(@Param("nickName") String nickName,@Param("cri") Criteria cri) throws DAOException;
		
		// 내 동행
		@Select("""
				SELECT 
				    *
				FROM tbl_user_group u
				JOIN (
				    SELECT g.groupno, g.groupname, g.recruitstatus, g.membernum, g.currentmember, g.area, b.postno,
				           b.title, b.content, b.startdate, b.enddate, b.views, b.regidate, b.modifydate, b.nickname writer
				    FROM tbl_groups g
				    JOIN tbl_groupboard b ON g.postno = b.postno
				) j ON u.groupno = j.groupno
				WHERE u.groupno IN (
	                SELECT GROUPNO
	                FROM
	                    TBL_USER_GROUP
	                WHERE nickname = #{nickName}
	                )
				ORDER BY 
					u.groupno,
					CASE WHEN u.nickName = #{nickName} THEN 0 ELSE 1 END,
					u.outcome ,u.appdate desc
				OFFSET (#{cri.currPage} -1) * #{cri.amount} ROWS
				FETCH NEXT #{cri.amount} ROWS ONLY
				""")
		public abstract List<UserGroupDTO> selectMyAppList(@Param("nickName") String nickName
														,@Param("cri") Criteria cri
														) throws DAOException;;
		
		// 2. 신청시 생성
		public abstract Integer insert(@Param("nickName") String nickName,@Param("groupNo") Integer groupNo) throws DAOException;
		
		// 2. 글 등록시 본인 자동등록
		public abstract Integer insertDefault(@Param("nickName") String nickName,@Param("groupNo")Integer groupNo) throws DAOException;
	
		// 3. 상세조회
		public abstract UserGroupDTO select(Integer appNo) throws DAOException;
		
		// 4. 이미 신청한 회원인지 중복체크
		public abstract Integer groupCheckID(@Param("nickName") String nickName,@Param("postNo") Integer postNo);
	   
		// 4. 거절시 삭제
		public abstract Integer delete(Integer appNo) throws DAOException;
		
		// 5. 수락및 거절 수락시 currentMember +1 
		public abstract Integer update(UserGroupDTO dto) throws DAOException;;
		
		@Select("""
				SELECT  
				    count(a.appno)
				FROM TBL_USER_GROUP a
				INNER JOIN tbl_groups b ON a.groupNo = b.groupNo
				where b.postno IN (select c.postno from tbl_groups c , tbl_groupboard d where c.postno=d.postno and d.nickname=#{nickName})
				and a.appno>0
				and a.outcome != '거절'
				""")
		public abstract Integer getTotalAmount(String nickName);
		
		// 6. 총 게시물 갯수 반환
		@Select("""
				SELECT 
				    count(u.appno)
				FROM tbl_user_group u
				JOIN (
				    SELECT g.groupno, g.groupname, g.recruitstatus, g.membernum, g.currentmember, g.area, b.postno,
				           b.title, b.content, b.startdate, b.enddate, b.views, b.regidate, b.modifydate, b.nickname writer
				    FROM tbl_groups g
				    JOIN tbl_groupboard b ON g.postno = b.postno
				) j ON u.groupno = j.groupno
				WHERE u.nickname = #{nickName}
				""")
		public abstract Integer getTotalAmountAppList(String nickName);


}
