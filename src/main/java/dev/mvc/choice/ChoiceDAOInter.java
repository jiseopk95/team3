package dev.mvc.choice;

import java.util.List;

import dev.mvc.survey.SurveyVO;
import dev.mvc.surveyitem.Survey_ItemVO;

public interface ChoiceDAOInter {
  
  /**
   * answer���
   * @param ChoiceVO �Է� ���� answer ����
   * @return
   */
    public int create(ChoiceVO choiceVO);
    
    /**
     * ���� ���
     * @return
     */
    public Surveyitem_ChoiceVO list(int surveyitemno);
    
    /**
     * FK �÷� ���� ���� ���ڵ� ���� ����
     * @param surveyitem
     * @return
     */
    public int countBySurveyitemno(int surveyitemno);
    
    /**
     * answer ����
     * @param choiceno
     * @return
     */
    public int update(ChoiceVO choiceVO);
    
    /**
     * answer ����
     * @param choiceno
     * @return
     */
    public int delete(int surveyitemno);
    
    

}
