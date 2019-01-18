package dev.mvc.chart;

import java.util.HashMap;
import java.util.List;

public interface ChartProcInter {

  /**
   * 차트 리스트 - petno, memberno가 같은것을 가져옴
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
