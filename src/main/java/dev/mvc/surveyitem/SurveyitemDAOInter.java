package dev.mvc.surveyitem;

import java.util.ArrayList;
import java.util.List;

import dev.mvc.survey.Manager_SurveyVO;

public interface SurveyitemDAOInter {
  /**
   * ���� ���
   * @param surveyitemVO �Է� ���� ���� ����
   * @return
   */
  public int create(SurveyitemVO surveyitemVO);
  
  /**
   * ���� ���
   * @return
   */
  public List<Survey_ItemVO> list(int surveyno);
  
  /**
   * ��ȸ
   * @param surveyitemno
   * @return
   */
  public Survey_ItemVO read(int surveyitemno);
  
  /**
   * ����ó��
   * @param vo
   * @return
   */
  public int update(SurveyitemVO surveyitemVO);
  
  /**
   * �Ѱ��� ���ڵ� ����
   * <delete id="delete" parameterType="int"> 
   * @param categoryno
   * @return
   */
  public int delete(int surveyitemno);
  
  /**
   * �켱���� ����
   * @param surveyno
   * @return
   */
  public int update_seqno_up(int surveyitemno);
  
  /**
   * �켱���� ����
   * @param surveyno
   * @return
   */
  public int update_seqno_down(int surveyitemno);
  
  /** FK �÷� ���� ���� ���ڵ� ���� ����*/
  public int countBySurveyno(int surveyno);
  
  /** FK �÷� ���� ���� ���ڵ� ����*/
  public int deleteBySurveyno(int surveyno);
  
  /**
   * itemCnt ����
   * @param surveyitemno
   * @return
   */
  public int itemCnt(int surveyitemno);
  
  
  
  
}
