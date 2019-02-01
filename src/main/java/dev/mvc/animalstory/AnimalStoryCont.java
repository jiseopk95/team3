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
   * 글 등록 폼
   * http://localhost:9090/ahr/animalstory/create.do?managerno=1
   * @return
   */
  @RequestMapping(value = "/animalstory/create.do", method = RequestMethod.GET)
  public ModelAndView create(int managerno) {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    
    mav.addObject("managerno", managerno);
    mav.addObject("title", "애니멀스토리");
    
    
    mav.setViewName("/animalstory/create"); // /webapp/contents/create.jsp

    return mav;
  }
  
  /**
   * 등록처리
   * @param request
   * @param aniVO
   * @return
   */
  @RequestMapping(value = "/animalstory/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, AnimalStoryVO aniVO) {
    // System.out.println("--> create() POST executed");
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/animalstory/storage");
    List<MultipartFile> filesMF = aniVO.getFilesMF(); // Spring이 File 객체를
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
    aniVO.setFiles(files);
    aniVO.setSizes(sizes);
    aniVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    count = aniProc.create(aniVO);
    if (count == 1) {
      //aniProc.increaseCnt(contentsVO.getCategoryno()); // 글수 증가
      System.out.println("애니멀스토리 글 업로드 성공");
    } else {
      System.out.println("애니멀스토리 글 업로드 실패");
    }

    mav.setViewName(
        "redirect:/animalstory/create_message.jsp?count=" + count + "&managerno=" + aniVO.getManagerno()); // /webapp/contents/create_message.jsp

    // mav.setViewName("redirect:/contents/list_by_category_search_paging.do?categoryno="
    // + contentsVO.getCategoryno());
    // mav.setViewName("redirect:/contents/list_all_category.do");

    return mav;
  }
  
  /**
   * 전체 리스트 - 검색 가능
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
    
    // 검색 목록
    List<AnimalStoryVO> list = aniProc.list_by_search_paging(hashMap);
    
    // 검색된 레코드 갯수
    int search_count = aniProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
     * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
     *
     * @param managerno 카테고리번호 
     * @param nowPage     현재 페이지
     * @param petname 검색어
     * @param name 검색어
     * @return 페이징 생성 문자열
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
    mav.addObject("title", "애니멀 스토리");
    mav.addObject("managerno", managerno);
    mav.addObject("nowPage", nowPage);
    mav.setViewName("/animalstory/list");
    return mav;
  }
  
  /**
   * 고양이 / 강아지별 분류 리스트 - 검색 가능
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
    
   
    
    // 검색 목록
    List<AnimalStoryVO> list = aniProc.list_by_search_paging_anitype(hashMap);
    
    // 검색된 레코드 갯수
    int search_count = aniProc.search_count_anitype(hashMap);
    mav.addObject("search_count", search_count);
    
    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
     * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
     *
     * @param managerno 카테고리번호 
     * @param nowPage     현재 페이지
     * @param petname 검색어
     * @param name 검색어
     * @return 페이징 생성 문자열
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
      mav.addObject("title", "강아지 스토리");
    } else {
      mav.addObject("title", "고양이 스토리");
    }
    mav.addObject("anitype", anitype);
    mav.addObject("managerno", managerno);
    mav.addObject("nowPage", nowPage);
    return mav;
  }
  
  /**
   * 하나의 글 읽어오기 
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
   * 하나의 글삭제 폼
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
   * 하나의 글 삭제처리
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
    
    String upDir = Tool.getRealPath(request, "/animalstory/storage"); // 저장 폴더 절대
    // 경로
    
    AnimalStoryVO aniVO = aniProc.read(anino); // 삭제할 파일 정보를 읽기 위한
    // 목적
    
    String thumbs_old = aniVO.getThumbs();
    String files_old = aniVO.getFiles();
    
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
    
    int count = aniProc.delete(anino); // 레코드 삭제


    
    if(count==1) {
      json.put("msgs", "삭제를 성공했습니다.");
      System.out.println("파일 삭제 성공");
   // 4개의 레코드가 하나의 페이지인경우 5번째 레코드가 삭제되면 페이지수도
      // 2페이지에서 1 페이지로 줄여야합니다. 
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
      json.put("msgs", "삭제에 실패했습니다.");
      System.out.println("파일 삭제 실패");
    }
    
   /* redirectAttributes.addAttribute("petname", petname);
    redirectAttributes.addAttribute("name", name);
    redirectAttributes.addAttribute("nowPage", nowPage);*/
    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  /**
   * 하나의 글 수정 폼
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
    mav.addObject("title", "애니멀스토리 글 수정");
    mav.setViewName("/animalstory/update"); 
    return mav;
  }
  
  /**
   * 하나의 글 수정 처리
   * @param redirectAttributes
   * @param request
   * @param aniVO
   * @return
   */
  @RequestMapping(value = "/animalstory/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, AnimalStoryVO aniVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/animalstory/storage");
    List<MultipartFile> filesMF = aniVO.getFilesMF(); // Spring이 File 객체를
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
    AnimalStoryVO aniVO_old = aniProc.read(aniVO.getAnino());
    if (filesMF.get(0).getSize() > 0) { // 새로운 파일을 등록함으로 기존에 등록된 파일 목록 삭제
      // thumbs 파일 삭제
      String thumbs_old = aniVO_old.getThumbs();
      StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname);
      }

      // 원본 파일 삭제
      String files_old = aniVO_old.getFiles();
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
   * index페이지 작은 애니멀스토리
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
      index_list.add(url.toString()); // 출력 목록에 하나의 category 추가 
      
      url.delete(0, url.toString().length()); // StringBuffer 문자열 삭제
    }
    mav.addObject("index_list", index_list);
    mav.setViewName("/animalstory/index_animal");
    
    return mav;
  }
  
  
  /**
   * 이벤트 추첨 - 폼 : 그냥 전송, 번호 확인겸
   * http://localhost:9090/ahr/animalstory/event.do?eventno=1
   * @param anino
   * @return
   */
  @RequestMapping(value = "/animalstory/event.do", method = RequestMethod.GET)
  public ModelAndView eventuser(int eventno) {
    ModelAndView mav = new ModelAndView();

    List<EventVO> list = aniProc.event(eventno);

    mav.addObject("eventno", eventno);
    mav.addObject("list", list);

    mav.setViewName("/animalstory/event"); // /webapp/contents/read.jsp
    return mav;
  }

  

  /**
   * 이벤트 추첨 - 기능
   * @param eventVO
   * @return
   */

  @RequestMapping(value = "/animalstory/event_proc.do", method = RequestMethod.POST)
  public ModelAndView eventuser_proc(EventVO eventVO) {
    ModelAndView mav = new ModelAndView();
    List<EventVO> list = null;
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   int count = 0;
   int[] winner_num = new int[3]; // 당첨자 번호를 담을 배열

    list = aniProc.event(eventVO.getEvnetno()); // 참여자 리스트

    // 3명만 뽑음
    for(int i = 0; i < 3; i++) {
    // 0~ 참여자리스트 길이 중 랜덤수를 뽑아서 당첨자번호 배열에 할당
    // 랜덤수를 0부터 뽑는 이유 
    //  : 단순히 번호를 뽑는게 아니라 이 랜덤수를 리스트의 인덱스로 사용할 것이기 때문임.
      winner_num[i] = (int)(Math.random() * list.size()); 

      // 중복 제거 구간
      // : 만약 winner_num의 i번째와 j번째가 같다면 i를 하나 낮추고 i j for문을 멈춰라
      // -> 상위 for문의 i의 수를 하나 낮춰서 그 배열 요소의 랜덤수를 다시 뽑아라
      for(int j = 0; j < i; j++) {
        if(winner_num[i] == winner_num[j]) {
          i--;
          break;
        }
      }
    }

    // 아래 for문은 확인을 위한것. 작동에는 필요 없는 요소
    for(int i = 0; i < list.size(); i++) {
      System.out.println("list의 " +i+ "번째 멤버번호 : " + list.get(i).getMemberno()); // 리스트의 멤버번호 확인
    }

    hashMap.put("eventno", eventVO.getEvnetno()); // 이벤트번호는 받아온걸로 고정 

    // 당첨번호 배열의 길이만큼 돌린다
    for(int i = 0; i < winner_num.length; i++) {
      System.out.println("winner_num : " + winner_num[i]); // 당첨번호

      // list.get(winner_num[i]).getMemberno() 
      // : list의 winner_num[i]번째 인덱스의 요소인 VO에서 memberno를 가져와라
      hashMap.put("memberno", list.get(winner_num[i]).getMemberno()); // 당첨자 번호를 할당함.
      System.out.println("winner memberno : " + list.get(winner_num[i]).getMemberno()); // 당첨된 멤버번호 확인

      // 당첨번호 배열의 길이만큼 update문 실행(3번으로 지정돼있음) 
      // - 주의점 : 중복참여 불가 전제조건 필수
      count = aniProc.win(hashMap);
    }

    
    mav.addObject("count", count);
    mav.addObject("eventno", eventVO.getEvnetno());
    mav.setViewName("/animalstory/result"); // /webapp/contents/read.jsp
    return mav;
  }
 
}
