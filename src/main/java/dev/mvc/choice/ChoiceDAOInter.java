package dev.mvc.choice;

import java.util.List;

import dev.mvc.survey.SurveyVO;
import dev.mvc.surveyitem.Survey_ItemVO;

public interface ChoiceDAOInter {
  
  /**
   * answer등록
   * @param ChoiceVO 입력 받은 answer 정보
   * @return
   */
    public int create(ChoiceVO choiceVO);
    
    /**
     * 조인 목록
     * @return
     */
    public Surveyitem_ChoiceVO list(int surveyitemno);
    
    /**
     * FK 컬럼 값이 사용된 레코드 갯수 산출
     * @param surveyitem
     * @return
     */
    public int countBySurveyitemno(int surveyitemno);
    
    /**
     * answer 수정
     * @param choiceno
     * @return
     */
    public int update(ChoiceVO choiceVO);
    
    /**
     * answer 삭제
     * @param choiceno
     * @return
     */
    public int delete(int surveyitemno);
    
    

}
