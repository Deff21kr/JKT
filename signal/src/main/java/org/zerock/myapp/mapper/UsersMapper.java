package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.http.ResponseEntity;
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
}
