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
  //뷰티의 카테고리번호,관리자번호->관리자번호, 선물번호
  /**
   * 등록 폼 
   * 
   * @return
   */
  @RequestMapping(value = "/event/create.do", method = RequestMethod.GET)
  public ModelAndView create(int managerno) {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();
    // 만약 내가 선물하나에 이벤트하나로 연결해서 
    // 폼뜰때 이미 선택한 선물은 나오게 하고 싶지 않다 하면 선물별로 구별이 필요하므로 
    // 선물번호가 필요하지만, 모든 선물이 그냥 셀렉트박스안에 띄워지게 하고싶은거니까
    // , 전체 선물 내용을 불러오는 거니까 선물번호를 불러올 필요가 없음. 구별하지않고 그냥 불러오는 거니까.
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
   * 등록 처리
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
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/event/storage");
    List<MultipartFile> filesMF = eventVO.getFilesMF(); // Spring이 File 객체를
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
    eventVO.setImage(files);
    eventVO.setImage_size(sizes);
    eventVO.setThumb(thumbs);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    count = eventProc.create(eventVO);
    
    mav.setViewName(
        "redirect:/event/create_message.jsp?count=" + count + "&managerno="+eventVO.getManagerno()); // /webapp/contents/create_message.jsp

    return mav;
  }
  
  /**
   * 전체 목록
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
   * 조회
   * 
   * @param eventno 관리자 조회유 
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
      eventProc.increaseCnt(eventno); // 조회 수 증가 
    }*/
    return mav;
  }
  /**
   * 조회
   * 
   * @param eventno 멤버 조회유 
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
      eventProc.increaseCnt(eventno); // 조회 수 증가 
    }*/
    return mav;
  }
  
  /**
   * 수정폼
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
     mav.addObject("eventVO", eventVO);             // 정보 추출

     ArrayList<EventFileVO> file_list = eventProc.getThumbs(eventVO);

     mav.addObject("file_list", file_list);

     mav.addObject("managerno",managerno);
     return mav;
   }
   /**
    * 수정처리
    * @param redirectAttributes
    * @param request
    * @param contentsVO
    * @return
    */
   @RequestMapping(value = "/event/update.do", method = RequestMethod.POST)
   public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, EventVO eventVO) {
     ModelAndView mav = new ModelAndView();

     // -------------------------------------------------------------------
     // 파일 전송 코드 시작
     // -------------------------------------------------------------------
     String upDir = Tool.getRealPath(request, "/event/storage");
     List<MultipartFile> filesMF = eventVO.getFilesMF(); // Spring이 File 객체를
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
     EventVO eventVO_old = eventProc.read(eventVO.getEventno());
     if (filesMF.get(0).getSize() > 0) { // 새로운 파일을 등록함으로 기존에 등록된 파일 목록 삭제
       // thumbs 파일 삭제
       String thumbs_old = eventVO_old.getThumb();
       StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
       while (thumbs_st.hasMoreTokens()) {
         String fname = upDir + thumbs_st.nextToken();
         Tool.deleteFile(fname);
       }

       // 원본 파일 삭제
       String files_old = eventVO_old.getImage();
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
       files = eventVO_old.getImage();
       sizes = eventVO_old.getImage_size();
       thumbs = eventVO_old.getThumb();
     }
     eventVO.setImage(files);
     eventVO.setImage_size(sizes);
     eventVO.setThumb(thumbs);

     //beautyVO.setMno(1); // 회원 개발후 session으로 변경

     count = eventProc.update(eventVO);

     redirectAttributes.addAttribute("count", count);

   
     // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
     redirectAttributes.addAttribute("eventno", eventVO.getEventno());
     redirectAttributes.addAttribute("managerno", eventVO.getManagerno());
     redirectAttributes.addAttribute("presentno", eventVO.getPresentno());

     mav.setViewName("redirect:/event/update_message.jsp");

     return mav;

   }
   /**
    * 목록 + 검색 + 페이징 지원/ 관리자
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
     
     // 검색 기능 추가,  /webapp/beauty/search_paging.jsp
     mav.setViewName("/event/search_paging");   
     
     // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
     HashMap<String, Object> hashMap = new HashMap<String, Object>();
     hashMap.put("managerno", managerno);     
     hashMap.put("content", content);                  // #{title}
     hashMap.put("nowPage", nowPage);       
     
     // 검색 목록
     List<event_managerVO> list = eventProc.search_paging(hashMap);
     mav.addObject("list", list);
     
     // 검색된 레코드 갯수
     int search_count = eventProc.search_count(hashMap);
     mav.addObject("search_count", search_count);
     
     mav.addObject("managerno",managerno);
   
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
     String paging = eventProc.paging(managerno,search_count, nowPage, content);
     mav.addObject("paging", paging);
   
     mav.addObject("nowPage", nowPage);
     
     return mav; 
   }
   
   /**
    * 목록 + 검색 + 페이징 지원/회원
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
     
     // 검색 기능 추가,  /webapp/beauty/search_paging.jsp
     mav.setViewName("/event/search_paging_member");   
     
     // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
     HashMap<String, Object> hashMap = new HashMap<String, Object>();
     hashMap.put("memberno", memberno);       
     hashMap.put("content", content);                  // #{title}
     hashMap.put("nowPage", nowPage);       
     
     //이거는 당첨자확인버튼 눌렀을때 아직 결과가 안나왔을시 win값 
     //이벤트별로 널체크해서 하려고 했던 건데 eventno를 굳이 받을 필요가 없는데.
     //일단 보류 ... 
     //win볼거 추가
     /*event_eventuserVO event_eventuserVO = eventProc.read_win(eventno); 
     mav.addObject("event_eventuserVO",event_eventuserVO);*/
     
     // 검색 목록
     List<EventVO> list = eventProc.search_paging_member(hashMap);
     mav.addObject("list", list);
     
     
     
     // 검색된 레코드 갯수
     int search_count = eventProc.search_count(hashMap);
     mav.addObject("search_count", search_count);
     
     mav.addObject("memberno",memberno);
   
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
    * 삭제 폼 http://localhost:9090/ahr/beauty/delete.do
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
    * 삭제 처리
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
       
       String upDir = Tool.getRealPath(request, "/event/storage"); // 저장 폴더 절대
                                                                      // 경로

       EventVO eventVO = eventProc.read(eventno); // 삭제할 파일 정보를 읽기 위한
                                                               // 목적
       
       String thumbs_old = eventVO.getThumb();
       String files_old = eventVO.getImage();

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

       int count = eventProc.delete(eventno); // 레코드 삭제

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
         hashMap.put("title", title);                  // #{word}
         if (eventProc.search_count(hashMap) % Event.RECORD_PER_PAGE == 0){ 
           nowPage = nowPage - 1;
           if (nowPage < 1){
             nowPage = 1;
           }
         }
         
       //}

       // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
       // 이제 연동할때 카테고리no에 값 받아오니까 수정하기.
       redirectAttributes.addAttribute("count", count); // 1 or 0
       redirectAttributes.addAttribute("eventno", eventVO.getEventno());
       //redirectAttributes.addAttribute("word", word);
       redirectAttributes.addAttribute("title", title);
       redirectAttributes.addAttribute("nowPage", nowPage);

       //mav.setViewName("redirect:/beauty/delete_message.jsp");

       return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
     }
   
     
   //----------------------------------ajax시도(회원이 응모하기 버튼누를시 중복검사후 등록)-------------------------------//
  
   
   //read에서 응모하기 버튼 누를시 연결되는 controller  여기서 이제 참여자테이블에 값이 등록 되도록.
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
     int count2 = eventProc.select_user(hashMap); // 쿼리문으로 갯수 검사!
     
/*     List<member_userVO> member_userVO = userProc.list_member(memberno);
     json.put("member_userVO",member_userVO);
    */
     
   // 2개이상전달할때 hashmap

     
     /*if(($('#memberno').val()==$('#userno').val())&&($('#eventno').val()==$('#userevent').val())){
       alert("이미 응모한 이벤트 입니다!");
     } */
     if(count2 == 0){ // 등록이 안되어있으면
       System.out.println("count2 == 0");
       int count = userProc.usercreate(hashMap);  
       if(count==1) { 
       msgs.add("응모완료.");
       System.out.println("등록성공");
       }
     } else if(count2 > 0) { // count2 가 0보다 크다 => 즉, 참여한 기록있다. 
       System.out.println("이미 참여함");
       msgs.add("응모에 실패했습니다.\n");
       msgs.add("다시한번 시도해주세요.");
     }
      
     json.put("msgs", msgs);
     
     return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
   
}
   
   
   
   //---------------------------------------------------------------------------------------------------------------------------//
   
   
   /**
    * 당첨자확인 전체 목록_페이징검색 부분
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
     
     // 검색 기능 추가,  /webapp/beauty/search_paging.jsp
     mav.setViewName("/event/search_paging_win");   
     
     // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
     HashMap<String, Object> hashMap = new HashMap<String, Object>();
     hashMap.put("eventno", eventno); // #{categoryno}
     hashMap.put("id", id);                  // #{title}
     hashMap.put("nowPage", nowPage);       
     
     // 검색 목록
     List<event_eventuserVO> list = eventProc.search_paging_win(hashMap);
     mav.addObject("list", list);
     
     // 검색된 레코드 갯수
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
      * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
      * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
      *
      * @param categoryno 카테고리번호 
      * @param search_count 검색(전체) 레코드수 
      * @param nowPage     현재 페이지
      * @param word 검색어
      * @return 페이징 생성 문자열
      */ 
     String paging = eventProc.paging_win(eventno, search_count, nowPage, id);
     mav.addObject("paging", paging);
   
     mav.addObject("nowPage", nowPage);
     
     return mav;
   }
   
   
   
   
   //관리자에서 마감 누르면 올 곳. 마감일전에 마감해야할수도 있으니까. 사실 날짜가 되면 실행되게하는걸 모르겠다 하하하
   //관리자가 마감안했는데 회원이 당첨확인버튼 누르면 그거 제어해두면 alert로 관리자 확인중이라고 하면 될듯.
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
     // 참여자수 세기
     int count = userProc.user_cnt(eventno);
     // 참여자 수 만큼 반복->난수생성해라
/*     Random random = new Random();*/
     int[] event_win = new int[3]; // 3개를 넣어야 뽑힌 3개의 난수를 넣어줄수있을듯.
        List<member_userVO> user = null;
        user = userProc.list_all_eventuserno(eventno);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("eventno", eventno);
         //user = userProc.list_all_eventuserno();
    
     //int num = 0;     
        int count3 = userProc.cnt_win(eventno);
        if(count3==0){
     //**********************************************랜덤부분***************************************************************//
     for(int i = 0; i< 3; i++){
       event_win[i] = (int)(Math.random()*user.size());  //근데 여기 그냥 event_win의(user였나.) 사이즈로 or length로 하면 1붙일필요없댔당.
       // 총 지원자수가 10명이면 1부터 10까지 중 3개의 난수를 뽑아라.
       // >>당첨자가 3명임. 
       // 난수가 들어가야할곳 eventuserno
       // win은 항상 1로 
       // 중복제거하려고 넣은 코드
       for(int j=0; j<i; j++){
         if(event_win[i]==event_win[j]){
           i--;
/*System.out.println("event_win번호:"+event_win[i]);*/
           break; 
           } 
       }
     }
       for(int k = 0; k < 3; k++){ 
         hashMap.put("memberno", user.get(event_win[k]).getMemberno());
       int count2 = userProc.winner(hashMap);
       json.put("count2", count2);
       if(count2 == 1){
         json.put("msgs", "마감되었습니다.\n");
       }else{
         json.put("msgs", "마감실패.\n");
       }
   
       }
        }else{
          json.put("msgs", "이미 마감된 이벤트입니다.\n");
        }
       //만약 win이 있으면 마감하기 버튼누르면 이미 마감했습니다. (0이면 위에꺼 돌리고 1이상이면 alert뜨게하기.)
     
    /*   HashMap<String, Object> hashMap = new HashMap<String, Object>();
       hashMap.put("eventno", eventno);*/
       //hashMap.put("win", event_win[i]);
       
       //userProc.winner(hashMap); // 참여자테이블에 위 hashmap 받는거 
     //******************************************************************************************************************//
     
     
    // 이제 그 배열 값을 win에 update로 값넣기
    // int count2 = userProc.update(userVO);
     
       //mav.setViewName("redirect:/event/search_paging_member.do?memberno="+memberno); // /webapp/categrp/list.jsp
    
     return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
   
}
}