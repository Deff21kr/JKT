package org.zerock.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
//@RequestMapping("/sample") 
@Controller		
public class SampleController {	
	
	@RequestMapping(path = "/qnaList", method=RequestMethod.GET)
	String list() {
		log.trace("qnaList() invoked.");
		
		return "qnaList";
	} // list
	
	@RequestMapping(path = "/qnaView", method=RequestMethod.GET)
	String view() {
		log.trace("qnaView() invoked.");
		
		return "qnaView";
	} // view
	
	@RequestMapping(path = "/qnaWrite", method=RequestMethod.GET)
	String write() {
		log.trace("qnaWrite() invoked.");
		
		return "qnaWrite";
	} // write
	
	@RequestMapping(path = "/qnaEdit", method=RequestMethod.GET)
	String edit() {
		log.trace("qnaEdit() invoked.");
		
		return "qnaEdit";
	} // edit
	
	@RequestMapping(path = "/replyEdit", method=RequestMethod.GET)
	String replyEdit() {
		log.trace("replyEdit() invoked.");
		
		return "replyEdit";
	} // edit

} // end class
