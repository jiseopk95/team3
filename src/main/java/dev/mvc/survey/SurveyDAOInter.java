package dev.mvc.survey;

import java.util.HashMap;
import java.util.List;


import dev.mvc.survey.SurveyVO;

public interface SurveyDAOInter {

/**
 * 설문조사등록
 * @param SurveyVO 입력 받은 설문조사 정보
 * @return
 */
  public int create(SurveyVO surveyVO);
  
  /**
   * 설문조사목록
   * @return 
   */
 public List<SurveyVO> list_m();
  
  
  /**
   * 조인한 목록
   * @return
   */
  public List<Manager_SurveyVO> list(int managerno);
  
  /**
   *  조인 조회
   * @param surveyno
   * @return
   */
  public Manager_SurveyVO read (int surveyno);
  
  public SurveyVO read_m(int surveyno);
  
  /**
   * 수정
   * @param surveyVO
   * @return
   */
  public int update(SurveyVO surveyVO);
  
  /**
   * 삭제
   * @param surveyno
   * @return
   */
  public int delete(int surveyno);
  
  /**
   * 우선순위 상향
   * @param surveyno
   * @return
   */
  public int update_seqno_up(int surveyno);
  
  /**
   * 우선순위 하향
   * @param surveyno
   * @return
   */
  public int update_seqno_down(int surveyno);
  
  /**
   * 글수 증가
   * @param categrpno
   * @return
   */
  public int increaseCnt(int surveyno);

  /**
   * 글수 감소
   * @param categrpno
   * @return
   */
  public int decreaseCnt(int surveyno);
  
  /**
   * 조인에서surveyitemno사용하기 ㅇ위함
   * @return
   */
  public List<FindVO> findNo(int surveyno);
  
  /**
   * 검색 목록
   * @param categoryno
   * @return
   */
  public List<Manager_SurveyVO>list_by_manager_search(HashMap hashMap);
  
  /**
   * category별 검색된 레코드 갯수
   * @return
   */
  public int search_count(HashMap hashMap);
      
  
  /**
   * 검색 목록 + 페이징
   * @param hashMap
   * @return
   */
  public List<Manager_SurveyVO> list_by_manager_search_paging(HashMap<String, Object> hashMap);

}
