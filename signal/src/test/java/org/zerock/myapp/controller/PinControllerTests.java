package org.zerock.myapp.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/**/root-*.xml",
		"file:src/main/webapp/WEB-INF/**/servlet-*.xml"
})	// 빈즈 컨테이너 설정
@WebAppConfiguration			
public class PinControllerTests {
	
	@Autowired
	private WebApplicationContext ctx;			
	private MockMvc mockMvc;					
	
	
	@Before
	public void setup() {
		log.trace("setup() invoked.");
	
		assertNotNull(this.ctx);
		log.info("\t+ 1. ctx : {}", this.ctx);
		
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);	
		this.mockMvc = mockMvcBuilder.build();
	} // setup
	

	@Test(timeout = 1000*2)
	public void testInsert() throws Exception {
		log.trace("testInsert() invoked.");
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/board/pin/register");
		requestBuilder.param("postNo", "2");
		requestBuilder.param("nickName", "nickName");

		ModelAndView modelAndView = 
				this.mockMvc.
					perform(requestBuilder).
					andReturn().
					getModelAndView();
		
		String viewName = modelAndView.getViewName();
		log.info("\t+ 1. viewName : {}", viewName);
		
		ModelMap modelMap = modelAndView.getModelMap();
		
		assert modelMap != null;
		modelMap.forEach( (k, v) -> log.info("\t+ 2. k : {}, v : {}", k, v) );
	} // testInsert
	
} // end class
