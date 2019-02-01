package dev.mvc.event;

import java.util.HashMap;
import java.util.List;

import dev.mvc.beauty.BeautyVO;

public interface EventDAOInter {
  
  public int select_user(HashMap<String, Object> hashMap);
  /**
   * 이벤트 컨텐츠 등록
   * @param beautyVO
   * @return
   */
  public int create(EventVO eventVO);
/**
 * 전체목록
 * @return
 */
  public List<EventVO> list_all_event();
  /**
   * 전체목록
   * @return
   */
    public List<event_presentVO> list_present();
    
    public List<event_eventuserVO> read_member();
    
    public  event_eventuserVO read_win(int eventno);
    
    public int usercreate(event_eventuserVO euVO);
    
  /**글 삭제
   * 
   * @param eventno
   * @return
   */
  public int delete(int eventno);
  /**
   * 수정처리
   * @param vo
   * @return
   */
  public int update(EventVO eventVO);
  /**
   * 조회
   * @param eventno
   * @return
   */
  public EventVO read(int eventno);
  /**
   * 조회
   * @param eventno
   * @return
   */
  public event_managerVO read_manager(int eventno);
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
 
}