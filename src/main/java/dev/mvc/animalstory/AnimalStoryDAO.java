package dev.mvc.animalstory;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.animalstory.AnimalStoryDAO")
public class AnimalStoryDAO implements AnimalStoryDAOInter{
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public AnimalStoryDAO() {
    System.out.println("---- AnimalStoryDAO created");
  }
  
  @Override
  public int create(AnimalStoryVO animalStoryVO) {
    
    return sqlSessionTemplate.insert("animalstory.create", animalStoryVO);
  }
  
  @Override
  public List<AnimalStoryVO> list() {
    
    return sqlSessionTemplate.selectList("animalstory.list");
  }
  
  @Override
  public List<AnimalStoryVO> list_anitype(HashMap hashmap) {
    
    return sqlSessionTemplate.selectList("animalstory.list_anitype", hashmap);
  }

  @Override
  public String manager(int managerno) {
    
    return sqlSessionTemplate.selectOne("animalstory.manager", managerno);
  }

  @Override
  public int delete(int anino) {
    
    return sqlSessionTemplate.delete("animalstory.delete", anino);
  }

  @Override
  public AnimalStoryVO read(int anino) {
    
    return sqlSessionTemplate.selectOne("animalstory.read", anino);
  }

  @Override
  public int update(AnimalStoryVO aniVO) {
    
    return sqlSessionTemplate.update("animalstory.update", aniVO);
  }

  @Override
  public List<AnimalStoryVO> list_by_search(String content) {
    
    return sqlSessionTemplate.selectList("animalstory.list_by_search", content);
  }

  @Override
  public List<AnimalStoryVO> list_by_search_paging(HashMap<String, Object> hashMap) {
    
    return sqlSessionTemplate.selectList("animalstory.list_by_search_paging", hashMap);
  }

  @Override
  public List<AnimalStoryVO> list_by_search_paging_anitype(HashMap hashMap) {
    
    return sqlSessionTemplate.selectList("animalstory.list_by_search_paging_anitype", hashMap);
  }

  @Override
  public int search_count(HashMap hashMap) {
   
    return sqlSessionTemplate.selectOne("animalstory.search_count", hashMap);
  }

  @Override
  public int search_count_anitype(HashMap hashMap) {
    
    return sqlSessionTemplate.selectOne("animalstory.search_count_anitype", hashMap);
  }

  
}
