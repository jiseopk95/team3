package dev.mvc.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.mvc.review.ReviewVO;


public interface QuestionProcInter {
  
  /**
   * 질문 등록
   * @param questionVO
   * @return
   */
  public int create(QuestionVO questionVO);
  

/**
 * 목록
 * @return
 */
  public List<QuestionVO> list(int categoryno);
  
/**
 * 조회
 * @param questionno
 * @return
 */
  public QuestionVO read(int questionno);
  
  /**
   * 비밀번호 확인
   * @param map
   * @return
   */
  public int passwd_check(int questionno , String passwd);
  
  /**
   * 삭제
   * @param reviewno
   * @return
   */
  public int delete(int questionno);
  
  /**
   *수정
   * @param reviewVO
   * @return
   */
  public int update(QuestionVO questionVO);
  
  /**
   * 신규 답변을 최우선으로 출력하기위한 답변 순서 조절
   * @param contentsVO
   * @return
   */
  public int updateAnsnum(QuestionVO questionVO);
  
  /**
   * 답변
   * @param contentsVO
   * @return
   */
  public int reply(QuestionVO questionVO);
 
  
}




  