package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.AuthDTO;

public interface AuthMapper {

	public abstract Integer insertAuth(AuthDTO auth); // user_auth 테이블에 데이터 삽입
	
	@Select("SELECT  /*+ index_desc(tbl_users) */  * FROM TBL_USERS ")
	public abstract List<AuthDTO> selectList();

	// 3. 회원권한검색
	public abstract AuthDTO selectAuth(String userid);

	// 4. 회원권한삭제
	public abstract Integer deleteAuth(String userid);

	// 5. 회원권한수정
	public abstract Integer updateAuth(AuthDTO dto);

}
