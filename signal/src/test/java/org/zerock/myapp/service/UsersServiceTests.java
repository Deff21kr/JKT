package org.zerock.myapp.service;

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
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

// JUnit4
// 1) 디폴트 컨스트럭쳐를 가진다

@Log4j2
@NoArgsConstructor

// Spring Framework 구동
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"file:src/main/webapp/WEB-INF/**/root-*.xml",
		"file:src/main/webapp/WEB-INF/**/servlet-*.xml"
		})
public class UsersServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private UsersService service;
	
	@Before
	public void setup() { // 전처리
		log.trace("\n************************************** 			setup() "
				+ "\n************************************** ");
		
		Objects.requireNonNull(this.service);
		log.info("\tthis.service : {}" ,this.service);
	}
	

	
	@Test
	public void testGetList() throws ServiceException {
		log.trace("\n************************************** 			"
				+ "\n		testGetList() "
				+ "\n************************************** ");
		
//		Criteria cri = new Criteria();
//		cri.setCurrPage(1);
		
		List<UsersVO> list = this.service.getList();
		
//		Objects.requireNonNull(list);
//		list.forEach(log::info);
		
	} //
	
	@Test(timeout = 1000 * 13)
	public void testRegister() throws ServiceException {
		log.trace("\n************************************** 			testRegister() "
				+ "\n************************************** ");
		
		UsersDTO dto = new UsersDTO();
		dto.setID("ㅎ2ㅎㅇ하이");
		dto.setNickName("깅가");
		dto.setName("홍기서");
		dto.setPassword("Signal123123");
		dto.setGender("멍청이");
		dto.setEMail("ggisou@gmail.com");
		dto.setPhoneNumber("010-0000-1234");
		dto.setBirthDate(LocalDate.parse("1997-02-26"));
		dto.setMBTI("ISTP");
		dto.setLikeArea("전주");
		
		boolean isSuccess = this.service.register(dto);
		log.info("\n\t isSuccess : {},\n\t dto : {}",isSuccess,dto );
		
	} //
	

	@Test(timeout = 1000 * 3)
	public void testGet() throws ServiceException {
		log.trace("\n*****************************************************\n 			testGet() "
				+ "\n*****************************************************");
		
		String id = "test444";
		UsersVO vo = this.service.get(id);
		
		Objects.requireNonNull(vo);
		log.info("\n\t vo : {} ",vo);
	}

	@Test(timeout = 1000 * 5)
	public void testModify() throws ServiceException {
		log.trace("\n*****************************************************\n 			testModify() "
				+ "\n*****************************************************");
		
		String id = "test444";
		
		// 우선 유저의 정보를 얻고
		UsersVO vo = this.service.get(id);
		
		// 널이 아니라면
		Objects.requireNonNull(vo);
		
		// 셋터가 있는 dto로 변환
		UsersDTO dto = vo.toDTO();
		log.info("\n\t 수정 전 : {} ",vo);
		// 회원정보수정
		dto.setPassword("ㅂ2ㅂㅇ바이");
		Objects.requireNonNull(vo);
		boolean isSuccess = this.service.modify(dto);
		log.info("\n\t isSuccess : {}" ,isSuccess);
		log.info("\n\t 수정 후 : {} ",vo);
		
	} // testModify

	

	@Test(timeout = 1000 * 3)
	public void testRemove() throws ServiceException {
		log.trace("\n*****************************************************\n 			testRemove() "
				+ "\n*****************************************************");
		
		String id = "ㅂ2ㅂㅇ바이";
		boolean isDeleted = this.service.remove(id);
		
		log.info("\n\t isDeleted : {} ",isDeleted);
	}

	
	
	
}	// end class
