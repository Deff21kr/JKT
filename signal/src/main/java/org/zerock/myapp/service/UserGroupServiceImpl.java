package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupsDTO;
import org.zerock.myapp.domain.UserGroupDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.GroupMapper;
import org.zerock.myapp.mapper.UserGroupMapper;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@NoArgsConstructor

@Service("userGroupService")
public class UserGroupServiceImpl implements UserGroupService {
	@Autowired
	private UserGroupMapper dao;
	@Autowired
	private GroupMapper group;
	
	@Override
	public List<UserGroupDTO> getList(String nickName,Criteria cri) throws ServiceException {
		
		try {
			return this.dao.selectList(nickName,cri);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}

	@Override
	public List<UserGroupDTO> getMyAppList(String nickName,Criteria cri) throws ServiceException {
		try {
			return this.dao.selectMyAppList(nickName,cri);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}
	
	@Override
	public List<UserGroupDTO> getFriendList(Integer groupNo) throws ServiceException {
		try {
			return this.dao.selectListFriend(groupNo);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}


	@Override
	public UserGroupDTO get(Integer appNo) throws ServiceException {
		try {
			return this.dao.select(appNo);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	}
//
	@Override
	public Boolean modify(UserGroupDTO dto) throws ServiceException {
		
		GroupsDTO a =this.group.select(dto.getGroupNo());
		log.info("\n\n 이프전 서비스\n\t dto : {}\n\ta : {} ",dto,a);
		try {
				if(dto.getOutCome().equals("수락") & (a.getMemberNum() > a.getCurrentMember() )) {
					log.info("\n\n++서비스 IF \n++	dto : {} ,\n	++a : {}\n\n" ,dto,a);
					Integer getCurrentMember = a.getCurrentMember();
					a.setCurrentMember(getCurrentMember+1);
					log.info("\n현재 멤버 +1 : {}",a.getCurrentMember());
					
					return ( 1==this.dao.update(dto) & (this.group.updateCurrentMem(a)==1) ) ;
				} else if(dto.getOutCome().equals("거절")) {
					log.info("\n\n++서비스 else \n++	dto : {} ,\n	++a : {}\n\n" ,dto,a);
					return ( 1==this.dao.update(dto) ) ;
				} else { // currMem이 최대멤버보다 같거나 클때
					// 수락을 누르든 거절을 누른든 무조건 거절로 업뎃
					dto.setOutCome("거절");
					log.info("무조건 거절 : {}",dto.getOutCome());
					return (1==this.dao.update(dto));
				}
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	}
//
//	@Override
//	public Boolean remove(Integer appNo) throws ServiceException {
//		return null;
//	}
//
//	@Override
//	public Integer getTotal() throws ServiceException {
//		return null;
//	}

	@Override
	public Boolean register(String nickName,Integer groupNo) throws ServiceException {
		
		try {
			
			return ( (this.dao.insert(nickName,groupNo) == 1));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Boolean registerDefault(String nickName, Integer groupNo) throws ServiceException {
try {
			log.info("글쓴이 등록됨");
			return ( (this.dao.insertDefault(nickName,groupNo) == 1));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Integer getTotal(String nickName) throws ServiceException {
		
			log.trace("getTotal() invoked.");
		
		return this.dao.getTotalAmount(nickName);
	}
	@Override
	public Integer getTotalAppList(String nickName) throws ServiceException {
		
			log.trace("getTotalAppList() invoked.");
		
		return this.dao.getTotalAmountAppList(nickName);
	}

	@Override
	public Integer groupCheckIDService(String nickName, Integer postNo) throws ServiceException {
		try {
			log.info("동행 신청한 id 중복 판별 :{}, {} ",nickName,postNo);
			return this.dao.groupCheckID(nickName,postNo);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}


} // end class
