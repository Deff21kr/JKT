package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.myapp.domain.PinDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/**/root-*.xml"})
public class PinMapperTests {

    @Setter(onMethod_ = {@Autowired})
    private PinMapper mapper;

    @Before
    public void setup() {
        log.trace("setup() invoked.");

        assertNotNull(this.mapper);
        log.info("\t+ this.mapper : {}", this.mapper);
    } // setup
    
    @Test(timeout = 1000 * 2)
    public void testInsert() throws Exception {
    	log.info("testInsert() invoked.");
    	
    	Integer postNo = 256;
    	String nickName = "123123";
    	
    	PinDTO pin = new PinDTO();
    	pin.setPostNo(postNo);
    	pin.setNickName(nickName);
    	log.info(pin);
    	
    	int result = 0;
    	result = mapper.insert(pin);
  	
    	log.info(">>> 결과: " + result);
    	
    } // testInsert
    
//    @Test(timeout = 1000 * 2)
//    public void testSelect() {
//    	log.info("testSelect() invoked.");
//    	
//    	Integer pinNo = 17;
//    	
//    	PinDTO dto = this.mapper.select(pinNo);
//    	log.info(">>>>>>>>>>>>>>>>>", dto);
//    	
//    } // testSelect
//    
//    @Test(timeout = 1000 * 2)
//    public void testDelete() {
//    	log.info("testDelete() invoked.");
//    	
//    	Integer pinNo = 3;
//    	
//    	mapper.delete(pinNo);
//    	
//    	log.info(">>> 삭제 성공: " + pinNo);
//    	
//    } // testDelete
//    
//    @Test(timeout = 1000 * 2)
//    public void testSelectList() {
//    	log.info("testSelectList() invoked.");
//    	
//    	String nickName = "강대수";
//    	
//    	List<PinDTO> list = mapper.select(nickName);
//    	log.info(">>> 찜 목록: " + nickName);
//    } // testSelectList
//
//    @Test(timeout = 1000 *3) 
//    public void TestCheckPin() {
//    	log.info("TestCheckPin() invoked.");
//    	
//    	Integer postNo = 268;
//    	String nickName = "Hyeonna";
//  
//    	
//    	PinDTO pin = new PinDTO();
//    	pin.setPostNo(postNo);
//    	pin.setNickName(nickName);
//    	
//    	Integer resultPin = mapper.checkPin(pin);
//    	log.info(">>> 결과: " + resultPin);
//    } // TestCheckPin


} // end class
