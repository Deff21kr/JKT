package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.CommentCriteria;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupBoardCommentDTO;
import org.zerock.myapp.domain.GroupBoardCommentVO;
import org.zerock.myapp.domain.QnACommentDTO;
import org.zerock.myapp.domain.QnACommentVO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.GroupBoardCommentMapper;
import org.zerock.myapp.mapper.QnACommentMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service("GroupBoardCommentService")
public class GroupBoardCommentServiceImpl implements GroupBoardCommentService, InitializingBean, DisposableBean {

	@Setter(onMethod_ = @Autowired)
	private GroupBoardCommentMapper dao;

	// 1. 게시판 목록을 얻어 반환해주는 기능 수행
	@Override
	public List<GroupBoardCommentVO> selectList(CommentCriteria commentCri, Integer postNo) throws ServiceException {
		log.trace("getList() invoked.");

		try {
			return this.dao.selectList(commentCri, postNo);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // getList

	// 댓글 작성
	@Override
	public Boolean insert(GroupBoardCommentDTO dto) throws ServiceException {
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
	public Boolean update(GroupBoardCommentDTO dto) throws ServiceException {
		log.info("update() invoked.");

		try {
			return (this.dao.update(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	}

	@Override
	public void destroy() throws Exception {
		log.trace("디스토로이이이이이");

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.trace("후처리이이이잉이ㅣ");
	}

	@Override
	public Integer getCommentTotal(Integer postNo) throws ServiceException {
		return this.dao.getCommentTotalAmount(postNo);
	}



	



} // end class
