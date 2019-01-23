package dev.mvc.survey;

import java.util.HashMap;
import java.util.List;

import dev.mvc.survey.SurveyVO;

public interface SurveyProcInter {

/**
 * 설문조사 등록
 * @param SurveyVO 입력 받은 설문 정보
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
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param categoryno 카테고리번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  public String paging(int managerno, int search_count, int nowPage, String survey_title);

}








