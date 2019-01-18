package dev.mvc.chart;

import java.util.HashMap;
import java.util.List;

public interface ChartDAOInter {

  /**
   * 차트 리스트 - 전체 다 가져옴
   * @param petno
   * @return
   */
  public List<ChartVO> list();
  
  /**
   * 차트내용 읽기
   * @param chartno
   * @return
   */
  public ChartVO read(int chartno);
  
  /**
   * 하나 지우기
   * @param chartno
   * @return
   */
  public int delete(int chartno);
}
