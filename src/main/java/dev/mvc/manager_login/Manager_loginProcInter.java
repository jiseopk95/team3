package dev.mvc.manager_login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import dev.mvc.manager.ManagerVO;
import dev.mvc.manager_login.Manager_loginVO;

public interface Manager_loginProcInter {
  /**
  회원 등록
  @param memberVO
  @return 등록된 회원수 1 or 0
  */
  public int create(Manager_loginVO manager_loginVO);
  
  /**
   * 회원 전체 목록
   * @return
   */
  public List<Manager_loginVO> list();
  
  public int delete(int manager_loginno);
  public Manager_loginVO read(int manager_loginno);
  
}









