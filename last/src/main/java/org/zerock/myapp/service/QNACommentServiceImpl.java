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

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service("QNACommentService")
public class QNACommentServiceImpl implements QNACommentService,
InitializingBean,
DisposableBean{
    @Autowired
    private QNACommentMapper dao;
    
    @Override
	public void destroy() throws Exception {
		log.info("파괴 호추루루루");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("afterPropertiesSet() 호추룬머ㅝㅜㅁㄹ너ㅜㄴ");
	}
	
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
		try {
			return ( this.dao.insert(dto) == 1 );
		} catch(Exception e){
			throw new ServiceException(e);
		} // try-catch
	}
	
	// 댓글 삭제
	@Override
	public Boolean deleteComment(Integer commentno) throws ServiceException {
		try {
			return ( this.dao.deletecomment(commentno) == 1 );
		} catch(Exception e){
			throw new ServiceException(e);
		} // try-catch
	}

	@Override
	public Boolean update(QNACommentDTO dto) throws ServiceException {
		log.info("업데이트 했따아아아");
		
		try {
			return (this.dao.update(dto) == 1);
		} catch(Exception e) {
			throw new ServiceException(e);
		}
	}

	

	

}
