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
   * 등록 폼 
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
   * 등록 처리
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
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/beauty/storage");
    List<MultipartFile> filesMF = beautyVO.getFilesMF(); // Spring이 File 객체를
                                                           // 저장해둠.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // 컬럼에 저장할 파일명
    String files_item = ""; // 하나의 파일명
    String sizes = "";
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
            sizes = sizes + "/" + sizes_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
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
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    count = beautyProc.create(beautyVO);
    
    mav.setViewName(
        "redirect:/beauty/create_message.jsp?count=" + count + "&categoryno=" + beautyVO.getCategoryno()+"&managerno="+beautyVO.getManagerno()); // /webapp/contents/create_message.jsp

    return mav;
  }
  
  /**
   * 전체 목록
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
   * 조회
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

/*    Categrp_CategoryVO categoryVO = categoryProc.read(contentsVO.getCategoryno()); // 카테고리
                                                                                   // 정보
                                                                                   // 추출
    mav.addObject("categoryVO", categoryVO);*/

    ArrayList<BeaFileVO> file_list = beautyProc.getThumbs(beautyVO);

    mav.addObject("file_list", file_list);
    
    BeautyVO count = beautyProc.read(styleno); 
    if (count != null) {
      beautyProc.increaseCnt(styleno); // 조회 수 증가 
    }
    return mav;
  }

  
  /**
   * 삭제 폼 http://localhost:9090/ahr/beauty/delete.do
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
 * 삭제 처리
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
    
    String upDir = Tool.getRealPath(request, "/beauty/storage"); // 저장 폴더 절대
                                                                   // 경로

    BeautyVO beautyVO = beautyProc.read(styleno); // 삭제할 파일 정보를 읽기 위한
                                                            // 목적
    BeautyVO categoryVO = beautyProc.read(categoryno);
    
    String thumbs_old = beautyVO.getThumb();
    String files_old = beautyVO.getImage();

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

    int count = beautyProc.delete(styleno); // 레코드 삭제

    if(count == 1){ 
      msgs.add("글 삭제에 성공했습니다.");
    } else if(count == 0) { 
      msgs.add("글 삭제에 실패했습니다.");
      msgs.add("다시한번 시도해주세요.");
    }
     
    json.put("msgs", msgs);
    
    /*if (count == 1) {
      categoryProc.decreaseCnt(categoryno); // 등록된 글수의 감소
      */
      // 4개의 레코드가 하나의 페이지인경우 5번째 레코드가 삭제되면 페이지수도
      // 2페이지에서 1 페이지로 줄여야합니다. 
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

    // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
    // 이제 연동할때 카테고리no에 값 받아오니까 수정하기.
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
  * 수정폼
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
                                                                                   // 정보
                                                                                   // 추출
    mav.addObject("categoryVO", categoryVO);

    ArrayList<BeaFileVO> file_list = beautyProc.getThumbs(beautyVO);

    mav.addObject("file_list", file_list);

    return mav;
  }
  /**
   * 수정처리
   * @param redirectAttributes
   * @param request
   * @param contentsVO
   * @return
   */
  @RequestMapping(value = "/beauty/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, BeautyVO beautyVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/beauty/storage");
    List<MultipartFile> filesMF = beautyVO.getFilesMF(); // Spring이 File 객체를
                                                           // 저장해둠.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // 컬럼에 저장할 파일명
    String files_item = ""; // 하나의 파일명
    String sizes = "";
    long sizes_item = 0; // 하나의 파일 사이즈
    String thumbs = ""; // Thumb 파일들
    String thumbs_item = ""; // 하나의 Thumb 파일명

    int count = filesMF.size(); // 업로드된 파일 갯수

    // 기존의 등록 정보 조회
    BeautyVO beautyVO_old = beautyProc.read(beautyVO.getStyleno());
    if (filesMF.get(0).getSize() > 0) { // 새로운 파일을 등록함으로 기존에 등록된 파일 목록 삭제
      // thumbs 파일 삭제
      String thumbs_old = beautyVO_old.getThumb();
      StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname);
      }

      // 원본 파일 삭제
      String files_old = beautyVO_old.getImage();
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
            sizes = sizes + "/" + sizes_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
            files = files_item; // file1.jpg
            sizes = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        }
      } // for END
      // --------------------------------------------
      // 새로운 파일의 등록 종료
      // --------------------------------------------

    } else { // 글만 수정하는 경우, 기존의 파일 정보 재사용
      files = beautyVO_old.getImage();
      sizes = beautyVO_old.getSizes();
      thumbs = beautyVO_old.getThumb();
    }
    beautyVO.setImage(files);
    beautyVO.setSizes(sizes);
    beautyVO.setThumb(thumbs);

    //beautyVO.setMno(1); // 회원 개발후 session으로 변경

    count = beautyProc.update(beautyVO);

    redirectAttributes.addAttribute("count", count);

    // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
    redirectAttributes.addAttribute("styleno", beautyVO.getStyleno());
    redirectAttributes.addAttribute("categoryno", beautyVO.getCategoryno());

    mav.setViewName("redirect:/beauty/update_message.jsp");

    return mav;

  }
  /**
   * 검색 목록
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

    // 검색 기능 추가, webapp/contents/list_by_category_search.jsp
    mav.setViewName("/contents/list_by_category_search");

    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("categoryno", categoryno); // #{categoryno}
    hashMap.put("word", word); // #{word}

    // System.out.println("categoryno: " + categoryno);
    // System.out.println("word_find: " + word_find);

    // 검색 목록
    List<ContentsVO> list = contentsProc.list_by_category_search(hashMap);
    mav.addObject("list", list);

    // 검색된 레코드 갯수
    int search_count = contentsProc.search_count(hashMap);
    mav.addObject("search_count", search_count);

    Categrp_CategoryVO categoryVO = categoryProc.read(categoryno);
    mav.addObject("categoryVO", categoryVO);

    // mav.addObject("word", word);

    return mav;
  }*/
  /**
   * 목록 + 검색 + 페이징 지원
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
    
    // 검색 기능 추가,  /webapp/beauty/search_paging.jsp
    mav.setViewName("/beauty/search_paging");   
    
    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("categoryno", categoryno); // #{categoryno}
    hashMap.put("title", title);                  // #{title}
    hashMap.put("nowPage", nowPage);       
    
    // 검색 목록
    List<BeautyVO> list = beautyProc.search_paging(hashMap);
    mav.addObject("list", list);
    
    // 검색된 레코드 갯수
    int search_count = beautyProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
  
    BeautyVO categoryVO = beautyProc.read(categoryno);
    mav.addObject("categoryVO", categoryVO);
    
    // mav.addObject("word", word);
  
    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
     * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
     *
     * @param categoryno 카테고리번호 
     * @param search_count 검색(전체) 레코드수 
     * @param nowPage     현재 페이지
     * @param word 검색어
     * @return 페이징 생성 문자열
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
      msgs.add("성공");
    } else if(count == null) { 
      msgs.add("실패");
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
      msgs.add("성공");
    } else if(count == null) { 
      msgs.add("실패");
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
      msgs.add("성공");
    } else if(count == null) { 
      msgs.add("실패");
    }
    json.put("msgs", msgs);
    //mav.setViewName("/beauty/list_all_rdate"); // /webapp/contents/list_all_category.jsp
    
    return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
  }
}