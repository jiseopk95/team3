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
   * 등록 폼 http://localhost:9090/ahr/review /create.do
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
   * 등록 처리
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
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/review/storage");
    List<MultipartFile> filesMF = reviewVO.getFilesMF(); // Spring이 File 객체를
                                                            // 저장해둠.
   
    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // 컬럼에 저장할 파일명
    String files_item = ""; // 하나의 파일명
    String filesize = "";
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
            filesize = filesize + "/" + sizes_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
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
    // 파일 전송 코드 종료
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
   * 전체 목록
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
 * 삭제폼
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

  // /contents/delete.do  <-- defaultValue 사용됨
  // /contents/delete.do?word=&nowPage=  <-- defaultValue 사용되지 않음, ERROR 발생.
  // /contents/delete.do?word=&nowPage=1  <-- 문자열은 생략 가능, nowPage는 필수.
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

    String upDir = Tool.getRealPath(request, "/review/storage"); // 저장 폴더 절대
                                                                   // 경로

   ReviewVO reviewVO = reviewProc.read(reviewno); // 삭제할 파일 정보를 읽기 위한
                                                           // 목적

    String thumbs_old = reviewVO.getThumbs();
    String files_old = reviewVO.getFiles();

    StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/"); // Thumbs
    while (thumbs_st.hasMoreTokens()) { // 단어가 있는지 검사
      String fname = upDir + thumbs_st.nextToken(); // 단어 추출
      Tool.deleteFile(fname);
    }

    StringTokenizer files_st = new StringTokenizer(files_old, "/"); // files
    while (files_st.hasMoreTokens()) { // 단어가 있는지 검사
      String fname = upDir + files_st.nextToken(); // 단어 추출
      Tool.deleteFile(fname);
    }

    int count = reviewProc.delete(reviewno); // 레코드 삭제
    System.out.println("삭제 count :" + count);

 /*    if (count == 1) {
     reviewProc.decreaseCnt(categoryno);    // 등록된 글수의 감소


      // 4개의 레코드가 하나의 페이지인경우 5번째 레코드가 삭제되면 페이지수도
      // 2페이지에서 1 페이지로 줄여야합니다. 
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

    // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("contentsno", reviewVO.getReviewno());
    redirectAttributes.addAttribute("categoryno", reviewVO.getCategoryno());


    mav.setViewName("redirect:/review/delete_message.jsp");

    return mav;
  }
  
  /**
   * 조회
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

  //  Categrp_CategoryVO categoryVO = categoryProc.read(contentsVO.getCategoryno()); // 카테고리
                                                                                   // 정보
                                                                                   // 추출
//    mav.addObject("categoryVO", categoryVO);

    ArrayList<FileVO> file_list = reviewProc.getThumbs(reviewVO);

    mav.addObject("file_list", file_list);

    return mav;

  }
  
  
  /**
   * 다중 파일 수정 폼 http://localhost:9090/contents/contents/update.do
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

 /*   Categrp_CategoryVO categoryVO = categoryProc.read(contentsVO.getCategoryno()); // 카테고리
                                                                                   // 정보
                                                                                   // 추출
    mav.addObject("categoryVO", categoryVO);*/

    ArrayList<FileVO> file_list = reviewProc.getThumbs(reviewVO);

    mav.addObject("file_list", file_list);

    return mav;
  }

  /**
   * - 글만 수정하는 경우의 구현 - 파일만 수정하는 경우의 구현 - 글과 파일을 동시에 수정하는 경우의 구현
   * 
   * @param request
   * @param contentsVO
   * @return
   */
  @RequestMapping(value = "/review/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, ReviewVO reviewVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/review/storage");
    List<MultipartFile> filesMF = reviewVO.getFilesMF(); // Spring이 File 객체를
                                                           // 저장해둠.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // 컬럼에 저장할 파일명
    String files_item = ""; // 하나의 파일명
    String filesize = "";
    long sizes_item = 0; // 하나의 파일 사이즈
    String thumbs = ""; // Thumb 파일들
    String thumbs_item = ""; // 하나의 Thumb 파일명

    int count = filesMF.size(); // 업로드된 파일 갯수
    System.out.println("업로드된 파일 갯수 : " +count);

    // 기존의 등록 정보 조회
    ReviewVO reviewVO_old = reviewProc.read(reviewVO.getReviewno());
    if (filesMF.get(0).getSize() > 0) { // 새로운 파일을 등록함으로 기존에 등록된 파일 목록 삭제
      // thumbs 파일 삭제
      String thumbs_old = reviewVO_old.getThumbs();
      StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname);
      }

      // 원본 파일 삭제
      String files_old = reviewVO_old.getFiles();
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
            filesize = filesize + "/" + sizes_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
            files = files_item; // file1.jpg
            filesize = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        }
      } // for END
      // --------------------------------------------
      // 새로운 파일의 등록 종료
      // --------------------------------------------

    } else { // 글만 수정하는 경우, 기존의 파일 정보 재사용
      files = reviewVO_old.getFiles();
      filesize = reviewVO_old.getFilesize();
      thumbs = reviewVO_old.getThumbs();
    }
    reviewVO.setFiles(files);
    reviewVO.setFilesize(filesize);
    reviewVO.setThumbs(thumbs);

    reviewVO.setMemberno(1); // 회원 개발후 session으로 변경

    count = reviewProc.update(reviewVO);

    System.out.println("마지막 count: " +count);
    
    redirectAttributes.addAttribute("count", count);

    // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
    redirectAttributes.addAttribute("reviewno", reviewVO.getReviewno());
    redirectAttributes.addAttribute("categoryno", reviewVO.getCategoryno());

    mav.setViewName("redirect:/review/update_message.jsp");

    return mav;

  }
  
  
}


