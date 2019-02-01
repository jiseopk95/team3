package dev.mvc.present;

import java.util.HashMap;
import java.util.List;

public interface PresentDAOInter{

  /**
   * 이벤트 상품 컨텐츠 등록
   * @param presentVO
   * @return
   */
  public int create(PresentVO presentVO);
/**
 * 전체목록
 * @return
 */
  public List<PresentVO> list_all_present();
  /**글 삭제
   * 
   * @param presentno
   * @return
   */
  public int delete(int presentno);
  /**
   * 수정처리
   * @param vo
   * @return
   */
  public int update(PresentVO presentVO);
  public PresentVO read(int presentno);
  /**
   * category별 검색된 레코드 갯수
   * @return
   */
  public int search_count(HashMap hashMap);
}