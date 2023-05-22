package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.DetailPlanDTO;
import org.zerock.myapp.domain.DetailPlanVO;
import org.zerock.myapp.domain.JoinMyPlanDTO;
import org.zerock.myapp.domain.MyPlanDTO;
import org.zerock.myapp.domain.MyPlanVO;
import org.zerock.myapp.exception.ServiceException;

public interface MyPlanService {
	
	public abstract List<MyPlanVO> getList(Criteria cri, String nickName) throws ServiceException;
	
	public abstract Boolean generate(MyPlanDTO dto) throws ServiceException;

	public abstract MyPlanVO get(Integer planNo) throws ServiceException;
	
	public abstract Boolean remove(Integer planNo) throws ServiceException;
	
	public abstract Boolean modify(String planName, Integer planNo) throws ServiceException;
	
	
	// 총 게시물 개수 반환
	public abstract Integer getTotal() throws ServiceException;	
	
//	=========== DetailPlan ===================
	
	// 1. 상세계획 리스트( List )
	public abstract List<DetailPlanVO> getDetailPlanList(Integer planNo) throws ServiceException;

	// 2. 특정 상세계획 등록( CREATE )
	public abstract Boolean registerDetailPlan(DetailPlanDTO dto) throws ServiceException;
	
	// 3. 특정 상세계획 상세조회( READ )
	public abstract DetailPlanVO getDetailPlan(Integer detailPlanNo) throws ServiceException;
	
	// 4. 특정 상세계획 업데이트 ( UPDATE )
	public abstract Boolean modifyDetailPlan(DetailPlanDTO dto) throws ServiceException;
	
	// 5. 특정 상세계획 삭제 ( DELETE )
	public abstract Boolean removeDetailPlan(Integer detailPlanNo) throws ServiceException;
	
//	=========== Join MyPlan and DetailPlan ===================
	public abstract List<JoinMyPlanDTO> joinList( String nickName) throws ServiceException;


} // end class
