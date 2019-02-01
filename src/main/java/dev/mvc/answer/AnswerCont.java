package dev.mvc.answer;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.category.CategoryProcInter;
import dev.mvc.category.CategoryVO;
import dev.mvc.category.Categrp_CategoryVO;
import dev.mvc.categrp.CategrpVO;
import nation.web.tool.Messages;
import nation.web.tool.Tool;
import nation.web.tool.Upload;


@Controller 
public class AnswerCont {
  @Autowired
  @Qualifier("dev.mvc.answer.AnswerProc")
  private AnswerProcInter answerProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.tool.Messages")
  private Messages messages = null;
  
  
  public AnswerCont() {
    System.out.println("--> AnswerCont crated.");
  }

  /**
   * 등록 폼 http://localhost:9090/ahr/answer /create.do
   * 
   * @return
   */
  @RequestMapping(value = "/answer/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    System.out.println("--> create() GET executed");
    ModelAndView mav = new ModelAndView();

   
    mav.setViewName("/answer/create"); // /webapp/contents/create.jsp

    return mav;
  }
  
//http://localhost:9090/ojt/category/create.do
 @RequestMapping(value = "/answer/create.do", method = RequestMethod.POST)
 public ModelAndView create(AnswerVO answerVO) {
   ModelAndView mav = new ModelAndView();

   int count = answerProc.create(answerVO);
   mav.setViewName("redirect:/category/create_message.jsp?count=" + count); // /webapp/category/create_message.jsp
   // mav.setViewName("redirect:/category/list.do"); //
   // /webapp/category/list.jsp

   return mav;
 }

 /**
   * 등록 처리
   * 
   * @param request
   * @param answerVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/review/create_json.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public ResponseEntity create_json(AnswerVO answerVO) {
    HttpHeaders responseHeaders = new HttpHeaders();
    
    JSONObject json = new JSONObject();
    JSONArray msgs = new JSONArray();

    if (answerProc.create( answerVO) == 1) {
      msgs.put("카테고리를 등록했습니다.");
      msgs.put("등록된 카테고리 " + answerVO.getTitle());
    } else {
      msgs.put("카테고리 등록에 실패했습니다.");
      msgs.put("다시한번 시도해주세요. ☏ 문의: 000-0000-0000");
    }
    json.put("msgs", msgs);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
//http://localhost:9090/ojt/categrp/list.do
 @RequestMapping(value="/answer/list.do", method=RequestMethod.GET)
 public ModelAndView list(int reviewno) {
   ModelAndView mav = new ModelAndView();
   
   List<AnswerVO> list = answerProc.list(reviewno);
   mav.addObject("list", list);
   mav.setViewName("/answer/list"); // /webapp/categrp/list.jsp
   
   return mav;
 }
  
  /**
   * JSON 기반 전체 목록
   * 
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/review/list_json.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public ResponseEntity list_json(int reviewno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    List<AnswerVO> list = answerProc.list(reviewno);

    JSONArray json = new JSONArray(list);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
   


  
  
}


