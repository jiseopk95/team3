package dev.mvc.review;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.category.Categrp_CategoryVO;
import nation.web.tool.Messages;
import nation.web.tool.Tool;
import nation.web.tool.Upload;


@Controller 
public class ReviewCont {
  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.tool.Messages")
  private Messages messages = null;
  
  public ReviewCont() {
    System.out.println("--> ReviewCont crated.");
  }

  /**
   * ��� �� http://localhost:9090/ahr/review /create.do
   * 
   * @return
   */
  @RequestMapping(value = "/review/create.do", method = RequestMethod.GET)
  public ModelAndView create(int categoryno) {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();

    
    ReviewVO reviewVO = reviewProc.read(categoryno);
    mav.addObject("reviewVO", reviewVO);
    
    mav.setViewName("/review/create"); // /webapp/contents/create.jsp

    return mav;
  }

 /**
   * ��� ó��
   * 
   * @param request
   * @param contentsVO
   * @return
   */
  @RequestMapping(value = "/review/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, ReviewVO reviewVO) {
    System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/review/storage");
    List<MultipartFile> filesMF = reviewVO.getFilesMF(); // Spring�� File ��ü��
                                                            // �����ص�.
   
    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // �÷��� ������ ���ϸ�
    String files_item = ""; // �ϳ��� ���ϸ�
    String filesize = "";
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
            filesize = filesize + "/" + sizes_item;
            // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
                                                    // ũ�� üũ
            files = files_item; // file1.jpg
            filesize = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        } // if (multipartFile.getSize() > 0) {  END
        
      }
    }
    reviewVO.setFiles(files);
    reviewVO.setFilesize(filesize);
    reviewVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------

  //  count = 1;
    
    count = reviewProc.create(reviewVO);
    
    System.out.println("count :" + count);
    
    mav.setViewName(
        "redirect:/review/create_message.jsp?count=" + count ); // /webapp/contents/create_message.jsp

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
  @RequestMapping(value = "/review/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();

    List<ReviewVO> list = reviewProc.list();
    mav.addObject("list", list);

    mav.setViewName("/review/list"); // /webapp/contents/list_all_category.jsp

    return mav;
  }
  
/**
 * ������
 * @param reviewno
 * @param categoryno
 * @return
 */
  @RequestMapping(value = "/review/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int reviewno, int categoryno) {
    // System.out.println("--> delete() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/review/delete"); // /webapp/review/delete.jsp

/*    Categrp_CategoryVO categoryVO = categoryProc.read(categoryno);
    mav.addObject("categoryVO", categoryVO);*/

    ReviewVO reviewVO = reviewProc.read(reviewno);
    mav.addObject("reviewVO", reviewVO);

    return mav;
  }

  // /contents/delete.do  <-- defaultValue ����
  // /contents/delete.do?word=&nowPage=  <-- defaultValue ������ ����, ERROR �߻�.
  // /contents/delete.do?word=&nowPage=1  <-- ���ڿ��� ���� ����, nowPage�� �ʼ�.
  @RequestMapping(value = "/review/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(RedirectAttributes redirectAttributes, 
                                        HttpServletRequest request, 
                                     //   int categoryno,
                                        int reviewno
                                     /*   @RequestParam(value="word", defaultValue="") String word,
                                        @RequestParam(value="nowPage", defaultValue="1") int nowPage */
      ) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/review/delete_message"); // /webapp/review/delete_message.jsp

    String upDir = Tool.getRealPath(request, "/review/storage"); // ���� ���� ����
                                                                   // ���

   ReviewVO reviewVO = reviewProc.read(reviewno); // ������ ���� ������ �б� ����
                                                           // ����

    String thumbs_old = reviewVO.getThumbs();
    String files_old = reviewVO.getFiles();

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

    int count = reviewProc.delete(reviewno); // ���ڵ� ����
    System.out.println("���� count :" + count);

 /*    if (count == 1) {
     reviewProc.decreaseCnt(categoryno);    // ��ϵ� �ۼ��� ����


      // 4���� ���ڵ尡 �ϳ��� �������ΰ�� 5��° ���ڵ尡 �����Ǹ� ����������
      // 2���������� 1 �������� �ٿ����մϴ�. 
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("categoryno", categoryno); // #{categoryno}
      hashMap.put("word", word);                  // #{word}
      if (contentsProc.search_count(hashMap) % Contents.RECORD_PER_PAGE == 0){ 
        nowPage = nowPage - 1;
        if (nowPage < 1){
          nowPage = 1;
        }
      }
      
    }*/

    // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("contentsno", reviewVO.getReviewno());
    redirectAttributes.addAttribute("categoryno", reviewVO.getCategoryno());


    mav.setViewName("redirect:/review/delete_message.jsp");

    return mav;
  }
  
  /**
   * ��ȸ
   * 
   * @param reviewno
   * @return
   */
  @RequestMapping(value = "/review/read.do", method = RequestMethod.GET)
  public ModelAndView read(int reviewno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/review/read"); // /webapp/contents/read.jsp

    ReviewVO reviewVO = reviewProc.read(reviewno);
    mav.addObject("reviewVO", reviewVO);

  //  Categrp_CategoryVO categoryVO = categoryProc.read(contentsVO.getCategoryno()); // ī�װ�
                                                                                   // ����
                                                                                   // ����
//    mav.addObject("categoryVO", categoryVO);

    ArrayList<FileVO> file_list = reviewProc.getThumbs(reviewVO);

    mav.addObject("file_list", file_list);

    return mav;

  }
  
  
  /**
   * ���� ���� ���� �� http://localhost:9090/contents/contents/update.do
   * 
   * @param contentsno
   * @return
   */
  @RequestMapping(value = "/review/update.do", method = RequestMethod.GET)
  public ModelAndView update(int reviewno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/review/update"); // /webapp/contents/update.jsp

    ReviewVO reviewVO = reviewProc.read(reviewno);
    mav.addObject("reviewVO", reviewVO);

 /*   Categrp_CategoryVO categoryVO = categoryProc.read(contentsVO.getCategoryno()); // ī�װ�
                                                                                   // ����
                                                                                   // ����
    mav.addObject("categoryVO", categoryVO);*/

    ArrayList<FileVO> file_list = reviewProc.getThumbs(reviewVO);

    mav.addObject("file_list", file_list);

    return mav;
  }

  /**
   * - �۸� �����ϴ� ����� ���� - ���ϸ� �����ϴ� ����� ���� - �۰� ������ ���ÿ� �����ϴ� ����� ����
   * 
   * @param request
   * @param contentsVO
   * @return
   */
  @RequestMapping(value = "/review/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, ReviewVO reviewVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/review/storage");
    List<MultipartFile> filesMF = reviewVO.getFilesMF(); // Spring�� File ��ü��
                                                           // �����ص�.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // �÷��� ������ ���ϸ�
    String files_item = ""; // �ϳ��� ���ϸ�
    String filesize = "";
    long sizes_item = 0; // �ϳ��� ���� ������
    String thumbs = ""; // Thumb ���ϵ�
    String thumbs_item = ""; // �ϳ��� Thumb ���ϸ�

    int count = filesMF.size(); // ���ε�� ���� ����
    System.out.println("���ε�� ���� ���� : " +count);

    // ������ ��� ���� ��ȸ
    ReviewVO reviewVO_old = reviewProc.read(reviewVO.getReviewno());
    if (filesMF.get(0).getSize() > 0) { // ���ο� ������ ��������� ������ ��ϵ� ���� ��� ����
      // thumbs ���� ����
      String thumbs_old = reviewVO_old.getThumbs();
      StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname);
      }

      // ���� ���� ����
      String files_old = reviewVO_old.getFiles();
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
            filesize = filesize + "/" + sizes_item;
            // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
                                                    // ũ�� üũ
            files = files_item; // file1.jpg
            filesize = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        }
      } // for END
      // --------------------------------------------
      // ���ο� ������ ��� ����
      // --------------------------------------------

    } else { // �۸� �����ϴ� ���, ������ ���� ���� ����
      files = reviewVO_old.getFiles();
      filesize = reviewVO_old.getFilesize();
      thumbs = reviewVO_old.getThumbs();
    }
    reviewVO.setFiles(files);
    reviewVO.setFilesize(filesize);
    reviewVO.setThumbs(thumbs);

    reviewVO.setMemberno(1); // ȸ�� ������ session���� ����

    count = reviewProc.update(reviewVO);

    System.out.println("������ count: " +count);
    
    redirectAttributes.addAttribute("count", count);

    // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
    redirectAttributes.addAttribute("reviewno", reviewVO.getReviewno());
    redirectAttributes.addAttribute("categoryno", reviewVO.getCategoryno());

    mav.setViewName("redirect:/review/update_message.jsp");

    return mav;

  }
  
  
}


