package org.zerock.myapp.service;

import java.util.List;
import java.util.Map;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupDetailPlanDTO;
import org.zerock.myapp.domain.GroupDetailPlanVO;
import org.zerock.myapp.domain.GroupPlanDTO;
import org.zerock.myapp.domain.GroupPlanVO;
import org.zerock.myapp.domain.JoinGroupPlanDTO;
import org.zerock.myapp.exception.ServiceException;

public interface GroupPlanService {

public abstract List<GroupPlanVO> getList(Criteria cri, String nickName) throws ServiceException;
	
	public abstract Boolean generate(GroupPlanDTO dto) throws ServiceException;

	public abstract GroupPlanVO get(Integer planNo) throws ServiceException;
	
	public abstract Boolean remove(Integer planNo) throws ServiceException;
	
	public abstract Boolean modify(String groupName, Integer planNo) throws ServiceException;
	
	public abstract List<Map<String, Integer>> nameList(String nickName) throws ServiceException;
	
	// 총 게시물 개수 반환
	public abstract Integer getTotal() throws ServiceException;	
	
//	=========== GroupDetailPlan ===================
	
	// 1. 상세계획 리스트( List )
	public abstract List<GroupDetailPlanVO> getDetailPlanList(Integer planNo) throws ServiceException;

	// 2. 특정 상세계획 등록( CREATE )
	public abstract Boolean registerDetailPlan(GroupDetailPlanDTO dto) throws ServiceException;
	
	// 3. 특정 상세계획 상세조회( READ )
	public abstract GroupDetailPlanVO getDetailPlan(Integer detailPlanNo) throws ServiceException;
	
	// 4. 특정 상세계획 업데이트 ( UPDATE )
	public abstract Boolean modifyDetailPlan(GroupDetailPlanDTO dto) throws ServiceException;
	
	// 5. 특정 상세계획 삭제 ( DELETE )
	public abstract Boolean removeDetailPlan(Integer detailPlanNo) throws ServiceException;
	
//	=========== Join MyPlan and DetailPlan ===================
	public abstract List<JoinGroupPlanDTO> joinList( String nickName) throws ServiceException;


	
}
