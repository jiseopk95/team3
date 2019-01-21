package dev.mvc.beauty;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.beauty.BeautyDAOInter;

@Repository("dev.mvc.beauty.BeautyDAO") // DBMS 저장소 접근 
public class BeautyDAO implements BeautyDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public BeautyDAO() {
    System.out.println("--> BeautyDAO created.");
  }

  @Override
  public int create(BeautyVO beautyVO) {
    return sqlSessionTemplate.insert("beauty.create",beautyVO);
  }
  @Override
  public List<BeautyVO> list_all_beauty() {
    return sqlSessionTemplate.selectList("beauty.list_all_beauty");
  }

  @Override
  public List<BeautyVO> list_all_cnt() {
    return sqlSessionTemplate.selectList("beauty.list_all_cnt");
  }
  @Override
  public List<BeautyVO> list_all_like1() {
    return sqlSessionTemplate.selectList("beauty.list_all_like1");
  }
  @Override
  public List<BeautyVO> list_all_rdate() {
    return sqlSessionTemplate.selectList("beauty.list_all_rdate");
  }
  @Override
  public int delete(int styleno) {
    return sqlSessionTemplate.delete("beauty.delete", styleno);
  }

  @Override
  public int update(BeautyVO beautyVO) {
    return sqlSessionTemplate.update("beauty.update",beautyVO);
  }
  
  @Override
  public BeautyVO read(int styleno) {
    return sqlSessionTemplate.selectOne("beauty.read", styleno);
  }
  
  @Override
  public int increaseCnt(int styleno) {
    return sqlSessionTemplate.update("beauty.increaseCnt", styleno);
  }
  
  @Override
  public int increaseLike1(int styleno) {
    return sqlSessionTemplate.update("beauty.increaseLike1", styleno);
  }
  @Override
  public List<BeautyVO> search_paging(HashMap<String, Object> hashMap) {
    return sqlSessionTemplate.selectList("beauty.search_paging", hashMap);
  }
  @Override
  public int search_count(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("beauty.search_count", hashMap);
  }
}
