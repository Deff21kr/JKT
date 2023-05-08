package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.AuthDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.AuthMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@Service("authService")
public class AuthServiceImpl implements AuthService {
	
	@Setter(onMethod_ = @Autowired)
	private AuthMapper dao;

	@Override
	public List<AuthDTO> getList() throws ServiceException {
		log.trace("\n*********************************************************\n			getList({}) "
				+ "\n********************************************************* ");

		try {
			return this.dao.selectList();

		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public Boolean register(AuthDTO dto) throws ServiceException {
		log.trace("\n*********************************************************\n			register({}) "
				+ "\n********************************************************* ",dto);
		
		try {
			return (this.dao.insertAuth(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public AuthDTO get(String userid) throws ServiceException {
		
		log.trace("\n*********************************************************\n			get({}) "
				+ "\n********************************************************* ",userid);

		try {

			return this.dao.selectAuth(userid);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Boolean modify(AuthDTO dto) throws ServiceException {
		log.trace("\n*********************************************************\n			register({}) "
				+ "\n********************************************************* ",dto);

		try {

			return (this.dao.updateAuth(dto) == 1);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Boolean remove(String userid) throws ServiceException {

		log.trace("\n*********************************************************\n			remove({}) "
				+ "\n********************************************************* ",userid);

		try {

			return (this.dao.deleteAuth(userid) == 1);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

}
