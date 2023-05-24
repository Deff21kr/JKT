package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.GroupPlanDTO;
import org.zerock.myapp.domain.GroupsDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/**/root-*.xml")
public class GroupPlanMapperTests {
	
	@Setter(onMethod_ = {@Autowired})
	private GroupPlanMapper mapper;

	@Test
	public void setup() {
		log.trace("setup invoked");
		
		assertNotNull(this.mapper);
		log.info("\t + this.mapper : {}", this.mapper);
	} //
	
	@Test
	public void testGroupNameList() {
		log.trace("testgroupNameList invoked");
		
		String id = "asdasd";
		List<String> dto = this.mapper.groupNameList(id);
		assertNotNull(dto);
		
		log.info("\t + id : {}", id);
	} // testgroupNameList
}
