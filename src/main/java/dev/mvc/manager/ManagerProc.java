package dev.mvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.manager.File2VO;
import dev.mvc.manager.ManagerVO;
import dev.mvc.member.Member;
import dev.mvc.member.MemberVO;
import nation.web.tool.Tool;

@Component("dev.mvc.manager.ManagerProc")
public class ManagerProc implements ManagerProcInter {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerDAO")
  private ManagerDAOInter managerDAO = null;
  
  public ManagerProc(){
    System.out.println("--> ManagerProc created.");
  }

  @Override
  public int checkId(String id) {
    int cnt = managerDAO.checkId(id);
    return cnt;
  }
  
  @Override
  public int checkemail(String email) {
    int cnt = managerDAO.checkemail(email);
    return cnt;
  }

  @Override
  public int create(ManagerVO managerVO) {
    int cnt = managerDAO.create(managerVO);
    return cnt;
  }
  
  @Override
  public List<ManagerVO> list() {
    List<ManagerVO> list = managerDAO.list();
      
    return list;
  }
  
  @Override
  public boolean isManager(HttpSession session) {
    boolean sw = false;
    
    String id = (String)session.getAttribute("id");
    
    if (id != null){
      sw = true;
    }
    return sw;
  }

  @Override
  public ManagerVO read(int managerno) {
    ManagerVO managerVO = managerDAO.read(managerno);
    
    return managerVO;
  }
  
  @Override
  public ManagerVO read2(String email) {
    ManagerVO managerVO = managerDAO.read2(email);
    
    return managerVO;
  }
  
  @Override
  public ManagerVO read3(String email) {
    ManagerVO managerVO = managerDAO.read3(email);
    
    return managerVO;
  }
  
  @Override
  public ManagerVO kind_update(int managerno) {
    ManagerVO managerVO = managerDAO.kind_update(managerno);
    
    return managerVO;
  }

  @Override
  public ManagerVO readById(String id) {
    ManagerVO managerVO = managerDAO.readById(id);
    
    return managerVO;
  }
  
  @Override
  public int update(ManagerVO managerVO) {
    int count = managerDAO.update(managerVO);
    return count;
  }
  
  @Override
  public int kind_update2(ManagerVO managerVO) {
    int count = managerDAO.kind_update2(managerVO);
    return count;
  }
  
 /* @Override
  public int kind_update(MemberVO memberVO) {
    int count = memberDAO.kind_update(memberVO);
    return count;
  }*/
  
  @Override
  public int passwd_update(int managerno, String passwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("managerno", managerno);
    map.put("passwd", passwd);
    
    int count = managerDAO.passwd_update(map);
    
    return count;
  }

  @Override
  public int delete(int managerno) {
    int count = managerDAO.delete(managerno);
    return count;
  }

  @Override
  public int login(String id, String passwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("passwd", passwd);
    int count = managerDAO.login(map);
    
    return count;
  }
  @Override
  public ArrayList<File2VO> getThumbs(ManagerVO managerVO) {
    ArrayList<File2VO> file_list = new ArrayList<File2VO>();
    
    String thumbs = managerVO.getThumbs(); // xmas01_2_t.jpg/xmas02_2_t.jpg...
    String files = managerVO.getFiles();          // xmas01_2.jpg/xmas02_2.jpg...
    String sizes = managerVO.getFilesizes();        // 272558/404087... 
    
    String[] thumbs_array = thumbs.split("/");  // Thumbs
    String[] files_array = files.split("/");   // ���ϸ� ����
    String[] sizes_array = sizes.split("/"); // ���� ������
 
    int count = sizes_array.length;
    // System.out.println("sizes_array.length: " + sizes_array.length);
    // System.out.println("sizes: " + sizes);
    // System.out.println("files: " + files);
 
    if (files.length() > 0) {
      for (int index = 0; index < count; index++) {
        sizes_array[index] = Tool.unit(new Integer(sizes_array[index]));  // 1024 -> 1KB
      
        File2VO fileVO = new File2VO(thumbs_array[index], files_array[index], sizes_array[index]);
        file_list.add(fileVO);
      }
    } 

    return file_list;
  }
  
  @Override
  public List<ManagerVO> list_search(HashMap<String, Object> hashMap) {
    
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
   
    List<ManagerVO> list = managerDAO.list_search(hashMap);
    Iterator<ManagerVO> iter = list.iterator();
    
    while(iter.hasNext() == true) {
      ManagerVO managerVO = iter.next();
      String name = Tool.textLength(managerVO.getName(), 90);
      name = Tool.convertChar(name); // �±� ó��
      managerVO.setName(name);
      
      String thumbs = managerVO.getThumbs();
      if (thumbs.length() > 0) { // preview �̹����� �ִ��� �˻�
        String thumb = (thumbs.split("/"))[0]; // ù��° ���ϸ� ����
        managerVO.setThumbs(thumb);
      }
    }
    
  /*  int count = list.size();
    for (int i=0; i < count; i++) {
      MemberVO memberVO = list.get(i);
    }*/
    
    return list;
  }

  @Override
  public int search_count(HashMap hashMap) {
    return managerDAO.search_count(hashMap);
  }
  
  @Override
  public List<ManagerVO> idsearch(HashMap hashMap) {
    List<ManagerVO> idsearch = managerDAO.idsearch(hashMap);
    
    int count = idsearch.size();
    for (int i=0; i < count; i++) {
      ManagerVO managerVO = idsearch.get(i);
    }
    
    return idsearch;
  }
  
  @Override
  public List<ManagerVO> list_id() {
    List<ManagerVO> list_id = managerDAO.list_id();
      
    return list_id;
  }
  
  @Override
  public int search_count2(HashMap hashMap) {
    return managerDAO.search_count2(hashMap);
  }
  
  @Override
  public List<ManagerVO> passwdsearch(HashMap hashMap) {
    List<ManagerVO> passwdsearch = managerDAO.passwdsearch(hashMap);
    
    int count = passwdsearch.size();
    for (int i=0; i < count; i++) {
      ManagerVO managerVO = passwdsearch.get(i);
    }
    
    return passwdsearch;
  }
  @Override
  public List<ManagerVO> list_passwd() {
    List<ManagerVO> list_id = managerDAO.list_passwd();
      
    return list_id;
  }
  
  
  @Override
  public int search_count3(HashMap hashMap) {
    return managerDAO.search_count3(hashMap);
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
    int totalPage = (int)(Math.ceil((double)search_count/Manager.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Manager.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Manager.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Manager.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Manager.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
    _nowPage = (nowGrp * Manager.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list_search.do?&name="+name+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  
}




