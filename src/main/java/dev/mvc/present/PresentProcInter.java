package dev.mvc.present;

import java.util.HashMap;
import java.util.List;


public interface PresentProcInter {
  /**
   * 이벤트상품 컨텐츠 등록
   * @param presentVO
   * @return
   */
  public int create(PresentVO presentVO);
/**
 * 상품 목록보기
 * @return
 */
  public List<PresentVO> list_all_present();
/**
 * 상품내용수정
 * @param presentVO
 * @return
 */
  public int update(PresentVO presentVO);
/**
 * 상품 삭제 
 * @param presentno
 * @return
 */
  public int delete(int presentno);
  public PresentVO read(int presentno);
  /**
   * category별 검색된 레코드 갯수
   * @return
   */
  /*public int search_count(HashMap hashMap);*/
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param categoryno 카테고리번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param title 
   * @return 페이징 생성 문자열
   */ 
 /* public String paging(int search_count, int nowPage);*/
}
   