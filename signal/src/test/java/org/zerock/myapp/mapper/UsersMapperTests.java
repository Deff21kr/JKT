package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.RatingsDTO;
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
public class UsersMapperTests {

	@Setter(onMethod_ = { @Autowired })
	private UsersMapper mapper;
	@Setter(onMethod_ = { @Autowired })
	private BCryptPasswordEncoder pw;
	@Setter(onMethod_ = @Autowired)
	private RatingsMapper map;

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
		
		UsersDTO dto = new UsersDTO();
		
		dto.setID("test444");
		dto.setNickName("test444");
		dto.setName("강대수");
		dto.setPassword(pw.encode("Signal123123"));
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
	@Test(timeout = 1000*10)
	public void testList(Criteria cri) {
		log.trace("글쓴내역 테스트응느은으은");
		
		String nickName = "강대수";
		List<UsersDTO> vo = this.mapper.selectWriteList(nickName, cri);
		
		
		log.info("vovovovovovovovovovovovovo:{}", vo);
	
	}
	
	@Test(timeout = 1000*5)
	public void testRating() {
		log.trace("점수 주고받고 조회까지 키야아아아아앙");
		String nickName2 = "한글";
		String nickName = "강대수";
		Integer rating = 2;
		Integer vo = this.map.setRaterUserNickName(nickName, nickName2,rating);
		
		Double dto = this.map.getRatedUserNickName(nickName);
		
		log.info("\t+ 점수우우무너ㅜ엄ㄴ웜눠웜누: ({}, {})", vo, dto);
	}

} // end class