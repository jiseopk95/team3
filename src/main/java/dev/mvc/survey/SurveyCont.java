package dev.mvc.survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import dev.mvc.choice.ChoiceProcInter;
import dev.mvc.choice.ChoiceVO;
import dev.mvc.choice.Surveyitem_ChoiceVO;

import dev.mvc.surveyitem.Survey_ItemVO;
import dev.mvc.surveyitem.SurveyitemProcInter;
import dev.mvc.surveyparty.SurveypartyProcInter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
public class SurveyCont {
  @Autowired
  @Qualifier("dev.mvc.surveyitem.SurveyitemProc")
  private SurveyitemProcInter surveyitemProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.survey.SurveyProc")
  private SurveyProcInter surveyProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.choice.ChoiceProc")
  private ChoiceProcInter choiceProc = null;
  
  @Autowired 
  @Qualifier("dev.mvc.surveyparty.SurveypartyProc")
  private SurveypartyProcInter surveypartyProc = null;
  
  public SurveyCont(){
    System.out.println("-->  SurveyCont created");
  }
  
//  http://localhost:9090/ahr/survey/create.do
  @RequestMapping(value = "/survey/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();

    mav.setViewName("/survey/create");
    return mav;
  }
  
/*  @RequestMapping(value="/survey/create.do", method=RequestMethod.POST)
  public ModelAndView create(SurveyVO surveyVO, Locale locale) {
    ModelAndView mav = new ModelAndView();
    
    int count = surveyProc.create(surveyVO);
    System.out.println("managerno:"+surveyVO.getManagerno());
    System.out.println("surveyno:"+surveyVO.getSurveyno());
   
    
    mav.addObject("count", count);
    mav.setViewName("redirect:/survey/create_message.jsp?count=" + count);// /categrp/create_message2.jsp

    return mav;
  }*/
  
  @ResponseBody
  @RequestMapping(value = "/survey/create_json.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public ResponseEntity create_json(SurveyVO surveyVO) {
    HttpHeaders responseHeaders = new HttpHeaders();

    JSONObject json = new JSONObject();
    JSONArray msgs = new JSONArray();

    if (surveyProc.create(surveyVO) == 1) {
      msgs.put("성공적으로 카테고리를 등록했습니다.");

    } else {
      msgs.put("카테고리 등록에 실패했습니다.");
      msgs.put("다시한번 시도해주세요. ☏ 문의: 000-0000-0000");
    }
    json.put("msgs", msgs);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  //http://localhost:9090/ahr/survey/list.do?managerno=1
  @RequestMapping(value="/survey/list.do", method=RequestMethod.GET)
  public ModelAndView list(int managerno) {
    ModelAndView mav = new ModelAndView();
    

    List<Manager_SurveyVO> list = surveyProc.list(managerno);
    mav.addObject("list", list);
    
    mav.setViewName("/survey/list"); // /webapp/categrp/list.jsp
    
    return mav;
  }
  /**
   * 수정 폼 http://localhost:9090/ahr/survey/update.do?surveyno=1
   * 
   * @param surveyno
   * @return
   */
/*  @RequestMapping(value = "/survey/update.do", method = RequestMethod.GET)
  public ModelAndView update(int surveyno) {

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey/update"); // /webapp/contents/delete.jsp

    Manager_SurveyVO surveyVO = surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);

    return mav;
  }
  */
  
  @ResponseBody
  @RequestMapping(value="/survey/update.do", 
                             method=RequestMethod.GET,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity update(int surveyno) {
    HttpHeaders responseHeaders = new HttpHeaders();
         
    Manager_SurveyVO  manager_surveyVO = surveyProc.read(surveyno);
    
    JSONObject json = new JSONObject();
    json.put("survey_title", manager_surveyVO.getSurvey_title());
    json.put("seqno",manager_surveyVO.getSeqno());
    json.put("startdate", manager_surveyVO.getStartdate());
    json.put("enddate", manager_surveyVO.getEnddate());
    json.put("surveyno", manager_surveyVO.getSurveyno());

    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  @ResponseBody
  @RequestMapping(value = "/survey/update_json.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public ResponseEntity update_json(SurveyVO surveyVO) {
    HttpHeaders responseHeaders = new HttpHeaders();

    JSONObject json = new JSONObject();
    JSONArray msgs = new JSONArray();

    if (surveyProc.update(surveyVO) == 1) {
      msgs.put("성공적으로 카테고리를 수정했습니다.");

    } else {
      msgs.put("카테고리 수정에 실패했습니다.");
      msgs.put("다시한번 시도해주세요. ☏ 문의: 000-0000-0000");
    }
    json.put("msgs", msgs);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  /*@RequestMapping(value="/survey/update.do", method=RequestMethod.POST)
  public ModelAndView update_proc(SurveyVO surveyVO) {
    ModelAndView mav = new ModelAndView();
    
    int count = surveyProc.update(surveyVO);

    
    mav.addObject("count", count);
    mav.setViewName("redirect:/survey/update_message.jsp?count=" + count); // /webapp/categrp/create_message.jsp 와 연결
    //mav.setViewName("redirect:/survey/list.do"); // /webapp/categrp/list.jsp
    
    return mav;
  }
  */
  @RequestMapping(value="/survey/delete_form.do", method=RequestMethod.GET)
  public ModelAndView delete_form(int surveyno){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey/delete_form"); // webapp/member/delete.jsp
    
    Manager_SurveyVO surveyVO = surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);
    int cnt=0;
    cnt = surveyitemProc.countBySurveyno(surveyno);
    mav.addObject("cnt", cnt);
    
    return mav;
  }  
  
  @RequestMapping(value = "/survey/delete_form.do", 
      method = RequestMethod.POST)
public ModelAndView delete_from(int surveyno) {
ModelAndView mav = new ModelAndView();
mav.setViewName("/survey/message"); // /webapp/categrp/message.jsp

ArrayList<String> msgs = new ArrayList<String>();
ArrayList<String> links = new ArrayList<String>();


int countBySurvey = surveyitemProc.countBySurveyno(surveyno);

if(countBySurvey ==0){
  surveyProc.delete(surveyno);
  msgs.add("공지사항을 삭제했습니다.");
}
else if(countBySurvey > 0 || surveyProc.delete(surveyno) ==0){
  surveypartyProc.delete(surveyno);
  
  surveyitemProc.deleteBySurveyno(surveyno);
  surveyProc.decreaseCnt(surveyno);
  surveyProc.delete(surveyno); 
  msgs.add("문제 목록을 모두 삭제 했습니다.");
}


mav.addObject("msgs", msgs);


return mav;
} 
  
  
/*   @RequestMapping(value = "/survey/delete_form.do", 
          method = RequestMethod.POST)
    public ModelAndView delete_from(int surveyno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey/message"); // /webapp/categrp/message.jsp
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    List<FindVO>list_find= surveyProc.findNo(surveyno); 
    for(int i =0;i<list_find.size();i++){
      FindVO findVO = list_find.get(i);
      int surveyitemno = findVO.getSurveyitemno();
      
      int countBySurvey = surveyitemProc.countBySurveyno(surveyno);
   
    if(countBySurvey ==0){
      surveyProc.delete(surveyno);
      msgs.add("공지사항을 삭제했습니다.");
    }
    else if(countBySurvey > 0 || surveyProc.delete(surveyno) ==0){
      choiceProc.delete(surveyitemno);
      surveyitemProc.deleteBySurveyno(surveyno);
      surveyProc.decreaseCnt(surveyno);
      surveyProc.delete(surveyno);    
      msgs.add("문제 목록을 모두 삭제 했습니다.");
    }
    
    }
    mav.addObject("msgs", msgs);

    
    return mav;
    } */
      
      
/*  @RequestMapping(value="/survey/delete_form.do", method=RequestMethod.POST)
  public ModelAndView delete_from(RedirectAttributes redirectAttributes,
                                        HttpServletRequest request, int surveyno){
    ModelAndView mav = new ModelAndView();
    
    int count = surveyProc.delete(surveyno);

    //mav.setViewName("redirect:/survey/list.do");
    mav.addObject("count", count);
    mav.setViewName("redirect:/survey/delete_message.jsp?count=" + count); 
    return mav;
  }*/
  
  @RequestMapping(value="/survey/update_seqno_up.do", method=RequestMethod.POST)
  public ModelAndView update_seqno_up(int surveyno,int managerno) {
    ModelAndView mav = new ModelAndView();

    
    if (surveyProc.update_seqno_up(surveyno) == 1) {
      
      mav.setViewName("redirect:/survey/list_by_manager_search_paging.do?managerno="+managerno); // /webapp/categrp/list.jsp
    }

    return mav;
  }

  @RequestMapping(value="/survey/update_seqno_down.do", method=RequestMethod.POST)
  public ModelAndView update_seqno_down(int surveyno,int managerno) {
    ModelAndView mav = new ModelAndView();
    
    if (surveyProc.update_seqno_down(surveyno) == 1) {
      
      mav.setViewName("redirect:/survey/list_by_manager_search_paging.do?managerno="+managerno); // /webapp/categrp/list.jsp
    }

    return mav;
  }
  
  //http://localhost:9090/ahr/survey/list_by_manager_search.do?managerno=1&survey_title='강아지'
  /**
   * 검색 목록
   * 
   * @param categoryno
   * @param word
   * @return
   */
  @RequestMapping(value = "/survey/list_by_manager_search.do", method = RequestMethod.GET)
  public ModelAndView list_by_manager_search(int managerno, String survey_title) {
  
    ModelAndView mav = new ModelAndView();

    mav.setViewName("survey/list_by_manager_search");

    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("managerno", managerno); // #{categoryno}
    hashMap.put("survey_title", survey_title); // #{word}


    // 검색 목록
    List<Manager_SurveyVO> list = surveyProc.list_by_manager_search(hashMap);
    mav.addObject("list", list);
    
    mav.addObject("managerno", managerno);
    mav.addObject("survey_title", survey_title);    

    
    

    // 검색된 레코드 갯수
/*    int search_count = contentsProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    */
    // mav.addObject("word", word);

    return mav;
  }
  
  /**http://localhost:9090/ahr/survey/list_by_manager_search_paging.do?managerno=1
   * 목록 + 검색 + 페이징 지원
   * @param managerno
   * @param survey_title
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/survey/list_by_manager_search_paging.do", 
                                       method = RequestMethod.GET)
  public ModelAndView list_by_manager_search_paging(
      @RequestParam(value="managerno") int managerno,
      @RequestParam(value="survey_title", defaultValue="") String survey_title,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) { 
    // System.out.println("--> list_by_category() GET called.");
    System.out.println("--> nowPage: " + nowPage);
    
    
    ModelAndView mav = new ModelAndView();
    
    // 검색 기능 추가,  /webapp/contents/list_by_category_search_paging.jsp
    mav.setViewName("/survey/list_by_manager_search_paging");   
    
    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("managerno", managerno); // #{categoryno}
    hashMap.put("survey_title", survey_title);                  // #{word}
    hashMap.put("nowPage", nowPage);       

    
    // 검색 목록
    List<Manager_SurveyVO> list = surveyProc.list_by_manager_search_paging(hashMap);
    mav.addObject("list", list);
    
    // 검색된 레코드 갯수
    int search_count = surveyProc.search_count(hashMap);
    System.out.println("search_count:"+search_count);
    mav.addObject("search_count", search_count);
  
    mav.addObject("managerno", managerno);
    mav.addObject("survey_title", survey_title); 
    
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
    String paging = surveyProc.paging(managerno, search_count, nowPage, survey_title);
    mav.addObject("paging", paging);
  
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
  //http://localhost:9090/ahr/survey/list_m.do
  @RequestMapping(value="/survey/list_m.do", method=RequestMethod.GET)
  public ModelAndView list_m(int memberno) {
    ModelAndView mav = new ModelAndView();
    
    
    List<SurveyVO> list = surveyProc.list_m();
    mav.addObject("list", list);
    mav.addObject("memberno",memberno);
    mav.setViewName("/survey/list_m");   

   
    return mav;
  }
/*  
  //http://localhost:9090/ahr/survey/list_m.do
  @RequestMapping(value="/survey/list_mnoCnt.do", method=RequestMethod.POST)
  public ModelAndView list_mnoCnt(int memberno,int surveyno) {
    ModelAndView mav = new ModelAndView();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("memberno",memberno ); // #{categoryno}
    hashMap.put("surveyno", surveyno);
    int mnoCnt=  surveypartyProc.mnoCnt(hashMap);
    mav.addObject("mnoCnt", mnoCnt);
    
    mav.setViewName("/survey/list_m");
    return mav;
  }*/
  
  @ResponseBody
  @RequestMapping(value="/survey/list_mnoCnt.do", 
                             method=RequestMethod.POST,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity list_mnoCnt(int memberno,int surveyno) {
    HttpHeaders responseHeaders = new HttpHeaders();
         
    SurveyVO  surveyVO = surveyProc.read_m(surveyno);
    
    JSONObject json = new JSONObject();
 
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("memberno",memberno ); // #{categoryno}
    hashMap.put("surveyno", surveyno);
    int mnoCnt=  surveypartyProc.mnoCnt(hashMap);
    json.put("mnoCnt", mnoCnt);

    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  
  
  

  
}
