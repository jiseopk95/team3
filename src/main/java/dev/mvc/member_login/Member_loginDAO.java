package dev.mvc.member_login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.member.MemberVO;
import dev.mvc.member_login.Member_loginVO;

@Repository("dev.mvc.member_login.Member_loginDAO") // DBMS 저장소 접근 
public class Member_loginDAO implements Member_loginDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;

  public Member_loginDAO() {
    System.out.println("--> Member_loginDAO created.");
  }

  @Override
  public int create(Member_loginVO member_loginVO) {
    int cnt = sqlSessionTemplate.insert("member_login.create", member_loginVO);
    return cnt;
  }  

  @Override
  public List<Member_loginVO> list() {
    List<Member_loginVO> list = sqlSessionTemplate.selectList("member_login.list");
    
    return list;
  }
  
  @Override
  public int delete(int member_loginno) {
    int count = sqlSessionTemplate.delete("member_login.delete", member_loginno);
    return count;
  }
  
  @Override
  public Member_loginVO read(int member_loginno) {
    Member_loginVO member_loginVO = sqlSessionTemplate.selectOne("member_login.read", member_loginno);
    
    return member_loginVO;
  }
}










