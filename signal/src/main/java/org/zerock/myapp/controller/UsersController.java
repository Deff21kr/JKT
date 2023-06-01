package org.zerock.myapp.controller;

import java.io.File;
import java.util.List;
import java.util.Objects;

//import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.GroupBoardDTO;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.domain.RatingsDTO;
import org.zerock.myapp.domain.UserGroupDTO;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.domain.UsersVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.LoginService;
import org.zerock.myapp.service.QnACommentService;
import org.zerock.myapp.service.RatingsService;
import org.zerock.myapp.service.UserGroupService;
import org.zerock.myapp.service.UsersService;
import org.zerock.myapp.utils.UploadFileUtils;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

@SessionAttributes({ "__FRIEND__" })
@NoArgsConstructor
@Log4j2

@RequestMapping("/user")
@Controller
public class UsersController {

   @Setter(onMethod_ = @Autowired)
   private UsersService service;
   @Setter(onMethod_ = @Autowired)
   private LoginService user;
   @Setter(onMethod_ = @Autowired)
   private UserGroupService group;
   @Setter(onMethod_ = @Autowired)
   private QnACommentService ser;
   @Setter(onMethod_ = @Autowired)
   private RatingsService ratingService;

   @Autowired
   private String uploadPath;

//   // 1. 회원 목록 조회 (전부)
//   @GetMapping("/list") // 리턴타입이 보이드이므로 리퀘스트 맵핑이 uri
//   void list(Model model) throws ControllerException {
//      log.trace("list({}) ㄲㄲ", model);
//      // model 상자 안에 담기만 하면 된다
//      // 비즈니스 수행결과인 list 를 model 상자에 담자
//      try {
//
//         List<UsersVO> list;
//         list = this.service.getList();
//
//         // 리퀘스트 스콥에다가 공유속성을 생성
//         model.addAttribute("__LIST__", list);
//      } catch (Exception e) {
//         throw new ControllerException(e);
//      }
//
//   } // list : 전체회원조회가 있어야 돼나??
//
//   // 3. 특정회원 조회
//   @GetMapping(path = { "/get" }, params = "ID")
//   void get(@RequestParam("ID") String ID, Model model) throws ControllerException {
//
//      log.trace("get() 인보크");
//      try {
//         UsersVO vo = this.service.get(ID);
//         model.addAttribute("__BOARD__", vo);
////         return "뷰이름";
//      } catch (Exception e) {
//         throw new ControllerException(e);
//      }
//   }

   // 특정 회원의 모든 게시물 조회
   @PostMapping(path = "/mypage")
   String modify(UsersDTO dto, RedirectAttributes rttrs, Model model) throws ControllerException {
      log.trace("modify({}) invoked.", dto);
      try {
         Objects.requireNonNull(dto);
         if (this.service.modify(dto)) {
            rttrs.addAttribute("result", "true");
            rttrs.addAttribute("userno", dto.getID());
         }

         return "redirect:/user/mypage";
      } catch (Exception e) {
         throw new ControllerException(e);
      }
   } // 회원정보수정

//   @PostMapping(path = "/mypage/remove")
//   String remove(UsersDTO dto, RedirectAttributes rttrs) throws ControllerException {
//      log.trace("modify({}) invoked.", dto);
//
//      try {
//         Objects.requireNonNull(dto);
//
//         if (this.service.remove(dto.getID())) {
//            rttrs.addAttribute("result", "true");
//            rttrs.addAttribute("userno", dto.getID());
//         }
//
//         return "/mainpage";
//      } catch (Exception e) {
//         throw new ControllerException(e);
//      }
//   } // 탈퇴?

//   @GetMapping(path={"/mypage/group/list"}, params = "ID")
//   String myGroupList() {
//      
//      return "redirect:mypage";
//      
//   }

   @ResponseBody
   @PostMapping("/mypage/friend")
   List<UserGroupDTO> friend(Model model, Integer groupNo) throws ControllerException {

      try {
         List<UserGroupDTO> friend = this.group.getFriendList(groupNo);
//         model.addAttribute("__FRIEND__", friend);
//         log.info("\n\n\t\tfriend : {}\n\n",friend);
         return friend;
      } catch (Exception e) {
         throw new ControllerException(e);
      }

   } // friend

   @GetMapping(path = { "/mypage" })
   String myGroupList(Model model, HttpServletRequest req, Criteria cri, Integer groupNo, String ratedNickname,
         String raterNickName, Integer rating) throws ControllerException {
      try {

         HttpSession session = req.getSession();
         UsersVO vo = (UsersVO) session.getAttribute("__AUTH__");
         log.info("\n\nvo : {}", vo);

         List<UserGroupDTO> list = this.group.getMyAppList(vo.getNickName(), cri);
         log.info("\n\nlist : {}", list);
         // Request Scope 공유속성 생성
         model.addAttribute("__APPLIST__", list);

         List<UsersDTO> dto = this.service.selectWriteList(vo.getNickName(), cri);
         model.addAttribute("_LIST_", dto);

         // 점수 조회
         RatingsDTO ratingDTO = this.ratingService.getRatedRating(vo.getNickName());
         log.info("ratingDTO: {}", ratingDTO);
         model.addAttribute("__rating__", ratingDTO);

         // 찜 내역 조회
         List<GroupBoardDTO> groupBoardDTO = this.service.selectPinLists(vo.getNickName(), cri);
         log.info("groupBoard: {}***********************************", groupBoardDTO);
         model.addAttribute("__pinList__", groupBoardDTO);

         PageDTO pageDTO = new PageDTO(cri, this.group.getTotalAppList(vo.getNickName()));
         model.addAttribute("pageMaker", pageDTO);

         PageDTO writeListDTO = new PageDTO(cri, this.service.getWriterList(vo.getNickName()));
         model.addAttribute("writePageMaker", writeListDTO);
         
         PageDTO pinListDTO = new PageDTO(cri, this.service.getPinList(vo.getNickName()));
         model.addAttribute("pinPageMaker", pinListDTO);

         return "/user/mypage";
      } catch (Exception e) {
         throw new ControllerException(e);
      }

   }
      
   
   @GetMapping(path={"/mypage/people"})
   String myGroupList(Model model,HttpServletRequest req,Criteria cri, String ratedNickname, String raterNickName, Integer rating, MultipartFile file) throws ControllerException {
      try {
         
         HttpSession session = req.getSession();
         UsersVO vo = (UsersVO)session.getAttribute("__AUTH__"); 
         log.info("\n\nvo : {}",vo);
         
         List<UserGroupDTO> list = this.group.getMyAppList( vo.getNickName(),cri );
         log.info("\n\nlist : {}",list);
         // Request Scope  공유속성 생성
         model.addAttribute("__APPLIST__", list);
         
         List<UsersDTO> dto = this.service.selectWriteList(vo.getNickName(), cri);
         model.addAttribute("_LIST_", dto);
         
         // 점수 조회
         RatingsDTO ratingDTO = this.ratingService.getRatedRating(vo.getNickName());
         log.info("ratingDTO: {}", ratingDTO);
         model.addAttribute("__rating__", ratingDTO);
         
         PageDTO pageDTO = new PageDTO(cri, this.group.getTotalAppList(vo.getNickName()));
         model.addAttribute("pageMaker", pageDTO);
         
         PageDTO writeListDTO = new PageDTO(cri, this.service.getWriterList(vo.getNickName()));
         model.addAttribute("writePageMaker", writeListDTO);
         
         return "/user/mypage";
      } catch (Exception e) {
         throw new ControllerException(e);
      }
      
      
   }

   // 프로필 수정
   @PostMapping("/edit")
   String profilModify(UsersDTO dto, HttpServletRequest req, Model model, MultipartFile file) throws ControllerException {
          log.info("\n\ndto : {}", dto);
          try {
              String imgUploadPath = uploadPath + File.separator + "imgUpload";
              String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
              String fileName = null;
   
              log.info("PATH :: {}, {}, {}", imgUploadPath, ymdPath, file);
   
              if (file != null) {
                  fileName = UploadFileUtils.fileUpload(imgUploadPath,
                          file.getOriginalFilename(), file.getBytes(), ymdPath);
   
                  // Generate thumbnail
                  String thumbnailName = "thumbnail_" + fileName;
                  Thumbnails.of(new File(imgUploadPath + File.separator + ymdPath + File.separator + fileName))
                          .size(160, 160)
                          .toFile(new File(imgUploadPath + File.separator + ymdPath + File.separator + thumbnailName));
   
                  dto.setFileName(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
              } else {
                  fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
                  dto.setFileName(fileName);
              }
   
              Objects.requireNonNull(dto);
   
              if (this.service.profileEdit(dto)) {
                  HttpSession session = req.getSession();
                  UsersVO vo = this.service.get(dto.getID());
                  log.info("\t+ 브이이이어ㅗ오오오오오 :{} ", vo);
                  session.setAttribute("__AUTH__", vo);
              }
              log.info("\t+ dto: ({}, {})", dto, dto.getID());
              return "redirect:/user/mypage";
          } catch (Exception e) {
              throw new ControllerException(e);
          }
       }


   // 프로필 수정
   @GetMapping("/edit")
   void myPageModify(HttpServletRequest req, Model model) throws ControllerException {
      try {
         HttpSession session = req.getSession();
         UsersVO vo = (UsersVO) session.getAttribute("__AUTH__");
         log.info("\n\nvo : {}", vo);

      } catch (Exception e) {
         throw new ControllerException(e);
      }

   }


   // 평점 제출
   @PostMapping("/rate")
   @ResponseBody
//      ResponseEntity<String>
   Integer rate(String raterUserNickName, String ratedUserNickName, Integer rating) throws ControllerException {
      log.trace("rate() invoked");

      try {
//              Boolean result = this.ratingService.setRaterRating(raterUserNickName, ratedUserNickName, rating) == 1;
//              if (result) {
//                  log.info("\t + rate : {}", result);
////                 return ResponseEntity.ok("평점이 부여되었습니다.");
//
//              }else {
//                 log.info("실패");
////                  return ResponseEntity.ok("평점이 부여에 실패하였습니다.");
//
//              }

         return (this.ratingService.setRaterRating(raterUserNickName, ratedUserNickName, rating));

      } catch (Exception e) {
         throw new ControllerException(e);
      }
   } // rate

      
   
   
} // end class