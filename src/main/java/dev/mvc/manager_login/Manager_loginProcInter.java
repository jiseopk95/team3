package dev.mvc.manager_login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import dev.mvc.manager.ManagerVO;
import dev.mvc.manager_login.Manager_loginVO;

public interface Manager_loginProcInter {
  /**
  ȸ�� ���
  @param memberVO
  @return ��ϵ� ȸ���� 1 or 0
  */
  public int create(Manager_loginVO manager_loginVO);
  
  /**
   * ȸ�� ��ü ���
   * @return
   */
  public List<Manager_loginVO> list();
  
  public int delete(int manager_loginno);
  public Manager_loginVO read(int manager_loginno);
  
}









