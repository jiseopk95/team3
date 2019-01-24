package dev.mvc.chart;

import java.util.HashMap;
import java.util.List;


public interface ChartDAOInter {

  
  /**
   * ��Ʈ ����Ʈ - ��ü �� ������
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
   * �˻� ���
   * @param hashMap
   * @return
   */
  public List<ChartVO> list_by_search(HashMap hashMap);
  
  /**
   * �˻� + ����¡
   * @param hashMap
   * @return
   */
  public List<ChartVO> list_by_search_paging(HashMap<String, Object> hashMap);
  
  /**
   * ��ü ������ ��
   * @param hashMap
   * @return
   */
  public int search_count(HashMap hashMap);
}
