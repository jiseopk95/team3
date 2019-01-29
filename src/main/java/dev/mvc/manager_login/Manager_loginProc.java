package dev.mvc.manager_login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.manager.ManagerVO;
import dev.mvc.manager_login.Manager_loginVO;

@Component("dev.mvc.manager_login.Manager_loginProc")
public class Manager_loginProc implements Manager_loginProcInter {
  @Autowired
  @Qualifier("dev.mvc.manager_login.Manager_loginDAO")
  private Manager_loginDAOInter manager_loginDAO = null;
  
  public Manager_loginProc(){
    System.out.println("--> Manager_loginProc created.");
  }

  @Override
  public int create(Manager_loginVO manager_loginVO) {
    int cnt = manager_loginDAO.create(manager_loginVO);
    return cnt;
  }
  
  @Override
  public List<Manager_loginVO> list() {
    List<Manager_loginVO> list = manager_loginDAO.list();
    return list;
  }
  

  @Override
  public int delete(int manager_loginno) {
    int count = manager_loginDAO.delete(manager_loginno);
    return count;
  }
  
  @Override
  public Manager_loginVO read(int manager_loginno) {
    Manager_loginVO manager_loginVO = manager_loginDAO.read(manager_loginno);
    
    return manager_loginVO;
  }
}




