package dev.mvc.chart;

import java.util.HashMap;
import java.util.List;

public interface ChartProcInter {

  /**
   * ��Ʈ ����Ʈ - petno, memberno�� �������� ������
   * @param petno
   * @return
   */
  public List<ChartVO> list();
  
  /**
   * ��Ʈ���� �б�
   * @param chartno
   * @return
   */
  public ChartVO read(int chartno);
  
  /**
   * �ϳ� �����
   * @param chartno
   * @return
   */
  public int delete(int chartno);
  
  /**
   * ��Ʈ ������, ������ �⺻ ������ �ҷ��´�.
   * @return
   */
  public Pet_infoVO pet_info(int petno);
  
  /**
   * ��Ʈ ������, ������ �⺻ ������ �ҷ��´�.
   * @return
   */
  public Member_infoVO member_info(int memberno);
  
  /**
   * ��Ʈ�� ����
   * @return
   */
  public int create(ChartVO chartVO);
  
  /**
   * ����
   * @param chartVO
   * @return
   */
  public int update(ChartVO chartVO);
  
  /**
   * �˻� ��� ��ü
   * @param hashMap
   * @return
   */
  public List<ChartVO> list_by_search(HashMap hashMap);
  
  /**
   * �˻� ��� + ����¡
   * @param hashMap
   * @return
   */
  public List<ChartVO> list_by_search_paging(HashMap<String, Object> hashMap);
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int managerno, int nowPage, int search_count, String petname, String name);
  
  /**
   * ��ü ������ ��
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);
}
