package dev.mvc.present;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.present.PresentDAO") // DBMS 저장소 접근 
public class PresentDAO implements PresentDAOInter {
  @Autowired 
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public PresentDAO() {
    System.out.println("--> PresentDAO created.");
  }
  @Override
  public int create(PresentVO presentVO) {
    return sqlSessionTemplate.insert("present.create",presentVO);
  }
  @Override
  public List<PresentVO> list_all_present() {
    return sqlSessionTemplate.selectList("present.list_all_present");
  }

  @Override
  public int delete(int styleno) {
    return sqlSessionTemplate.delete("present.delete", styleno);
  }

  @Override
  public int update(PresentVO presentVO) {
    return sqlSessionTemplate.update("present.update",presentVO);
  }
  @Override
  public PresentVO read(int presentno) {
    return sqlSessionTemplate.selectOne("present.read",presentno);
  }
  @Override
  public int search_count(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("present.search_count", hashMap);
  }
}