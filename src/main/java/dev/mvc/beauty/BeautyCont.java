package dev.mvc.beauty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

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
  
import dev.mvc.beauty.BeaFileVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class BeautyCont {

  @Autowired
  @Qualifier("dev.mvc.beauty.BeautyProc")
  private BeautyProcInter beautyProc = null;
 
  /**
   * ��� �� 
   * 
   * @return
   */
  @RequestMapping(value = "/beauty/create.do", method = RequestMethod.GET)
  public ModelAndView create(int categoryno,int managerno) {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
  /*  
    CategoryVO categoryVO = categoryProc.read(categoryno);
    mav.addObject("categoryVO", categoryVO);*/
    BeautyVO beautyVO = beautyProc.read(categoryno);
    mav.addObject("beautyVO",beautyVO);
   BeautyVO managerVO = beautyProc.read(managerno);
    mav.addObject("managerVO",managerVO);
   System.out.println("categoryno : "+categoryno);
   System.out.println("managerno : "+managerno);
    mav.setViewName("/beauty/create"); // /webapp/contents/create.jsp

    return mav;
  }
  /**
   * ��� ó��
   * 
   * @param request
   * @param contentsVO
   * @return
   */
  @RequestMapping(value = "/beauty/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, BeautyVO beautyVO) {
    System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();
    System.out.println("categoryno : "+beautyVO.getCategoryno());
    System.out.println("managerno : "+beautyVO.getManagerno());
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/beauty/storage");
    List<MultipartFile> filesMF = beautyVO.getFilesMF(); // Spring�� File ��ü��
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
    beautyVO.setImage(files);
    beautyVO.setSizes(sizes);
    beautyVO.setThumb(thumbs);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------

    count = beautyProc.create(beautyVO);
    
    mav.setViewName(
        "redirect:/beauty/create_message.jsp?count=" + count + "&categoryno=" + beautyVO.getCategoryno()+"&managerno="+beautyVO.getManagerno()); // /webapp/contents/create_message.jsp

    return mav;
  }
  
  /**
   * ��ü ���
   * 
   * @return
   */
  // http://localhost:9090/ahr/beauty/list_all_beauty.do
  @RequestMapping(value = "/beauty/list_all_beauty.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();

    List<BeautyVO> list = beautyProc.list_all_beauty(); 
    mav.addObject("list", list);

    mav.setViewName("/beauty/list_all_beauty"); // /webapp/contents/list_all_category.jsp

    return mav;
  }
 
  /**
   * ��ȸ
   * 
   * @param styleno
   * @return
   */ 
  @RequestMapping(value = "/beauty/read.do", method = RequestMethod.GET)
  public ModelAndView read(int styleno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/beauty/read"); // /webapp/contents/read.jsp

    BeautyVO beautyVO = beautyProc.read(styleno);
    mav.addObject("beautyVO", beautyVO);

/*    Categrp_CategoryVO categoryVO = categoryProc.read(contentsVO.getCategoryno()); // ī�װ�
                                                                                   // ����
                                                                                   // ����
    mav.addObject("categoryVO", categoryVO);*/

    ArrayList<BeaFileVO> file_list = beautyProc.getThumbs(beautyVO);

    mav.addObject("file_list", file_list);
    
    BeautyVO count = beautyProc.read(styleno); 
    if (count != null) {
      beautyProc.increaseCnt(styleno); // ��ȸ �� ���� 
    }
    return mav;
  }

  
  /**
   * ���� �� http://localhost:9090/ahr/beauty/delete.do
   * 
   * @param styleno
   * @param categoryno
   * @return
   */
  @RequestMapping(value = "/beauty/delete.do", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
  public ResponseEntity delete(int styleno, int categoryno) {
    // System.out.println("--> delete() GET executed");
    //ModelAndView mav = new ModelAndView();
    HttpHeaders responseHeaders = new HttpHeaders();
    //mav.setViewName("/beauty/delete"); // /webapp/contents/delete.jsp
  
    JSONObject json = new JSONObject(); 
    BeautyVO beautyVO = beautyProc.read(styleno);
    BeautyVO categoryVO = beautyProc.read(categoryno);
    json.put("styleno", beautyVO.getStyleno()); 
    json.put("title", beautyVO.getTitle());
    json.put("categoryno", beautyVO.getCategoryno());

    return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
  }
/**
 * ���� ó��
 * @param redirectAttributes
 * @param request
 * @param categoryno
 * @param styleno
 * @param title
 * @param nowPage
 * @return
 */
  @RequestMapping(value = "/beauty/delete.do", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
  public ResponseEntity delete(RedirectAttributes redirectAttributes, 
                                        HttpServletRequest request, 
                                        int categoryno,
                                        int styleno, 
                                        @RequestParam(value="title", defaultValue="") String title,
                                        @RequestParam(value="nowPage", defaultValue="1") int nowPage 
      ) {
    //ModelAndView mav = new ModelAndView();
    //mav.setViewName("/beauty/delete_message"); // /webapp/contents/delete_message.jsp
    HttpHeaders responseHeaders = new HttpHeaders();
    JSONObject json = new JSONObject();
    ArrayList<String> msgs = new ArrayList<String>();
    
    String upDir = Tool.getRealPath(request, "/beauty/storage"); // ���� ���� ����
                                                                   // ���

    BeautyVO beautyVO = beautyProc.read(styleno); // ������ ���� ������ �б� ����
                                                            // ����
    BeautyVO categoryVO = beautyProc.read(categoryno);
    
    String thumbs_old = beautyVO.getThumb();
    String files_old = beautyVO.getImage();

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

    int count = beautyProc.delete(styleno); // ���ڵ� ����

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
      hashMap.put("categoryno", categoryno); // #{categoryno}
      hashMap.put("title", title);                  // #{word}
      if (beautyProc.search_count(hashMap) % Beauty.RECORD_PER_PAGE == 0){ 
        nowPage = nowPage - 1;
        if (nowPage < 1){
          nowPage = 1;
        }
      }
      
    //}

    // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
    // ���� �����Ҷ� ī�װ�no�� �� �޾ƿ��ϱ� �����ϱ�.
    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("styleno", beautyVO.getStyleno());
    redirectAttributes.addAttribute("categoryno", 1);
    //redirectAttributes.addAttribute("word", word);
    redirectAttributes.addAttribute("title", title);
    redirectAttributes.addAttribute("nowPage", nowPage);

    //mav.setViewName("redirect:/beauty/delete_message.jsp");

    return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
  }
 /**
  * ������
  * @param styleno
  * @return
  */
  @RequestMapping(value = "/beauty/update.do", method = RequestMethod.GET)
  public ModelAndView update(int styleno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/beauty/update"); // /webapp/contents/update.jsp

    BeautyVO beautyVO = beautyProc.read(styleno);
    mav.addObject("beautyVO", beautyVO);

    BeautyVO categoryVO = beautyProc.read(beautyVO.getCategoryno());
    mav.addObject("categoryVO",categoryVO);
                                                                                   // ����
                                                                                   // ����
    mav.addObject("categoryVO", categoryVO);

    ArrayList<BeaFileVO> file_list = beautyProc.getThumbs(beautyVO);

    mav.addObject("file_list", file_list);

    return mav;
  }
  /**
   * ����ó��
   * @param redirectAttributes
   * @param request
   * @param contentsVO
   * @return
   */
  @RequestMapping(value = "/beauty/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, BeautyVO beautyVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/beauty/storage");
    List<MultipartFile> filesMF = beautyVO.getFilesMF(); // Spring�� File ��ü��
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
    BeautyVO beautyVO_old = beautyProc.read(beautyVO.getStyleno());
    if (filesMF.get(0).getSize() > 0) { // ���ο� ������ ��������� ������ ��ϵ� ���� ��� ����
      // thumbs ���� ����
      String thumbs_old = beautyVO_old.getThumb();
      StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname);
      }

      // ���� ���� ����
      String files_old = beautyVO_old.getImage();
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
      files = beautyVO_old.getImage();
      sizes = beautyVO_old.getSizes();
      thumbs = beautyVO_old.getThumb();
    }
    beautyVO.setImage(files);
    beautyVO.setSizes(sizes);
    beautyVO.setThumb(thumbs);

    //beautyVO.setMno(1); // ȸ�� ������ session���� ����

    count = beautyProc.update(beautyVO);

    redirectAttributes.addAttribute("count", count);

    // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
    redirectAttributes.addAttribute("styleno", beautyVO.getStyleno());
    redirectAttributes.addAttribute("categoryno", beautyVO.getCategoryno());

    mav.setViewName("redirect:/beauty/update_message.jsp");

    return mav;

  }
  /**
   * �˻� ���
   * 
   * @param categoryno
   * @param word
   * @return
   */
/*  @RequestMapping(value = "/beauty/search.do", method = RequestMethod.GET)
  public ModelAndView list_by_category_search(int categoryno, String word) {
    // System.out.println("--> list_by_category(int categoryno, String
    // word_find) GET called.");
    ModelAndView mav = new ModelAndView();
    // mav.setViewName("/contents/list_by_categoryno"); //
    // webapp/contents/list_by_categoryno.jsp

    // �˻� ��� �߰�, webapp/contents/list_by_category_search.jsp
    mav.setViewName("/contents/list_by_category_search");

    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("categoryno", categoryno); // #{categoryno}
    hashMap.put("word", word); // #{word}

    // System.out.println("categoryno: " + categoryno);
    // System.out.println("word_find: " + word_find);

    // �˻� ���
    List<ContentsVO> list = contentsProc.list_by_category_search(hashMap);
    mav.addObject("list", list);

    // �˻��� ���ڵ� ����
    int search_count = contentsProc.search_count(hashMap);
    mav.addObject("search_count", search_count);

    Categrp_CategoryVO categoryVO = categoryProc.read(categoryno);
    mav.addObject("categoryVO", categoryVO);

    // mav.addObject("word", word);

    return mav;
  }*/
  /**
   * ��� + �˻� + ����¡ ����
   * @param categoryno
   * @param word
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/beauty/search_paging.do", 
                                       method = RequestMethod.GET)
  public ModelAndView search_paging(
      @RequestParam(value="categoryno") int categoryno,
      @RequestParam(value="title", defaultValue="") String title,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) { 
    // System.out.println("--> list_by_category() GET called.");
    System.out.println("--> nowPage: " + nowPage);
    
    ModelAndView mav = new ModelAndView();
    
    // �˻� ��� �߰�,  /webapp/beauty/search_paging.jsp
    mav.setViewName("/beauty/search_paging");   
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("categoryno", categoryno); // #{categoryno}
    hashMap.put("title", title);                  // #{title}
    hashMap.put("nowPage", nowPage);       
    
    // �˻� ���
    List<BeautyVO> list = beautyProc.search_paging(hashMap);
    mav.addObject("list", list);
    
    // �˻��� ���ڵ� ����
    int search_count = beautyProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
  
    BeautyVO categoryVO = beautyProc.read(categoryno);
    mav.addObject("categoryVO", categoryVO);
    
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
    String paging = beautyProc.paging(categoryno, search_count, nowPage, title);
    mav.addObject("paging", paging);
  
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  @RequestMapping(value="/beauty/increaseLike1.do", method=RequestMethod.POST)
  public ModelAndView increaseLike1(int styleno) {
    ModelAndView mav = new ModelAndView();
    
    BeautyVO beautyVO = beautyProc.read(styleno);
    
    int count = beautyProc.increaseLike1(styleno);
    mav.setViewName("redirect:/beauty/search_paging.do?categoryno="+beautyVO.getCategoryno()); 
    
    return mav;
  }
  @RequestMapping(value="/beauty/list_all_cnt.do", method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
  public ResponseEntity list_all_cnt() {
    //ModelAndView mav = new ModelAndView();
    
    HttpHeaders responseHeaders = new HttpHeaders();
    JSONObject json = new JSONObject();
    ArrayList<String> msgs = new ArrayList<String>();
    List<BeautyVO> count = beautyProc.list_all_cnt(); 

    if(count != null){ 
      msgs.add("����");
    } else if(count == null) { 
      msgs.add("����");
    }
    json.put("msgs", msgs);
    //mav.setViewName("/beauty/list_all_cnt"); // /webapp/contents/list_all_category.jsp
    
    return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
  }
  @RequestMapping(value="/beauty/list_all_like1.do", method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
  public ResponseEntity list_all_like1() {
    //ModelAndView mav = new ModelAndView();
    
    HttpHeaders responseHeaders = new HttpHeaders();
    JSONObject json = new JSONObject();
    ArrayList<String> msgs = new ArrayList<String>();
    List<BeautyVO> count = beautyProc.list_all_like1(); 

    if(count != null){ 
      msgs.add("����");
    } else if(count == null) { 
      msgs.add("����");
    }
    json.put("msgs", msgs);
    //mav.setViewName("/beauty/list_all_like1"); // /webapp/contents/list_all_category.jsp
    
    return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
  }
  @RequestMapping(value="/beauty/list_all_rdate.do", method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
  public ResponseEntity list_all_rdate() {
    //ModelAndView mav = new ModelAndView();
    HttpHeaders responseHeaders = new HttpHeaders();
    JSONObject json = new JSONObject();
    ArrayList<String> msgs = new ArrayList<String>();
    List<BeautyVO> count = beautyProc.list_all_rdate();

    if(count != null){ 
      msgs.add("����");
    } else if(count == null) { 
      msgs.add("����");
    }
    json.put("msgs", msgs);
    //mav.setViewName("/beauty/list_all_rdate"); // /webapp/contents/list_all_category.jsp
    
    return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
  }
}