package dev.mvc.member_login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import dev.mvc.member.MemberVO;
import dev.mvc.member_login.Member_loginVO;

public interface Member_loginProcInter {
  /**
  회원 등록
  @param memberVO
  @return 등록된 회원수 1 or 0
  */
  public int create(Member_loginVO member_loginVO);
  
  /**
   * 회원 전체 목록
   * @return
   */
  public List<Member_loginVO> list();
  
  public int delete(int member_loginno);
  public Member_loginVO read(int member_loginno);
  
}









