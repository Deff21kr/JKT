package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.http.ResponseEntity;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupBoardDTO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;

public interface UsersMapper {

	// 1. 어따쓸지 고민
	@Select("SELECT  /*+ index_desc(tbl_users) */  * FROM TBL_USERS ")
	public abstract List<UsersVO> selectList();

	// 2. 회원가입
	public abstract Integer insert(UsersDTO dto);

	// 3. 회원id로 게시물 검색
	public abstract UsersVO select(String ID);

	// 3. nickName으로 게시물 검색
	public abstract UsersVO selectByNick(String nickName);

	// 4. 회원탈퇴
	public abstract Integer delete(String ID);

	// 5. 회원정보수정
	public abstract Integer update(UsersDTO dto);

	// 6. id중복체크
	public abstract Integer checkID(String ID);

	// 6. nick 중복체크
	public abstract Integer checkNickName(String nickName);

	// 7. 회원 프로필 수정
	public abstract Boolean profileEdit(UsersDTO dto);

	// 8. 글쓴 내역보기
	@Select("""
				SELECT NICKNAME, TITLE, CONTENT, REGIDATE, BOARDNAME, POSTNO
			FROM (
			SELECT NICKNAME, TITLE, CONTENT, REGIDATE, BOARDNAME, POSTNO
			FROM TBL_QNABOARD WHERE NICKNAME = #{nickName}
			UNION
			SELECT NICKNAME, TITLE, CONTENT, REGIDATE, BOARDNAME, POSTNO
			FROM TBL_REVIEWBOARD WHERE NICKNAME = #{nickName}
			UNION
			SELECT NICKNAME, TITLE, CONTENT, REGIDATE, BOARDNAME, POSTNO
			FROM TBL_GROUPBOARD WHERE NICKNAME = #{nickName}
			)
			ORDER BY REGIDATE DESC
				OFFSET (#{cri.currPage} -1) * #{cri.amount} ROWS
				FETCH NEXT #{cri.amount} ROWS ONLY
				""")
	public abstract List<UsersDTO> selectWriteList(@Param("nickName") String nickName, @Param("cri") Criteria cri);

	// 9. 글쓴 내역의 게시글 총 개수
	@Select("SELECT count(nickName) \r\n" + "FROM\r\n"
			+ "(SELECT NICKNAME, title, content, regidate, boardname FROM TBL_QNABOARD WHERE NICKNAME = #{nickName}\r\n"
			+ "UNION\r\n"
			+ "SELECT NICKNAME, title, content, regidate, boardname FROM TBL_REVIEWBOARD WHERE NICKNAME = #{nickName}\r\n"
			+ "UNION\r\n"
			+ "SELECT NICKNAME, title, content, regidate, boardname FROM TBL_GROUPBOARD WHERE NICKNAME = #{nickName})")
	public abstract Integer getWriteList(String nickName);

	// 10. 찜 내역 보기
	@Select("""
			select 
				b.nickName ,a.postNo, a.groupName, a.title, a.area, a.startDate, a.endDate, a.RECRUITSTATUS
			From 
				TBL_GROUPBOARD a, TBL_PIN b 
			WHERE 
				a.postNo = b.postNo AND b.nickName = #{nickName}
			Order By a.startDate DESC
				OFFSET (#{cri.currPage} -1) * #{cri.amount} ROWS
				FETCH NEXT #{cri.amount} ROWS ONLY
			""")
	public abstract List<GroupBoardDTO> selectPinList(@Param("nickName") String nickName, @Param("cri") Criteria cri);

	// 11. 찜 내역의 찜 총 개수
	@Select("""
			select count(nickName) FROM TBL_PIN WHERE nickName = #{nickName}
			""")
	public abstract Integer getPinList(@Param("nickName") String nickName);
	
	// 12. 회원이름 조회
	public abstract Integer checkUserName(String name);
	
	// 13. 회원이메일 조회
	public abstract Integer checkUserEMail(String EMail);
	
	// 14. 찾은 id 반환
	public abstract String showId(String name, String EMail);
	
	// 15. 회원ID 조회
	public abstract Integer checkUserId(String EMail);
	
	// 16. 비밀번호 변경
	public abstract Boolean changepw(String ID, String password);
	
	// 17. 이름 이메일 매칭 확인
	public Integer nameEMail(String name, String EMail) throws ServiceException;
	
	// 18. ID 이메일 매칭 확인
	public Integer IDEMail(String name, String ID, String EMail) throws ServiceException;
}
