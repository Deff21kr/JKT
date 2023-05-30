package org.zerock.myapp.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.CommentCriteria;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupBoardCommentDTO;
import org.zerock.myapp.domain.GroupBoardCommentVO;
import org.zerock.myapp.domain.QnACommentDTO;
import org.zerock.myapp.domain.QnACommentVO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;

//@Repository
@Service("groupBoardComment")
public interface GroupBoardCommentService {

	// 댓글 조회
	public abstract List<GroupBoardCommentVO> selectList(CommentCriteria commentCri, Integer postNo) throws ServiceException;
	
	// 댓글 작성
	public abstract Boolean insert(GroupBoardCommentDTO dto) throws ServiceException;

	// 댓글 삭제
	public abstract Boolean delete(Integer commentNo) throws ServiceException;

	// 댓글 업데이트
	public abstract Boolean update(GroupBoardCommentDTO dto) throws ServiceException;
	
	// 6. 총 댓글 갯수 반환
	public abstract Integer getCommentTotal(Integer postNo) throws ServiceException;

	

} // end interface
