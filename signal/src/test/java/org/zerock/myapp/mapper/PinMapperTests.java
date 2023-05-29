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
    	
    	Integer postNo = 224;
    	String nickName = "1111";
    	
    	PinDTO pin = new PinDTO();
    	pin.setPostNo(postNo);
    	pin.setNickName(nickName);
    	
    	int result = 0;
    	result = mapper.insert(pin);
    	
    	log.info(">>> 결과: " + result);
    	
    } // testInsert
    
    @Test(timeout = 1000 * 2)
    public void testDelete() {
    	log.info("testDelete() invoked.");
    	
    	Integer cartId = 3;
    	
    	mapper.delete(cartId);
    	
    	log.info(">>> 삭제 성공: " + cartId);
    	
    } // testDelete
    
    @Test(timeout = 1000 * 2)
    public void TestSelect() {
    	log.info("testGet() invoked.");
    	
    	String nickName = "강대수";
    	
    	List<PinDTO> list = mapper.select(nickName);
    	log.info(">>> 찜 목록: " + nickName);
    } // testGet

    @Test(timeout = 1000 *2) 
    public void TestCheckPin() {
    	log.info("TestCheckPin() invoked.");
    	
    	Integer postNo = 224;
    	String nickName = "1111";
    	
    	PinDTO pin = new PinDTO();
    	pin.setPostNo(postNo);
    	pin.setNickName(nickName);
    	
    	PinDTO resultPin = mapper.checkPin(pin);
    	log.info(">>> 결과: " + resultPin);
    } // TestCheckPin


} // end class
