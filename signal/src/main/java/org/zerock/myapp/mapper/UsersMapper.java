package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;

public interface UsersMapper {

	// 1. 어따쓸지 고민
	@Select("SELECT  /*+ index_desc(tbl_users) */  * FROM TBL_USERS ")
	public abstract List<UsersVO> selectList();

	// 2. 회원가입
	public abstract Integer insert(UsersDTO dto);

	// 3. 회원id로 게시물 검색
	public abstract UsersVO select(Integer userno);

	// 4. 회원탈퇴
	public abstract Integer delete(Integer userno);

	// 5. 회원정보수정
	public abstract Integer update(UsersDTO dto);

}
