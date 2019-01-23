package dev.mvc.surveyparty;

import java.util.List;

public interface SurveypartyProcInter {
  /**
   * 참여자 목록 create
   * @param surveypartyVO
   * @return
   */
  public int create(SurveypartyVO surveypartyVO);
  
  public List<Survey_PartyVO> list();
  
  public List<Survey_PartyVO> list_survey(int surveyno);
  
  public int mnoCnt(int memberno);


}
