package org.zerock.myapp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.DetailPlanDTO;
import org.zerock.myapp.domain.DetailPlanVO;
import org.zerock.myapp.domain.JoinMyPlanDTO;
import org.zerock.myapp.domain.MyPlanDTO;
import org.zerock.myapp.domain.MyPlanVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.DetailPlanMapper;
import org.zerock.myapp.mapper.JoinMyPlanMapper;
import org.zerock.myapp.mapper.MyPlanMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2

@Service("MyPlanService")
public class MyPlanServiceImpl implements MyPlanService {

	@Setter(onMethod_ = {@Autowired})
	private MyPlanMapper mapper;
	@Setter(onMethod_ = {@Autowired})
	private DetailPlanMapper detailPlanMapper;
	@Setter(onMethod_ = {@Autowired})
	private JoinMyPlanMapper joinMapper;
	

	@Override
	public List<MyPlanVO> getList(Criteria cri, String nickName) throws ServiceException {
		this.mapper.selectList(cri, nickName);
		
		try {
			
			return this.mapper.selectList(cri, nickName);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getList
	
	@Override
	public Boolean generate(MyPlanDTO dto) throws ServiceException {
	    try {
	        
	        return (this.mapper.make(dto) == 1);
	    } catch (Exception e) {
	        throw new ServiceException(e);
	    }
	} // generate

	@Override
	public MyPlanVO get(Integer planNo) throws ServiceException {
		try {
			return this.mapper.select(planNo);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	}


	@Override
	public Boolean remove(Integer planNo) throws ServiceException {
		try {

			return this.mapper.delete(planNo) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // remove

	@Override
	public Boolean modify(String planName, Integer planNo) throws ServiceException {
		try {

			return this.mapper.update(planName, planNo) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
 
	} // modify

	
	@Override
	public Integer getTotal() throws ServiceException {
		log.trace("getTotal() invoked");
		
		return this.mapper.getTotalAmount();
	}
	
	
	
//	====== DETAIL PLAN SERVICE IMPL ===========
	@Override
	public List<DetailPlanVO> getDetailPlanList(Integer planNo) throws ServiceException {
		try {
			log.trace("getDetailPlanList({}) invoked",planNo);
			
			return this.detailPlanMapper.selectList(planNo);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getDetailPlanList

	@Override
	public Boolean registerDetailPlan(DetailPlanDTO dto) throws ServiceException {
		try {
			
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			String date = format.format(dto.getPlanTime());
			Date Date = format.parse(date);
			dto.setPlanTime(Date);
			
			return (this.detailPlanMapper.insert(dto) == 1);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		

	} // registerDetailPlan

	@Override
	public DetailPlanVO getDetailPlan(Integer detailPlanNo) throws ServiceException {
		try {
			return this.detailPlanMapper.select(detailPlanNo);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getDetailPlan

	@Override
	public Boolean modifyDetailPlan(DetailPlanDTO dto) throws ServiceException {
		try {
			return (this.detailPlanMapper.update(dto) == 1);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // modifyDetailPlan

	@Override
	public Boolean removeDetailPlan(Integer detailPlanNo) throws ServiceException {
		try {
			
			return (this.detailPlanMapper.delete(detailPlanNo) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}

	@Override
	public List<JoinMyPlanDTO> joinList(String nickName) throws ServiceException {
		try {
			log.trace("joinList({}, {}) invoked", nickName);

			return this.joinMapper.selectList(nickName);
		} catch (Exception e) {
			throw new ServiceException(e);
		}	
		

	}


}
