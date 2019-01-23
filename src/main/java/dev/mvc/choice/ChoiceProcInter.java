package dev.mvc.choice;

import java.util.List;

public interface ChoiceProcInter {
  
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
