package dev.mvc.animalstory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.chart.ChartVO;
import dev.mvc.chart.Contents;
import nation.web.tool.Tool;

@Component("dev.mvc.animalstory.AnimalStoryProc")
public class AnimalStoryProc implements AnimalStoryProcInter{

  @Autowired
  @Qualifier("dev.mvc.animalstory.AnimalStoryDAO")
  
  private AnimalStoryDAOInter aniDAO = null;
  
  public AnimalStoryProc() {
    System.out.println("--> AnimalStoryProc created");
  }
  
  @Override
  public int create(AnimalStoryVO animalStoryVO) {
    
    return aniDAO.create(animalStoryVO);
  }
  
  @Override
  public List<AnimalStoryVO> list() {
    List<AnimalStoryVO> list = aniDAO.list();
    AnimalStoryVO aniVO = null;
    String title = "";
    String date = "";
    String anitype = "";
    
    int size = list.size();
    for(int i = 0; i < size; i++) {
      aniVO = list.get(i);

      anitype = aniVO.getAnitype();
      if(anitype.equals("1")) {
        aniVO.setAnitype("������");
      } else {
        aniVO.setAnitype("�����");
      }
      
      title = aniVO.getTitle();
      aniVO.setTitle(Tool.textLength(title, 10));
      
      date = aniVO.getRdate();
      aniVO.setRdate(date.replace("/", "-"));
      
      String thumbs = aniVO.getThumbs();
      if (thumbs.length() > 0) { // preview �̹����� �ִ��� �˻�
        String thumb = (thumbs.split("/"))[0]; // ù��° ���ϸ� ����
        aniVO.setThumb(thumb);
      }
    }
    
    
    return list;
  }
  
  /**
   * �˻� - �з�
   */
  @Override
  public List<AnimalStoryVO> list_anitype(HashMap hashmap) {
    
    List<AnimalStoryVO> list = aniDAO.list_anitype(hashmap);
    AnimalStoryVO aniVO = null;
    String title = "";
    String date = "";
    String anitype = "";
    
    int size = list.size();
    for(int i = 0; i < size; i++) {
      aniVO = list.get(i);
      
      anitype = aniVO.getAnitype();
      if(anitype.equals("1")) {
        aniVO.setAnitype("������");
      } else {
        aniVO.setAnitype("�����");
      }
      
      title = aniVO.getTitle();
      aniVO.setTitle(Tool.textLength(title, 10));
      
      date = aniVO.getRdate();
      aniVO.setRdate(date.replace("/", "-"));
      
      String thumbs = aniVO.getThumbs();
      if (thumbs.length() > 0) { // preview �̹����� �ִ��� �˻�
        String thumb = (thumbs.split("/"))[0]; // ù��° ���ϸ� ����
        aniVO.setThumb(thumb);
      }
    }
    
    
    return list;
  }

  @Override
  public String manager(int managerno) {
    
    return aniDAO.manager(managerno);
  }

  @Override
  public int delete(int anino) {
    
    return aniDAO.delete(anino);
  }

  @Override
  public AnimalStoryVO read(int anino) {
    
    AnimalStoryVO aniVO = aniDAO.read(anino);
    
    String date = "";
    
    date = aniVO.getRdate();
    aniVO.setRdate(date.replace("/", "-"));
    
    return aniVO;
  }

  /**
   * �̹��� ����߿� ù��° �̹��� ���ϸ��� �����Ͽ� ����
   * @param contentsVO
   * @return
   */
  public String getThumb(AnimalStoryVO aniVO) {
    String thumbs = aniVO.getThumbs();
    String thumb = "";
    
    if (thumbs != null) {
      String[] thumbs_array = thumbs.split("/");
      int count = thumbs_array.length;
      
      if (count > 0) {
        thumb = thumbs_array[0];    
      }
    }
    return thumb;
  }
  
  @Override
  public ArrayList<FileVO> getThumbs(AnimalStoryVO aniVO) {
    ArrayList<FileVO> file_list = new ArrayList<FileVO>();
    
    String thumbs = aniVO.getThumbs(); // xmas01_2_t.jpg/xmas02_2_t.jpg...
    String files = aniVO.getFiles();          // xmas01_2.jpg/xmas02_2.jpg...
    String sizes = aniVO.getSizes();        // 272558/404087... 
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
      
        FileVO fileVO = new FileVO(thumbs_array[index], files_array[index], sizes_array[index]);
        file_list.add(fileVO);
      }
    } 

    return file_list;
  }

  @Override
  public int update(AnimalStoryVO aniVO) {
    
    return aniDAO.update(aniVO);
  }

  /**
   * �˻� - ��ü
   */
  @Override
  public List<AnimalStoryVO> list_by_search(String content) {
    List<AnimalStoryVO> list = aniDAO.list_by_search(content);
    AnimalStoryVO aniVO = null;
    String title = "";
    String date = "";
    
    int size = list.size();
    for(int i = 0; i < size; i++) {
      aniVO = list.get(i);
      title = aniVO.getTitle();
      aniVO.setTitle(Tool.textLength(title, 10));
      date = aniVO.getRdate();
      aniVO.setRdate(date.replace("/", "-"));
      String thumbs = aniVO.getThumbs();
      if (thumbs.length() > 0) { // preview �̹����� �ִ��� �˻�
        String thumb = (thumbs.split("/"))[0]; // ù��° ���ϸ� ����
        aniVO.setThumb(thumb);
      }
    }
    
    return list;
  }

  /**
   * �˻� + ����¡
   */
  @Override
  public List<AnimalStoryVO> list_by_search_paging(HashMap<String, Object> hashMap) {
    
    /* 
    ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
    2 ������: nowPage = 2, (2 - 1) * 10 --> 10
    3 ������: nowPage = 3, (3 - 1) * 10 --> 20
    */
   int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Contents.RECORD_PER_PAGE;
   
    // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
   int startNum = beginOfPage + 1; 
   //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
   int endNum = beginOfPage + Contents.RECORD_PER_PAGE;   
   /*
    1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30
    */
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
   
   List<AnimalStoryVO> list = aniDAO.list_by_search_paging(hashMap);
   Iterator<AnimalStoryVO> iter = list.iterator();
   AnimalStoryVO aniVO = null;
   String title = "";
   String date = "";
   String thumbs = "";
   
   while(iter.hasNext() == true) {
     aniVO = iter.next();
     
     title = Tool.textLength(aniVO.getTitle(), 10);
     title = Tool.convertChar(title); // �±� ó��
     aniVO.setTitle(title);
     
     date = aniVO.getRdate();
     aniVO.setRdate(date.replace("/", "-"));
     
     thumbs = aniVO.getThumbs();
     if (thumbs.length() > 0) { // preview �̹����� �ִ��� �˻�
       String thumb = (thumbs.split("/"))[0]; // ù��° ���ϸ� ����
       aniVO.setThumb(thumb);
     }
   } 
    return list;
  }
  @Override
  public List<AnimalStoryVO> list_by_search_paging_anitype(HashMap hashMap) {
    
    /* 
    ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
    2 ������: nowPage = 2, (2 - 1) * 10 --> 10
    3 ������: nowPage = 3, (3 - 1) * 10 --> 20
    */
   int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Contents.RECORD_PER_PAGE;
   
    // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
   int startNum = beginOfPage + 1; 
   //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
   int endNum = beginOfPage + Contents.RECORD_PER_PAGE;   
   /*
    1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30
    */
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
   
   
    List<AnimalStoryVO> list = aniDAO.list_by_search_paging_anitype(hashMap);
    Iterator<AnimalStoryVO> iter = list.iterator();
    AnimalStoryVO aniVO = null;
    String title = "";
    String date = "";
    String thumbs = "";
    
    while(iter.hasNext() == true) {
      aniVO = iter.next();
      
      title = Tool.textLength(aniVO.getTitle(), 10);
      title = Tool.convertChar(title); // �±� ó��
      aniVO.setTitle(title);
      
      date = aniVO.getRdate();
      aniVO.setRdate(date.replace("/", "-"));
      
      thumbs = aniVO.getThumbs();
      if (thumbs.length() > 0) { // preview �̹����� �ִ��� �˻�
        String thumb = (thumbs.split("/"))[0]; // ù��° ���ϸ� ����
        aniVO.setThumb(thumb);
      }
    } 
    return list;
  }

  @Override
  public int search_count(HashMap hashMap) {

    return aniDAO.search_count(hashMap);
  }
  
  @Override
  public int search_count_anitype(HashMap hashMap) {
   
    return aniDAO.search_count_anitype(hashMap);
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
  public String paging(int nowPage, int search_count, String content){ 
    int totalPage = (int)(Math.ceil((double)search_count/Contents.RECORD_PER_PAGE)); // ��ü ������    
    int totalGrp = (int)(Math.ceil((double)totalPage/Contents.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Contents.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Contents.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Contents.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     System.out.println("totalPage : " + totalPage);
     System.out.println("nowGrp : " + nowGrp);
     System.out.println("totalGrp : " + totalGrp);
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
    int _nowPage = (nowGrp-1) * Contents.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list.do?nowPage="+_nowPage+"&content=" + content + "'>����</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./list.do?nowPage="+i+"&content=" + content + "'>"+i+"</A></span>");   
      } 
    } 

    // 10�� ���� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 1�׷��� ���: (1 * 10) + 1 = 2�׷��� 11
    // ���� 2�׷��� ���: (2 * 10) + 1 = 3�׷��� 21
    _nowPage = (nowGrp * Contents.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list.do?nowPage="+_nowPage+"&content=" + content + "'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
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
  public String paging_anitype(int nowPage, int search_count, String content, int anitype){  
    int totalPage = (int)(Math.ceil((double)search_count/Contents.RECORD_PER_PAGE)); // ��ü ������    
    int totalGrp = (int)(Math.ceil((double)totalPage/Contents.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Contents.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Contents.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Contents.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     System.out.println("totalPage : " + totalPage);
     System.out.println("nowGrp : " + nowGrp);
     System.out.println("totalGrp : " + totalGrp);
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
    int _nowPage = (nowGrp-1) * Contents.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list_anitype.do?nowPage="+_nowPage+"&content=" + content + "&anitype=" + anitype+"'>����</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./list_anitype.do?nowPage="+i+"&content=" + content + "&anitype=" + anitype+"'>"+i+"</A></span>");   
      } 
    } 

    // 10�� ���� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 1�׷��� ���: (1 * 10) + 1 = 2�׷��� 11
    // ���� 2�׷��� ���: (2 * 10) + 1 = 3�׷��� 21
    _nowPage = (nowGrp * Contents.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list_anitype.do?nowPage="+_nowPage+"&content=" + content + "&anitype=" + anitype+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  

}
