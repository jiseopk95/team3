package dev.mvc.answer;

import java.util.HashMap;
import java.util.List;

public interface AnswerDAOInter {
  
  /**
   * ��� ���
   * @param answerVO
   * @return
   */
  public int create(AnswerVO answerVO);
  
  /**
   * ���
   * @return
   */
    public List<AnswerVO> list(int reviewno);
  
  
}








