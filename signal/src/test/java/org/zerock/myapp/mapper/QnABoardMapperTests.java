package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.QnABoardDTO;
import org.zerock.myapp.domain.QnABoardVO;
import org.zerock.myapp.exception.DAOException;
import org.zerock.myapp.exception.ServiceException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/**/root-*.xml"})
public class QnABoardMapperTests {

    @Setter(onMethod_ = {@Autowired})
    private QnABoardMapper mapper;


    @Before
    public void setup() {
        log.trace("setup() invoked.");

        assertNotNull(this.mapper);
        log.info("\t+ this.mapper : {}", this.mapper);
    } // setup


    @Test(timeout = 1000 * 2)
    public void testSelectList() {
        log.trace("testSelectList() invoked.");
        
        Criteria cri = new Criteria();
        cri.setCurrPage(20);

        List<QnABoardVO> boards = this.mapper.selectList(cri);
        
        Objects.requireNonNull(boards);
        boards.forEach(log::info);
    } // testSelectList

    @Test(timeout = 1000 * 2)
    public void testInsert() {
        log.trace("testInsert() invoked.");

        QnABoardDTO dto = new QnABoardDTO();
        dto.setNickName("NEW_NICKNAME");
        dto.setTitle("NEW_TITLE");
        dto.setContent("NEW_CONTENT");
        log.info("\t+ dto : {}", dto);

        Integer affectedLines = this.mapper.insert(dto);
        assert affectedLines != null;
        log.info("\t+ result : {}", (affectedLines == 1));

    } // testInsert

    @Test(timeout = 1000 * 2)
    public void testSelect() {
        log.trace("testSelect() invoked.");

        Integer postno = 120;

        QnABoardVO vo = this.mapper.select(postno);
        log.info("\t+ postno : {}, vo : {}", postno, vo);

    } //  testSelect

    @Test(timeout = 1000 * 2)
    public void testDelete() throws ServiceException, DAOException {
        log.trace("testDelete() invoked.");

        Integer postno = -1;
        Integer affectedLines = this.mapper.delete(postno);
        log.info("\t+ postno : {}, affectedLines : {}, isSuccess : {}", postno, affectedLines, (affectedLines == 1));

    } //  testDelete

    @Test(timeout = 1000 * 2)
    public void testUpdate() {
        log.trace("testUpdate() invoked.");

        Integer postno = 100;
        QnABoardVO vo = this.mapper.select(postno);

        assertNotNull(vo);

        // VO -> DTO
        QnABoardDTO dto = vo.toDTO();
        dto.setTitle("MODIFIIED_TITLE");
        log.info("\t+ dto : {}", dto);

        Integer affectedLines = this.mapper.update(dto);
        log.info("\t+ affectedLines : {}, isSuccess : {}", affectedLines, (affectedLines == 1));

    } // testUpdate
    
    @Test(timeout = 1000 *2)
    public void testGetTotalAmount() {
    	log.trace("testGetTotalAmount() invoked.");
    	
    	int totalAmount = this.mapper.getTotalAmount();
    	log.info("\t + totalAmount : {}", totalAmount);
    	
    } // testGetTotalAmount
    
    @Test(timeout = 1000 * 2)
    public void testUpdateReadcnt() {
    	log.trace("testUpdateReadcnt() inovoked.");
    	
    	Integer postno = 120;
    	Integer rc = this.mapper.updateReadcnt(postno);
    	log.info("testUpdateReadcnt({}, {}) invoked.", postno, rc);
    }

} // end class
