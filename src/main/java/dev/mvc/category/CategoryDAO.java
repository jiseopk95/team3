package dev.mvc.category;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.category.CategoryDAO") // DBMS 저장소 접근 
public class CategoryDAO implements CategoryDAOInter {
  @Autowired  // 빈을 스프링이 만들어서 자동 할당, 개발자는 new 사용 안함.
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public CategoryDAO() {
    System.out.println("--> CategoryDAO created.");
  }

  @Override
  public int create(CategoryVO categoryVO) {
    return sqlSessionTemplate.insert("category.create", categoryVO);
  }

  @Override
  public List<Categrp_CategoryVO> list() {
    return sqlSessionTemplate.selectList("category.list");
  }

  @Override
  public List<Categrp_CategoryVO> list_by_categrp(int categrpno) {
    return sqlSessionTemplate.selectList("category.list_by_categrp", categrpno);
  }

  @Override
  public Categrp_CategoryVO read(int categoryno) {
    return sqlSessionTemplate.selectOne("category.read", categoryno);
  }

  @Override
  public int update(CategoryVO categoryVO) {
    return sqlSessionTemplate.update("category.update", categoryVO);
  }

  @Override
  public int delete(int categoryno) {
    return sqlSessionTemplate.delete("category.delete", categoryno);
  }

  @Override
  public int count_by_categrp(int categrpno) {
    return sqlSessionTemplate.selectOne("category.count_by_categrp", categrpno);
  }

  @Override
  public int delete_by_categrp(int categrpno) {
    return sqlSessionTemplate.delete("category.delete_by_categrp", categrpno);
  }

  @Override
  public int increaseCnt(int categoryno) {
    return sqlSessionTemplate.update("category.increaseCnt", categoryno);
  }

  @Override
  public int decreaseCnt(int categoryno) {
    return sqlSessionTemplate.update("category.decreaseCnt", categoryno);
  }
  
}












