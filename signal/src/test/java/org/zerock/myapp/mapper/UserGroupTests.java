package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.UserGroupDTO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;

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

	// 테스트 메소드 이름은 반드시 접두사 test를 붙여야 함
	@Test
	public void testSelectList() {
		log.trace("111 invoked");
		List<UsersVO> users = this.mapper.selectList();
//      users.forEach(log::trace);
	}

	
	@Test(timeout = 1000 * 10)
	public void testInsert() {
		log.trace("testInsert invoked");
		
		UserGroupDTO dto = new UserGroupDTO();
		dto.setAppNo();

		log.info("\t+ dto: {}", dto);

////      ----------------------------

		Integer aff = this.mapper.insert(dto);
		assert aff != null;
		log.info("\t+ aff: {} \n\tdto : {} ", (aff == 1), dto);

	} // 회원가입
	

	// 테스트 메소드 이름은 반드시 접두사 test를 붙여야 함
	@Test(timeout = 1000 * 12)
	public void testSelect() {
		log.trace("testSelect invoked");

		String id = "test444";

		UsersVO vo = this.mapper.select(id);
		log.info("\tbno : {} , vo : {}", id, vo);

	} // 유저 조회

	@Test(timeout = 1000 * 5)
	public void testDelete() {
		log.trace("testDelete invoked");

		String id = "Deff21kr";
		Integer affectedLines = this.mapper.delete(id);
		log.info("\tbno : {}, affectedLines : {} \nisSucces ", id, affectedLines, (affectedLines == 1));

	} // 회원 탈퇴 : 아이디,비번 요구

	@Test(timeout = 1000 * 5)
	public void testUpdate() {
		log.trace("testUpdate invoked");

		String id = "Deff21kr";
		UsersVO vo = this.mapper.select(id);

		Objects.requireNonNull(vo);
		// VO => DTO
		log.info("\tdto : {}", vo);
		UsersDTO dto = vo.toDTO();
		dto.setPassword("123qwe123");

		log.info("\tdto : {}", dto);
		Integer affectedLines = this.mapper.update(dto);
		log.info("\tbno : {}, affectedLines : {} \nisSucces ", dto.getID(), affectedLines, (affectedLines == 1));

	} // cpmtext:pads

} // end class