package dev.mvc.animalstory;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class AnimalStoryCont {

  @Autowired
  @Qualifier("dev.mvc.animalstory.AnimalStoryProc")
  private AnimalStoryProcInter aniProc = null;
  
  public AnimalStoryCont() {
    System.out.println("--> AnimalStoryCont created");
  }
  
  /**
   * �� ��� ��
   * http://localhost:9090/ahr/animalstory/create.do?managerno=1
   * @return
   */
  @RequestMapping(value = "/animalstory/create.do", method = RequestMethod.GET)
  public ModelAndView create(int managerno) {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    
    mav.addObject("managerno", managerno);
    mav.addObject("title", "�ִϸֽ��丮");
    
    
    mav.setViewName("/animalstory/create"); // /webapp/contents/create.jsp

    return mav;
  }
  
  /**
   * ���ó��
   * @param request
   * @param aniVO
   * @return
   */
  @RequestMapping(value = "/animalstory/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, AnimalStoryVO aniVO) {
    // System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/animalstory/storage");
    List<MultipartFile> filesMF = aniVO.getFilesMF(); // Spring�� File ��ü��
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
    aniVO.setFiles(files);
    aniVO.setSizes(sizes);
    aniVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------

    count = aniProc.create(aniVO);
    if (count == 1) {
      //aniProc.increaseCnt(contentsVO.getCategoryno()); // �ۼ� ����
      System.out.println("�ִϸֽ��丮 �� ���ε� ����");
    } else {
      System.out.println("�ִϸֽ��丮 �� ���ε� ����");
    }

    mav.setViewName(
        "redirect:/animalstory/create_message.jsp?count=" + count + "&managerno=" + aniVO.getManagerno()); // /webapp/contents/create_message.jsp

    // mav.setViewName("redirect:/contents/list_by_category_search_paging.do?categoryno="
    // + contentsVO.getCategoryno());
    // mav.setViewName("redirect:/contents/list_all_category.do");

    return mav;
  }
  
  /**
   * ��ü ����Ʈ - �˻� ����
   * http://localhost:9090/ahr/animalstory/list.do?content=&nowPage=
   * @return
   */
  @RequestMapping(value = "/animalstory/list.do", method = RequestMethod.GET)
  public ModelAndView list(
      @RequestParam(value="content") String content,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) {
    ModelAndView mav = new ModelAndView();
 
    AnimalStoryVO aniVO = new AnimalStoryVO();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("content", content);
    hashMap.put("nowPage", nowPage);
    
    // �˻� ���
    List<AnimalStoryVO> list = aniProc.list_by_search_paging(hashMap);
    
    // �˻��� ���ڵ� ����
    int search_count = aniProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    /*
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     *
     * @param managerno ī�װ���ȣ 
     * @param nowPage     ���� ������
     * @param petname �˻���
     * @param name �˻���
     * @return ����¡ ���� ���ڿ�
     */ 
    String paging = aniProc.paging(nowPage, search_count, content);
    mav.addObject("paging", paging);
    
    int size = list.size();
    int managerno = 0;
    String manager = "";
    
    for(int i = 0; i < size; i++) {
      aniVO = list.get(i);
      managerno = aniVO.getManagerno();
      manager = aniProc.manager(managerno);
      aniVO.setManager(manager);
    }
    
    mav.addObject("list", list);
    mav.addObject("title", "�ִϸ� ���丮");
    mav.addObject("managerno", managerno);
    mav.addObject("nowPage", nowPage);
    mav.setViewName("/animalstory/list");
    return mav;
  }
  
  /**
   * ����� / �������� �з� ����Ʈ - �˻� ����
   * @param anitype
   * @param content
   * @return
   */
  @RequestMapping(value = "/animalstory/list_anitype.do", method = RequestMethod.GET)
  public ModelAndView list_anitype(
      @RequestParam(value="anitype") int anitype,
      @RequestParam(value="content") String content,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) {
    ModelAndView mav = new ModelAndView();
    AnimalStoryVO aniVO = new AnimalStoryVO();

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("content", content);
    hashMap.put("anitype", anitype);
    hashMap.put("nowPage", nowPage);
    
      mav.setViewName("/animalstory/list_anitype");
    
   
    
    // �˻� ���
    List<AnimalStoryVO> list = aniProc.list_by_search_paging_anitype(hashMap);
    
    // �˻��� ���ڵ� ����
    int search_count = aniProc.search_count_anitype(hashMap);
    mav.addObject("search_count", search_count);
    
    /*
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     *
     * @param managerno ī�װ���ȣ 
     * @param nowPage     ���� ������
     * @param petname �˻���
     * @param name �˻���
     * @return ����¡ ���� ���ڿ�
     */ 
    String paging = aniProc.paging_anitype(nowPage, search_count, content, anitype);
    mav.addObject("paging", paging);
    
    int size = list.size();
    int managerno = 0;
    String manager = "";
    
    for(int i = 0; i < size; i++) {
      aniVO = list.get(i);
      managerno = aniVO.getManagerno();
      manager = aniProc.manager(managerno);
      aniVO.setManager(manager);
    }
    
    mav.addObject("list", list);
    if(anitype == 1) {
      mav.addObject("title", "������ ���丮");
    } else {
      mav.addObject("title", "����� ���丮");
    }
    mav.addObject("anitype", anitype);
    mav.addObject("managerno", managerno);
    mav.addObject("nowPage", nowPage);
    return mav;
  }
  
  /**
   * �ϳ��� �� �о���� 
   * http://localhost:9090/ahr/animalstory/read.do?anino=1
   * @param anino
   * @return
   */
  @RequestMapping(value = "/animalstory/read.do", method = RequestMethod.GET)
  public ModelAndView read(int anino) {
    ModelAndView mav = new ModelAndView();

    AnimalStoryVO aniVO = aniProc.read(anino);
    mav.addObject("aniVO", aniVO);

    ArrayList<FileVO> file_list = aniProc.getThumbs(aniVO);
    
    mav.addObject("file_list", file_list);
    
    mav.setViewName("/animalstory/read"); // /webapp/contents/read.jsp
    return mav;
  }
  
  /**
   * �ϳ��� �ۻ��� ��
   * @param anino
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/animalstory/delete.do", 
                             method=RequestMethod.GET,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity deleteOne(int anino) {
    HttpHeaders responseHeaders = new HttpHeaders();
    AnimalStoryVO aniVO = aniProc.read(anino);
   
    JSONObject json = new JSONObject();
    
    int managerno = 0;
    String manager = "";
    
    managerno = aniVO.getManagerno();
    
    manager = aniProc.manager(managerno);
    
    aniVO.setManager(manager);
    
    
    json.put("anino", aniVO.getAnino());
    json.put("manager", aniVO.getManager());
    json.put("title", aniVO.getTitle());
    json.put("rdate", aniVO.getRdate());
        
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  /**
   * �ϳ��� �� ����ó��
   * @param chartno
   * @return
   */
  
  @ResponseBody
  @RequestMapping(value="/animalstory/delete.do", 
                             method=RequestMethod.POST,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity delete_proc(RedirectAttributes redirectAttributes, 
                                              HttpServletRequest request,
                                              int anino){
    HttpHeaders responseHeaders = new HttpHeaders();
    JSONObject json = new JSONObject();
    
    String upDir = Tool.getRealPath(request, "/animalstory/storage"); // ���� ���� ����
    // ���
    
    AnimalStoryVO aniVO = aniProc.read(anino); // ������ ���� ������ �б� ����
    // ����
    
    String thumbs_old = aniVO.getThumbs();
    String files_old = aniVO.getFiles();
    
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
    
    int count = aniProc.delete(anino); // ���ڵ� ����


    
    if(count==1) {
      json.put("msgs", "������ �����߽��ϴ�.");
      System.out.println("���� ���� ����");
   // 4���� ���ڵ尡 �ϳ��� �������ΰ�� 5��° ���ڵ尡 �����Ǹ� ����������
      // 2���������� 1 �������� �ٿ����մϴ�. 
      /*HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("name", name); // #{categoryno}
      hashMap.put("petname", petname);                  // #{word}
      if (chartProc.search_count(hashMap) % Contents.RECORD_PER_PAGE == 0){ 
        nowPage = nowPage - 1;
        System.out.println("delete nowPage : " + nowPage);
        if (nowPage < 1){
          nowPage = 1;
          System.out.println("delete nowPage < 1  : " + nowPage);
        }
      }*/
    } else {
      json.put("msgs", "������ �����߽��ϴ�.");
      System.out.println("���� ���� ����");
    }
    
   /* redirectAttributes.addAttribute("petname", petname);
    redirectAttributes.addAttribute("name", name);
    redirectAttributes.addAttribute("nowPage", nowPage);*/
    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  /**
   * �ϳ��� �� ���� ��
   * @param anino
   * @return
   */
  @RequestMapping(value = "/animalstory/update.do", method = RequestMethod.GET)
  public ModelAndView update(int anino) {
    ModelAndView mav = new ModelAndView();

    AnimalStoryVO aniVO = aniProc.read(anino);
    mav.addObject("aniVO", aniVO);

    ArrayList<FileVO> file_list = aniProc.getThumbs(aniVO);

    mav.addObject("file_list", file_list);
    mav.addObject("title", "�ִϸֽ��丮 �� ����");
    mav.setViewName("/animalstory/update"); 
    return mav;
  }
  
  /**
   * �ϳ��� �� ���� ó��
   * @param redirectAttributes
   * @param request
   * @param aniVO
   * @return
   */
  @RequestMapping(value = "/animalstory/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, AnimalStoryVO aniVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/animalstory/storage");
    List<MultipartFile> filesMF = aniVO.getFilesMF(); // Spring�� File ��ü��
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
    AnimalStoryVO aniVO_old = aniProc.read(aniVO.getAnino());
    if (filesMF.get(0).getSize() > 0) { // ���ο� ������ ��������� ������ ��ϵ� ���� ��� ����
      // thumbs ���� ����
      String thumbs_old = aniVO_old.getThumbs();
      StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname);
      }

      // ���� ���� ����
      String files_old = aniVO_old.getFiles();
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
      files = aniVO_old.getFiles();
      sizes = aniVO_old.getSizes();
      thumbs = aniVO_old.getThumbs();
    }
    aniVO.setFiles(files);
    aniVO.setSizes(sizes);
    aniVO.setThumbs(thumbs);
    count = aniProc.update(aniVO);

    redirectAttributes.addAttribute("count", count);
    redirectAttributes.addAttribute("anino", aniVO.getAnino());

    mav.setViewName("redirect:/animalstory/update_message.jsp");

    return mav;

  }
  
  /**
   * index������ ���� �ִϸֽ��丮
   * http://localhost:9090/ahr/animalstory/index_animal.do
   * @param request
   * @return
   */
  @RequestMapping(value="/animalstory/index_animal.do", method = RequestMethod.GET)
  public ModelAndView index_animal(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    String manager = "";
    
    List<AnimalStoryVO> list = aniProc.list();
    
    ArrayList<String> index_list = new ArrayList<String>();
    
    StringBuffer url = new StringBuffer();
    
    for(int index = 0; index < list.size(); index++) {
      AnimalStoryVO aniVO = list.get(index);
      manager = aniProc.manager(aniVO.getManagerno());

      url.append("<LI class='category_title'>");
      url.append("  <span style='font-size: 0.9em; color: #555555;'>"+ aniVO.getAnitype() +"</span>");
      url.append("  <A href='" + request.getContextPath()+ "/animalstory/read.do?anino=" + aniVO.getAnino() + "'>");
      url.append(aniVO.getTitle());
      url.append("  </A>");
      url.append("  <span style='font-size: 0.9em; color: #555555;'>("+manager+")</span>");
      url.append("  <span style='font-size: 0.9em; color: #555555;'>"+aniVO.getRdate()+"</span>");
      url.append("</LI>");
      index_list.add(url.toString()); // ��� ��Ͽ� �ϳ��� category �߰� 
      
      url.delete(0, url.toString().length()); // StringBuffer ���ڿ� ����
    }
    mav.addObject("index_list", index_list);
    mav.setViewName("/animalstory/index_animal");
    
    return mav;
  }
  
  
}
