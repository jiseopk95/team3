package dev.mvc.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.mvc.review.ReviewVO;


public interface QuestionProcInter {
  
  /**
   * ���� ���
   * @param questionVO
   * @return
   */
  public int create(QuestionVO questionVO);
  

/**
 * ���
 * @return
 */
  public List<QuestionVO> list(int categoryno);
  
/**
 * ��ȸ
 * @param questionno
 * @return
 */
  public QuestionVO read(int questionno);
  
  /**
   * ��й�ȣ Ȯ��
   * @param map
   * @return
   */
  public int passwd_check(int questionno , String passwd);
  
  /**
   * ����
   * @param reviewno
   * @return
   */
  public int delete(int questionno);
  
  /**
   *����
   * @param reviewVO
   * @return
   */
  public int update(QuestionVO questionVO);
  
  /**
   * �ű� �亯�� �ֿ켱���� ����ϱ����� �亯 ���� ����
   * @param contentsVO
   * @return
   */
  public int updateAnsnum(QuestionVO questionVO);
  
  /**
   * �亯
   * @param contentsVO
   * @return
   */
  public int reply(QuestionVO questionVO);
 
  
}




  