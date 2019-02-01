package dev.mvc.surveyitem;

import java.util.ArrayList;
import java.util.List;



public interface SurveyitemProcInter {
  
  /**
   * 질문 등록
   * @param surveyitemVO 입력 받은 질문 정보
   * @return
   */
  public int create(SurveyitemVO surveyitemVO);
  
  /**
   * 조인 목록
   * @return
   */
  public List<Survey_ItemVO> list(int surveyno);
  
/*  public ArrayList<FileVO> getThumbs(Survey_ItemVO survey_itemVO);*/
  
  /**
   * 조회
   * @param surveyitemno
   * @return
   */
  public Survey_ItemVO read(int surveyitemno);
  
  /**
   * 파일 목록 추출
   * @param contentsVO
   * @return
   */
  public ArrayList<SurFileVO> getThumbs(Survey_ItemVO survey_itemVO);
  
  /**
   * 수정처리
   * @param vo
   * @return
   */
  public int update(SurveyitemVO surveyitemVO);
  
  /**
   * 한건의 레코드 삭제
   * <delete id="delete" parameterType="int"> 
   * @param categoryno
   * @return
   */
  public int delete(int surveyitemno);
  
  /**
   * 우선순위 상향
   * @param surveyno
   * @return
   */
  public int update_seqno_up(int surveyitemno);
  
  /**
   * 우선순위 하향
   * @param surveyno
   * @return
   */
  public int update_seqno_down(int surveyitemno);
  
  /** FK 컬럼 값이 사용된 레코드 갯수 산출*/
  public int countBySurveyno(int surveyno);
  
  public int deleteBySurveyno(int surveyno);
  
  /**
   * itemCnt 증가
   * @param surveyitemno
   * @return
   */
  public int itemCnt(int surveyitemno);
  
  
}
