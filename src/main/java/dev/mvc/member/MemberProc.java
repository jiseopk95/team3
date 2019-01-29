package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.member.MemberVO;

@Component("dev.mvc.member.MemberProc")
public class MemberProc implements MemberProcInter {
  @Autowired
  @Qualifier("dev.mvc.member.MemberDAO")
  private MemberDAOInter memberDAO = null;
  
  public MemberProc(){
    System.out.println("--> MemberProc created.");
  }

  @Override
  public int checkId(String id) {
    int cnt = memberDAO.checkId(id);
    return cnt;
  }
  
  @Override
  public int checkemail(String email) {
    int cnt = memberDAO.checkemail(email);
    return cnt;
  }

  @Override
  public int create(MemberVO memberVO) {
    int cnt = memberDAO.create(memberVO);
    return cnt;
  }
  
  @Override
  public List<MemberVO> list() {
    List<MemberVO> list = memberDAO.list();
      
    return list;
  }
  
  @Override
  public boolean isMember(HttpSession session) {
    boolean sw = false;
    
    String id = (String)session.getAttribute("id");
    
    if (id != null){
      sw = true;
    }
    return sw;
  }

  @Override
  public MemberVO read(int memberno) {
    MemberVO memberVO = memberDAO.read(memberno);
    
    return memberVO;
  }
  



  @Override
  public MemberVO readById(String id) {
    MemberVO memberVO = memberDAO.readById(id);
    
    return memberVO;
  }
  
  @Override
  public int update(MemberVO memberVO) {
    int count = memberDAO.update(memberVO);
    return count;
  }
  
 /* @Override
  public int kind_update(MemberVO memberVO) {
    int count = memberDAO.kind_update(memberVO);
    return count;
  }*/
  
  @Override
  public int passwd_update(int memberno, String passwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("memberno", memberno);
    map.put("passwd", passwd);
    
    int count = memberDAO.passwd_update(map);
    
    return count;
  }

  @Override
  public int delete(int memberno) {
    int count = memberDAO.delete(memberno);
    return count;
  }

  @Override
  public int login(String id, String passwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("passwd", passwd);
    int count = memberDAO.login(map);
    
    return count;
  }
  
  @Override
  public List<MemberVO> list_search(HashMap hashMap) {
    List<MemberVO> list = memberDAO.list_search(hashMap);
    
    int count = list.size();
    for (int i=0; i < count; i++) {
      MemberVO memberVO = list.get(i);
    }
    
    return list;
  }

  @Override
  public int search_count(HashMap hashMap) {
    return memberDAO.search_count(hashMap);
  }
  
  @Override
  public List<MemberVO> idsearch(HashMap hashMap) {
    List<MemberVO> idsearch = memberDAO.idsearch(hashMap);
    
    int count = idsearch.size();
    for (int i=0; i < count; i++) {
      MemberVO memberVO = idsearch.get(i);
    }
    
    return idsearch;
  }
  
  @Override
  public List<MemberVO> list_id() {
    List<MemberVO> list_id = memberDAO.list_id();
      
    return list_id;
  }
  
  @Override
  public int search_count2(HashMap hashMap) {
    return memberDAO.search_count2(hashMap);
  }
  
  @Override
  public List<MemberVO> passwdsearch(HashMap hashMap) {
    List<MemberVO> passwdsearch = memberDAO.passwdsearch(hashMap);
    
    int count = passwdsearch.size();
    for (int i=0; i < count; i++) {
      MemberVO memberVO = passwdsearch.get(i);
    }
    
    return passwdsearch;
  }
  @Override
  public List<MemberVO> list_passwd() {
    List<MemberVO> list_id = memberDAO.list_passwd();
      
    return list_id;
  }
  
  @Override
  public int search_count3(HashMap hashMap) {
    return memberDAO.search_count3(hashMap);
  }
}




