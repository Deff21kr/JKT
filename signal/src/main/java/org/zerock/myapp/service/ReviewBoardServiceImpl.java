package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.myapp.domain.AttachFileDTO;
import org.zerock.myapp.domain.ReviewBoardDTO;
import org.zerock.myapp.domain.ReviewBoardVO;
import org.zerock.myapp.domain.ReviewBoardCriteria;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.BoardAttachMapper;
import org.zerock.myapp.mapper.ReviewBoardMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service("ReviewService")
public class ReviewBoardServiceImpl implements ReviewBoardService, InitializingBean, DisposableBean {

	//// 영속성 계층의 DAO 빈을 주입 for DB 작업
	@Setter(onMethod_ = { @Autowired })
	private ReviewBoardMapper dao;

	@Setter(onMethod_ = { @Autowired })
	private BoardAttachMapper attachMapper;

	
	// 1. 게시판 목록을 얻어 반환해주는 기능 수행 (페이징 처리O)
	@Override
	public List<ReviewBoardVO> getList(ReviewBoardCriteria cri) throws ServiceException {
		log.trace("getList() invoked.");

		try {
			return this.dao.selectList(cri);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // getList

	
	// 2. 새로운 게시물 등록 기능 수행 (CREATE)
	@Override
	public Boolean register(ReviewBoardDTO dto) throws ServiceException {
		log.trace("register({}) invoked.", dto);

		try {
			return (this.dao.insert(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // register

	
	@Transactional
	@Override
	public Boolean registerWithFile(ReviewBoardDTO dto) throws ServiceException {
		log.trace("register({}) invoked.", dto);

		try {

			// 1. 먼저 boardmapper에서 dto로 전달받은 내용을 insert 실행
			dao.insert(dto);
			
			if (dto.getAttachList() == null || dto.getAttachList().size() <= 0) {
				return false;
			} else {
				dto.getAttachList().forEach(attach -> {	
					
					// 2. 게시판의dto로부터 게시물번호를 전달받아서 atttach의 게시물번호에 넣기
					attach.setPostNo(dto.getPostNo());
					attachMapper.insert(attach);
					
				});// forEach
				return true;
			} // if-else
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // registerWithFile

	
	
	// 3. 특정 게시물 상세 조회 기능 수행 (READ)
	@Override
	public ReviewBoardVO get(Integer postNo) throws ServiceException {
		log.trace("get({}) invoked.", postNo);

		try {
			return this.dao.select(postNo);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // get

	
	
	// 4. 특정 게시물 업데이트 기능 수행 (UPDATE)
	@Override
	public Boolean modify(ReviewBoardDTO dto) throws ServiceException {
		log.trace("modify({}) invoked.", dto);

		try {
			return (this.dao.update(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // modify

	
	
	
	// 5. 특정 게시물 삭제 기능 수행 (DELETE)
	@Override
	public Boolean remove(Integer postNo) throws ServiceException {
		log.trace("remove({}) invoked.", postNo);

		try {
			return (this.dao.delete(postNo) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // remove

	
	
	//6. 총 게시물 수 얻기
	@Override
	public Integer getTotal() throws ServiceException {
		log.trace("getTotal() invoked.");

		return this.dao.getTotalAmount();
	}// getTotal
	
	
	//7. 특정게시물 번호의 첨부파일 얻기
	@Override
	public List<AttachFileDTO> getAttachList(Integer postNo) {
		log.info("{}번의 게시물로부터 첨부파일을 얻었습니다.", postNo);

		return attachMapper.findByPostNo(postNo);
		
	}// getAttachList
	
	
	//8. 조회수
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
	public void afterPropertiesSet() throws Exception { // 초기화(=전처리)
		log.trace("afterPropertiesSet() invoked.");

//		assert this.dao != null;
		Objects.requireNonNull(this.dao);
//		assertNotNull(this.dao);	// main에서는 사용 불가능(JUnit test에서만 가능)

		log.info("\t+ this.dao : {}", this.dao);
	} // afterPropertiesSet

	@Override
	public void destroy() throws Exception { // 자원해제(=후처리)
		log.trace("destroy() invoked.");

	}






}// end class
