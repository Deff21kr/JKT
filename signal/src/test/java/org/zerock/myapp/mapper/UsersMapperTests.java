package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

// Spring Framework 구동
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/**/root-*.xml" })
public class UsersMapperTests {

	@Setter(onMethod_ = { @Autowired })
	private UsersMapper mapper;

	@Before
	public void setup() {
		log.trace("setup() 인보크");

		assertNotNull(this.mapper);
		log.info("\tthis.mapper : {}", this.mapper);
	}

	// 테스트 메소드 이름은 반드시 접두사 test를 붙여야 함
	@Test
	public void testSelectList() {
		log.trace("111 invoked");
		List<UsersVO> users = this.mapper.selectList();
//      users.forEach(log::trace);
	}

	@Test(timeout = 1000 * 4)
	public void testInsert() {
		log.trace("testInsert invoked");
		
		UsersDTO dto = new UsersDTO();
		dto.setID("Deff21kr");
		dto.setNickName("데프");
		dto.setName("강대수");
		dto.setPassword("Signal123123");
		dto.setGender("상남자");
		dto.setEMail("deff21kr@gmail.com");
		dto.setPhoneNumber("010-2797-1234");
		dto.setBirthDate(LocalDate.parse("1993-04-01"));
		dto.setMBTI("ISTP");
		dto.setLikeArea("전주");

		log.info("\t+ dto: {}", dto);

////      ----------------------------

		Integer aff = this.mapper.insert(dto);
		assert aff != null;
		log.info("\t+ aff: {} \n\tdto : {} ", (aff == 1), dto);
	} // 회원가입

	// 테스트 메소드 이름은 반드시 접두사 test를 붙여야 함
	@Test(timeout = 1000 * 5)
	public void testSelect() {
		log.trace("testSelect invoked");

		Integer userno = 303;

		UsersVO vo = this.mapper.select(userno);
		log.info("\tbno : {} , vo : {}", userno, vo);

	} // 유저 조회

	@Test(timeout = 1000 * 5)
	public void testDelete() {
		log.trace("testDelete invoked");

		Integer userno = 10;
		Integer affectedLines = this.mapper.delete(userno);
		log.info("\tbno : {}, affectedLines : {} \nisSucces ", userno, affectedLines, (affectedLines == 1));

	} // 회원 탈퇴 : 아이디,비번 요구

	@Test(timeout = 1000 * 5)
	public void testUpdate() {
		log.trace("testUpdate invoked");

		Integer userno = 303;
		UsersVO vo = this.mapper.select(userno);

		Objects.requireNonNull(vo);
		// VO => DTO
		UsersDTO dto = vo.toDTO();
		log.info("\tdto : {}", dto);

		dto.setPassword("123123Signal");

		log.info("\tdto : {}", dto);
		Integer affectedLines = this.mapper.update(dto);
		log.info("\tbno : {}, affectedLines : {} \nisSucces ", dto.getUserno(), affectedLines, (affectedLines == 1));

	} // cpmtext:pads

} // end class