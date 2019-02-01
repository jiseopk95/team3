package dev.mvc.answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface AnswerProcInter {
  
  /**
   * 댓글 등록
   * @param answerVO
   * @return
   */
  public int create(AnswerVO answerVO);
  
/**
 * 목록
 * @return
 */
  public List<AnswerVO> list(int reviewno);
 
   
  
  
  
}




  