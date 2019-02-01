package dev.mvc.present;

import java.util.HashMap;
import java.util.List;


public interface PresentProcInter {
  /**
   * �̺�Ʈ��ǰ ������ ���
   * @param presentVO
   * @return
   */
  public int create(PresentVO presentVO);
/**
 * ��ǰ ��Ϻ���
 * @return
 */
  public List<PresentVO> list_all_present();
/**
 * ��ǰ�������
 * @param presentVO
 * @return
 */
  public int update(PresentVO presentVO);
/**
 * ��ǰ ���� 
 * @param presentno
 * @return
 */
  public int delete(int presentno);
  public PresentVO read(int presentno);
  /**
   * category�� �˻��� ���ڵ� ����
   * @return
   */
  /*public int search_count(HashMap hashMap);*/
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
 /* public String paging(int search_count, int nowPage);*/
}
   