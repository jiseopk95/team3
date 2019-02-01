package dev.mvc.pet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dev.mvc.pet.PetVO;
import dev.mvc.manager.Manager;
import dev.mvc.manager.ManagerVO;
import dev.mvc.member.Member;
import dev.mvc.member.MemberVO;
import dev.mvc.pet.PetFileVO;
import nation.web.tool.Tool;


@Component("dev.mvc.pet.PetProc")
public class PetProc implements PetProcInter {
  @Autowired
  @Qualifier("dev.mvc.pet.PetDAO")
  private PetDAOInter petDAO = null;
  
  public PetProc(){
    System.out.println("--> PetProc created.");
  }


  @Override
  public int create(PetVO petVO) {
    int cnt =petDAO.create(petVO);
    return cnt;
  }
  
  @Override
  public List<PetVO> list() {
    List<PetVO> list = petDAO.list();
      
    return list;
  }
  
  /*@Override
  public boolean isMember(HttpSession session) {
    boolean sw = false;
    
    String id = (String)session.getAttribute("id");
    
    if (id != null){
      sw = true;
    }
    return sw;
  }*/

  @Override
  public PetVO read(int petno) {
    PetVO petVO = petDAO.read(petno);
    
    return petVO;
  }
  

 /* @Override
  public PetVO readById(String id) {
    PetVO petVO = petDAO.readById(id);
    
    return petVO;
  }*/
  
  @Override
  public int update(PetVO petVO) {
    int count = petDAO.update(petVO);
    return count;
  }
  
  @Override
  public int delete(int petno) {
    int count = petDAO.delete(petno);
    return count;
  }
  
  @Override
  public ArrayList<PetFileVO> getThumbs(PetVO petVO) {
    ArrayList<PetFileVO> file_list = new ArrayList<PetFileVO>();
    
    String thumbs = petVO.getThumbs(); // xmas01_2_t.jpg/xmas02_2_t.jpg...
    String files = petVO.getFiles();          // xmas01_2.jpg/xmas02_2.jpg...
    String sizes = petVO.getFilesizes();        // 272558/404087... 
    
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
      
        PetFileVO fileVO = new PetFileVO(thumbs_array[index], files_array[index], sizes_array[index]);
        file_list.add(fileVO);
      }
    } 

    return file_list;
  }
  
  @Override
  public List<PetVO> list_search(HashMap<String, Object> hashMap) {
    
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
   
    List<PetVO> list = petDAO.list_search(hashMap);
    Iterator<PetVO> iter = list.iterator();
    
    while(iter.hasNext() == true) {
      PetVO petVO = iter.next();
      String name = Tool.textLength(petVO.getName(), 90);
      name = Tool.convertChar(name); // �±� ó��
      petVO.setName(name);
      
      String thumbs = petVO.getThumbs();
      if (thumbs.length() > 0) { // preview �̹����� �ִ��� �˻�
        String thumb = (thumbs.split("/"))[0]; // ù��° ���ϸ� ����
        petVO.setThumbs(thumb);
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
    return petDAO.search_count(hashMap);
  }
  
  /*@Override
  public PetVO pet_list(int memberno) {
    PetVO petVO = petDAO.pet_list(memberno);
    
    return petVO;
  }*/
  
  /**
   * ���� �� ��ȸ
   */
    @Override
    public List<PetVO> pet_list(HashMap hashMap) {
      List<PetVO> pet_list = petDAO.pet_list(hashMap);
      
      int count = pet_list.size();
      for (int i=0; i < count; i++) {
        PetVO petVO = pet_list.get(i);
      }
      
      return pet_list;
    }
    
    @Override
    public int search_count2(HashMap hashMap) {
      return petDAO.search_count2(hashMap);
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
      int totalPage = (int)(Math.ceil((double)search_count/Pet.RECORD_PER_PAGE)); // ��ü ������  
      int totalGrp = (int)(Math.ceil((double)totalPage/Pet.PAGE_PER_BLOCK));// ��ü �׷� 
      int nowGrp = (int)(Math.ceil((double)nowPage/Pet.PAGE_PER_BLOCK));    // ���� �׷� 
      int startPage = ((nowGrp - 1) * Pet.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
      int endPage = (nowGrp * Pet.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
       
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
//      str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 

      // ���� 10�� �������� �̵�
      // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
      // ���� 2�׷��� ���: (2 - 1) * 10 = 1�׷��� 10
      // ���� 3�׷��� ���: (3 - 1) * 10 = 2�׷��� 20
      int _nowPage = (nowGrp-1) * Pet.PAGE_PER_BLOCK;  
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
      _nowPage = (nowGrp * Pet.PAGE_PER_BLOCK)+1;  
      if (nowGrp < totalGrp){ 
        str.append("<span class='span_box_1'><A href='./list_search.do?&name="+name+"&nowPage="+_nowPage+"'>����</A></span>"); 
      } 
      str.append("</DIV>"); 
       
      return str.toString(); 
    }
}




