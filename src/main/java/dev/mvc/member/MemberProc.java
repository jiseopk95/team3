package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.member.MemberVO;
import nation.web.tool.Tool;

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
  public MemberVO read2(String email) {
    MemberVO memberVO = memberDAO.read2(email);
    
    return memberVO;
  }
  
  @Override
  public MemberVO read3(String email) {
    MemberVO memberVO = memberDAO.read3(email);
    
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
  public List<MemberVO> list_search(HashMap<String, Object> hashMap) {
    
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Member.RECORD_PER_PAGE;
    
    // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
   int startNum = beginOfPage + 1; 
   //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
   int endNum = beginOfPage + Member.RECORD_PER_PAGE;   
   /*
    1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30
    */
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
   
    List<MemberVO> list = memberDAO.list_search(hashMap);
    Iterator<MemberVO> iter = list.iterator();
    
    while(iter.hasNext() == true) {
      MemberVO memberVO = iter.next();
      String name = Tool.textLength(memberVO.getName(), 90);
      name = Tool.convertChar(name); // �±� ó��
      memberVO.setName(name);
    }
    
  /*  int count = list.size();
    for (int i=0; i < count; i++) {
      MemberVO memberVO = list.get(i);
    }*/
    
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
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  @Override
  public String paging(int search_count, int nowPage, String name){ 
    int totalPage = (int)(Math.ceil((double)search_count/Member.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Member.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Member.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Member.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Member.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 

    // ���� 10�� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 2�׷��� ���: (2 - 1) * 10 = 1�׷��� 10
    // ���� 3�׷��� ���: (3 - 1) * 10 = 2�׷��� 20
    int _nowPage = (nowGrp-1) * Member.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list_search.do?&name="+name+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./list_search.do?name="+name+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 

    // 10�� ���� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 1�׷��� ���: (1 * 10) + 1 = 2�׷��� 11
    // ���� 2�׷��� ���: (2 * 10) + 1 = 3�׷��� 21
    _nowPage = (nowGrp * Member.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list_search.do?&name="+name+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
}




