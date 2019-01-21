package dev.mvc.manager;

import java.util.ArrayList;
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
import dev.mvc.member.MemberVO;
import dev.mvc.manager.File2VO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class ManagerCont {
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
   * 중복 ID 검사
   * http://localhost:9090/ojt/member/checkId.do?id=user1
   * 결과: {"cnt":1}
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
   * 회원 가입 처리
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
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/manager/storage");
    List<MultipartFile> filesMF = managerVO.getFilesMF(); // Spring이 File 객체를
                                                           // 저장해둠.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // 컬럼에 저장할 파일명
    String files_item = ""; // 하나의 파일명
    String filesizes = "";
    long sizes_item = 0; // 하나의 파일 사이즈
    String thumbs = ""; // Thumb 파일들
    String thumbs_item = ""; // 하나의 Thumb 파일명

    int count = filesMF.size(); // 업로드된 파일 갯수

    // Spring은 파일 선택을 안해도 1개의 MultipartFile 객체가 생성됨.
    // System.out.println("--> 업로드된 파일 갯수 count: " + count);

    if (count > 0) { // 전송 파일이 존재한다면
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // 전송파일이 있는지 체크, filesMF
        if (multipartFile.getSize() > 0) { // 전송파일이 있는지 체크
          // System.out.println("전송 파일이 있습니다.");
          files_item = Upload.saveFileSpring(multipartFile, upDir);
          sizes_item = multipartFile.getSize();

          if (Tool.isImage(files_item)) {
            thumbs_item = Tool.preview(upDir, files_item, 120, 80); // Thumb 이미지
                                                                    // 생성
          }

          if (i != 0 && i < count) { // index가 1 이상이면(두번째 파일 이상이면)
            // 하나의 컬럼에 여러개의 파일명을 조합하여 저장, file1.jpg/file2.jpg/file3.jpg
            files = files + "/" + files_item;
            // 하나의 컬럼에 여러개의 파일 사이즈를 조합하여 저장, 12546/78956/42658
            filesizes = filesizes + "/" + sizes_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
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
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    count = managerProc.checkId(managerVO.getId());
    
    if (count == 1) { // ID 중복시 메시지 출력
      redirectAttributes.addAttribute("sw", "id");
      redirectAttributes.addAttribute("count", count); // 1 or 0
      
    } else {
      count = managerProc.create(managerVO); // 등록
 
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
  
  @RequestMapping(value="/manager/idsearch.do", method=RequestMethod.GET)
  public ModelAndView idsearch(String email){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager/idsearch"); // webapp/member/read.jsp
    
    ManagerVO managerVO = managerProc.idsearch(email);
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
    
    int count = managerProc.kind_update2(managerVO); // 수정

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("managerno", managerVO.getManagerno()); // 회원 번호
    
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
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/manager/storage");
    List<MultipartFile> filesMF = managerVO.getFilesMF(); // Spring이 File 객체를
                                                           // 저장해둠.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // 컬럼에 저장할 파일명
    String files_item = ""; // 하나의 파일명
    String filesizes = "";
    long sizes_item = 0; // 하나의 파일 사이즈
    String thumbs = ""; // Thumb 파일들
    String thumbs_item = ""; // 하나의 Thumb 파일명

    int count = filesMF.size(); // 업로드된 파일 갯수

    // 기존의 등록 정보 조회
    ManagerVO managerVO_old = managerProc.read(managerVO.getManagerno());
    if (filesMF.get(0).getSize() > 0) { // 새로운 파일을 등록함으로 기존에 등록된 파일 목록 삭제
      // thumbs 파일 삭제
      String thumbs_old = managerVO_old.getThumbs();
      StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname);
      }

      // 원본 파일 삭제
      String files_old = managerVO_old.getFiles();
      StringTokenizer files_st = new StringTokenizer(files_old, "/");
      while (files_st.hasMoreTokens()) {
        String fname = upDir + files_st.nextToken();
        Tool.deleteFile(fname);
      }

      // --------------------------------------------
      // 새로운 파일의 등록 시작
      // --------------------------------------------
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // 전송파일이 있는지 체크, filesMF
        if (multipartFile.getSize() > 0) { // 전송파일이 있는지 체크
          // System.out.println("전송 파일이 있습니다.");
          files_item = Upload.saveFileSpring(multipartFile, upDir);
          sizes_item = multipartFile.getSize();

          if (Tool.isImage(files_item)) {
            thumbs_item = Tool.preview(upDir, files_item, 120, 80); // Thumb 이미지
                                                                    // 생성
          }

          if (i != 0 && i < count) { // index가 1 이상이면(두번째 파일 이상이면)
            // 하나의 컬럼에 여러개의 파일명을 조합하여 저장, file1.jpg/file2.jpg/file3.jpg
            files = files + "/" + files_item;
            // 하나의 컬럼에 여러개의 파일 사이즈를 조합하여 저장, 12546/78956/42658
            filesizes = filesizes + "/" + sizes_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
            files = files_item; // file1.jpg
            filesizes = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        }
      } // for END
      // --------------------------------------------
      // 새로운 파일의 등록 종료
      // --------------------------------------------

    } else { // 글만 수정하는 경우, 기존의 파일 정보 재사용
      files = managerVO_old.getFiles();
      filesizes = managerVO_old.getFilesizes();
      thumbs = managerVO_old.getThumbs();
    }
    managerVO.setFiles(files);
    managerVO.setFilesizes(filesizes);
    managerVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    count = managerProc.update(managerVO); // 수정

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("managerno", managerVO.getManagerno()); // 회원 번호
    
    mav.setViewName("redirect:/manager/update_message.jsp");
   
    return mav;
  }
  
  /**
   * 권한 변경폼
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
    
    int count = memberProc.kind_update(memberVO); // 수정

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("memberno", memberVO.getMemberno()); // 회원 번호
    
    mav.setViewName("/member/kind_update"); // webapp/member/passwd_update.jsp
  
    mav.setViewName("redirect:/member/update_message.jsp");
   
    return mav;
  }*/
  
  
  /**
   * 패스워드 변경폼
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
   * 패스워드를 변경합니다.
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
    
    // 로그인 관련 추가 영역
    // int count = memberProc.login(id, passwd); // 현재 패스워드 검사
    int count = 1;
    System.out.println("--> count: " + count);
    if (count == 1) { // 로그인한 회원의 패스워드 검사
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
    redirectAttributes.addAttribute("managerno", managerno); // 회원 번호
    
    mav.setViewName("redirect:/manager/delete_message.jsp");
   
    return mav;
  }

  /**
   * 로그인 폼
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

    String ck_id = ""; // id 저장 변수
    String ck_id_save = ""; // id 저장 여부를 체크하는 변수
    String ck_passwd = ""; // passwd 저장 변수
    String ck_passwd_save = ""; // passwd 저장 여부를 체크하는 변수

    if (cookies != null) {
      for (int i=0; i < cookies.length; i++){
        cookie = cookies[i]; // 쿠키 객체 추출
        
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
   * 로그인 처리
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
    
    if (managerProc.login(id, passwd) != 1) { // 로그인 실패시
      mav.setViewName("redirect:/manager/login_message.jsp");
      
    } else { // 패스워드 일치하는 경우
      ManagerVO old_managerVO = managerProc.readById(id);

      session.setAttribute("managerno",  old_managerVO.getManagerno()); // session 내부 객체
      session.setAttribute("id", id);
      session.setAttribute("passwd", passwd);
      session.setAttribute("name", old_managerVO.getName());
      
      // -------------------------------------------------------------------
      // id 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (id_save.equals("Y")) { // id를 저장할 경우
        Cookie ck_id = new Cookie("ck_id", id);
        ck_id.setMaxAge(60 * 60 * 72 * 10); // 30 day, 초단위
        response.addCookie(ck_id);
      } else { // N, id를 저장하지 않는 경우
        Cookie ck_id = new Cookie("ck_id", "");
        ck_id.setMaxAge(0);
        response.addCookie(ck_id);
      }
      // id를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_id_save = new Cookie("ck_id_save", id_save);
      ck_id_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_id_save);
      // -------------------------------------------------------------------

      // -------------------------------------------------------------------
      // Password 관련 쿠기 저장
      // -------------------------------------------------------------------
      if (passwd_save.equals("Y")) { // 패스워드 저장할 경우
        Cookie ck_passwd = new Cookie("ck_passwd", passwd);
        ck_passwd.setMaxAge(60 * 60 * 72 * 10); // 30 day
        response.addCookie(ck_passwd);
      } else { // N, 패스워드를 저장하지 않을 경우
        Cookie ck_passwd = new Cookie("ck_passwd", "");
        ck_passwd.setMaxAge(0);
        response.addCookie(ck_passwd);
      }
      // passwd를 저장할지 선택하는  CheckBox 체크 여부
      Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
      ck_passwd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_passwd_save);
      // -------------------------------------------------------------------
      
      mav.setViewName("redirect:/index.do"); // 확장자 명시 
      
    }
    
    return mav;
  }
  
  
  /**
   * 로그아웃 처리
   * @param request
   * @param session
   * @return
   */
  @RequestMapping(value="/manager/logout.do", method=RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request, 
                                         HttpSession session){
    // System.out.println("--> logout() GET called.");
    ModelAndView mav = new ModelAndView();

    session.invalidate(); // session 내부 객체의 등록된 모든 session 변수 삭제
    
    // webapp/member/message_logout.jsp
    mav.setViewName("redirect:/manager/logout_message.jsp"); 
    
    return mav;
  }

  
}






