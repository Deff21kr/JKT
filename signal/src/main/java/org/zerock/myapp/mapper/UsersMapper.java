package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.http.ResponseEntity;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;

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
				SELECT NICKNAME, TITLE, CONTENT, REGIDATE, BOARDNAME
			FROM (
			SELECT NICKNAME, TITLE, CONTENT, REGIDATE, BOARDNAME
			FROM TBL_QNABOARD WHERE NICKNAME = #{nickName}
			UNION
			SELECT NICKNAME, TITLE, CONTENT, REGIDATE, BOARDNAME
			FROM TBL_REVIEWBOARD WHERE NICKNAME = #{nickName}
			UNION
			SELECT NICKNAME, TITLE, CONTENT, REGIDATE, BOARDNAME
			FROM TBL_GROUPBOARD WHERE NICKNAME = #{nickName}
			)
			ORDER BY REGIDATE DESC
				OFFSET (#{cri.currPage} -1) * #{cri.amount} ROWS
				FETCH NEXT #{cri.amount} ROWS ONLY
				""")
	public abstract List<UsersDTO> selectWriteList(String nickName, Criteria cri);

	// 9. 글쓴 내역의 게시글 총 개수
	@Select("SELECT count(nickName) \r\n" + "FROM\r\n"
			+ "(SELECT NICKNAME, title, content, regidate, boardname FROM TBL_QNABOARD WHERE NICKNAME = #{nickName}\r\n"
			+ "UNION\r\n"
			+ "SELECT NICKNAME, title, content, regidate, boardname FROM TBL_REVIEWBOARD WHERE NICKNAME = #{nickName}\r\n"
			+ "UNION\r\n"
			+ "SELECT NICKNAME, title, content, regidate, boardname FROM TBL_GROUPBOARD WHERE NICKNAME = #{nickName})")
	public abstract Integer getWriteList(String nickName);
}
