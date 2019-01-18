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
}
