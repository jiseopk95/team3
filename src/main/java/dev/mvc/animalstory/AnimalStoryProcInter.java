package dev.mvc.animalstory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface AnimalStoryProcInter {

  /**
   * 글 생성
   * @param animalStoryVO
   * @return
   */
  public int create(AnimalStoryVO animalStoryVO);
  
  /**
   * 차트 리스트 - 전체 다 가져옴 => managerno 없이 불러올 수 있어야함
   * @return
   */
  public List<AnimalStoryVO> list();
  
  /**
   * 차트리스트 - anitype에 따라
   * @param anitype
   * @return
   */
  public List<AnimalStoryVO> list_anitype(HashMap hashmap);
  
  /**
   * 매니저 이름 검색 - 글쓴이 나타내기 위해
   * @return
   */
  public String manager(int managerno);
  
  /**
   * 하나의 글 삭제
   * @param anino
   * @return
   */
  public int delete(int anino);
  
  /**
   * 하나의 글 읽기
   * @param anino
   * @return
   */
  public AnimalStoryVO read(int anino);
  
  /**
   * 파일목록 읽어오기
   * @param aniVO
   * @return
   */
  public ArrayList<FileVO> getThumbs(AnimalStoryVO aniVO);
  
  /**
   * 하나의 글 수정
   * @param aniVO
   * @return
   */
  public int update(AnimalStoryVO aniVO);
  
  /**
   * 내용 검색
   * @param content
   * @return
   */
  public List<AnimalStoryVO> list_by_search(String content);

  /**
   * 검색 + 페이징 - 전체
   * @param hashMap
   * @return
   */
  public List<AnimalStoryVO> list_by_search_paging(HashMap<String, Object> hashMap);
  
  /**
   * 검색 + 페이징 - 분류
   * @param hashMap
   * @return
   */
  public List<AnimalStoryVO> list_by_search_paging_anitype(HashMap hashMap);

  /**
   * 검색된 갯수
   * @param content
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * 검색된 게시물 개수 - 분류
   * @param hashMap
   * @return
   */
  public int search_count_anitype(HashMap hashMap);
  
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
  public String paging(int nowPage, int search_count, String content);
  
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
  public String paging_anitype(int nowPage, int search_count, String content, int anitype);
  
  /**
   * 이벤트별 참여자 추출
   * @param evnetno
   * @return
   */
  public List<EventVO> event(int evnetno); 
  
  /**
   * 당첨자 업데이트
   * @param hashMap
   * @return
   */
  public int win(HashMap hashMap);

}
