package dev.mvc.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.mvc.beauty.BeautyVO;
import dev.mvc.event.EventFileVO;

public interface EventProcInter {
  
  public int select_user(HashMap<String, Object> hashMap);
  
  /**
   * 이벤트 컨텐츠 등록
   * @param eventVO
   * @return
   */
  public int create(EventVO eventVO);
/**
 * 목록
 * @return
 */
  public List<EventVO> list_all_event();
  /**
   * 선물목록
   * @return
   */
    public List<event_presentVO> list_present();  
    
    public  List<event_eventuserVO> read_member();
    
    public  event_eventuserVO read_win(int eventno);
    
    public int usercreate(event_eventuserVO euVO);
    
  /**
   * 조회
   * @param eventno 
   * @return
   */
  public EventVO read(int eventno);
  /**
   * 조회
   * @param eventno-관리자
   * @return
   */
  public event_managerVO read_manager(int eventno);
  /**글 삭제
   * 
   * @param eventno
   * @return
   */
  public int delete(int eventno);
  /**
   * 파일 목록 추출
   * @param beautyVO
   * @return
   */
  public ArrayList<EventFileVO> getThumbs(EventVO eventVO);
  /**
   * 파일 목록 추출
   * @param beautyVO
   * @return
   */
  public ArrayList<EventFileVO> getThumbs(event_managerVO event_managerVO);
  /**
   * 수정처리
   * @param vo
   * @return
   */
  public int update(EventVO eventVO);
  /**
   * 검색 목록 + 페이징
   * @param hashMap
   * @return
   */
  public List<event_managerVO> search_paging(HashMap<String, Object> hashMap);
  /**
   * 검색 목록 + 페이징
   * @param hashMap
   * @return
   */
  public List<EventVO> search_paging_member(HashMap<String, Object> hashMap);
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
  public String paging(int managerno,int search_count, int nowPage, String content);
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
  public String paging_member(int memberno,int search_count, int nowPage, String content);

  
  
  
//여기부터 당첨자확인 페이징, 검색
  /**
   * 검색 목록 + 페이징
   * @param hashMap
   * @return
   */
  public List<event_eventuserVO> search_paging_win(HashMap<String, Object> hashMap);
  /**
   * category별 검색된 레코드 갯수
   * @return
   */
  public int search_count_win(HashMap hashMap);
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
  public String paging_win(int eventno,int search_count, int nowPage, String id);
}