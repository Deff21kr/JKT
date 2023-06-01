package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupBoardDTO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ServiceException;
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

	@Setter (onMethod_ = @Autowired)
	private BCryptPasswordEncoder encoder;
	
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
		
		String hashedPassword = this.encoder.encode(dto.getPassword());
		try {
			dto.setPassword(hashedPassword);
			
			return ( (this.dao.insert(dto) == 1));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public UsersVO get(String ID) throws ServiceException {
		log.trace("\n*********************************************************\n			get({}) "
				+ "\n********************************************************* ",ID);

		try {

			return this.dao.select(ID);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public UsersVO getByNick(String nickName) throws ServiceException {
		try {

			return this.dao.selectByNick(nickName);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	
	

	@Override
	public Boolean modify(UsersDTO dto) throws ServiceException {
		log.trace("\n*********************************************************\n			register({}) "
				+ "\n********************************************************* ",dto);

		String hashedPassword = this.encoder.encode(dto.getPassword());
		try {
			dto.setPassword(hashedPassword);
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
	
	@Override
	public Integer checkIdService(String ID) throws ServiceException {
		
		try {
			log.trace("\n*********************************************************\n			id 체크({}) "
					+ "\n********************************************************* ",ID);
			return this.dao.checkID(ID);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Integer checkNickNameService(String checkNickName) throws ServiceException {
		
		try {
			log.trace("\n*********************************************************\n			닉넴 체크({}) "
					+ "\n********************************************************* ",checkNickName);

			return this.dao.checkNickName(checkNickName);

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public Boolean profileEdit(UsersDTO dto) throws ServiceException {
		try {
			return this.dao.profileEdit(dto);
		} catch(Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	public Integer checkUserName(String name) throws ServiceException {
		try {
			return this.dao.checkUserName(name);
		} catch(Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public Integer checkUserEMail(String EMail) throws ServiceException {
		try {
			return this.dao.checkUserEMail(EMail);
		} catch(Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public Integer checkUserId(String ID) throws ServiceException {
		try {
			return this.dao.checkUserId(ID);
		} catch(Exception e) {
			throw new ServiceException(e);
		}
	}
	
	
	
	public String showId(String name, String EMail) throws ServiceException{
		try {
			return this.dao.showId(name, EMail);
		} catch(Exception e) {
			throw new ServiceException(e);
		}
	}
	

	public Boolean changepw(String ID, String password) throws ServiceException {
		try {
			UsersVO vo = this.dao.select(ID);
			UsersDTO dto = vo.toDTO();
			
			String hashedPassword = this.encoder.encode(password);
			dto.setPassword(hashedPassword);
			
			return this.dao.changepw(ID, hashedPassword);
			
		} catch(Exception e) {
			throw new ServiceException(e);
		}
	}
	
	// 아이디 찾기
	public Integer nameEMail(String name, String EMail) throws ServiceException {
		try {
			return this.dao.nameEMail(name, EMail);
		} catch(Exception e) {
			throw new ServiceException(e);
		}
	}
	
	
	// 비밀번호 찾기
	public Integer IDEMail(String name, String ID, String EMail) throws ServiceException {
		try {
			return this.dao.IDEMail(name, ID, EMail);
		} catch(Exception e) {
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
	
	// 글쓴 내역
	@Override
	public List<UsersDTO> selectWriteList(String nickName, Criteria cri) throws ServiceException {
		try {
			return this.dao.selectWriteList(nickName, cri);
		} catch(Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	// 글쓴 내역의 총 개수
	@Override
	public Integer getWriterList(String nickName) throws ServiceException {
		try {
			return this.dao.getWriteList(nickName);
		}catch(Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	// 찜 리스트
	@Override
	public List<GroupBoardDTO> selectPinLists(String nickName, Criteria cri) throws ServiceException {
		try {
		return this.dao.selectPinList(nickName, cri);
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}
	
	// 찜 총 개수
	@Override
	public Integer getPinList(String nickName) throws ServiceException {
		try {
		return this.dao.getPinList(nickName);
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}
	

//	======================================================

}
