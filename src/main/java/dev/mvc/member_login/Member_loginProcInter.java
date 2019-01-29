package dev.mvc.member_login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import dev.mvc.member.MemberVO;
import dev.mvc.member_login.Member_loginVO;

public interface Member_loginProcInter {
  /**
  ȸ�� ���
  @param memberVO
  @return ��ϵ� ȸ���� 1 or 0
  */
  public int create(Member_loginVO member_loginVO);
  
  /**
   * ȸ�� ��ü ���
   * @return
   */
  public List<Member_loginVO> list();
  
  public int delete(int member_loginno);
  public Member_loginVO read(int member_loginno);
  
}









