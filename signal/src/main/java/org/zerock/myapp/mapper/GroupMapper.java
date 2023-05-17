package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.GroupsDTO;

public interface GroupMapper {

	// 1. 어따쓸지 고민
	@Select("SELECT  /*+ index_desc(TBL_groups) */  * FROM TBL_groups ")
	public abstract List<GroupsDTO> selectList();

	public abstract Integer insert(GroupsDTO dto);

	public abstract GroupsDTO select(Integer groupNo);
	
	public abstract GroupsDTO selectPost(Integer postNo);

	public abstract Integer delete(String groupNo);

	public abstract Integer update(GroupsDTO dto);
	
	public abstract Integer updateCurrentMem(GroupsDTO dto);
}
