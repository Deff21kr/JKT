package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupBoardCriteria;
import org.zerock.myapp.domain.QnACommentDTO;
import org.zerock.myapp.domain.QnACommentVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.QnACommentMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service("QNACommentService")
public class QnACommentServiceImpl implements QnACommentService, InitializingBean, DisposableBean {

	@Setter(onMethod_ = @Autowired)
	private QnACommentMapper dao;

	// 1. 게시판 목록을 얻어 반환해주는 기능 수행
	@Override
	public List<QnACommentVO> getList(Criteria cri) throws ServiceException {
		log.trace("getList() invoked.");

		try {
			return this.dao.selectList(cri);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // getList

	// 댓글 작성
	@Override
	public Boolean insert(QnACommentDTO dto) throws ServiceException {
		log.trace("insert() invoked.");

		try {
			return (this.dao.insert(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // insert

	// 댓글 삭제
	@Override
	public Boolean delete(Integer commentNo) throws ServiceException {
		log.trace("delete() invoked.");

		try {
			return (this.dao.delete(commentNo) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // deleteComment

	@Override
	public Boolean update(QnACommentDTO dto) throws ServiceException {
		log.info("update() invoked.");

		try {
			return (this.dao.update(dto) == 1);
		} catch (Exception e) {
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

	}

	@Override
	public Integer getTotal() throws ServiceException {
		log.trace("getTotal() invoked.");

		return this.dao.getTotalAmount();

	}

} // end class
