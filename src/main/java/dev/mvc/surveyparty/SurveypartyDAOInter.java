package dev.mvc.surveyparty;

import java.util.HashMap;
import java.util.List;


import dev.mvc.surveyitem.Survey_ItemVO;

public interface SurveypartyDAOInter {
  /**
   * 참여자 목록 create
   * @param surveypartyVO
   * @return
   */
  public int create(SurveypartyVO surveypartyVO);
  
  public List<Survey_PartyVO> list();
  
  public List<Survey_PartyVO> list_survey(int surveyno);
  
  public int mnoCnt(HashMap hashMap);
  
  public int delete(int surveyno);
  
  public int delete_item(int surveyitemno);
  
  
  


}
