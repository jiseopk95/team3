package dev.mvc.manager_login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.manager_login.Manager_loginVO;
import dev.mvc.manager.ManagerVO;

@Repository("dev.mvc.manager_login.Manager_loginDAO") // DBMS 저장소 접근 
public class Manager_loginDAO implements Manager_loginDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;

  public Manager_loginDAO() {
    System.out.println("--> Manager_loginDAO created.");
  }

  @Override
  public int create(Manager_loginVO manager_loginVO) {
    int cnt = sqlSessionTemplate.insert("manager_login.create", manager_loginVO);
    return cnt;
  }  

  @Override
  public List<Manager_loginVO> list() {
    List<Manager_loginVO> list = sqlSessionTemplate.selectList("manager_login.list");
    
    return list;
  }
  
  @Override
  public int delete(int manager_loginno) {
    int count = sqlSessionTemplate.delete("manager_login.delete", manager_loginno);
    return count;
  }
  
  @Override
  public Manager_loginVO read(int manager_loginno) {
    Manager_loginVO manager_loginVO = sqlSessionTemplate.selectOne("manager_login.read", manager_loginno);
    
    return manager_loginVO;
  }
}










