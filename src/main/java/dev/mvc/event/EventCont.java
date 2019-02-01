package dev.mvc.event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Date;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.beauty.Beauty;
import dev.mvc.beauty.BeautyVO;
import dev.mvc.category.Categrp_CategoryVO;
import dev.mvc.event.EventFileVO;
import dev.mvc.present.PresentProcInter;
import dev.mvc.present.PresentVO;
import dev.mvc.user.UserProcInter;
import dev.mvc.user.UserVO;
import dev.mvc.user.member_userVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class EventCont{
  @Autowired
  @Qualifier("dev.mvc.present.PresentProc")
  private PresentProcInter presentProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.user.UserProc")
  private UserProcInter userProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.event.EventProc")
  private EventProcInter eventProc = null;
  //��Ƽ�� ī�װ���ȣ,�����ڹ�ȣ->�����ڹ�ȣ, ������ȣ
  /**
   * ��� �� 
   * 
   * @return
   */
  @RequestMapping(value = "/event/create.do", method = RequestMethod.GET)
  public ModelAndView create(int managerno) {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    // ���� ���� �����ϳ��� �̺�Ʈ�ϳ��� �����ؼ� 
    // ���㶧 �̹� ������ ������ ������ �ϰ� ���� �ʴ� �ϸ� �������� ������ �ʿ��ϹǷ� 
    // ������ȣ�� �ʿ�������, ��� ������ �׳� ����Ʈ�ڽ��ȿ� ������� �ϰ�����Ŵϱ�
    // , ��ü ���� ������ �ҷ����� �Ŵϱ� ������ȣ�� �ҷ��� �ʿ䰡 ����. ���������ʰ� �׳� �ҷ����� �Ŵϱ�.
    /*PresentVO presentVO = presentProc.read(presentno);
    mav.addObject("presentVO",presentVO);*/
    List<event_presentVO> list_present = eventProc.list_present();
    mav.addObject("list_present", list_present);
    
  /*  
    CategoryVO categoryVO = categoryProc.read(categoryno);
    mav.addObject("categoryVO", categoryVO);*/
   /*ManagerVO managerVO = eventProc.read(managerno);
    mav.addObject("managerVO",managerVO);*/
   System.out.println("managerno : "+managerno);
    mav.setViewName("/event/create"); // /webapp/contents/create.jsp

    return mav;
  }
  /**
   * ��� ó��
   * 
   * @param request
   * @param contentsVO
   * @return
   */
  @RequestMapping(value = "/event/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, EventVO eventVO) {
    System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();
    
    
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/event/storage");
    List<MultipartFile> filesMF = eventVO.getFilesMF(); // Spring�� File ��ü��
                                                           // �����ص�.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // �÷��� ������ ���ϸ�
    String files_item = ""; // �ϳ��� ���ϸ�
    String sizes = "";
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
            sizes = sizes + "/" + sizes_item;
            // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
                                                    // ũ�� üũ
            files = files_item; // file1.jpg
            sizes = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        } // if (multipartFile.getSize() > 0) {  END
        
      }
    }
    eventVO.setImage(files);
    eventVO.setImage_size(sizes);
    eventVO.setThumb(thumbs);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------

    count = eventProc.create(eventVO);
    
    mav.setViewName(
        "redirect:/event/create_message.jsp?count=" + count + "&managerno="+eventVO.getManagerno()); // /webapp/contents/create_message.jsp

    return mav;
  }
  
  /**
   * ��ü ���
   * 
   * @return
   */
  @RequestMapping(value = "/event/list_all_event.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    List<EventVO> list = eventProc.list_all_event(); 
 
    mav.addObject("list", list);

    mav.setViewName("/event/list_all_event"); // /webapp/contents/list_all_category.jsp

    return mav;
  }
 
  /**
   * ��ȸ
   * 
   * @param eventno ������ ��ȸ�� 
   * @return
   */ 
  @RequestMapping(value = "/event/read.do", method = RequestMethod.GET)
  public ModelAndView read(int eventno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/event/read"); // /webapp/contents/read.jsp

    event_managerVO eventVO = eventProc.read_manager(eventno);
    mav.addObject("eventVO", eventVO);
    
    ArrayList<EventFileVO> file_list = eventProc.getThumbs(eventVO);
 
    mav.addObject("file_list", file_list);
    
  /*  EventVO count = eventProc.read(eventno); 
    if (count != null) {
      eventProc.increaseCnt(eventno); // ��ȸ �� ���� 
    }*/
    return mav;
  }
  /**
   * ��ȸ
   * 
   * @param eventno ��� ��ȸ�� 
   * @return
   */ 
  @RequestMapping(value = "/event/read_member.do", method = RequestMethod.GET)
  public ModelAndView read_member(int eventno,int memberno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/event/read_member"); // /webapp/contents/read.jsp

    EventVO eventVO = eventProc.read(eventno);
    mav.addObject("eventVO", eventVO);

/*    List<member_userVO> member_userVO = userProc.list_member(memberno);
    mav.addObject("member_userVO",member_userVO);
    */
    ArrayList<EventFileVO> file_list = eventProc.getThumbs(eventVO);
 
    mav.addObject("file_list", file_list);
    
    mav.addObject("memberno",memberno);
    
  /*  EventVO count = eventProc.read(eventno); 
    if (count != null) {
      eventProc.increaseCnt(eventno); // ��ȸ �� ���� 
    }*/
    return mav;
  }
  
  /**
   * ������
   * @param eventno
   * @return
   */
   @RequestMapping(value = "/event/update.do", method = RequestMethod.GET)
   public ModelAndView update(int eventno,int managerno) {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/event/update"); // /webapp/contents/update.jsp
     
     List<event_presentVO> list_present = eventProc.list_present();
     mav.addObject("list_present", list_present);
      
     EventVO eventVO = eventProc.read(eventno);
     mav.addObject("eventVO", eventVO);             // ���� ����

     ArrayList<EventFileVO> file_list = eventProc.getThumbs(eventVO);

     mav.addObject("file_list", file_list);

     mav.addObject("managerno",managerno);
     return mav;
   }
   /**
    * ����ó��
    * @param redirectAttributes
    * @param request
    * @param contentsVO
    * @return
    */
   @RequestMapping(value = "/event/update.do", method = RequestMethod.POST)
   public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, EventVO eventVO) {
     ModelAndView mav = new ModelAndView();

     // -------------------------------------------------------------------
     // ���� ���� �ڵ� ����
     // -------------------------------------------------------------------
     String upDir = Tool.getRealPath(request, "/event/storage");
     List<MultipartFile> filesMF = eventVO.getFilesMF(); // Spring�� File ��ü��
                                                            // �����ص�.

     // System.out.println("--> filesMF.get(0).getSize(): " +
     // filesMF.get(0).getSize());

     String files = ""; // �÷��� ������ ���ϸ�
     String files_item = ""; // �ϳ��� ���ϸ�
     String sizes = "";
     long sizes_item = 0; // �ϳ��� ���� ������
     String thumbs = ""; // Thumb ���ϵ�
     String thumbs_item = ""; // �ϳ��� Thumb ���ϸ�

     int count = filesMF.size(); // ���ε�� ���� ����

     // ������ ��� ���� ��ȸ
     EventVO eventVO_old = eventProc.read(eventVO.getEventno());
     if (filesMF.get(0).getSize() > 0) { // ���ο� ������ ��������� ������ ��ϵ� ���� ��� ����
       // thumbs ���� ����
       String thumbs_old = eventVO_old.getThumb();
       StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
       while (thumbs_st.hasMoreTokens()) {
         String fname = upDir + thumbs_st.nextToken();
         Tool.deleteFile(fname);
       }

       // ���� ���� ����
       String files_old = eventVO_old.getImage();
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
             sizes = sizes + "/" + sizes_item;
             // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
             thumbs = thumbs + "/" + thumbs_item;
           } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
                                                     // ũ�� üũ
             files = files_item; // file1.jpg
             sizes = "" + sizes_item; // 123456
             thumbs = thumbs_item; // file1_t.jpg
           }

         }
       } // for END
       // --------------------------------------------
       // ���ο� ������ ��� ����
       // --------------------------------------------

     } else { // �۸� �����ϴ� ���, ������ ���� ���� ����
       files = eventVO_old.getImage();
       sizes = eventVO_old.getImage_size();
       thumbs = eventVO_old.getThumb();
     }
     eventVO.setImage(files);
     eventVO.setImage_size(sizes);
     eventVO.setThumb(thumbs);

     //beautyVO.setMno(1); // ȸ�� ������ session���� ����

     count = eventProc.update(eventVO);

     redirectAttributes.addAttribute("count", count);

   
     // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
     redirectAttributes.addAttribute("eventno", eventVO.getEventno());
     redirectAttributes.addAttribute("managerno", eventVO.getManagerno());
     redirectAttributes.addAttribute("presentno", eventVO.getPresentno());

     mav.setViewName("redirect:/event/update_message.jsp");

     return mav;

   }
   /**
    * ��� + �˻� + ����¡ ����/ ������
    * @param categoryno
    * @param word
    * @param nowPage
    * @return
    */
   @RequestMapping(value = "/event/search_paging.do", 
                                        method = RequestMethod.GET)
   public ModelAndView search_paging(
       @RequestParam(value="managerno") int managerno,
       @RequestParam(value="content", defaultValue="") String content,
       @RequestParam(value="nowPage", defaultValue="1") int nowPage
       ) { 
     // System.out.println("--> list_by_category() GET called.");
     System.out.println("--> nowPage: " + nowPage);
     
     ModelAndView mav = new ModelAndView();
     
     // �˻� ��� �߰�,  /webapp/beauty/search_paging.jsp
     mav.setViewName("/event/search_paging");   
     
     // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
     HashMap<String, Object> hashMap = new HashMap<String, Object>();
     hashMap.put("managerno", managerno);     
     hashMap.put("content", content);                  // #{title}
     hashMap.put("nowPage", nowPage);       
     
     // �˻� ���
     List<event_managerVO> list = eventProc.search_paging(hashMap);
     mav.addObject("list", list);
     
     // �˻��� ���ڵ� ����
     int search_count = eventProc.search_count(hashMap);
     mav.addObject("search_count", search_count);
     
     mav.addObject("managerno",managerno);
   
     // mav.addObject("word", word);
     /*
      * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
      * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
      *
      * @param categoryno ī�װ���ȣ 
      * @param search_count �˻�(��ü) ���ڵ�� 
      * @param nowPage     ���� ������
      * @param word �˻���
      * @return ����¡ ���� ���ڿ�
      */ 
     String paging = eventProc.paging(managerno,search_count, nowPage, content);
     mav.addObject("paging", paging);
   
     mav.addObject("nowPage", nowPage);
     
     return mav; 
   }
   
   /**
    * ��� + �˻� + ����¡ ����/ȸ��
    * @param categoryno
    * @param word
    * @param nowPage
    * @return
    */
   @RequestMapping(value = "/event/search_paging_member.do", 
                                        method = RequestMethod.GET)
   public ModelAndView search_paging_member(
       @RequestParam(value="memberno") int memberno,
       @RequestParam(value="content", defaultValue="") String content,
       @RequestParam(value="nowPage", defaultValue="1") int nowPage
       ) { 
     // System.out.println("--> list_by_category() GET called.");
     System.out.println("--> nowPage: " + nowPage);
     
     ModelAndView mav = new ModelAndView();
     
     // �˻� ��� �߰�,  /webapp/beauty/search_paging.jsp
     mav.setViewName("/event/search_paging_member");   
     
     // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
     HashMap<String, Object> hashMap = new HashMap<String, Object>();
     hashMap.put("memberno", memberno);       
     hashMap.put("content", content);                  // #{title}
     hashMap.put("nowPage", nowPage);       
     
     //�̰Ŵ� ��÷��Ȯ�ι�ư �������� ���� ����� �ȳ������� win�� 
     //�̺�Ʈ���� ��üũ�ؼ� �Ϸ��� �ߴ� �ǵ� eventno�� ���� ���� �ʿ䰡 ���µ�.
     //�ϴ� ���� ... 
     //win���� �߰�
     /*event_eventuserVO event_eventuserVO = eventProc.read_win(eventno); 
     mav.addObject("event_eventuserVO",event_eventuserVO);*/
     
     // �˻� ���
     List<EventVO> list = eventProc.search_paging_member(hashMap);
     mav.addObject("list", list);
     
     
     
     // �˻��� ���ڵ� ����
     int search_count = eventProc.search_count(hashMap);
     mav.addObject("search_count", search_count);
     
     mav.addObject("memberno",memberno);
   
     // mav.addObject("word", word);
     /*
      * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
      * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
      *
      * @param categoryno ī�װ���ȣ 
      * @param search_count �˻�(��ü) ���ڵ�� 
      * @param nowPage     ���� ������
      * @param word �˻���
      * @return ����¡ ���� ���ڿ�
      */ 
     String paging_member = eventProc.paging_member(memberno,search_count, nowPage, content);
     mav.addObject("paging_member", paging_member);
   
     mav.addObject("nowPage", nowPage);
   
     return mav;
   }
   
   /*SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");   
   Date time = new Date();
   String time1 = format1.format(time);
   String eventVO = eventVO.getWindate();
   System.out.println(time1); */
   /**
    * ���� �� http://localhost:9090/ahr/beauty/delete.do
    * 
    * @param styleno
    * @param categoryno
    * @return
    */
   @RequestMapping(value = "/event/delete.do", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
   public ResponseEntity delete(int eventno) {
     // System.out.println("--> delete() GET executed");
     //ModelAndView mav = new ModelAndView();
     HttpHeaders responseHeaders = new HttpHeaders();
     //mav.setViewName("/beauty/delete"); // /webapp/contents/delete.jsp
   
     JSONObject json = new JSONObject(); 
     EventVO eventVO = eventProc.read(eventno);
     json.put("eventno", eventVO.getEventno()); 
     json.put("title", eventVO.getTitle());

     return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
   }
   /**
    * ���� ó��
    * @param redirectAttributes
    * @param request
    * @param eventno
    * @param content
    * @param nowPage
    * @return
    */
     @RequestMapping(value = "/event/delete.do", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
     public ResponseEntity delete(RedirectAttributes redirectAttributes, 
                                           HttpServletRequest request, 
                                           int eventno, 
                                           @RequestParam(value="title", defaultValue="") String title,
                                           @RequestParam(value="nowPage", defaultValue="1") int nowPage 
         ) {
       //ModelAndView mav = new ModelAndView();
       //mav.setViewName("/beauty/delete_message"); // /webapp/contents/delete_message.jsp
       HttpHeaders responseHeaders = new HttpHeaders();
       JSONObject json = new JSONObject();
       ArrayList<String> msgs = new ArrayList<String>();
       
       String upDir = Tool.getRealPath(request, "/event/storage"); // ���� ���� ����
                                                                      // ���

       EventVO eventVO = eventProc.read(eventno); // ������ ���� ������ �б� ����
                                                               // ����
       
       String thumbs_old = eventVO.getThumb();
       String files_old = eventVO.getImage();

       StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/"); // Thumbs
       while (thumbs_st.hasMoreTokens()) { // �ܾ �ִ��� �˻�
         String fname = upDir + thumbs_st.nextToken(); // �ܾ� ����
         Tool.deleteFile(fname);
       }

       StringTokenizer files_st = new StringTokenizer(files_old, "/"); // files
       while (files_st.hasMoreTokens()) { // �ܾ �ִ��� �˻�
         String fname = upDir + files_st.nextToken(); // �ܾ� ����
         Tool.deleteFile(fname);
       }

       int count = eventProc.delete(eventno); // ���ڵ� ����

       if(count == 1){ 
         msgs.add("�� ������ �����߽��ϴ�.");
       } else if(count == 0) { 
         msgs.add("�� ������ �����߽��ϴ�.");
         msgs.add("�ٽ��ѹ� �õ����ּ���.");
       }
        
       json.put("msgs", msgs);
       
       /*if (count == 1) {
         categoryProc.decreaseCnt(categoryno); // ��ϵ� �ۼ��� ����
         */
         // 4���� ���ڵ尡 �ϳ��� �������ΰ�� 5��° ���ڵ尡 �����Ǹ� ����������
         // 2���������� 1 �������� �ٿ����մϴ�. 
         HashMap<String, Object> hashMap = new HashMap<String, Object>();
         hashMap.put("title", title);                  // #{word}
         if (eventProc.search_count(hashMap) % Event.RECORD_PER_PAGE == 0){ 
           nowPage = nowPage - 1;
           if (nowPage < 1){
             nowPage = 1;
           }
         }
         
       //}

       // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
       // ���� �����Ҷ� ī�װ�no�� �� �޾ƿ��ϱ� �����ϱ�.
       redirectAttributes.addAttribute("count", count); // 1 or 0
       redirectAttributes.addAttribute("eventno", eventVO.getEventno());
       //redirectAttributes.addAttribute("word", word);
       redirectAttributes.addAttribute("title", title);
       redirectAttributes.addAttribute("nowPage", nowPage);

       //mav.setViewName("redirect:/beauty/delete_message.jsp");

       return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
     }
   
     
   //----------------------------------ajax�õ�(ȸ���� �����ϱ� ��ư������ �ߺ��˻��� ���)-------------------------------//
  
   
   //read���� �����ϱ� ��ư ������ ����Ǵ� controller  ���⼭ ���� ���������̺� ���� ��� �ǵ���.
   @RequestMapping(value = "/event/check.do", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
   public ResponseEntity check_proc(int eventno,int memberno) {
     HttpHeaders responseHeaders = new HttpHeaders();
     JSONObject json = new JSONObject();
     ArrayList<String> msgs = new ArrayList<String>();
     System.out.println("cont eventno" + eventno);
     System.out.println("cont memberno" + memberno);
     member_userVO userVO = null;
     /*EventVO eventVO = eventProc.read(eventno);
     json.put("eventVO", eventVO); */
     HashMap<String, Object> hashMap = new HashMap<String, Object>();
     hashMap.put("eventno", eventno);     
     hashMap.put("memberno", memberno);
     int count2 = eventProc.select_user(hashMap); // ���������� ���� �˻�!
     
/*     List<member_userVO> member_userVO = userProc.list_member(memberno);
     json.put("member_userVO",member_userVO);
    */
     
   // 2���̻������Ҷ� hashmap

     
     /*if(($('#memberno').val()==$('#userno').val())&&($('#eventno').val()==$('#userevent').val())){
       alert("�̹� ������ �̺�Ʈ �Դϴ�!");
     } */
     if(count2 == 0){ // ����� �ȵǾ�������
       System.out.println("count2 == 0");
       int count = userProc.usercreate(hashMap);  
       if(count==1) { 
       msgs.add("����Ϸ�.");
       System.out.println("��ϼ���");
       }
     } else if(count2 > 0) { // count2 �� 0���� ũ�� => ��, ������ ����ִ�. 
       System.out.println("�̹� ������");
       msgs.add("���� �����߽��ϴ�.\n");
       msgs.add("�ٽ��ѹ� �õ����ּ���.");
     }
      
     json.put("msgs", msgs);
     
     return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
   
}
   
   
   
   //---------------------------------------------------------------------------------------------------------------------------//
   
   
   /**
    * ��÷��Ȯ�� ��ü ���_����¡�˻� �κ�
    *      
    * @return
    */
   @RequestMapping(value = "/event/search_paging_win.do", method = RequestMethod.GET)
   public ModelAndView search_paging_win(
       @RequestParam(value="eventno") int eventno,
       @RequestParam(value="id", defaultValue="") String id,
       @RequestParam(value="nowPage", defaultValue="1") int nowPage) {
     System.out.println("--> SEARCH_PAGING_eventno: " + eventno);
     System.out.println("--> nowPage: " + nowPage);
     
     ModelAndView mav = new ModelAndView();
     
     // �˻� ��� �߰�,  /webapp/beauty/search_paging.jsp
     mav.setViewName("/event/search_paging_win");   
     
     // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
     HashMap<String, Object> hashMap = new HashMap<String, Object>();
     hashMap.put("eventno", eventno); // #{categoryno}
     hashMap.put("id", id);                  // #{title}
     hashMap.put("nowPage", nowPage);       
     
     // �˻� ���
     List<event_eventuserVO> list = eventProc.search_paging_win(hashMap);
     mav.addObject("list", list);
     
     // �˻��� ���ڵ� ����
     int search_count = eventProc.search_count_win(hashMap);
     mav.addObject("search_count", search_count);
   
     EventVO eventVO = eventProc.read(eventno);
     mav.addObject("eventVO", eventVO);
     
     mav.addObject("eventno",eventno);
     //event_eventuserVO event_eventuserVO = userProc.
     /*Categrp_CategoryVO categoryVO = categoryProc.read(categoryno);
     mav.addObject("categoryVO", categoryVO);*/
     
     // mav.addObject("word", word);
   
     /*
      * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
      * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
      *
      * @param categoryno ī�װ���ȣ 
      * @param search_count �˻�(��ü) ���ڵ�� 
      * @param nowPage     ���� ������
      * @param word �˻���
      * @return ����¡ ���� ���ڿ�
      */ 
     String paging = eventProc.paging_win(eventno, search_count, nowPage, id);
     mav.addObject("paging", paging);
   
     mav.addObject("nowPage", nowPage);
     
     return mav;
   }
   
   
   
   
   //�����ڿ��� ���� ������ �� ��. ���������� �����ؾ��Ҽ��� �����ϱ�. ��� ��¥�� �Ǹ� ����ǰ��ϴ°� �𸣰ڴ� ������
   //�����ڰ� �������ߴµ� ȸ���� ��÷Ȯ�ι�ư ������ �װ� �����صθ� alert�� ������ Ȯ�����̶�� �ϸ� �ɵ�.
   @RequestMapping(value = "/event/event_submit.do", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
   public ResponseEntity event_submit(int eventno) {
     System.out.println("cont eventno : " + eventno);
     HttpHeaders responseHeaders = new HttpHeaders();
     JSONObject json = new JSONObject();
     ArrayList<String> msgs = new ArrayList<String>();
     member_userVO userVO = null;
     
     EventVO eventVO = eventProc.read(eventno);
     //mav.addObject("eventVO", eventVO);     
     
     
     json.put("title", eventVO.getTitle());
     //*******************************************************************//
     // �����ڼ� ����
     int count = userProc.user_cnt(eventno);
     // ������ �� ��ŭ �ݺ�->���������ض�
/*     Random random = new Random();*/
     int[] event_win = new int[3]; // 3���� �־�� ���� 3���� ������ �־��ټ�������.
        List<member_userVO> user = null;
        user = userProc.list_all_eventuserno(eventno);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("eventno", eventno);
         //user = userProc.list_all_eventuserno();
    
     //int num = 0;     
        int count3 = userProc.cnt_win(eventno);
        if(count3==0){
     //**********************************************�����κ�***************************************************************//
     for(int i = 0; i< 3; i++){
       event_win[i] = (int)(Math.random()*user.size());  //�ٵ� ���� �׳� event_win��(user����.) ������� or length�� �ϸ� 1�����ʿ�����.
       // �� �����ڼ��� 10���̸� 1���� 10���� �� 3���� ������ �̾ƶ�.
       // >>��÷�ڰ� 3����. 
       // ������ �����Ұ� eventuserno
       // win�� �׻� 1�� 
       // �ߺ������Ϸ��� ���� �ڵ�
       for(int j=0; j<i; j++){
         if(event_win[i]==event_win[j]){
           i--;
/*System.out.println("event_win��ȣ:"+event_win[i]);*/
           break; 
           } 
       }
     }
       for(int k = 0; k < 3; k++){ 
         hashMap.put("memberno", user.get(event_win[k]).getMemberno());
       int count2 = userProc.winner(hashMap);
       json.put("count2", count2);
       if(count2 == 1){
         json.put("msgs", "�����Ǿ����ϴ�.\n");
       }else{
         json.put("msgs", "��������.\n");
       }
   
       }
        }else{
          json.put("msgs", "�̹� ������ �̺�Ʈ�Դϴ�.\n");
        }
       //���� win�� ������ �����ϱ� ��ư������ �̹� �����߽��ϴ�. (0�̸� ������ ������ 1�̻��̸� alert�߰��ϱ�.)
     
    /*   HashMap<String, Object> hashMap = new HashMap<String, Object>();
       hashMap.put("eventno", eventno);*/
       //hashMap.put("win", event_win[i]);
       
       //userProc.winner(hashMap); // ���������̺� �� hashmap �޴°� 
     //******************************************************************************************************************//
     
     
    // ���� �� �迭 ���� win�� update�� ���ֱ�
    // int count2 = userProc.update(userVO);
     
       //mav.setViewName("redirect:/event/search_paging_member.do?memberno="+memberno); // /webapp/categrp/list.jsp
    
     return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
   
}
}