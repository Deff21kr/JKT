package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.GroupBoardCriteria;
import org.zerock.myapp.domain.GroupBoardDTO;
import org.zerock.myapp.domain.GroupBoardVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.GroupBoardMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service("GroupBoardService")
public class GroupBoardServiceImpl 
	implements 
		GroupBoardService ,
		InitializingBean,
		DisposableBean {

	@Setter(onMethod_= {@Autowired} )
	private GroupBoardMapper dao;		// 영속성 계층의 DAO 빈을 주입받음 (DB작업이 필요하기 때문)

	// 1. 게시판 목록을 얻어 반환
	@Override
	public List<GroupBoardVO> getList(GroupBoardCriteria cri) throws ServiceException {
		log.trace("getList() invoekd.");
		
		try {
			return this.dao.selectList(cri);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getList
	
	// 2. 조건검색조회
	@Override
	public List<GroupBoardVO> getSearchList(GroupBoardCriteria cri) throws ServiceException {
		log.trace("getList() invoekd.");
		
		try {
			return this.dao.selectSearchList(cri);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getList

	
	// 3. 게시물 등록
	@Override
	public Boolean register(GroupBoardDTO dto) throws ServiceException {
		log.trace("register({}) invoked.", dto);
		
		try {
			return (this.dao.insert(dto) == 1);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // register

	
	// 4. 게시물 상세조회
	@Override
	public GroupBoardVO get(Integer postno) throws ServiceException {
		log.trace("get({}) invoked.", postno);
		
		try {
			return this.dao.select(postno);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // get

	
	// 5. 게시물 업데이트
	@Override
	public Boolean modify(GroupBoardDTO dto) throws ServiceException {
		log.trace("modify({}) invoked.", dto);
		
		try {
			return ( this.dao.update(dto) == 1 );
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // modify

	
	// 6. 게시물 삭제
	@Override
	public Boolean remove(Integer postno) throws ServiceException {
		log.trace("remove({}) invoked.", postno);
		
		try {
			return ( this.dao.delete(postno) == 1 );
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // remove

	
	// 7. 총 게시물 갯수 반환
	@Override
	public Integer getTotal() throws ServiceException {
		log.trace("getTotal() invoked.");
		
		return this.dao.getTotalAmount();
	} // getTotal
	
	
	// 8. 검색 조회된 게시물 갯수 반환
	@Override
	public Integer getSearchTotal(GroupBoardCriteria cri) throws ServiceException {
		log.trace("getSearchTotal() invoked.");
		
		return this.dao.getSearchTotalAmount(cri);
	} // getSearchTotal
	
	// 9. 조회 수 증가
	@Override
	public Integer plusReadcnt(Integer postNo) throws ServiceException {
		log.trace("plusReadcnt({}) invoked.", postNo);
		
		try {
			return this.dao.plusReadcnt(postNo);
		}catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
		

	} // plusReadcnt
	
//	====== IntitializingBean, DisposableBean ======


	@Override
	public void afterPropertiesSet() throws Exception {	// 초기화(=전처리)
		log.trace("afterPropertiesSet() invoked.");
		
//		assert this.dao != null;
		Objects.requireNonNull(this.dao);
//		assertNotNull(this.dao);	// main에서는 사용 불가능(JUnit test에서만 가능)
		
		log.info("\t+ this.dao : {}", this.dao);
	} // afterPropertiesSet
	
	@Override
	public void destroy() throws Exception {	// 자원해제(=후처리)
		log.trace("destroy() invoked.");
		
	} // destroy
	

} // end class
