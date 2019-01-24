package dev.mvc.chart;

import java.util.HashMap;
import java.util.List;

public interface ChartProcInter {

  /**
   * 차트 리스트 - petno, memberno가 같은것을 가져옴
   * @param petno
   * @return
   */
  public List<ChartVO> list();
  
  /**
   * 차트내용 읽기
   * @param chartno
   * @return
   */
  public ChartVO read(int chartno);
  
  /**
   * 하나 지우기
   * @param chartno
   * @return
   */
  public int delete(int chartno);
  
  /**
   * 차트 생성시, 동물의 기본 정보를 불러온다.
   * @return
   */
  public Pet_infoVO pet_info(int petno);
  
  /**
   * 차트 생성시, 주인의 기본 정보를 불러온다.
   * @return
   */
  public Member_infoVO member_info(int memberno);
  
  /**
   * 차트값 저장
   * @return
   */
  public int create(ChartVO chartVO);
  
  /**
   * 수정
   * @param chartVO
   * @return
   */
  public int update(ChartVO chartVO);
  
  /**
   * 검색 목록 전체
   * @param hashMap
   * @return
   */
  public List<ChartVO> list_by_search(HashMap hashMap);
  
  /**
   * 검색 목록 + 페이징
   * @param hashMap
   * @return
   */
  public List<ChartVO> list_by_search_paging(HashMap<String, Object> hashMap);
  
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
  public String paging(int managerno, int nowPage, int search_count, String petname, String name);
  
  /**
   * 전체 페이지 수
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);
}
