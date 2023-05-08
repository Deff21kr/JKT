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
public class QnABoardControllerTests {
	
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
	public void testList() throws Exception {
		log.trace("testList() invoked.");
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/board/list");
		
		ModelAndView modelAndView = 
				this.mockMvc.
					perform(requestBuilder).
					andReturn().
					getModelAndView();
		
		String viewName = modelAndView.getViewName();
		log.info("\t+ 1. viewName : {}", viewName);
		
		ModelMap modelMap = modelAndView.getModelMap();
		
		assertNotNull(modelMap);
		modelMap.forEach( (k, v) -> log.info("\t+ 2. k : {}, v : {}", k, v) );	
		
	} // testList

	@Test(timeout = 1000*2)
	public void testRegister() throws Exception {
		log.trace("testRegister() invoked.");
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/board/register");
		requestBuilder.param("title", "new_title");
		requestBuilder.param("content", "new_content");
		requestBuilder.param("nickname", "new_nickname");

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
	} // testRegister
	
	
	@Test(timeout = 1000*3)
	public void testGet() throws Exception {
		log.trace("testGet() invoked.");
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/board/get");
		requestBuilder.param("postno", "100");

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
	} // testGet
	
	@Test(timeout = 1000*2)
	public void testModify() throws Exception {
		log.trace("testModify() invoked.");
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/board/modify");
		requestBuilder.param("postno", "33");
		requestBuilder.param("title", "modified_title");
		requestBuilder.param("content", "modified_content");
		requestBuilder.param("nickname", "modified_writer");

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
	} // testModify
	
	
	@Test(timeout = 1000*2)
	public void testRemove() throws Exception {
		log.trace("testRemove() invoked.");
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/board/remove");
		requestBuilder.param("postno", "100");

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
	} // testRemove
	
} // end class
