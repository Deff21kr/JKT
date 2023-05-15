package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.UserGroupDTO;

public interface UserGroupMapper {
	
	@Select("SELECT  /*+ index_desc(tbl_user_group) */  * FROM TBL_USER_GROUP ")
	public abstract List<UserGroupDTO> selectList();
	
	public abstract Integer insert(UserGroupDTO dto);

}
