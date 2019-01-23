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
 // public List<ReviewVO> list_by_category_search(HashMap hashMap);

  /**
   * category별 검색된 레코드 갯수
   * @return
   */
 // public int search_count(HashMap hashMap);

  
  
  
  
}




  