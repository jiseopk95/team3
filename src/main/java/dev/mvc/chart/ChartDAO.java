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

  @Override
  public Pet_infoVO pet_info(int petno) {
    
    return sqlSessionTemplate.selectOne("chart.pet_info", petno);
  }

  @Override
  public Member_infoVO member_info(int memberno) {
    
    return sqlSessionTemplate.selectOne("chart.member_info", memberno);
  }

  @Override
  public int create(ChartVO chartVO) {
    
    return sqlSessionTemplate.insert("chart.create", chartVO);
  }

  @Override
  public int update(ChartVO chartVO) {
    
    return sqlSessionTemplate.update("chart.update", chartVO);
  }

  @Override
  public List<ChartVO> list_by_search(HashMap hashMap) {
    
    return sqlSessionTemplate.selectList("chart.search", hashMap);
  }

  @Override
  public List<ChartVO> list_by_search_paging(HashMap<String, Object> hashMap) {
    
    return sqlSessionTemplate.selectList("chart.search_paging", hashMap);
  }

  @Override
  public int search_count(HashMap hashMap) {
    
    return sqlSessionTemplate.selectOne("chart.search_count", hashMap);
  }
  
}
