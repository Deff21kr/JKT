package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.GroupsDTO;
import org.zerock.myapp.domain.UserGroupDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.GroupMapper;
import org.zerock.myapp.mapper.UserGroupMapper;

@Service("userGroupService")
public class UserGroupServiceImpl implements UserGroupService {
	@Autowired
	private UserGroupMapper dao;
	@Autowired
	private GroupMapper group;
	
	@Override
	public List<UserGroupDTO> getList(String nickName) throws ServiceException {
		
		try {
			return this.dao.selectList(nickName);
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
		try {
				if(dto.getOutCome()=="수락") {
					a.setCurrentMember(a.getCurrentMember()+1);
					this.group.update(a);
					return ( 1==this.dao.update(dto) ) ;
				} else {
					return ( 1==this.dao.update(dto) ) ;
				}
//				else if(dto.getOutCome()=="거절") {
//					return this.dao.delete(dto.getAppNo()) ==1;
//				}
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

}
