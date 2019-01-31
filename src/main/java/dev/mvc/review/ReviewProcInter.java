package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




public interface ReviewProcInter {
  
  /**
   * 컨텐츠 등록
   * @param contentsVO
   * @return
   */
  public int create(ReviewVO reviewVO);
  
  /**
   * 목록
   * <xmp>
   * <select id="list" resultType="CategrpVO">
   * </xmp> 
   * @return
   */
  public List<ReviewVO> list();
  
/**
 * 5개만 나오는 목록
 * @return
 */
  public List<ReviewVO> index_list();
  
  /**
   * 조회
   * @param reviewno
   * @return
   */
  public ReviewVO read(int reviewno);
  
  /**
   * 삭제
   * @param reviewno
   * @return
   */
  public int delete(int reviewno);
  
  /**
   * 파일 목록 추출
   * @param contentsVO
   * @return
   */
  public ArrayList<FileVO> getThumbs(ReviewVO reviewVO);
  
  /**
   *수정
   * @param reviewVO
   * @return
   */
  public int update(ReviewVO reviewVO);
  
  /**
   * 검색 목록
   * @param categoryno
   * @return
   */
  public List<ReviewVO> list_by_category_search(HashMap hashMap);

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
  public List<ReviewVO> list_by_category_search_paging(HashMap<String, Object> hashMap);

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
  public String paging(int categoryno, int search_count, int nowPage, String title);
  

  
  
  
  
}




  