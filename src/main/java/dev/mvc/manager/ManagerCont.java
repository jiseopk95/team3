package dev.mvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.manager.ManagerVO;
import dev.mvc.manager_login.Manager_loginProcInter;
import dev.mvc.manager_login.Manager_loginVO;
import dev.mvc.member.MemberVO;
import dev.mvc.manager.File2VO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class ManagerCont {
  @Autowired
  @Qualifier("dev.mvc.manager_login.Manager_loginProc")
  private Manager_loginProcInter manager_loginProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc")
  private ManagerProcInter managerProc = null;
   
  public ManagerCont(){
    System.out.println("--> ManagerCont created.");
  }
  
  // http://localhost:9090/ojt/member/create.do
  @RequestMapping(value = "/manager/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/create"); // webapp/member/create.jsp
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
  @RequestMapping(value = "/manager/checkId.do", 
                           method = RequestMethod.GET, 
                           produces = "text/plain;charset=UTF-8")
  public String checkId(String id) {
    JSONObject json = new JSONObject();
    int cnt = managerProc.checkId(id);
    
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
  @RequestMapping(value = "/manager/checkemail.do", 
                           method = RequestMethod.GET, 
                           produces = "text/plain;charset=UTF-8")
  public String checkemail(String email) {
    JSONObject json = new JSONObject();
    int cnt = managerProc.checkemail(email);
    
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
  @RequestMapping(value="/manager/create.do", method=RequestMethod.POST)
  public ModelAndView create(RedirectAttributes redirectAttributes,
                                        HttpServletRequest request, 
                                        ManagerVO managerVO){
    // System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();
    
 // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/manager/storage");
    List<MultipartFile> filesMF = managerVO.getFilesMF(); // Spring�� File ��ü��
                                                           // �����ص�.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // �÷��� ������ ���ϸ�
    String files_item = ""; // �ϳ��� ���ϸ�
    String filesizes = "";
    long sizes_item = 0; // �ϳ��� ���� ������
    String thumbs = ""; // Thumb ���ϵ�
    String thumbs_item = ""; // �ϳ��� Thumb ���ϸ�

    int count = filesMF.size(); // ���ε�� ���� ����

    // Spring�� ���� ������ ���ص� 1���� MultipartFile ��ü�� ������.
    // System.out.println("--> ���ε�� ���� ���� count: " + count);

    if (count > 0) { // ���� ������ �����Ѵٸ�
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // ���������� �ִ��� üũ, filesMF
        if (multipartFile.getSize() > 0) { // ���������� �ִ��� üũ
          // System.out.println("���� ������ �ֽ��ϴ�.");
          files_item = Upload.saveFileSpring(multipartFile, upDir);
          sizes_item = multipartFile.getSize();

          if (Tool.isImage(files_item)) {
            thumbs_item = Tool.preview(upDir, files_item, 120, 80); // Thumb �̹���
                                                                    // ����
          }

          if (i != 0 && i < count) { // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
            // �ϳ��� �÷��� �������� ���ϸ��� �����Ͽ� ����, file1.jpg/file2.jpg/file3.jpg
            files = files + "/" + files_item;
            // �ϳ��� �÷��� �������� ���� ����� �����Ͽ� ����, 12546/78956/42658
            filesizes = filesizes + "/" + sizes_item;
            // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
                                                    // ũ�� üũ
            files = files_item; // file1.jpg
            filesizes = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        } // if (multipartFile.getSize() > 0) {  END
        
      }
    }
    managerVO.setFiles(files);
    managerVO.setFilesizes(filesizes);
    managerVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    
    count = managerProc.checkId(managerVO.getId());
    int count2 = managerProc.checkemail(managerVO.getEmail());
    if (count == 1 || count2==1) { // ID �ߺ��� �޽��� ���
      redirectAttributes.addAttribute("sw", "id");
      redirectAttributes.addAttribute("count", count); // 1 or 0
      
    } else {
      count = managerProc.create(managerVO); // ���
 
      redirectAttributes.addAttribute("sw", "create");
      redirectAttributes.addAttribute("count", count); // 1 or 0
    }
    
    mav.setViewName("redirect:/manager/create_message.jsp");
   
    return mav;
  }
  
  @RequestMapping(value="/manager/list.do", method=RequestMethod.GET)
  public ModelAndView list(HttpSession session){
    // System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/list"); // webapp/member/list.jsp
    
    if (managerProc.isManager(session) == false) {
      mav.setViewName("redirect:/manager/login_need.jsp"); 
    } else {
      mav.setViewName("/manager/list"); // webapp/member/list.jsp
      
      List<ManagerVO> list = managerProc.list();
      mav.addObject("list", list);
    }
    
    return mav;
  }  
  
  @RequestMapping(value="/manager/read.do", method=RequestMethod.GET)
  public ModelAndView read(int managerno){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/read"); // webapp/member/read.jsp
    
    ManagerVO managerVO = managerProc.read(managerno);
    mav.addObject("managerVO", managerVO);
    
    ArrayList<File2VO> file_list=managerProc.getThumbs(managerVO);
    mav.addObject("file_list", file_list);
    return mav;
  }  
  
  @RequestMapping(value="/manager/read2.do", method=RequestMethod.GET)
  public ModelAndView read2(String email){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/idsearch"); // webapp/member/read.jsp
    
    ManagerVO managerVO = managerProc.read2(email);
    mav.addObject("managerVO", managerVO);
    
    return mav;
  }  
  
  @RequestMapping(value="/manager/read3.do", method=RequestMethod.GET)
  public ModelAndView read3(String email){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/passwdsearch"); // webapp/member/read.jsp
    
    ManagerVO managerVO = managerProc.read3(email);
    mav.addObject("managerVO", managerVO);
    
    return mav;
  }
  
  @RequestMapping(value="/manager/kind_update.do", method=RequestMethod.GET)
  public ModelAndView kind_update(int managerno){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/kind_update"); // webapp/member/read.jsp
    
    ManagerVO managerVO = managerProc.kind_update(managerno);
    mav.addObject("managerVO", managerVO);
    
    ArrayList<File2VO> file_list=managerProc.getThumbs(managerVO);
    mav.addObject("file_list", file_list);
    
    return mav;
  }  
  
  @RequestMapping(value="/manager/kind_update2.do", method=RequestMethod.POST)
  public ModelAndView kind_update2(RedirectAttributes redirectAttributes,
                                        HttpServletRequest request, ManagerVO managerVO){
    // System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();
    
    int count = managerProc.kind_update2(managerVO); // ����

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("managerno", managerVO.getManagerno()); // ȸ�� ��ȣ
    
    mav.setViewName("redirect:/manager/update_message.jsp");
   
    return mav;
  }
  
  @RequestMapping(value="/manager/update.do", method=RequestMethod.GET)
  public ModelAndView update(int managerno){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/update"); // webapp/member/read.jsp
    
    ManagerVO managerVO = managerProc.read(managerno);
    mav.addObject("managerVO", managerVO);
    
    return mav;
  }  
  
  @RequestMapping(value="/manager/update.do", method=RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes,
                                        HttpServletRequest request, ManagerVO managerVO){
    // System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/manager/storage");
    List<MultipartFile> filesMF = managerVO.getFilesMF(); // Spring�� File ��ü��
                                                           // �����ص�.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // �÷��� ������ ���ϸ�
    String files_item = ""; // �ϳ��� ���ϸ�
    String filesizes = "";
    long sizes_item = 0; // �ϳ��� ���� ������
    String thumbs = ""; // Thumb ���ϵ�
    String thumbs_item = ""; // �ϳ��� Thumb ���ϸ�

    int count = filesMF.size(); // ���ε�� ���� ����

    // ������ ��� ���� ��ȸ
    ManagerVO managerVO_old = managerProc.read(managerVO.getManagerno());
    if (filesMF.get(0).getSize() > 0) { // ���ο� ������ ��������� ������ ��ϵ� ���� ��� ����
      // thumbs ���� ����
      String thumbs_old = managerVO_old.getThumbs();
      StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname);
      }

      // ���� ���� ����
      String files_old = managerVO_old.getFiles();
      StringTokenizer files_st = new StringTokenizer(files_old, "/");
      while (files_st.hasMoreTokens()) {
        String fname = upDir + files_st.nextToken();
        Tool.deleteFile(fname);
      }

      // --------------------------------------------
      // ���ο� ������ ��� ����
      // --------------------------------------------
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // ���������� �ִ��� üũ, filesMF
        if (multipartFile.getSize() > 0) { // ���������� �ִ��� üũ
          // System.out.println("���� ������ �ֽ��ϴ�.");
          files_item = Upload.saveFileSpring(multipartFile, upDir);
          sizes_item = multipartFile.getSize();

          if (Tool.isImage(files_item)) {
            thumbs_item = Tool.preview(upDir, files_item, 120, 80); // Thumb �̹���
                                                                    // ����
          }

          if (i != 0 && i < count) { // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
            // �ϳ��� �÷��� �������� ���ϸ��� �����Ͽ� ����, file1.jpg/file2.jpg/file3.jpg
            files = files + "/" + files_item;
            // �ϳ��� �÷��� �������� ���� ����� �����Ͽ� ����, 12546/78956/42658
            filesizes = filesizes + "/" + sizes_item;
            // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
                                                    // ũ�� üũ
            files = files_item; // file1.jpg
            filesizes = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        }
      } // for END
      // --------------------------------------------
      // ���ο� ������ ��� ����
      // --------------------------------------------

    } else { // �۸� �����ϴ� ���, ������ ���� ���� ����
      files = managerVO_old.getFiles();
      filesizes = managerVO_old.getFilesizes();
      thumbs = managerVO_old.getThumbs();
    }
    managerVO.setFiles(files);
    managerVO.setFilesizes(filesizes);
    managerVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    count = managerProc.update(managerVO); // ����

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("managerno", managerVO.getManagerno()); // ȸ�� ��ȣ
    
    mav.setViewName("redirect:/manager/update_message.jsp");
   
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
  @RequestMapping(value="/manager/passwd_update.do", method=RequestMethod.GET)
  public ModelAndView passwd_update(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/passwd_update"); // webapp/member/passwd_update.jsp
    
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
  @RequestMapping(value="/manager/passwd_update.do", method=RequestMethod.POST)
  public ModelAndView passwd_update(HttpServletRequest request,
                                                    HttpSession session,
                                                    String passwd,
                                                    String new_passwd){
    // System.out.println("--> passwd_update() POST called.");
    ModelAndView mav = new ModelAndView();
    
    String id = "user1";
    // String id = (String)session.getAttribute("id"); // session
    int managerno = 1;
    // int memberno = (Integer)session.getAttribute("memberno"); // session
    
    // �α��� ���� �߰� ����
    // int count = memberProc.login(id, passwd); // ���� �н����� �˻�
    int count = 1;
    System.out.println("--> count: " + count);
    if (count == 1) { // �α����� ȸ���� �н����� �˻�
      int count_update = managerProc.passwd_update(managerno, new_passwd);
      System.out.println("--> count_update: " + count_update);
      mav.setViewName("redirect:/manager/passwd_update_message.jsp?count=" + count_update + "&managerno=" + managerno);
    } else {
      mav.setViewName("redirect:/manager/login.do");      
    }
    
    return mav;
  } 

  @RequestMapping(value="/manager/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int managerno){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/delete"); // webapp/member/delete.jsp
    
    ManagerVO managerVO = managerProc.read(managerno);
    mav.addObject("managerVO", managerVO);
    
    return mav;
  }  
  
  @RequestMapping(value="/manager/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(RedirectAttributes redirectAttributes,
                                        HttpServletRequest request, int managerno){
    ModelAndView mav = new ModelAndView();
    
    int count = managerProc.delete(managerno);

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("managerno", managerno); // ȸ�� ��ȣ
    
    mav.setViewName("redirect:/manager/delete_message.jsp");
   
    return mav;
  }

  /**
   * �α��� ��
   * @return
   */
  // http://localhost:9090/member/member/login.do 
  @RequestMapping(value = "/manager/login.do", 
                             method = RequestMethod.GET)
  public ModelAndView login(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/login_ck_form"); // /webapp/member/login_ck_form.jsp
    
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
  @RequestMapping(value="/manager/login.do", method=RequestMethod.POST)
  public ModelAndView login(HttpServletRequest request, 
                                       HttpServletResponse response,
                                       HttpSession session,
                                       String id, 
                                       @RequestParam(value="id_save", defaultValue="") String id_save,
                                       String passwd,
                                       @RequestParam(value="passwd_save", defaultValue="") String passwd_save){
    // System.out.println("--> login() POST called.");
    ModelAndView mav = new ModelAndView();
    
    if (managerProc.login(id, passwd) != 1) { // �α��� ���н�
      mav.setViewName("redirect:/manager/login_message.jsp");
      
    } else { // �н����� ��ġ�ϴ� ���
      ManagerVO old_managerVO = managerProc.readById(id);

      session.setAttribute("managerno",  old_managerVO.getManagerno()); // session ���� ��ü
      session.setAttribute("id", id);
      session.setAttribute("passwd", passwd);
      session.setAttribute("name", old_managerVO.getName());
      
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

      Manager_loginVO manager_loginVO=new Manager_loginVO();
      
      /**
       * member_loginno,memberno,ip,rdate
       */
      
      int managerno=old_managerVO.getManagerno();
      manager_loginVO.setManagerno(managerno);
      manager_loginVO.setIp(request.getRemoteAddr());
      
      int count=manager_loginProc.create(manager_loginVO);
      
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
  @RequestMapping(value="/manager/logout.do", method=RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request, 
                                         HttpSession session){
    // System.out.println("--> logout() GET called.");
    ModelAndView mav = new ModelAndView();

    session.invalidate(); // session ���� ��ü�� ��ϵ� ��� session ���� ����
    
    // webapp/member/message_logout.jsp
    mav.setViewName("redirect:/manager/logout_message.jsp"); 
    
    return mav;
  }
  
  /**
   * �˻� ���
   * 
   * @param categoryno
   * @param word
   * @return
   */
  @RequestMapping(value = "/manager/list_search.do", method = RequestMethod.GET)
  public ModelAndView list_search( @RequestParam(value="name", defaultValue="") String name,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage) {
    // System.out.println("--> list_by_category(int categoryno, String
    // word_find) GET called.");
    System.out.println("--> nowPage: " + nowPage);
    
    ModelAndView mav = new ModelAndView();
    // mav.setViewName("/contents/list_by_categoryno"); //
    // webapp/contents/list_by_categoryno.jsp

    // �˻� ��� �߰�, webapp/contents/list_by_category_search.jsp
    mav.setViewName("/manager/list_search");

    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("name", name); // #{word}
    hashMap.put("nowPage", nowPage);    

    // System.out.println("categoryno: " + categoryno);
    // System.out.println("word_find: " + word_find);

    // �˻� ���
    List<ManagerVO> list = managerProc.list_search(hashMap);
    mav.addObject("list", list);

    // �˻��� ���ڵ� ����
    int search_count = managerProc.search_count(hashMap);
    mav.addObject("search_count", search_count);

    // mav.addObject("word", word);
    
    String paging = managerProc.paging(search_count, nowPage, name);
    mav.addObject("paging", paging);
    mav.addObject("nowPage", nowPage);

    return mav;
  }
  
  @RequestMapping(value="/manager/idsearch.do", method=RequestMethod.GET)
  public ModelAndView idsearch(String name, String email){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/idsearch"); // webapp/member/read.jsp
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("name", name); // #{word}
    hashMap.put("email", email); // #{word}
    
    List<ManagerVO> idsearch = managerProc.idsearch(hashMap);
    mav.addObject("idsearch", idsearch);
   
    
    int search_count2 = managerProc.search_count2(hashMap);
    System.out.println("search_count2: " + search_count2 );
    mav.addObject("search_count2", search_count2);
    
   
    
    if(search_count2==1){
      ManagerVO managerVO = managerProc.read2(email);
      mav.addObject("managerVO", managerVO);
    }
      
    return mav;
  }  
  
  /**
   * ���̵� ã�⿡ �� ����Ʈ
   * @param session
   * @return
   */
  @RequestMapping(value="/manager/list_id.do", method=RequestMethod.GET)
  public ModelAndView list_id(HttpSession session){
    // System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/list_id"); // webapp/member/list.jsp

      List<ManagerVO> list_id = managerProc.list_id();
      mav.addObject("list_id", list_id);
    
    return mav;
  }  
  @RequestMapping(value="/manager/passwdsearch.do", method=RequestMethod.GET)
  public ModelAndView passwdsearch(String name, String id, String email){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    ManagerVO managerVO=new ManagerVO();
    mav.setViewName("/manager/passwdsearch"); // webapp/member/read.jsp
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("name", name); // #{word}
    hashMap.put("id", id); // #{word}
    hashMap.put("email", email); // #{word}
    
    List<ManagerVO> passwdsearch = managerProc.passwdsearch(hashMap);
    mav.addObject("passwdsearch", passwdsearch);
    
    int search_count3 = managerProc.search_count3(hashMap);
    mav.addObject("search_count3", search_count3);
    
    if(search_count3==1){
      managerVO = managerProc.read3(email);
      mav.addObject("managerVO", managerVO);
    }
      
    return mav;
  }  
  
  /**
   * ���̵� ã�⿡ �� ����Ʈ
   * @param session
   * @return
   */
  @RequestMapping(value="/manager/list_passwd.do", method=RequestMethod.GET)
  public ModelAndView list_passwd(HttpSession session){
    // System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/list_passwd"); // webapp/member/list.jsp

      List<ManagerVO> list_passwd = managerProc.list_passwd();
      mav.addObject("list_passwd", list_passwd);
    
    return mav;
  }  
  
}






