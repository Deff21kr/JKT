package org.zerock.myapp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupDetailPlanDTO;
import org.zerock.myapp.domain.GroupDetailPlanVO;
import org.zerock.myapp.domain.GroupPlanDTO;
import org.zerock.myapp.domain.GroupPlanVO;
import org.zerock.myapp.domain.JoinGroupPlanDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.GroupDetailPlanMapper;
import org.zerock.myapp.mapper.GroupPlanMapper;
import org.zerock.myapp.mapper.JoinGroupPlanMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
@Log4j2

@Service("GroupPlanService")
public class GroupPlanServiceImpl implements GroupPlanService {

	@Setter(onMethod_ = @Autowired)
	private GroupPlanMapper mapper;
	@Setter(onMethod_ = @Autowired)
	private GroupDetailPlanMapper detailPlanMapper;
	@Setter(onMethod_ = @Autowired)
	private JoinGroupPlanMapper joinMapper;
	
	@Override
	public List<GroupPlanVO> getList(Criteria cri, String nickName) throws ServiceException {

		try {
			
			return this.mapper.selectList(cri, nickName);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	} // getList

	@Override
	public Boolean generate(GroupPlanDTO dto) throws ServiceException {

		try {
			
			return this.mapper.make(dto) == 1;
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	
	} // generate

	@Override
	public GroupPlanVO get(Integer planNo) throws ServiceException {

		try {
				
				return this.mapper.select(planNo);
			} catch (Exception e) {
				throw new ServiceException(e);
			} // try-catch
		
	} // get

	@Override
	public Boolean remove(Integer planNo) throws ServiceException {
		
		try {
				return this.mapper.delete(planNo) == 1;
			} catch (Exception e) {
				throw new ServiceException(e);
			} // try-catch
		
	}

	@Override
	public Boolean modify(String groupName, Integer planNo) throws ServiceException {
		try {
				
				return this.mapper.update(groupName, planNo) == 1;
			} catch (Exception e) {
				throw new ServiceException(e);
			} // try-catch
		
	}

	@Override
	public List<Map<String, Integer>> nameList(String nickName) throws ServiceException {
		try {
				
				return this.mapper.groupNameList(nickName);
			} catch (Exception e) {
				throw new ServiceException(e);
			} // try-catch
		
	}

	@Override
	public Integer getTotal() throws ServiceException {
		log.trace("getTotal() invoked");
		
		return this.mapper.getTotalAmount();
	}

	@Override
	public List<GroupDetailPlanVO> getDetailPlanList(Integer planNo) throws ServiceException {
		try {
			log.trace("getDetailPlanList({}) invoked",planNo);
			
			return this.detailPlanMapper.selectList(planNo);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}

	@Override
	public Boolean registerDetailPlan(GroupDetailPlanDTO dto) throws ServiceException {
		try {
			
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			String date = format.format(dto.getPlanTime());
			Date Date = format.parse(date);
			dto.setPlanTime(Date);
			
			return (this.detailPlanMapper.insert(dto) == 1);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	}

	@Override
	public GroupDetailPlanVO getDetailPlan(Integer detailPlanNo) throws ServiceException {
		try {
			return this.detailPlanMapper.select(detailPlanNo);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}

	@Override
	public Boolean modifyDetailPlan(GroupDetailPlanDTO dto) throws ServiceException {
		try {
			return (this.detailPlanMapper.update(dto) == 1);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}

	@Override
	public Boolean removeDetailPlan(Integer detailPlanNo) throws ServiceException {
		try {
				
				return (this.detailPlanMapper.delete(detailPlanNo) == 1);
			} catch (Exception e) {
				throw new ServiceException(e);
			} // try-catch
		
	}

	@Override
	public List<JoinGroupPlanDTO> joinList(String nickName) throws ServiceException {
		try {
			log.trace("joinList({}, {}) invoked", nickName);

			return this.joinMapper.selectList(nickName);
		} catch (Exception e) {
			throw new ServiceException(e);
		}	
	}

}
