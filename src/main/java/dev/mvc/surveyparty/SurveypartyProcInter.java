package dev.mvc.surveyparty;

import java.util.List;

public interface SurveypartyProcInter {
  /**
   * ������ ��� create
   * @param surveypartyVO
   * @return
   */
  public int create(SurveypartyVO surveypartyVO);
  
  public List<Survey_PartyVO> list();
  
  public List<Survey_PartyVO> list_survey(int surveyno);
  
  public int mnoCnt(int memberno);


}
