package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.AuthDTO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.AuthMapper;
import org.zerock.myapp.mapper.UsersMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

// 얘도 빈

@Log4j2
@NoArgsConstructor

@Service("usersService")
public class UsersServiceImpl implements UsersService, InitializingBean, DisposableBean {

	@Setter(onMethod_ = { @Autowired })
	private UsersMapper dao;
	@Setter(onMethod_ = { @Autowired })
	private AuthMapper auth;
	@Override
	public List<UsersVO> getList() throws ServiceException {
		log.trace("\n*********************************************************\n			getList({}) "
				+ "\n********************************************************* ");

		try {
			return this.dao.selectList();

		} catch (Exception e) {
			throw new ServiceException(e);
		}

	} // selectAll

	@Override
	public Boolean register(UsersDTO dto) throws ServiceException {
		log.trace("\n*********************************************************\n			register({}) "
				+ "\n********************************************************* ",dto);
		
		String hashedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		try {
			AuthDTO user = new AuthDTO();
			dto.setPassword(hashedPassword);
			String id = dto.getID();
			user.setUserId(id);
			return ( (this.dao.insert(dto) == 1) && (this.auth.insertAuth(user)==1 ));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public UsersDTO get(String ID) throws ServiceException {
		log.trace("\n*********************************************************\n			get({}) "
				+ "\n********************************************************* ",ID);

		try {

			return this.dao.select(ID);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Boolean modify(UsersDTO dto) throws ServiceException {
		log.trace("\n*********************************************************\n			register({}) "
				+ "\n********************************************************* ",dto);

		try {

			return (this.dao.update(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Boolean remove(String ID) throws ServiceException {
		log.trace("\n*********************************************************\n			remove({}) "
				+ "\n********************************************************* ",ID);

		try {

			return (this.dao.delete(ID) == 1);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

//	====== InitializingBean, DisposalbleBean =====

	@Override
	public void afterPropertiesSet() throws Exception {
		// 초기화(전처리)
		log.trace("\n*********************************************************\n			afterPropertiesSet() "
				+ "\n********************************************************* ");

//		aseertNotNull(this.dao); ==> junit용
//		assert this.dao != null;
		Objects.requireNonNull(this.dao);

	}

	@Override
	public void destroy() throws Exception {
		// 자원해제(후처리)
		log.trace("\n*********************************************************\n			destroy() "
				+ "\n********************************************************* ");
	}

//	======================================================

}