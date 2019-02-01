package dev.mvc.member;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.member_login.Member_loginVO;
import dev.mvc.member_login.Member_loginProcInter;
/*import dev.mvc.member_login.Member_loginVO;*/
import dev.mvc.pet.PetVO;
/*import nation.web.login.LoginVO;*/
import nation.web.tool.Tool;

@Controller
public class MemberCont {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.member_login.Member_loginProc")
  private Member_loginProcInter member_loginProc = null;
   
  public MemberCont(){
    System.out.println("--> MemberCont created.");
  }
  
  // http://localhost:9090/ojt/member/create.do
  @RequestMapping(value = "/member/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/create"); // webapp/member/create.jsp
    return mav;
  }
  
  
  /**
   * �ߺ� ID �˻�
   * http://localhost:9090/ojt/member/checkId.do?id=user1
   * ���: {"cnt":1}
   * @param id
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkId.do", 
                           method = RequestMethod.GET, 
                           produces = "text/plain;charset=UTF-8")
  public String checkId(String id) {
    JSONObject json = new JSONObject();
    int cnt = memberProc.checkId(id);
    
    json.put("cnt", cnt);
    return json.toString();
  }
  
  /**
   * �ߺ� �̸��� �˻�
   * http://localhost:9090/ojt/member/checkId.do?id=user1
   * ���: {"cnt":1}
   * @param id
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/member/checkemail.do", 
                           method = RequestMethod.GET, 
                           produces = "text/plain;charset=UTF-8")
  public String checkemail(String email) {
    JSONObject json = new JSONObject();
    int cnt = memberProc.checkemail(email);
    
    json.put("cnt", cnt);
    return json.toString();
  }
  
  /**
   * ȸ�� ���� ó��
   * @param redirectAttributes
   * @param request
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/member/create.do", method=RequestMethod.POST)
  public ModelAndView create(RedirectAttributes redirectAttributes,
                                        HttpServletRequest request, 
                                        MemberVO memberVO){
    // System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();
   
    int count = memberProc.checkId(memberVO.getId());
    int count2 = memberProc.checkemail(memberVO.getEmail());
    if (count == 1 || count2==1) { // ID �ߺ��� �޽��� ���
      redirectAttributes.addAttribute("sw", "id");
      redirectAttributes.addAttribute("count", count); // 1 or 0
      
    } else {
      count = memberProc.create(memberVO); // ���
     
      redirectAttributes.addAttribute("sw", "create");
      redirectAttributes.addAttribute("count", count); // 1 or 0
    }
    
    mav.setViewName("redirect:/member/create_message.jsp");
   
    return mav;
  }
  
  @RequestMapping(value="/member/list.do", method=RequestMethod.GET)
  public ModelAndView list(HttpSession session){
    // System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/list"); // webapp/member/list.jsp
    
    if (memberProc.isMember(session) == false) {
      mav.setViewName("redirect:/member/login_need.jsp"); 
    } else {
      mav.setViewName("/member/list"); // webapp/member/list.jsp
      
      List<MemberVO> list = memberProc.list();
      mav.addObject("list", list);
    }
    
    return mav;
  }  
  
  @RequestMapping(value="/member/read.do", method=RequestMethod.GET)
  public ModelAndView read(int memberno){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/read"); // webapp/member/read.jsp
    
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    
    return mav;
  }  
  
  @RequestMapping(value="/member/read2.do", method=RequestMethod.GET)
  public ModelAndView read2(String email){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/idsearch"); // webapp/member/read.jsp
    
    MemberVO memberVO = memberProc.read2(email);
    mav.addObject("memberVO", memberVO);
    
    return mav;
  }  
  
  @RequestMapping(value="/member/read3.do", method=RequestMethod.GET)
  public ModelAndView read3(String email){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/passwdsearch"); // webapp/member/read.jsp
    
    MemberVO memberVO = memberProc.read3(email);
    mav.addObject("memberVO", memberVO);
    
    return mav;
  }
  
  @RequestMapping(value="/member/update.do", method=RequestMethod.GET)
  public ModelAndView update(int memberno){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/update"); // webapp/member/read.jsp
    
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    
    return mav;
  }  
  
  @RequestMapping(value="/member/update.do", method=RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes,
                                        HttpServletRequest request, MemberVO memberVO){
    // System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();
    
    int count = memberProc.update(memberVO); // ����

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("memberno", memberVO.getMemberno()); // ȸ�� ��ȣ
    
    mav.setViewName("redirect:/member/update_message.jsp");
   
    return mav;
  }
  
  /**
   * ���� ������
   * @return
   *//*
  @RequestMapping(value="/member/kind_update.do", method=RequestMethod.GET)
  public ModelAndView kind_update(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/kind_update"); // webapp/member/passwd_update.jsp
    // mav.addObject("memberno", memberno);
    
    return mav;
  } 
  
  
  @RequestMapping(value="/member/kind_update.do", method=RequestMethod.POST)
  public ModelAndView update2(RedirectAttributes redirectAttributes,
                                        HttpServletRequest request, MemberVO memberVO){
    // System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();
    
    int count = memberProc.kind_update(memberVO); // ����

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("memberno", memberVO.getMemberno()); // ȸ�� ��ȣ
    
    mav.setViewName("/member/kind_update"); // webapp/member/passwd_update.jsp
  
    mav.setViewName("redirect:/member/update_message.jsp");
   
    return mav;
  }*/
  
  
  /**
   * �н����� ������
   * @return
   */
  @RequestMapping(value="/member/passwd_update.do", method=RequestMethod.GET)
  public ModelAndView passwd_update(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/passwd_update"); // webapp/member/passwd_update.jsp
    
    // mav.addObject("memberno", memberno);
    
    return mav;
  }  
  
  /**
   * �н����带 �����մϴ�.
   * @param request
   * @param passwd
   * @param new_passwd
   * @return
   */
  @RequestMapping(value="/member/passwd_update.do", method=RequestMethod.POST)
  public ModelAndView passwd_update(HttpServletRequest request,
                                                    HttpSession session,
                                                    String passwd,
                                                    String new_passwd){
    // System.out.println("--> passwd_update() POST called.");
    ModelAndView mav = new ModelAndView();
    
    String id = "user1";
    // String id = (String)session.getAttribute("id"); // session
    int memberno = 1;
    // int memberno = (Integer)session.getAttribute("memberno"); // session
    
    // �α��� ���� �߰� ����
    // int count = memberProc.login(id, passwd); // ���� �н����� �˻�
    int count = 1;
    System.out.println("--> count: " + count);
    if (count == 1) { // �α����� ȸ���� �н����� �˻�
      int count_update = memberProc.passwd_update(memberno, new_passwd);
      System.out.println("--> count_update: " + count_update);
      mav.setViewName("redirect:/member/passwd_update_message.jsp?count=" + count_update + "&memberno=" + memberno);
    } else {
      mav.setViewName("redirect:/member/login.do");      
    }
    
    return mav;
  } 

  @RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int memberno){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/delete"); // webapp/member/delete.jsp
    
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    
    return mav;
  }  
  
  @RequestMapping(value="/member/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(RedirectAttributes redirectAttributes,
                                        HttpServletRequest request, int memberno){
    ModelAndView mav = new ModelAndView();
    
    int count = memberProc.delete(memberno);

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("memberno", memberno); // ȸ�� ��ȣ
    
    mav.setViewName("redirect:/member/delete_message.jsp");
   
    return mav;
  }

  /**
   * �α��� ��
   * @return
   */
  // http://localhost:9090/member/member/login.do 
  @RequestMapping(value = "/member/login.do", 
                             method = RequestMethod.GET)
  public ModelAndView login(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/login_ck_form"); // /webapp/member/login_ck_form.jsp
    
    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;

    String ck_id = ""; // id ���� ����
    String ck_id_save = ""; // id ���� ���θ� üũ�ϴ� ����
    String ck_passwd = ""; // passwd ���� ����
    String ck_passwd_save = ""; // passwd ���� ���θ� üũ�ϴ� ����

    if (cookies != null) {
      for (int i=0; i < cookies.length; i++){
        cookie = cookies[i]; // ��Ű ��ü ����
        
        if (cookie.getName().equals("ck_id")){
          ck_id = cookie.getValue(); 
        }else if(cookie.getName().equals("ck_id_save")){
          ck_id_save = cookie.getValue();  // Y, N
        }else if (cookie.getName().equals("ck_passwd")){
          ck_passwd = cookie.getValue();         // 1234
        }else if(cookie.getName().equals("ck_passwd_save")){
          ck_passwd_save = cookie.getValue();  // Y, N
        }
      }
    }
    
    mav.addObject("ck_id", ck_id);
    mav.addObject("ck_id_save", ck_id_save);
    mav.addObject("ck_passwd", ck_passwd);
    mav.addObject("ck_passwd_save", ck_passwd_save);
    
    return mav;
  }
  
  /**
   * �α��� ó��
   * @param request
   * @param response
   * @param session
   * @param id
   * @param id_save
   * @param passwd
   * @param passwd_save
   * @return
   */
  @RequestMapping(value="/member/login.do", method=RequestMethod.POST)
  public ModelAndView login(HttpServletRequest request, 
                                       HttpServletResponse response,
                                       HttpSession session,
                                       String id, 
                                       @RequestParam(value="id_save", defaultValue="") String id_save,
                                       String passwd,
                                       @RequestParam(value="passwd_save", defaultValue="") String passwd_save){
    // System.out.println("--> login() POST called.");
    ModelAndView mav = new ModelAndView();
    
    if (memberProc.login(id, passwd) != 1) { // �α��� ���н�
      mav.setViewName("redirect:/member/login_message.jsp");
      
    } else { // �н����� ��ġ�ϴ� ���
      MemberVO old_memberVO = memberProc.readById(id);
     
      session.setAttribute("memberno",  old_memberVO.getMemberno()); // session ���� ��ü
      session.setAttribute("id", id);
      session.setAttribute("passwd", passwd);
      session.setAttribute("name", old_memberVO.getName());
      
      // -------------------------------------------------------------------
      // id ���� ��� ����
      // -------------------------------------------------------------------
      if (id_save.equals("Y")) { // id�� ������ ���
        Cookie ck_id = new Cookie("ck_id", id);
        ck_id.setMaxAge(60 * 60 * 72 * 10); // 30 day, �ʴ���
        response.addCookie(ck_id);
      } else { // N, id�� �������� �ʴ� ���
        Cookie ck_id = new Cookie("ck_id", "");
        ck_id.setMaxAge(0);
        response.addCookie(ck_id);
      }
      // id�� �������� �����ϴ�  CheckBox üũ ����
      Cookie ck_id_save = new Cookie("ck_id_save", id_save);
      ck_id_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_id_save);
      // -------------------------------------------------------------------

      // -------------------------------------------------------------------
      // Password ���� ��� ����
      // -------------------------------------------------------------------
      if (passwd_save.equals("Y")) { // �н����� ������ ���
        Cookie ck_passwd = new Cookie("ck_passwd", passwd);
        ck_passwd.setMaxAge(60 * 60 * 72 * 10); // 30 day
        response.addCookie(ck_passwd);
      } else { // N, �н����带 �������� ���� ���
        Cookie ck_passwd = new Cookie("ck_passwd", "");
        ck_passwd.setMaxAge(0);
        response.addCookie(ck_passwd);
      }
      // passwd�� �������� �����ϴ�  CheckBox üũ ����
      Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
      ck_passwd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_passwd_save);
      // -------------------------------------------------------------------
      
      
      // �α��� ���� �߰�

      Member_loginVO member_loginVO=new Member_loginVO();
      
      /**
       * member_loginno,memberno,ip,rdate
       */
      
      int memberno=old_memberVO.getMemberno();
      member_loginVO.setMemberno(memberno);
      member_loginVO.setIp(request.getRemoteAddr());
      
      int count=member_loginProc.create(member_loginVO);
      
      mav.setViewName("redirect:/index.do"); // Ȯ���� ��� 
      
    }
    
    return mav;
  }
  
  
  /**
   * �α׾ƿ� ó��
   * @param request
   * @param session
   * @return
   */
  @RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request, 
                                         HttpSession session){
    // System.out.println("--> logout() GET called.");
    ModelAndView mav = new ModelAndView();

    session.invalidate(); // session ���� ��ü�� ��ϵ� ��� session ���� ����
    
    // webapp/member/message_logout.jsp
    mav.setViewName("redirect:/member/logout_message.jsp"); 
    
    return mav;
  }
  /**
   * �˻� ���
   * 
   * @param categoryno
   * @param word
   * @return
   */
  @RequestMapping(value = "/member/list_search.do", method = RequestMethod.GET)
  public ModelAndView list_search( @RequestParam(value="name", defaultValue="") String name,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage) {
    // System.out.println("--> list_by_category(int categoryno, String
    // word_find) GET called.");
    System.out.println("--> nowPage: " + nowPage);
    
    ModelAndView mav = new ModelAndView();
    // mav.setViewName("/contents/list_by_categoryno"); //
    // webapp/contents/list_by_categoryno.jsp

    // �˻� ��� �߰�, webapp/contents/list_by_category_search.jsp
    mav.setViewName("/member/list_search");

    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("name", name); // #{word}
    hashMap.put("nowPage", nowPage);    

    // System.out.println("categoryno: " + categoryno);
    // System.out.println("word_find: " + word_find);

    // �˻� ���
    List<MemberVO> list = memberProc.list_search(hashMap);
    mav.addObject("list", list);

    // �˻��� ���ڵ� ����
    int search_count = memberProc.search_count(hashMap);
    mav.addObject("search_count", search_count);

    // mav.addObject("word", word);
    
    String paging = memberProc.paging(search_count, nowPage, name);
    mav.addObject("paging", paging);
    mav.addObject("nowPage", nowPage);

    return mav;
  }
  
  @RequestMapping(value="/member/idsearch.do", method=RequestMethod.GET)
  public ModelAndView idsearch(String name, String email){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/idsearch"); // webapp/member/read.jsp
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("name", name); // #{word}
    hashMap.put("email", email); // #{word}
    
    List<MemberVO> idsearch = memberProc.idsearch(hashMap);
    mav.addObject("idsearch", idsearch);
   
    
    int search_count2 = memberProc.search_count2(hashMap);
    System.out.println("search_count2: " + search_count2 );
    mav.addObject("search_count2", search_count2);
    
   if(search_count2==1){
     MemberVO memberVO = memberProc.read2(email);
     mav.addObject("memberVO", memberVO);
   }
     
    return mav;
  }  
  
  /**
   * ���̵� ã�⿡ �� ����Ʈ
   * @param session
   * @return
   */
  @RequestMapping(value="/member/list_id.do", method=RequestMethod.GET)
  public ModelAndView list_id(HttpSession session){
    // System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/list_id"); // webapp/member/list.jsp

      List<MemberVO> list_id = memberProc.list_id();
      mav.addObject("list_id", list_id);
    
    return mav;
  }  
  @RequestMapping(value="/member/passwdsearch.do", method=RequestMethod.GET)
  public ModelAndView passwdsearch(String name, String id, String email){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    MemberVO memberVO=new MemberVO();
    mav.setViewName("/member/passwdsearch"); // webapp/member/read.jsp
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("name", name); // #{word}
    hashMap.put("id", id); // #{word}
    hashMap.put("email", email); // #{word}
    
    List<MemberVO> passwdsearch = memberProc.passwdsearch(hashMap);
    mav.addObject("passwdsearch", passwdsearch);
    
    int search_count3 = memberProc.search_count3(hashMap);
    mav.addObject("search_count3", search_count3);
    
    if(search_count3==1){
      memberVO = memberProc.read3(email);
      mav.addObject("memberVO", memberVO);
    }
      
    return mav;
  }  
  
  /**
   * ���̵� ã�⿡ �� ����Ʈ
   * @param session
   * @return
   */
  @RequestMapping(value="/member/list_passwd.do", method=RequestMethod.GET)
  public ModelAndView list_passwd(HttpSession session){
    // System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member/list_passwd"); // webapp/member/list.jsp

      List<MemberVO> list_passwd = memberProc.list_passwd();
      mav.addObject("list_passwd", list_passwd);
    
    return mav;
  }  

}






