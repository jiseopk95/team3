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
   * ��� �� http://localhost:9090/ahr/question /create.do
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
   * ��� ó��
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
   * ��ü ���
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
   * ��ȸ
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

 //   Categrp_CategoryVO categoryVO = categoryProc.read(contentsVO.getCategoryno()); // ī�װ�
                                                                                   // ����
                                                                                   // ����
  //   mav.addObject("categoryVO", categoryVO);


    return mav;

  }
  
  /**
   * �亯��ȸ
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

 //   Categrp_CategoryVO categoryVO = categoryProc.read(contentsVO.getCategoryno()); // ī�װ�
                                                                                   // ����
                                                                                   // ����
  //   mav.addObject("categoryVO", categoryVO);


    return mav;

  }
  
  /**
   * ��й�ȣ Ȯ����
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
    
    if (questionProc.passwd_check(questionno, passwd) != 1) { // �α��� ���н�
      mav.setViewName("redirect:/question/passwd_fail.jsp?questionno=" + questionno );
      
    } else { // �н����� ��ġ�ϴ� ���
      mav.setViewName("redirect:/question/read.do?questionno=" + questionno+"&categoryno="+ categoryno); // Ȯ���� ��� 
 
  }
    return mav;
 }
  
  
  
  /**
   * ������
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
    * ����
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

      
      QuestionVO questionVO = questionProc.read(questionno); // ������ ���� ������ �б� ����

      int count = questionProc.delete(questionno); // ���ڵ� ����
      System.out.println("���� count :" + count);

  
      // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
      redirectAttributes.addAttribute("count", count); // 1 or 0
      redirectAttributes.addAttribute("questionno", questionVO.getQuestionno());
      redirectAttributes.addAttribute("categoryno", questionVO.getCategoryno());


      mav.setViewName("redirect:/question/delete_message.jsp");

      return mav;
    }
    
    /**
     * �� ���� �� http://localhost:9090/contents/contents/update.do
     * 
     * @param questionno
     * @return
     */
    @RequestMapping(value = "/question/update.do", method = RequestMethod.GET)
    public ModelAndView update(int questionno) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/question/update"); // /webapp/contents/update.jsp

      System.out.println("���� ��questionno: "+questionno);
      
      QuestionVO questionVO = questionProc.read(questionno);
      mav.addObject("questionVO", questionVO);

   //   Categrp_CategoryVO categoryVO = categoryProc.read(questionVO.getCategoryno()); // ī�װ�
                                                                                     // ����
                                                                                   // ����
  //    mav.addObject("categoryVO", categoryVO);


      return mav;
    }
    
    /**
     * �� ����
     * 
     * @param request
     * @param questionVO
     * @return
     */
    @RequestMapping(value = "/question/update.do", method = RequestMethod.POST)
    public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, QuestionVO questionVO) {
      ModelAndView mav = new ModelAndView();
      
      questionVO.setMemberno(1); // ȸ�� ������ session���� ����

      int count = questionProc.update(questionVO);

      System.out.println("������ count: " +count);
      
      redirectAttributes.addAttribute("count", count);

      // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
      redirectAttributes.addAttribute("questionno", questionVO.getQuestionno());
      redirectAttributes.addAttribute("categoryno", questionVO.getCategoryno());

      mav.setViewName("redirect:/question/update_message.jsp");

      return mav;

    }
  
    /**
     * �亯
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

      
      
      // ȸ�� ���� �� session ���κ���
      // int mno = (Integer)session.getAttribute("mno");
      questionVO.setMemberno(1);
      
      // --------------------------- �亯 ���� �ڵ� ���� --------------------------
      QuestionVO parentVO = questionProc.read(questionVO.getQuestionno()); // �θ�� ���� ����
      
      questionVO.setGrpno(parentVO.getGrpno());     // �׷� ��ȣ
      questionVO.setAnsnum(parentVO.getAnsnum()); // �亯 ����

      questionProc.updateAnsnum(questionVO); // ���� ��ϵ� �亯 �ڷ� +1 ó����.

      questionVO.setIndent(parentVO.getIndent() + 1); // �亯 ���� ����
      questionVO.setAnsnum(parentVO.getAnsnum() + 1); // �θ� �ٷ� �Ʒ� ���
      // --------------------------- �亯 ���� �ڵ� ���� --------------------------
      
      int count = questionProc.reply(questionVO); // DBMS �亯 ó��
      
      if (count == 1) {
        categoryProc.increaseCnt(categoryno); // �� �� ����
      }
      
      // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
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


