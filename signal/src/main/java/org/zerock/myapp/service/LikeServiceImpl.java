package org.zerock.myapp.service;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.LikeDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.LikeMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service("LikeService")
public class LikeServiceImpl 
	implements 
		LikeService,
		InitializingBean,
		DisposableBean {

	@Setter(onMethod_= {@Autowired} )
	private LikeMapper likeMapper;
	
	@Override
	public Integer doLike(LikeDTO like) throws ServiceException {
		log.info("doLike() invoked.");
		
		try {
			if(this.likeMapper.checkLike(like) == 0) {
				return this.likeMapper.insert(like);
			} else {
				LikeDTO dto = this.likeMapper.select(like.getPostNo(), like.getNickName());
				return this.likeMapper.delete(dto.getLikeNo()) -1;
			} // if-else
		}catch(Exception e) {
			throw new ServiceException(e);
		} // try-catch
		
	} // doLike

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
