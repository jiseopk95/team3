package dev.mvc.event;

import java.util.HashMap;
import java.util.List;

import dev.mvc.beauty.BeautyVO;

public interface EventDAOInter {
  
  public int select_user(HashMap<String, Object> hashMap);
  /**
   * �̺�Ʈ ������ ���
   * @param beautyVO
   * @return
   */
  public int create(EventVO eventVO);
/**
 * ��ü���
 * @return
 */
  public List<EventVO> list_all_event();
  /**
   * ��ü���
   * @return
   */
    public List<event_presentVO> list_present();
    
    public List<event_eventuserVO> read_member();
    
    public  event_eventuserVO read_win(int eventno);
    
    public int usercreate(event_eventuserVO euVO);
    
  /**�� ����
   * 
   * @param eventno
   * @return
   */
  public int delete(int eventno);
  /**
   * ����ó��
   * @param vo
   * @return
   */
  public int update(EventVO eventVO);
  /**
   * ��ȸ
   * @param eventno
   * @return
   */
  public EventVO read(int eventno);
  /**
   * ��ȸ
   * @param eventno
   * @return
   */
  public event_managerVO read_manager(int eventno);
  /**
   * �˻� ��� + ����¡
   * @param hashMap
   * @return
   */
  public List<event_managerVO> search_paging(HashMap<String, Object> hashMap);
  /**
   * �˻� ��� + ����¡
   * @param hashMap
   * @return
   */
  public List<EventVO> search_paging_member(HashMap<String, Object> hashMap);
  /**
   * category�� �˻��� ���ڵ� ����
   * @return
   */
  public int search_count(HashMap hashMap);
  
  
  
  /**
   * �˻� ��� + ����¡
   * @param hashMap
   * @return
   */
  public List<event_eventuserVO> search_paging_win(HashMap<String, Object> hashMap);
  /**
   * category�� �˻��� ���ڵ� ����
   * @return
   */
  public int search_count_win(HashMap hashMap);
 
}