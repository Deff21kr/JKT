package org.zerock.myapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupBoardCriteria;
import org.zerock.myapp.domain.GroupBoardVO;
import org.zerock.myapp.domain.QnABoardVO;
import org.zerock.myapp.domain.ReviewBoardCriteria;
import org.zerock.myapp.domain.ReviewBoardVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.GroupBoardService;
import org.zerock.myapp.service.QnABoardService;
import org.zerock.myapp.service.ReviewBoardService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Controller
public class HomeController {
	 
	@Setter(onMethod_ = @Autowired)
	private GroupBoardService GBService;
	@Setter(onMethod_ = @Autowired)
	private QnABoardService QnaService;
	@Setter(onMethod_ = @Autowired)
	private ReviewBoardService ReviewService;
	 
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@GetMapping("/")
	String home(Model model ) throws ControllerException {
		try {

		    // ============== GROUP BOARD ================
			GroupBoardCriteria GBCri = new GroupBoardCriteria();
			GBCri.setAmount(10);
			List<GroupBoardVO> groupBoard = this.GBService.getList(GBCri);
		    model.addAttribute("__GROUPBOARD__", groupBoard);
		    
		    // ============== REVIEW BOARD ================
			ReviewBoardCriteria ReviewCri = new ReviewBoardCriteria();
			List<ReviewBoardVO> review = this.ReviewService.getList(ReviewCri);
			model.addAttribute("__REVIEW__", review);
			 
		    
		    // ============== QNA BOARD ================
		    Criteria QnAcri = new Criteria();
		    QnAcri.setAmount(7);
			List<QnABoardVO> qna = this.QnaService.getList(QnAcri);
			model.addAttribute("__QNA__", qna);
			
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
		return "mainpage";
	} // home
}
