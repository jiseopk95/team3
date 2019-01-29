package dev.mvc.member_login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.member.MemberVO;
import dev.mvc.member_login.Member_loginVO;

@Component("dev.mvc.member_login.Member_loginProc")
public class Member_loginProc implements Member_loginProcInter {
  @Autowired
  @Qualifier("dev.mvc.member_login.Member_loginDAO")
  private Member_loginDAOInter member_loginDAO = null;
  
  public Member_loginProc(){
    System.out.println("--> Member_loginProc created.");
  }

  @Override
  public int create(Member_loginVO member_loginVO) {
    int cnt = member_loginDAO.create(member_loginVO);
    return cnt;
  }
  
  @Override
  public List<Member_loginVO> list() {
    List<Member_loginVO> list = member_loginDAO.list();
    return list;
  }
  

  @Override
  public int delete(int member_loginno) {
    int count = member_loginDAO.delete(member_loginno);
    return count;
  }
  
  @Override
  public Member_loginVO read(int member_loginno) {
    Member_loginVO member_loginVO = member_loginDAO.read(member_loginno);
    
    return member_loginVO;
  }
}




