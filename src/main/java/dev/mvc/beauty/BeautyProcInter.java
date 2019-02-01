package dev.mvc.beauty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.mvc.beauty.BeaFileVO;

public interface BeautyProcInter {
  /**
   * 미용스타일 컨텐츠 등록
   * @param beautyVO
   * @return
   */
  public int create(BeautyVO beautyVO);

  public List<BeautyVO> list_all_beauty();
  public List<BeautyVO> list_all_cnt();
  public List<BeautyVO> list_all_like1();
  public List<BeautyVO> list_all_rdate();
  /**
   * 조회
   * @param styleno
   * @return
   */
  public BeautyVO read(int styleno);
  /**글 삭제
   * 
   * @param styleno
   * @return
   */
  public int delete(int styleno);
  /**
   * 파일 목록 추출
   * @param beautyVO
   * @return
   */
  public ArrayList<BeaFileVO> getThumbs(BeautyVO beautyVO);
  /**
   * 수정처리
   * @param vo
   * @return
   */
  public int update(BeautyVO beautyVO);
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
  public String paging(int categoryno, int search_count, int nowPage, String title);
}
