package dev.mvc.beauty;

import java.util.HashMap;
import java.util.List;

public interface BeautyDAOInter{
  /**
   * 미용스타일 컨텐츠 등록
   * @param beautyVO
   * @return
   */
  public int create(BeautyVO beautyVO);
/**
 * 전체목록
 * @return
 */
  public List<BeautyVO> list_all_beauty();
  public List<BeautyVO> list_all_cnt();
  public List<BeautyVO> list_all_like1();
  public List<BeautyVO> list_all_rdate();
  /**글 삭제
   * 
   * @param styleno
   * @return
   */
  public int delete(int styleno);
  /**
   * 수정처리
   * @param vo
   * @return
   */
  public int update(BeautyVO beautyVO);
  /**
   * 조회
   * @param styleno
   * @return
   */
  public BeautyVO read(int styleno);
  /**
   * 조회수 증가
   * @param styleno
   * @return
   */
  public int increaseCnt(int styleno);
  /**
   * 좋아요수 증가 
   * @param styleno
   * @return
   */
  public int increaseLike1(int styleno);
  /**
   * 검색 목록 + 페이징
   * @param hashMap
   * @return
   */
  public List<BeautyVO> search_paging(HashMap<String, Object> hashMap);
  /**
   * category별 검색된 레코드 갯수
   * @return
   */
  public int search_count(HashMap hashMap);
}