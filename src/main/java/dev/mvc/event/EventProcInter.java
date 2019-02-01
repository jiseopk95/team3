package dev.mvc.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.mvc.beauty.BeautyVO;
import dev.mvc.event.EventFileVO;

public interface EventProcInter {
  
  public int select_user(HashMap<String, Object> hashMap);
  
  /**
   * �̺�Ʈ ������ ���
   * @param eventVO
   * @return
   */
  public int create(EventVO eventVO);
/**
 * ���
 * @return
 */
  public List<EventVO> list_all_event();
  /**
   * �������
   * @return
   */
    public List<event_presentVO> list_present();  
    
    public  List<event_eventuserVO> read_member();
    
    public  event_eventuserVO read_win(int eventno);
    
    public int usercreate(event_eventuserVO euVO);
    
  /**
   * ��ȸ
   * @param eventno 
   * @return
   */
  public EventVO read(int eventno);
  /**
   * ��ȸ
   * @param eventno-������
   * @return
   */
  public event_managerVO read_manager(int eventno);
  /**�� ����
   * 
   * @param eventno
   * @return
   */
  public int delete(int eventno);
  /**
   * ���� ��� ����
   * @param beautyVO
   * @return
   */
  public ArrayList<EventFileVO> getThumbs(EventVO eventVO);
  /**
   * ���� ��� ����
   * @param beautyVO
   * @return
   */
  public ArrayList<EventFileVO> getThumbs(event_managerVO event_managerVO);
  /**
   * ����ó��
   * @param vo
   * @return
   */
  public int update(EventVO eventVO);
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
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param title 
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int managerno,int search_count, int nowPage, String content);
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param title 
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging_member(int memberno,int search_count, int nowPage, String content);

  
  
  
//������� ��÷��Ȯ�� ����¡, �˻�
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
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param title 
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging_win(int eventno,int search_count, int nowPage, String id);
}