package dev.mvc.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.category.CategoryProc")
public class CategoryProc implements CategoryProcInter {
  @Autowired
  @Qualifier("dev.mvc.category.CategoryDAO")
  private CategoryDAOInter categoryDAO = null;

  public CategoryProc() {
    System.out.println("--> CategoryProc created.");
  }

  @Override
  public int create(CategoryVO categoryVO) {
    return categoryDAO.create(categoryVO);
  }

  @Override
  public List<Categrp_CategoryVO> list() {
    return categoryDAO.list();
  }

  @Override
  public List<Categrp_CategoryVO> list_by_categrp(int categrpno) {
    System.out.println("--> list_by_categrp(int categrpno) »£√‚µ ");
    return categoryDAO.list_by_categrp(categrpno);
  }

  @Override
  public Categrp_CategoryVO read(int categoryno) {
    return categoryDAO.read(categoryno);
  }

  @Override
  public int update(CategoryVO categoryVO) {
    return categoryDAO.update(categoryVO);
  }

  @Override
  public int delete(int categoryno) {
    return categoryDAO.delete(categoryno);
  }

  @Override
  public int count_by_categrp(int categrpno) {
    return categoryDAO.count_by_categrp(categrpno);
  }

  @Override
  public int delete_by_categrp(int categrpno) {
    return categoryDAO.delete_by_categrp(categrpno);
  }

  @Override
  public int increaseCnt(int categoryno) {
    return categoryDAO.increaseCnt(categoryno);
  }

  @Override
  public int decreaseCnt(int categoryno) {
    return categoryDAO.decreaseCnt(categoryno);
  }
}



