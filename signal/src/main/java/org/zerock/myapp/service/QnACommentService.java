package org.zerock.myapp.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.QnACommentDTO;
import org.zerock.myapp.domain.QnACommentVO;
import org.zerock.myapp.exception.ServiceException;

@Repository
public interface QnACommentService {
	
	
	// 댓글 조회
	public abstract List<QnACommentVO> getList(Integer postNo) throws ServiceException;
	
	// 댓글 작성
	public abstract Boolean insert(QnACommentDTO dto) throws ServiceException;
	
	// 댓글 삭제
	public abstract Boolean delete(Integer commentNo) throws ServiceException;
	
	// 댓글 업데이트
	public abstract Boolean update(QnACommentDTO dto) throws ServiceException;
	
} // end interface
