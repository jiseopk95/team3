package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.member.MemberVO;

@Repository("dev.mvc.member.MemberDAO") // DBMS 저장소 접근 
public class MemberDAO implements MemberDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;

  public MemberDAO() {
    System.out.println("--> MemberDAO created.");
  }

  @Override
  public int checkId(String id) {
    int cnt = sqlSessionTemplate.selectOne("member.checkId", id);
    return cnt;
  }
  
  @Override
  public int checkemail(String email) {
    int cnt = sqlSessionTemplate.selectOne("member.checkemail", email);
    return cnt;
  }

  @Override
  public int create(MemberVO memberVO) {
    int cnt = sqlSessionTemplate.insert("member.create", memberVO);
    return cnt;
  }  

  @Override
  public List<MemberVO> list() {
    List<MemberVO> list = sqlSessionTemplate.selectList("member.list");
    
    return list;
  }

  @Override
  public MemberVO read(int memberno) {
    MemberVO memberVO = sqlSessionTemplate.selectOne("member.read", memberno);
    
    return memberVO;
  }
  
  @Override
  public MemberVO read2(String email) {
    MemberVO memberVO = sqlSessionTemplate.selectOne("member.read2", email);
    
    return memberVO;
  }
  
  @Override
  public MemberVO read3(String email) {
    MemberVO memberVO = sqlSessionTemplate.selectOne("member.read3", email);
    
    return memberVO;
  }
  

  @Override
  public MemberVO readById(String id) {
    MemberVO memberVO = sqlSessionTemplate.selectOne("member.readById", id);
    
    return memberVO;
  }  
  
  @Override
  public int update(MemberVO memberVO) {
    int count = sqlSessionTemplate.update("member.update", memberVO);
    return count;
  } 
  
 @Override
  public int passwd_update(Map<String, Object> map) {
    int count = sqlSessionTemplate.update("member.passwd_update", map);
    return count;
  }  

  @Override
  public int delete(int memberno) {
    int count = sqlSessionTemplate.delete("member.delete", memberno);
    return count;
  }

  @Override
  public int login(Map map) {
    int count = sqlSessionTemplate.selectOne("member.login", map);
    return count;
  }
  
  @Override
  public List<MemberVO> list_search(HashMap<String, Object> hashMap) {
    return sqlSessionTemplate.selectList("member.list_search", hashMap);
  }

  @Override
  public int search_count(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("member.search_count", hashMap);
  }
  
  @Override
  public List<MemberVO> idsearch(HashMap hashMap) {
    return sqlSessionTemplate.selectList("member.idsearch", hashMap);
  }
  
  @Override
  public List<MemberVO> list_id() {
    List<MemberVO> list_id = sqlSessionTemplate.selectList("member.list_id");
    
    return list_id;
  }
  
  @Override
  public int search_count2(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("member.search_count2", hashMap);
  }
  
  @Override
  public List<MemberVO> passwdsearch(HashMap hashMap) {
    return sqlSessionTemplate.selectList("member.passwdsearch", hashMap);
  }
  
  @Override
  public List<MemberVO> list_passwd() {
    List<MemberVO> list_passwd = sqlSessionTemplate.selectList("member.list_passwd");
    
    return list_passwd;
  }
  
  @Override
  public int search_count3(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("member.search_count3", hashMap);
  }
  
}










