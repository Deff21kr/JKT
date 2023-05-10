package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.OneOnOneBoardDTO;
import org.zerock.myapp.domain.OneOnOneBoardVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.OneOnOneBoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service("OneOnOneBoardService")
public class OneOnOneBoardServiceImpl implements OneOnOneBoardService, InitializingBean, DisposableBean {

	@Setter(onMethod_ = {@Autowired})
	private OneOnOneBoardMapper dao;

	@Override
	public List<OneOnOneBoardVO> getList(Criteria cri) throws ServiceException {
		log.trace("getList({}) invoked", cri);

		try {
			return this.dao.selectList(cri);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	} // getList

	@Override
	public Boolean register(OneOnOneBoardDTO dto) throws ServiceException {
		log.trace("register({}) invoked", dto);

		try {

			return (this.dao.insert(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	}

	@Override
	public OneOnOneBoardVO get(Integer postno) throws ServiceException {
		log.trace("get({}) invoked", postno);

		try {
			return this.dao.select(postno);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // get

	@Override
	public Boolean modify(OneOnOneBoardDTO dto) throws ServiceException {
		try {
			return (this.dao.update(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch

	} // modify

	@Override
	public Boolean remove(Integer postno) throws ServiceException {
		log.trace("remove({}) invoked", postno);

		try {
			return (this.dao.delete(postno) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
	} // remove

//	============ InitializingBean, DisposableBean ============ //
	
	@Override
	public void afterPropertiesSet() throws Exception { // 초기화 (= 전처리)
		log.trace("afterPropertiesSet() invoked");
		
//		aseertNotNull(this.dao); ==> junit용
//		assert this.dao != null;
		Objects.requireNonNull(this.dao);
		
		log.info("\t + dao: {}", this.dao);
		
	} // afterPropertiesSet
	
	@Override
	public void destroy() throws Exception { // 자원해제 (= 후처리)
		log.trace("destroy() invoked");
		
	}

	@Override
	public Integer getTotal() throws ServiceException {
		log.trace("getTotal() invoked");
		
		return this.dao.getTotalAmount();
	}

	@Override
	public Boolean reply(OneOnOneBoardDTO dto) throws ServiceException {
		log.trace("reply() invoked");
		try {
			this.dao.increase(dto);
			
			return (this.dao.response(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
		
		
	} // reply

	
} // end class

