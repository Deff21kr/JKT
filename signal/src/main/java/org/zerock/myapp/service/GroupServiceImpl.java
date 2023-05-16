package org.zerock.myapp.service;

import java.util.Objects;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.GroupBoardDTO;
import org.zerock.myapp.domain.GroupsDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.GroupBoardMapper;
import org.zerock.myapp.mapper.GroupMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Service("GroupService")
public class GroupServiceImpl implements GroupService,InitializingBean,
DisposableBean {
	
	@Setter(onMethod_= {@Autowired} )
	private GroupMapper group;
	@Setter(onMethod_= {@Autowired} )
	private GroupBoardMapper board;


	@Override
	public Boolean register(GroupsDTO dto,GroupBoardDTO bo) throws ServiceException {
		
		try {
			Integer postNo=bo.getPostNo();
			dto.setPostNo(postNo);
			return (this.group.insert(dto) ==1);
		} catch (Exception e){
			throw new ServiceException(e);
		}
	}
	
	@Override
	public GroupsDTO get(Integer groupNo) throws ServiceException {
		try {
			return this.group.select(groupNo);
		} catch (Exception e){
			throw new ServiceException(e);
		}
	} // destroy


	@Override
	public void afterPropertiesSet() throws Exception {	// 초기화(=전처리)
		log.trace("afterPropertiesSet() invoked.");
		
//		assert this.dao != null;
		Objects.requireNonNull(this.group);
//		assertNotNull(this.dao);	// main에서는 사용 불가능(JUnit test에서만 가능)
		
		log.info("\t+ this.dao : {}", this.group);
	} // afterPropertiesSet
	
	@Override
	public void destroy() throws Exception {	// 자원해제(=후처리)
		log.trace("destroy() invoked.");
		
	}

	@Override
	public GroupsDTO getPost(Integer postNo) throws ServiceException {
		try {
			return this.group.selectPost(postNo);
		} catch (Exception e){
			throw new ServiceException(e);
		}
	}



	
}