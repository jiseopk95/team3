package dev.mvc.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.member.MemberVO;

@Repository("dev.mvc.manager.ManagerDAO") // DBMS 저장소 접근 
public class ManagerDAO implements ManagerDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;

  public ManagerDAO() {
    System.out.println("--> ManagerDAO created.");
  }

  @Override
  public int checkId(String id) {
    int cnt = sqlSessionTemplate.selectOne("manager.checkId", id);
    return cnt;
  }

  @Override
  public int create(ManagerVO managerVO) {
    int cnt = sqlSessionTemplate.insert("manager.create", managerVO);
    return cnt;
  }  

  @Override
  public List<ManagerVO> list() {
    List<ManagerVO> list = sqlSessionTemplate.selectList("manager.list");
    
    return list;
  }

  @Override
  public ManagerVO read(int managerno) {
    ManagerVO managerVO = sqlSessionTemplate.selectOne("manager.read", managerno);
    
    return managerVO;
  }
  
  @Override
  public ManagerVO idsearch(String email) {
    ManagerVO managerVO = sqlSessionTemplate.selectOne("manager.idsearch", email);
    
    return managerVO;
  }
  
  @Override
  public ManagerVO kind_update(int managerno) {
    ManagerVO managerVO = sqlSessionTemplate.selectOne("manager.kind_update", managerno);
    
    return managerVO;
  }

  @Override
  public ManagerVO readById(String id) {
    ManagerVO managerVO = sqlSessionTemplate.selectOne("manager.readById", id);
    
    return managerVO;
  }  
  
  @Override
  public int update(ManagerVO managerVO) {
    int count = sqlSessionTemplate.update("manager.update", managerVO);
    return count;
  } 
  
  @Override
  public int kind_update2(ManagerVO managerVO) {
    int count = sqlSessionTemplate.update("manager.kind_update2", managerVO);
    return count;
  } 
  
 @Override
  public int passwd_update(Map<String, Object> map) {
    int count = sqlSessionTemplate.update("manager.passwd_update", map);
    return count;
  }  

  @Override
  public int delete(int managerno) {
    int count = sqlSessionTemplate.delete("manager.delete", managerno);
    return count;
  }

  @Override
  public int login(Map map) {
    int count = sqlSessionTemplate.selectOne("manager.login", map);
    return count;
  }
  
  @Override
  public List<ManagerVO> list_search(HashMap hashMap) {
    return sqlSessionTemplate.selectList("manager.list_search", hashMap);
  }

  @Override
  public int search_count(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("manager.search_count", hashMap);
  }
}










