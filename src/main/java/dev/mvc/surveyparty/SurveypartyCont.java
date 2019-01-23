package dev.mvc.surveyparty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.survey.Manager_SurveyVO;



@Controller
public class SurveypartyCont {
  @Autowired
  @Qualifier("dev.mvc.surveyparty.SurveypartyProc")
 
  private SurveypartyProcInter surveypartyProc = null;
  
 //http://localhost:9090/ahr/surveyparty/list.do
  @RequestMapping(value="/surveyparty/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
   
    List<Survey_PartyVO> list = surveypartyProc.list();
    mav.addObject("list", list);
    
    mav.setViewName("/surveyparty/list"); // /webapp/categrp/list.jsp
    
    return mav;
  }
  
  //http://localhost:9090/ahr/surveyparty/list_survey.do?surveyno=12;
  @RequestMapping(value="/surveyparty/list_survey.do", method=RequestMethod.GET)
  public ModelAndView list_survey(int surveyno) {
    ModelAndView mav = new ModelAndView();
    

    List<Survey_PartyVO> list_survey = surveypartyProc.list_survey(surveyno);
    mav.addObject("list_survey", list_survey);
    
    mav.addObject("surveyno", surveyno);
    mav.setViewName("/surveyparty/list_survey"); // /webapp/categrp/list.jsp
    
    return mav;
  }

  
  
  
  

}
