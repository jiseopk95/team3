package dev.mvc.categrp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.category.CategoryProcInter;
/*import dev.mvc.category.CategoryProcInter;*/
import nation.web.tool.Messages;

@Controller
public class CategrpCont {
  @Autowired
  @Qualifier("dev.mvc.categrp.CategrpProc")
  private CategrpProcInter categrpProc = null;

  @Autowired
  @Qualifier("dev.mvc.category.CategoryProc")
  private CategoryProcInter categoryProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.tool.Messages")
  private Messages messages = null;
  
  public CategrpCont() {
    System.out.println("--> CategrpCont crated.");
  }

  // http://localhost:9090/ahr/categrp/create.do
  @RequestMapping(value="/categrp/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/categrp/create"); // /webapp/categrp/create.jsp
    
    return mav;
  }

  // request.getParameter() 자동 실행
  // 형변환 자동 실행
  // CategrpVO 객체 자동 생성
  // http://localhost:9090/ahr/categrp/create.do
  @RequestMapping(value="/categrp/create.do", method=RequestMethod.POST)
  public ModelAndView create(CategrpVO categrpVO) {
    ModelAndView mav = new ModelAndView();
    
    int count = categrpProc.create(categrpVO);
    
    System.out.println("create count: " + count);
    
    mav.addObject("count", count);
    
    mav.addObject("name", categrpVO.getName());
   
    mav.setViewName( "redirect:/categrp/create_message.jsp?count=" + count);   // /categrp/create_message2.jsp
    
    return mav;
  }
  
  
 @ResponseBody
 @RequestMapping(value = "/categrp/create_json.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
 public ResponseEntity create_json(CategrpVO categrpVO) {
   HttpHeaders responseHeaders = new HttpHeaders();

   JSONObject json = new JSONObject();
   JSONArray msgs = new JSONArray();

   if (categrpProc.create(categrpVO) == 1) {
     msgs.put("새로운 카테고리그룹을 등록했습니다.");
   } else {
     msgs.put("새로운 카테고리 그룹 등록에 실패했습니다.");
   }
   json.put("msgs", msgs);

   return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
 }
  
  // http://localhost:9090/ojt/categrp/list.do
  @RequestMapping(value="/categrp/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    
    List<CategrpVO> list = categrpProc.list();
    mav.addObject("list", list);
    mav.setViewName("/categrp/list"); // /webapp/categrp/list.jsp
    
    return mav;
  }
  
  /**
   * JSON 기반 전체 목록
   * 
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/categrp/list_json.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public ResponseEntity list_json() {
    HttpHeaders responseHeaders = new HttpHeaders();
    List<CategrpVO> list = categrpProc.list();

    JSONArray json = new JSONArray(list);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  // http://localhost:9090/ojt/categrp/update.do?categrpno=1
  //  일반 텍스트 출력
  /*
  {
  "categrpno":1,
  "visible":"Y",
  "seqno":1,
  "rdate":"2018-11-09 16:38:41.0",
  "name":"여행",
  "classification":1
  }
  */
  @ResponseBody
  @RequestMapping(value="/categrp/update.do", 
                             method=RequestMethod.GET,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity update(int categrpno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    CategrpVO categrpVO = categrpProc.read(categrpno);
    
    JSONObject json = new JSONObject();
    json.put("categrpno", categrpVO.getCategrpno());
    json.put("classification", categrpVO.getClassification());
    json.put("name", categrpVO.getName());
    json.put("seqno", categrpVO.getSeqno());
    json.put("visible",  categrpVO.getVisible());
    json.put("rdate", categrpVO.getRdate());
    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  // request.getParameter() 자동 실행
  // 형변환 자동 실행
  // CategrpVO 객체  자동 생성
  // http://localhost:9090/ojt/categrp/update.do -> 실행 주소
  @RequestMapping(value="/categrp/update.do", method=RequestMethod.POST)
  public ModelAndView update(CategrpVO categrpVO) {
    ModelAndView mav = new ModelAndView();
    
    int count = categrpProc.update(categrpVO);
    mav.setViewName("redirect:/categrp/create_message.jsp?count=" + count); // /webapp/categrp/create_message.jsp 와 연결
    mav.setViewName("redirect:/categrp/list.do"); // /webapp/categrp/list.jsp
    
    return mav;
  }
  
  // http://localhost:9090/ojt/categrp/delete.do?categrpno=1
  // {"categrpno":1,"name":"여행2"}
  @ResponseBody
  @RequestMapping(value="/categrp/delete.do", 
                             method=RequestMethod.GET,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity delete(int categrpno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    CategrpVO categrpVO = categrpProc.read(categrpno);
    
    int count_by_categrp = categoryProc.count_by_categrp(categrpno);
    
    JSONObject json = new JSONObject();
    json.put("categrpno", categrpVO.getCategrpno());
    json.put("name", categrpVO.getName());
    json.put("count_by_categrp", count_by_categrp);
        
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }

  // http://localhost:9090/ojt/categrp/delete.do -> 실행 주소
  @RequestMapping(value="/categrp/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_proc(int categrpno) {
    ModelAndView mav = new ModelAndView();
    
    int count = categrpProc.delete(categrpno);
    mav.setViewName("redirect:/categrp/delete_message.jsp?count=" + count); // /webapp/categrp/create_message.jsp 와 연결
    mav.setViewName("redirect:/categrp/list.do"); // /webapp/categrp/list.jsp
    
    return mav;
  }
   
  @RequestMapping(value="/categrp/update_seqno_up.do", method=RequestMethod.POST)
  public ModelAndView update_seqno_up(int categrpno) {
    ModelAndView mav = new ModelAndView();
    
    int count = categrpProc.update_seqno_up(categrpno);
    mav.setViewName("redirect:/categrp/list.do"); // /webapp/categrp/list.jsp
    
    return mav;
  }

  @RequestMapping(value="/categrp/update_seqno_down.do", method=RequestMethod.POST)
  public ModelAndView update_seqno_down(int categrpno) {
    ModelAndView mav = new ModelAndView();
    
    int count = categrpProc.update_seqno_down(categrpno);
    mav.setViewName("redirect:/categrp/list.do"); // /webapp/categrp/list.jsp
    
    return mav;
  }
 
  /**
   * 카테고리 테이블에서 카테고리 그룹에 소속된 레코드 모두 삭제
   * @param categrpno
   * @return
   */
  /*@ResponseBody
  @RequestMapping(value="/categrp/delete_category_by_categrp.do", 
                             method=RequestMethod.POST,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity delete_category_by_categrp(int categrpno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    CategrpVO categrpVO = categrpProc.read(categrpno);
    
    int delete_by_categrp = categoryProc.delete_by_categrp(categrpno);
    
    JSONObject json = new JSONObject();
    json.put("categrpno", categrpVO.getCategrpno());
    json.put("name", categrpVO.getName());
    json.put("delete_by_categrp", delete_by_categrp);
        
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }*/
  
}








