package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.QNACommentDTO;
import org.zerock.myapp.domain.QNACommentVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.QNACommentMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Service("QNACommentService")
public class QNACommentServiceImpl implements QNACommentService, InitializingBean, DisposableBean{
	
	@Setter(onMethod_ = @Autowired)
	private QNACommentMapper dao;
    
	// 1. 게시판 목록을 얻어 반환해주는 기능 수행
	@Override
	public List<QNACommentVO> getList(Integer postNo) throws ServiceException {
		log.trace("getList() invoked.");
		
		try {
			return this.dao.selectList(postNo);
		} catch(Exception e){
			throw new ServiceException(e);
		} // try-catch
		
	} // getList

	
	// 댓글 작성
	@Override
	public Boolean insert(QNACommentDTO dto) throws ServiceException {
		log.trace("insert() invoked.");
		
		try {
			return ( this.dao.insert(dto) == 1 );
		} catch(Exception e){
			throw new ServiceException(e);
		} // try-catch
		
	} // insert
	
	// 댓글 삭제
	@Override
	public Boolean deleteComment(Integer commentNo) throws ServiceException {
		log.trace("delete() invoked.");
		
		try {
			return ( this.dao.deletecomment(commentNo) == 1 );
		} catch(Exception e){
			throw new ServiceException(e);
		} // try-catch
		
	} // deleteComment

	@Override
	public Boolean update(QNACommentDTO dto) throws ServiceException {
		log.info("update() invoked.");
		
		try {
			return (this.dao.update(dto) == 1);
		} catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // update
	
//	====== IntitializingBean, DisposableBean ======

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("afterPropertiesSet() invoked.");
		
	} // afterPropertiesSet
	
   @Override
	public void destroy() throws Exception {
		log.info("destroy() invoked.");
		
	} // destroy
	

} // end class
