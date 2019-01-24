package dev.mvc.chart;

import java.util.HashMap;
import java.util.List;


public interface ChartDAOInter {

  
  /**
   * 차트 리스트 - 전체 다 가져옴
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
   * 검색 목록
   * @param hashMap
   * @return
   */
  public List<ChartVO> list_by_search(HashMap hashMap);
  
  /**
   * 검색 + 페이징
   * @param hashMap
   * @return
   */
  public List<ChartVO> list_by_search_paging(HashMap<String, Object> hashMap);
  
  /**
   * 전체 페이지 수
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);
}
