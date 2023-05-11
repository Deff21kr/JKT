package org.zerock.myapp.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.DAOException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository("loginDAO")
@AllArgsConstructor
@Log4j2
public class LoginDAOImpl implements LoginDAO, InitializingBean, DisposableBean {
	
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public void afterPropertiesSet() throws Exception {
		log.trace("afterPropertiesSet() invoked");

		Objects.requireNonNull(this.sqlSessionFactory);
		log.info("{}", sqlSessionFactory);
	}

	// 사용자가 제공한 아이디,비밀번호로 인증 수행
	@Override
	public UsersVO selectUserIdPw(UsersDTO dto) throws DAOException {
		log.trace("selectUser({}) invoked", dto);

		SqlSession sqlSession = this.sqlSessionFactory.openSession();

		try (sqlSession) {
			// SQL 문장을 수행시킬 클래스의 FQCN을 namespace로 하고
			// sqlId는 , namespace안의 특정 메소드 이름으로 관례상 맞춤
			String namespace = "org.zerock.myapp.persistence.LoginDAO";
			String sqlId = "selectUserIdPw";
			String sql = namespace + "." + sqlId;

			return sqlSession.selectOne(sql, dto);

		} catch (Exception e) {
			throw new DAOException(e);
		} // try-with-resources

	}
	


	@Override
	public Integer updateUserWithRememberMe(
			String ID, 
			String rememberMeCookie, 
			Date rememberMeMaxAge)
			throws DAOException {
		log.trace("updateUserWithRememberMe({}, {}, {}) invoked.", ID, rememberMeCookie, rememberMeMaxAge);

		SqlSession sqlSession = this.sqlSessionFactory.openSession();

		try (sqlSession) {
			// SQL 문장을 수행시킬 클래스의 FQCN을 namespace로 하고
			// sqlId는 , namespace안의 특정 메소드 이름으로 관례상 맞춤
			String namespace = "org.zerock.myapp.persistence.LoginDAO";
			String sqlId = "updateUserWithRememberMe";
			String sql = namespace + "." + sqlId;

			// 바인드 변수에 줄 값은 보관하고 있는 Map<k,v>갹채 샹송
			Map<String, Object> params = new HashMap<>();
			params.put("ID", ID);
			params.put("rememberMe", rememberMeCookie);
			params.put("rememberMeAge", rememberMeMaxAge);
			log.info("\n\nparams : {}\n\n ",params);
			
			
			return sqlSession.update(sql, params);

		} catch (Exception e) {
			throw new DAOException(e);
		} // try-with-resources

	}

	@Override
	public UsersVO selectUserByRememberMe(String rememberMe) throws DAOException {
		log.trace("selectUserByRememberMe{}() invocked",rememberMe);
		
		SqlSession sqlSession= this.sqlSessionFactory.openSession();
		
		try (sqlSession){
			String namespace = "org.zerock.myapp.persistence.LoginDAO";
			String sqlId = "selectUserByRememberMe";
			String sql = namespace+"."+sqlId;
			
			return sqlSession.selectOne(sql, rememberMe);
		} catch(Exception e) {
			throw new DAOException(e);
		} // try-with-resources
		
	}
	@Override
	public void destroy() throws Exception {

	}

	

}
