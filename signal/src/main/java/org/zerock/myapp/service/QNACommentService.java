package org.zerock.myapp.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.QNACommentDTO;
import org.zerock.myapp.domain.QNACommentVO;
import org.zerock.myapp.exception.ServiceException;

@Repository
public interface QNACommentService {
	
	
	// 댓글 조회
	public abstract List<QNACommentVO> getList(Integer postno) throws ServiceException;
	
	// 댓글 작성
	public abstract Boolean insert(QNACommentDTO dto) throws ServiceException;
	
	// 댓글 삭제
	public abstract Boolean deleteComment(Integer commentno) throws ServiceException;
	
	// 댓글 업데이트
	public abstract Boolean update(QNACommentDTO dto) throws ServiceException;
	
}
