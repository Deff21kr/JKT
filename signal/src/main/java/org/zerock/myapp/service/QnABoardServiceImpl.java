package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.QnABoardDTO;
import org.zerock.myapp.domain.QnABoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.QnABoardMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service("QnABoardService")
public class QnABoardServiceImpl 
	implements 
		QnABoardService ,
		InitializingBean,
		DisposableBean {

	@Setter(onMethod_= {@Autowired} )
	private QnABoardMapper dao;				// 영속성 계층의 DAO 빈을 주입받음 (DB작업이 필요하기 때문)
	
	
	// 1. 게시판 목록을 얻어 반환해주는 기능 수행
	@Override
	public List<QnABoardVO> getList(Criteria cri) throws ServiceException {
		log.trace("getList() invoked.");
		
		try {
			return this.dao.selectList(cri);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getList
	
	
	// 2. 새로운 게시물 등록 기능 수행 (CREATE)
	@Override
	public Boolean register(QnABoardDTO dto) throws ServiceException {
		log.trace("register({}) invoked.", dto);
		
		try {
			return ( this.dao.insert(dto) == 1 );
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // register
	
	
	// 3. 특정 게시물 상세 조회 기능 수행 (READ)
	@Override
	public QnABoardVO get(Integer postNo) throws ServiceException {
		log.trace("get({}) invoked.", postNo);
		
		try {
			return this.dao.select(postNo);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // get
	
	
	// 4. 특정 게시물 업데이트 기능 수행 (UPDATE)
	@Override
	public Boolean modify(QnABoardDTO dto) throws ServiceException {
		log.trace("modify({}) invoked.", dto);
		
		try {
			return ( this.dao.update(dto) == 1 );
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // modify
	
	
	// 5. 특정 게시물 삭제 기능 수행 (DELETE)
	@Override
	public Boolean remove(Integer postNo) throws ServiceException {
		log.trace("remove({}) invoked.", postNo);
		
		try {
			return ( this.dao.delete(postNo) == 1 );
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // remove
	
	// 조회수
	@Override
	public Integer updateReadcnt(Integer postNo) throws ServiceException {
		log.trace("updateReadcnt({}) invoked.", postNo);
		
		try {
			return this.dao.updateReadcnt(postNo);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // updateRecnt
	
	
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

	@Override
	public Integer getTotal() throws ServiceException {
		log.trace("getTotal() invoked.");
		
		return this.dao.getTotalAmount();
		
	} // getTotal


} // end class
