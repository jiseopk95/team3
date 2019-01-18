package dev.mvc.chart;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.reservation.PetVO;


@Component("dev.mvc.chart.ChartProc")
public class ChartProc implements ChartProcInter{

  @Autowired
  @Qualifier("dev.mvc.chart.ChartDAO")
  
  private ChartDAOInter chartDAO = null;

  public ChartProc(){
    System.out.println("--> ChartProc created.");
  }

  @Override
  public List<ChartVO> list() {
    List<ChartVO> list = chartDAO.list();
    ChartVO chartVO = null;
  
    int count = list.size();
    String date = "";
    
    for(int i = 0; i < count; i++) {
      chartVO = list.get(i);
      date = chartVO.getRdate();
      chartVO.setRdate(date.replace("/", "-"));
    }
    return list;
  }

  @Override
  public ChartVO read(int chartno) {
    
    return chartDAO.read(chartno);
  }

  @Override
  public int delete(int chartno) {
    
    return chartDAO.delete(chartno);
  }
  
  
}
