package dev.mvc.question;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.category.CategoryProcInter;
import dev.mvc.category.Categrp_CategoryVO;
import dev.mvc.categrp.CategrpProcInter;
import dev.mvc.categrp.CategrpVO;
/*import nation.web.tool.Messages;*/



@Controller 
public class QuestionCont {
  @Autowired
  @Qualifier("dev.mvc.question.QuestionProc")
  private QuestionProcInter questionProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.category.CategoryProc")
  private CategoryProcInter categoryProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.categrp.CategrpProc")
  private CategrpProcInter categrpProc = null;
  
/*  @Autowired
  @Qualifier("dev.mvc.tool.Messages")
  private Messages messages = null;*/
  
  public QuestionCont() {
    System.out.println("--> QuestionCont crated.");
  }

  /**
   * 등록 폼 http://localhost:9090/ahr/question /create.do
   * 
   * @return
   */
  @RequestMapping(value = "/question/create.do", method = RequestMethod.GET)
  public ModelAndView create(int categoryno) {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();

    
    QuestionVO questionVO =questionProc.read(categoryno);
    mav.addObject("questionVO", questionVO);
    
    mav.setViewName("/question/create"); // /webapp/contents/create.jsp

    return mav;
  }

 /**
   * 등록 처리
   * 
   * @param request
   * @param questionVO
   * @return
   */
  @RequestMapping(value = "/question/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, QuestionVO questionVO,int categoryno) {
    System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();

    int count = questionProc.create(questionVO);
    
    System.out.println("count :" + count);
    
    mav.setViewName(
        "redirect:/question/create_message.jsp?count=" + count+"&categoryno=" +categoryno ); // /webapp/question/create_message.jsp

    // mav.setViewName("redirect:/contents/list_by_category_search_paging.do?categoryno="
    // + contentsVO.getCategoryno());
    // mav.setViewName("redirect:/contents/list_all_category.do");

    return mav;
  }

  /**
   * 전체 목록
   * 
   * @return
   */
  // http://localhost:9090/ojt/contents/list_all_category.do
  @RequestMapping(value = "/question/list.do", method = RequestMethod.GET)
  public ModelAndView list(int categoryno) {
    ModelAndView mav = new ModelAndView();

    List<QuestionVO> list = questionProc.list(categoryno);
    mav.addObject("list", list);
    
 /*   List<CategrpVO> categrp_list = categrpProc.list();
    mav.addObject("categrp_list", categrp_list);*/
    
 
    Categrp_CategoryVO categoryVO = categoryProc.read(categoryno);
    
    mav.addObject("categoryVO", categoryVO);
    
 //   mav.setViewName("/question/list"); // /webapp/contents/list_all_category.jsp

    return mav;
  }
  
  /**
   * 조회
   * 
   * @param reviewno
   * @return
   */
  @RequestMapping(value = "/question/read.do", method = RequestMethod.GET)
  public ModelAndView read(int questionno,int categoryno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/question/read"); // /webapp/contents/read.jsp

    QuestionVO questionVO = questionProc.read(questionno);
    mav.addObject("questionVO", questionVO);
    
 /*   Categrp_CategoryVO categoryVO = categoryProc.read(categoryno);
    mav.addObject("categoryVO", categoryVO);*/

 //   Categrp_CategoryVO categoryVO = categoryProc.read(contentsVO.getCategoryno()); // 카테고리
                                                                                   // 정보
                                                                                   // 추출
  //   mav.addObject("categoryVO", categoryVO);


    return mav;

  }
  
  /**
   * 답변조회
   * 
   * @param reviewno
   * @return
   */
  @RequestMapping(value = "/question/reply_read.do", method = RequestMethod.GET)
  public ModelAndView reply_read(int questionno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/question/reply_read"); // /webapp/contents/read.jsp

    QuestionVO questionVO = questionProc.read(questionno);
    mav.addObject("questionVO", questionVO);

 //   Categrp_CategoryVO categoryVO = categoryProc.read(contentsVO.getCategoryno()); // 카테고리
                                                                                   // 정보
                                                                                   // 추출
  //   mav.addObject("categoryVO", categoryVO);


    return mav;

  }
  
  /**
   * 비밀번호 확인폼
   * @return
   */
  @RequestMapping(value = "/question/passwd_check.do", 
                             method = RequestMethod.GET)
  public ModelAndView passwd_check(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/question/passwd_message"); // /webapp/member/login_ck_form.jsp
    
    String ck_passwd="";
    
    mav.addObject("ck_passwd", ck_passwd);
    
    return mav;
  }
  

  @RequestMapping(value="/question/passwd_check.do", method=RequestMethod.POST)
  public ModelAndView passwd_check(HttpServletRequest request, int questionno, int categoryno,QuestionVO questionVO){
    System.out.println("--> login() POST called.");
    ModelAndView mav = new ModelAndView();
    
    String passwd = questionVO.getPasswd();
    System.out.println("passwd: " +passwd);
    
    if (questionProc.passwd_check(questionno, passwd) != 1) { // 로그인 실패시
      mav.setViewName("redirect:/question/passwd_fail.jsp?questionno=" + questionno );
      
    } else { // 패스워드 일치하는 경우
      mav.setViewName("redirect:/question/read.do?questionno=" + questionno+"&categoryno="+ categoryno); // 확장자 명시 
 
  }
    return mav;
 }
  
  
  
  /**
   * 삭제폼
   * @param reviewno
   * @param categoryno
   * @return
   */
    @RequestMapping(value = "/question/delete.do", method = RequestMethod.GET)
    public ModelAndView delete(int questionno, int categoryno) {
      // System.out.println("--> delete() GET executed");
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/question/delete"); // /webapp/review/delete.jsp


      QuestionVO questionVO = questionProc.read(questionno);
      mav.addObject("questionVO", questionVO);

      return mav;
    }

   /**
    * 삭제
    * @param redirectAttributes
    * @param request
    * @param questionno
    * @return
    */
    @RequestMapping(value = "/question/delete.do", method = RequestMethod.POST)
    public ModelAndView delete(RedirectAttributes redirectAttributes, 
                                          HttpServletRequest request, 
                                       //   int categoryno,
                                          int questionno
                                       /*   @RequestParam(value="word", defaultValue="") String word,
                                          @RequestParam(value="nowPage", defaultValue="1") int nowPage */
        ) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/question/delete_message"); // /webapp/review/delete_message.jsp

      
      QuestionVO questionVO = questionProc.read(questionno); // 삭제할 파일 정보를 읽기 위한

      int count = questionProc.delete(questionno); // 레코드 삭제
      System.out.println("삭제 count :" + count);

  
      // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
      redirectAttributes.addAttribute("count", count); // 1 or 0
      redirectAttributes.addAttribute("questionno", questionVO.getQuestionno());
      redirectAttributes.addAttribute("categoryno", questionVO.getCategoryno());


      mav.setViewName("redirect:/question/delete_message.jsp");

      return mav;
    }
    
    /**
     * 글 수정 폼 http://localhost:9090/contents/contents/update.do
     * 
     * @param questionno
     * @return
     */
    @RequestMapping(value = "/question/update.do", method = RequestMethod.GET)
    public ModelAndView update(int questionno) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/question/update"); // /webapp/contents/update.jsp

      System.out.println("수정 폼questionno: "+questionno);
      
      QuestionVO questionVO = questionProc.read(questionno);
      mav.addObject("questionVO", questionVO);

   //   Categrp_CategoryVO categoryVO = categoryProc.read(questionVO.getCategoryno()); // 카테고리
                                                                                     // 정보
                                                                                   // 추출
  //    mav.addObject("categoryVO", categoryVO);


      return mav;
    }
    
    /**
     * 글 수정
     * 
     * @param request
     * @param questionVO
     * @return
     */
    @RequestMapping(value = "/question/update.do", method = RequestMethod.POST)
    public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, QuestionVO questionVO) {
      ModelAndView mav = new ModelAndView();
      
      questionVO.setMemberno(1); // 회원 개발후 session으로 변경

      int count = questionProc.update(questionVO);

      System.out.println("마지막 count: " +count);
      
      redirectAttributes.addAttribute("count", count);

      // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
      redirectAttributes.addAttribute("questionno", questionVO.getQuestionno());
      redirectAttributes.addAttribute("categoryno", questionVO.getCategoryno());

      mav.setViewName("redirect:/question/update_message.jsp");

      return mav;

    }
  
    /**
     * 답변
     * @param questionVO
     * @return
     */
    @RequestMapping(value="/question/reply.do", method=RequestMethod.GET)
    public ModelAndView reply(QuestionVO questionVO){
      // System.out.println("--> reply() GET called.");
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/question/reply"); // webapp/contents/reply.jsp
      
      Categrp_CategoryVO categoryVO = categoryProc.read(questionVO.getCategoryno());
      mav.addObject("categoryVO", categoryVO);  // FK column
      
      mav.addObject("questionVO", questionVO);
      
      return mav;
    }

    @RequestMapping(value="/question/reply.do", method=RequestMethod.POST)
    public ModelAndView reply(RedirectAttributes redirectAttributes, 
                                         HttpServletRequest request, 
                                         QuestionVO questionVO
                                         /*@RequestParam(value="word", defaultValue="") String word,
                                         @RequestParam(value="nowPage", defaultValue="1") int nowPage*/){
      // System.out.println("Controller word: " + word);
      
      // System.out.println("--> create() POST called.");
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/question/reply_message"); // webapp/contents/reply_message.jsp
      
      int categoryno = questionVO.getCategoryno();

      
      
      // 회원 개발 후 session 으로변경
      // int mno = (Integer)session.getAttribute("mno");
      questionVO.setMemberno(1);
      
      // --------------------------- 답변 관련 코드 시작 --------------------------
      QuestionVO parentVO = questionProc.read(questionVO.getQuestionno()); // 부모글 정보 추출
      
      questionVO.setGrpno(parentVO.getGrpno());     // 그룹 번호
      questionVO.setAnsnum(parentVO.getAnsnum()); // 답변 순서

      questionProc.updateAnsnum(questionVO); // 현재 등록된 답변 뒤로 +1 처리함.

      questionVO.setIndent(parentVO.getIndent() + 1); // 답변 차수 증가
      questionVO.setAnsnum(parentVO.getAnsnum() + 1); // 부모 바로 아래 등록
      // --------------------------- 답변 관련 코드 종료 --------------------------
      
      int count = questionProc.reply(questionVO); // DBMS 답변 처리
      
      if (count == 1) {
        categoryProc.increaseCnt(categoryno); // 글 수 증가
      }
      
      // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
      redirectAttributes.addAttribute("count", count); // 1 or 0
      redirectAttributes.addAttribute("questionno", questionVO.getQuestionno());
      redirectAttributes.addAttribute("passwd", questionVO.getPasswd());
      redirectAttributes.addAttribute("categoryno", questionVO.getCategoryno());
/*      redirectAttributes.addAttribute("nowPage", nowPage);
      redirectAttributes.addAttribute("word", word);*/
      
      mav.setViewName("redirect:/question/reply_message.jsp");
      
      return mav;
    }
  
}


