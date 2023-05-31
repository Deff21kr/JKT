package org.zerock.myapp.service;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.PinDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.PinMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service("PinService")
public class PinServiceImpl 
	implements 
		PinService,
		InitializingBean,
		DisposableBean {

	@Setter(onMethod_= {@Autowired} )
	private PinMapper pinMapper;

	@Override
	public Integer doPin(PinDTO pin) throws ServiceException {
		log.trace("doPin() asdsaddassaddasdas.");
		
		try {
			
			if(this.pinMapper.checkPin(pin) == 0){
				return this.pinMapper.insert(pin);
			} else {
				PinDTO dto = this.pinMapper.select(pin.getPostNo(),pin.getNickName());
				return this.pinMapper.delete(dto.getPinNo())-1;
			} // if-else
		}catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
////		찜 데이터 체크
//		PinDTO checkPin = pinMapper.checkPin(pin);
//		
//		if(checkPin != null) {
//			log.info("*** 데이터가 있습니다.");
//			return 2;
//		} // if
//		
////		찜 등록 & 에러 시, 0 반환
//		try {
//			log.info("*** 데이터가 없습니다. 데이터 등록합니다.");
//			return pinMapper.insert(pin);
//		} catch(Exception e) {
//			log.info("*** 오류가 났습니다.");
//			return 0;
//		} // try-catch
		
	} // insert	
	
//	@Override
//	public Boolean delete(Integer pinNo) throws ServiceException {
//		log.trace("delete() invoked.");
//		
//		
//		try {
//			return (pinMapper.delete(pinNo) == 1);
//		} catch(Exception e) {
//			throw new ServiceException(e);
//		}
//		
//	} // delete
	
//	@Override
//	public List<PinDTO> select(String nickName) {
//		log.trace("select() invoked.");
//		
//		List<PinDTO> list = pinMapper.select(nickName);
//		log.trace("찜 목록 체크: " + list);
//		
//		return list;
//	
//	} // select
//	
//	@Override
//	public Integer checkPin(PinDTO pin) {
//		// TODO Auto-generated method stub
//		return null;
//	} // checkPin

//	====== IntitializingBean, DisposableBean ======

	@Override
	public void afterPropertiesSet() throws Exception {
		log.trace("afterPropertiesSet() invoked.");
		
	} // afterPropertiesSet

	@Override
	public void destroy() throws Exception {
		log.trace("destroy() invoked.");
		
	} // destroy

} // end class
