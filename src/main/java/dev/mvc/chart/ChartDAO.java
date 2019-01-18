package dev.mvc.chart;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.reservation.PetVO;

@Repository("dev.mvc.chart.ChartDAO")
public class ChartDAO implements ChartDAOInter{
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public ChartDAO() {
    System.out.println("-----> ChartDAO created");
  }

  @Override
  public List<ChartVO> list() {
    
    return sqlSessionTemplate.selectList("chart.list");
  }

  @Override
  public ChartVO read(int chartno) {
    
    return sqlSessionTemplate.selectOne("chart.read", chartno);
  }

  @Override
  public int delete(int chartno) {
    
    return sqlSessionTemplate.delete("chart.delete", chartno);
  }
  
}
