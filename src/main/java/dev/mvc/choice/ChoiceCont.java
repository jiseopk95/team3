package dev.mvc.choice;

import java.util.List;
import java.util.Locale;

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
import org.springframework.web.servlet.ModelAndView;


import dev.mvc.survey.Manager_SurveyVO;
import dev.mvc.survey.SurveyVO;
import dev.mvc.surveyitem.Survey_ItemVO;
import dev.mvc.surveyitem.SurveyitemProcInter;



@Controller
public class ChoiceCont {

  @Autowired
  @Qualifier("dev.mvc.choice.ChoiceProc")
  private ChoiceProcInter choiceProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.surveyitem.SurveyitemProc")
  private SurveyitemProcInter surveyitemProc = null;
  
  
  
  public ChoiceCont(){
    System.out.println("-->  ChoiceCont created");
  }
  
    //http://localhost:9090/ahr/choice/create.do
    @RequestMapping(value = "/choice/create.do", method = RequestMethod.GET)
    public ModelAndView create() {
      ModelAndView mav = new ModelAndView();
    
      mav.setViewName("/choice/create");
      return mav;
    }
    
/*    @RequestMapping(value="/choice/create.do", method=RequestMethod.POST)
    public ModelAndView create(ChoiceVO choiceVO, Locale locale) {
      ModelAndView mav = new ModelAndView();
      
      int count = choiceProc.create(choiceVO);

      mav.addObject("count", count);
      mav.addObject("surveyitemno",choiceVO.getSurveyitemno());
      mav.setViewName("redirect:/choice/create_message.jsp?count=" + count+"&surveyitemno="+choiceVO.getSurveyitemno());// /categrp/create_message2.jsp

      return mav;
    }*/
    
    @ResponseBody
    @RequestMapping(value = "/choice/create_json.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public ResponseEntity create_json(ChoiceVO choiceVO) {
      HttpHeaders responseHeaders = new HttpHeaders();

      JSONObject json = new JSONObject();
      JSONArray msgs = new JSONArray();

      if (choiceProc.create(choiceVO) == 1) {
        msgs.put("성공적으로 카테고리를 등록했습니다.");

      } else {
        msgs.put("카테고리 등록에 실패했습니다.");
        msgs.put("다시한번 시도해주세요. ☏ 문의: 000-0000-0000");
      }
      json.put("msgs", msgs);

      return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
    }
    
    
    
    //http://localhost:9090/ahr/choice/list.do?surveyitemno=1
    @RequestMapping(value = "/choice/list.do", method = RequestMethod.GET)
    public ModelAndView list(int surveyitemno) {
      ModelAndView mav = new ModelAndView();
      
      Survey_ItemVO survey_itemVO = surveyitemProc.read(surveyitemno);
      mav.addObject("survey_itemVO", survey_itemVO);
      
      int cnt=0;
      cnt = choiceProc.countBySurveyitemno(surveyitemno);
      mav.addObject("cnt", cnt);
      
    System.out.println("cnt 값:"+cnt);
      Surveyitem_ChoiceVO surveyitem_choiceVO = choiceProc.list(surveyitemno);
      mav.addObject("surveyitem_choiceVO",surveyitem_choiceVO);

      mav.setViewName("/choice/list");

      return mav;
    }
    //http://localhost:9090/ahr/choice/update.do?surveyitemno=6
    @ResponseBody
    @RequestMapping(value="/choice/update.do", 
                               method=RequestMethod.GET,
                               produces="text/plain;charset=UTF-8")
    public ResponseEntity update(int surveyitemno) {
      HttpHeaders responseHeaders = new HttpHeaders();
           
      Surveyitem_ChoiceVO  surveyitem_choiceVO = choiceProc.list(surveyitemno);
      
      JSONObject json = new JSONObject();
      json.put("a1", surveyitem_choiceVO.getA1());
      json.put("a2",surveyitem_choiceVO.getA2());
      json.put("a3", surveyitem_choiceVO.getA3());
      json.put("a4", surveyitem_choiceVO.getA4());
      json.put("choiceno", surveyitem_choiceVO.getChoiceno());

      
      return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
    }
    
/*    @RequestMapping(value="/choice/update.do", method=RequestMethod.POST)
    public ModelAndView update(ChoiceVO choiceVO) {
      ModelAndView mav = new ModelAndView();
     
      mav.addObject("surveyitemno",choiceVO.getSurveyitemno());
      int count = choiceProc.update(choiceVO);
      
      mav.setViewName("redirect:/choice/list"); // /webapp/categrp/list.jsp
      
      return mav;
    }*/
    
    
    @ResponseBody
    @RequestMapping(value = "/choice/update_json.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public ResponseEntity update_json(ChoiceVO choiceVO) {
      HttpHeaders responseHeaders = new HttpHeaders();

      JSONObject json = new JSONObject();
      JSONArray msgs = new JSONArray();

      if (choiceProc.update(choiceVO) == 1) {
        msgs.put("성공적으로 카테고리를 수정했습니다.");

      } else {
        msgs.put("카테고리 수정에 실패했습니다.");
        msgs.put("다시한번 시도해주세요. ☏ 문의: 000-0000-0000");
      }
      json.put("msgs", msgs);

      return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
    }
    
    /**
     * 수정 폼
     * http://localhost:9090/ojt/category/update.do?categoryno=1
     * @param categrpno
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/choice/delete.do", 
                               method=RequestMethod.GET,
                               produces="text/plain;charset=UTF-8")
    public ResponseEntity delete(int surveyitemno) {
      HttpHeaders responseHeaders = new HttpHeaders();
      // System.out.println("--> update() GET executed");
      
      Surveyitem_ChoiceVO  surveyitem_choiceVO = choiceProc.list(surveyitemno);
      
      JSONObject json = new JSONObject();
      json.put("surveyitemno", surveyitem_choiceVO.getSurveyitemno());
      json.put("question", surveyitem_choiceVO.getQuestion());


      return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
    }
    
    @ResponseBody
    @RequestMapping(value = "/choice/delete_json.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public ResponseEntity delete_json(int surveyitemno) {
      HttpHeaders responseHeaders = new HttpHeaders();

      JSONObject json = new JSONObject();
      JSONArray msgs = new JSONArray();

      if (choiceProc.delete(surveyitemno)== 1) {
        msgs.put("성공적으로 카테고리를 삭제했습니다.");

      } else {
        msgs.put("카테고리 삭제에 실패했습니다.");
        msgs.put("다시한번 시도해주세요. ☏ 문의: 000-0000-0000");
      }
      json.put("msgs", msgs);

      return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
    }
    
    

    
    
    
}
