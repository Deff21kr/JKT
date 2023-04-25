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
	
	@RequestMapping(path = "/list", method=RequestMethod.GET)
	String list() {
		log.trace("list() invoked.");
		
		return "list";
	} // list
	
	@RequestMapping(path = "/view", method=RequestMethod.GET)
	String view() {
		log.trace("view() invoked.");
		
		return "view";
	} // view
	
	@RequestMapping(path = "/write", method=RequestMethod.GET)
	String write() {
		log.trace("write() invoked.");
		
		return "write";
	} // write
	
	@RequestMapping(path = "/edit", method=RequestMethod.GET)
	String edit() {
		log.trace("edit() invoked.");
		
		return "edit";
	} // edit
	
	@RequestMapping(path = "/replyEdit", method=RequestMethod.GET)
	String replyEdit() {
		log.trace("replyEdit() invoked.");
		
		return "replyEdit";
	} // edit

} // end class
