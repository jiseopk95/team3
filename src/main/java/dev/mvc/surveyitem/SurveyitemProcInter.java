package dev.mvc.surveyitem;

import java.util.ArrayList;
import java.util.List;



public interface SurveyitemProcInter {
  
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
  
/*  public ArrayList<FileVO> getThumbs(Survey_ItemVO survey_itemVO);*/
  
  /**
   * ��ȸ
   * @param surveyitemno
   * @return
   */
  public Survey_ItemVO read(int surveyitemno);
  
  /**
   * ���� ��� ����
   * @param contentsVO
   * @return
   */
  public ArrayList<SurFileVO> getThumbs(Survey_ItemVO survey_itemVO);
  
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
  
  public int deleteBySurveyno(int surveyno);
  
  /**
   * itemCnt ����
   * @param surveyitemno
   * @return
   */
  public int itemCnt(int surveyitemno);
  
  
}
