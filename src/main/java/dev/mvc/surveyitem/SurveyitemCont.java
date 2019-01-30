package dev.mvc.surveyitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

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

import dev.mvc.survey.Manager_SurveyVO;
import dev.mvc.survey.SurveyProcInter;
import dev.mvc.survey.SurveyVO;
import dev.mvc.surveyparty.SurveypartyProcInter;
import dev.mvc.surveyparty.SurveypartyVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class SurveyitemCont {
  @Autowired
  @Qualifier("dev.mvc.surveyitem.SurveyitemProc")
  private SurveyitemProcInter surveyitemProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.survey.SurveyProc")
  private SurveyProcInter surveyProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.surveyparty.SurveypartyProc")
  private SurveypartyProcInter surveypartyProc = null;
  
  public SurveyitemCont(){
    System.out.println("-->  SurveyitemCont created");
  }
//http://localhost:9090/ahr/surveyitem/create.do
  @RequestMapping(value = "/surveyitem/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/surveyitem/create");
    return mav;
  }
  
  @RequestMapping(value="/surveyitem/create.do", method=RequestMethod.POST)
  public ModelAndView create(RedirectAttributes redirectAttributes, HttpServletRequest request,SurveyitemVO surveyitemVO) {
    ModelAndView mav = new ModelAndView();
    
 // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/surveyitem/storage");
    List<MultipartFile> filesMF = surveyitemVO.getFilesMF(); // Spring이 File 객체를
                                                           // 저장해둠.



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
  /*      System.out.println("multipartFile.getName(): " + multipartFile.getName());
*/
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
    surveyitemVO.setFiles(files);
    surveyitemVO.setSizes(sizes);
    surveyitemVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    
    
    count = surveyitemProc.create(surveyitemVO);
    
    if (count == 1) {
      surveyProc.increaseCnt(surveyitemVO.getSurveyno());
    }
    redirectAttributes.addAttribute("count", count); 
    redirectAttributes.addAttribute("surveyno", surveyitemVO.getSurveyno()); 
    

    mav.setViewName("redirect:/surveyitem/create_message.jsp");
    /*mav.setViewName("redirect:/surveyitem/create_message.jsp?count=" + count+"&"+surveyno);*/


    return mav;
  }
  //http://localhost:9090/ahr/surveyitem/list.do?surveyno=1
  @RequestMapping(value = "/surveyitem/list.do", method = RequestMethod.GET)
  public ModelAndView list(int surveyno) {
    ModelAndView mav = new ModelAndView();
    Manager_SurveyVO surveyVO =surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);
    List<Survey_ItemVO> list = surveyitemProc.list(surveyno);
    mav.addObject("list", list);
    
    

    mav.setViewName("/surveyitem/list"); 

    return mav;
  }
  
/*  //http://localhost:9090/ahr/surveyitem/party_list.do?surveyno=1
  @RequestMapping(value = "/surveyitem/party_list.do", method = RequestMethod.GET)
  public ModelAndView party_list(int surveyno,int memberno) {
    ModelAndView mav = new ModelAndView();
    Manager_SurveyVO surveyVO =surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);
    List<Survey_ItemVO> list = surveyitemProc.list(surveyno);
    mav.addObject("list", list);
    mav.addObject("memberno", memberno);
    mav.setViewName("/surveyitem/party_list"); 

    return mav;
  }*/
  //http://localhost:9090/ahr/surveyitem/party_list.do?surveyno=1
  @RequestMapping(value = "/surveyitem/party_list.do", method = RequestMethod.GET)
  public ModelAndView party_list(int surveyno,int memberno) {
    ModelAndView mav = new ModelAndView();
    SurveyVO surveyVO =surveyProc.read_m(surveyno);
    System.out.println("surveyno:"+surveyVO.getSurveyno());
    mav.addObject("surveyVO", surveyVO);
    List<Survey_ItemVO> list = surveyitemProc.list(surveyno);
    mav.addObject("list", list);
    mav.addObject("memberno", memberno);
    mav.setViewName("/surveyitem/party_list"); 

    return mav;
  }
  
  //http://localhost:9090/ahr/surveyitem/party_list.do?surveyno=1
  @RequestMapping(value = "/surveyitem/party_bar.do", method = RequestMethod.GET)
  public ModelAndView party_bar(int surveyno) {
    ModelAndView mav = new ModelAndView();
    Manager_SurveyVO surveyVO =surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);
    List<Survey_ItemVO> list = surveyitemProc.list(surveyno);
    mav.addObject("list", list);
    
    

    mav.setViewName("/surveyitem/party_bar"); 

    return mav;
  }
  
  /**
   * 다중 파일 수정 폼 http://localhost:9090/contents/contents/update.do
   * 
   * @param contentsno
   * @return
   */
  //http://localhost:9090/ahr/surveyitem/update.do?surveyitemno=1&surveyno=10
  @RequestMapping(value = "/surveyitem/update.do", method = RequestMethod.GET)
  public ModelAndView update(int surveyitemno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/surveyitem/update"); // /webapp/contents/update.jsp

    Survey_ItemVO survey_itemVO = surveyitemProc.read(surveyitemno);
    mav.addObject("survey_itemVO",survey_itemVO);

    if(survey_itemVO.getThumbs() != null){
    ArrayList<FileVO> file_list = surveyitemProc.getThumbs(survey_itemVO);

    mav.addObject("file_list", file_list);
    }
    
    return mav;
  }
  
  /**
   * - 글만 수정하는 경우의 구현 - 파일만 수정하는 경우의 구현 - 글과 파일을 동시에 수정하는 경우의 구현
   * 
   * @param request
   * @param contentsVO
   * @return
   */
  @RequestMapping(value = "/surveyitem/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, SurveyitemVO surveyitemVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/surveyitem/storage");
    List<MultipartFile> filesMF = surveyitemVO.getFilesMF(); // Spring이 File 객체를
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
    Survey_ItemVO surveyitemVO_old = surveyitemProc.read(surveyitemVO.getSurveyitemno());
    if (filesMF.get(0).getSize() > 0) { // 새로운 파일을 등록함으로 기존에 등록된 파일 목록 삭제
      // thumbs 파일 삭제
      String thumbs_old = surveyitemVO_old.getThumbs();
      if(thumbs_old != null){
      StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
      
      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname);
      }
      }

      // 원본 파일 삭제
      String files_old = surveyitemVO_old.getFiles();
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
    /*    System.out.println("multipartFile.getName(): " + multipartFile.getName());*/

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
      files = surveyitemVO_old.getFiles();
      sizes = surveyitemVO_old.getSizes();
      thumbs = surveyitemVO_old.getThumbs();
    }
    surveyitemVO.setFiles(files);
    surveyitemVO.setSizes(sizes);
    surveyitemVO.setThumbs(thumbs);

  

    count = surveyitemProc.update(surveyitemVO);

    redirectAttributes.addAttribute("count", count);

    // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
    redirectAttributes.addAttribute("surveyitemno", surveyitemVO.getSurveyitemno());
    redirectAttributes.addAttribute("surveyno", surveyitemVO.getSurveyno());

    mav.setViewName("redirect:/surveyitem/update_message.jsp");

    return mav;

  }
  
  /**
   * 삭제 폼http://localhost:9090/ahr/surveyitem/delete_form.do?surveyitemno=1&surveyno=10
   * 
   * @param contentsno
   * @param categoryno
   * @return
   */
  @RequestMapping(value = "/surveyitem/delete_form.do", method = RequestMethod.GET)
  public ModelAndView delete_form(int surveyitemno) {
    // System.out.println("--> delete() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/surveyitem/delete_form"); 

    Survey_ItemVO survey_itemVO = surveyitemProc.read(surveyitemno);

    mav.addObject("survey_itemVO", survey_itemVO);

    return mav;
  }
  
  @RequestMapping(value = "/surveyitem/delete_form.do", method = RequestMethod.POST)
  public ModelAndView delete_form(RedirectAttributes redirectAttributes, 
                                        HttpServletRequest request, 
                                        int surveyitemno

      ) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/surveyitem/delete_message"); // /webapp/contents/delete_message.jsp

    String upDir = Tool.getRealPath(request, "/surveyitem/storage"); // 저장 폴더 절대
                                                                   // 경로

    Survey_ItemVO survey_itemVO = surveyitemProc.read(surveyitemno);
                                                           // 목적

    String thumbs_old = survey_itemVO.getThumbs();
    String files_old = survey_itemVO.getFiles();
    if(thumbs_old != null){
    StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/"); // Thumbs
    while (thumbs_st.hasMoreTokens()) { // 단어가 있는지 검사
      String fname = upDir + thumbs_st.nextToken(); // 단어 추출
      Tool.deleteFile(fname);
    }
    }

    StringTokenizer files_st = new StringTokenizer(files_old, "/"); // files
    while (files_st.hasMoreTokens()) { // 단어가 있는지 검사
      String fname = upDir + files_st.nextToken(); // 단어 추출
      Tool.deleteFile(fname);
    }
    surveypartyProc.delete_item(surveyitemno);
    int count = surveyitemProc.delete(surveyitemno); // 레코드 삭제

  if (count == 1) {
    surveyProc.decreaseCnt(survey_itemVO.getSurveyno());
      
  }

    // redirect시에는 request가 전달이안됨으로 아래의 방법을 이용하여 데이터 전달
    redirectAttributes.addAttribute("count", count); // 1 or 0
    mav.setViewName("redirect:/surveyitem/delete_message.jsp");

    return mav;
  }
  
  @RequestMapping(value="/surveyitem/update_seqno_up.do", method=RequestMethod.POST)
  public ModelAndView update_seqno_up(int surveyitemno,int surveyno) {
    ModelAndView mav = new ModelAndView();

    
    if (surveyitemProc.update_seqno_up(surveyitemno) == 1) {
     
      mav.setViewName("redirect:/surveyitem/list.do?surveyno="+surveyno); // /webapp/categrp/list.jsp
    }

    return mav;
  }

  @RequestMapping(value="/surveyitem/update_seqno_down.do", method=RequestMethod.POST)
  public ModelAndView update_seqno_down(int surveyitemno,int surveyno) {
    ModelAndView mav = new ModelAndView();

    
    if (surveyitemProc.update_seqno_down(surveyitemno) == 1) {
     
      mav.setViewName("redirect:/surveyitem/list.do?surveyno="+surveyno); // /webapp/categrp/list.jsp
    }

    return mav;
  }
  
  @RequestMapping(value="/surveyitem/submit_proc.do", method=RequestMethod.POST)
  public ModelAndView submit_proc(int surveyitemno,SurveypartyVO surveypartyVO) {
    
    ModelAndView mav = new ModelAndView();
    

        surveyitemProc.itemCnt(surveyitemno);
        
        Survey_ItemVO survey_itemVO = surveyitemProc.read(surveyitemno);
        mav.addObject("survey_itemVO",survey_itemVO);
         
        System.out.println("("+survey_itemVO.getSurveyitemno()+")"+survey_itemVO.getQuestion()+" 의itemCnt횟수:"+survey_itemVO.getItemcnt());

          surveypartyProc.create(surveypartyVO);
          
          Manager_SurveyVO surveyVO = surveyProc.read(survey_itemVO.getSurveyno());
          mav.addObject("surveyVO", surveyVO);
          
     
      mav.setViewName("redirect:/survey/list_m.do"); // /webapp/categrp/list.jsp
 
    return mav;
  }


}
