package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.UserGroupDTO;
import org.zerock.myapp.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

// Spring Framework 구동
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"file:src/main/webapp/WEB-INF/**/root-*.xml",
		"file:src/main/webapp/WEB-INF/**/servlet-*.xml"
})
public class UserGroupTests {

	@Setter(onMethod_ = { @Autowired })
	private UserGroupMapper mapper;

	@Before
	public void setup() {
		log.trace("setup() 인보크");

		assertNotNull(this.mapper);
		log.info("\tthis.mapper : {}", this.mapper);
		
//		assertNotNull(this.authMapper);
//		log.info("\tthis.authMapper : {}", this.authMapper);
	
	}
	@Test
	public void selectList() throws DAOException {
		this.mapper.selectList(2);
	}
	
	@Test
	public void insert() throws DAOException {
		
		UserGroupDTO dto = new UserGroupDTO();
		dto.setID("1111");
		dto.setGroupNo(2);
		
		Integer aff = this.mapper.insert(dto);
		assert aff != null;
		log.info("\t+ aff: {} \n\tdto : {} ", (aff == 1), dto);
	}

} // end class