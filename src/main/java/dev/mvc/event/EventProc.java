package dev.mvc.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.event.EventFileVO;
import nation.web.tool.Tool;

@Component("dev.mvc.event.EventProc") 
public class EventProc implements EventProcInter {
  @Autowired  
  @Qualifier("dev.mvc.event.EventDAO")
  private EventDAOInter eventDAO = null;
   
  public EventProc() { 
    System.out.println("--> EventProc created.");
  }
  
  
  @Override
  public int select_user(HashMap<String, Object> hashMap) {
    return eventDAO.select_user(hashMap);
  }

  
  
  @Override 
  public int create(EventVO eventVO) { 
    return eventDAO.create(eventVO);
  }

  @Override
  public List<EventVO> list_all_event() {
    return eventDAO.list_all_event();
  }
  @Override
  public event_managerVO read_manager(int eventno) {
    return eventDAO.read_manager(eventno);   
  } 
  @Override
  public EventVO read(int eventno) {
    return eventDAO.read(eventno);
  }

  @Override
  public int delete(int eventno) {
    return eventDAO.delete(eventno);
  }
  
  @Override
  public ArrayList<EventFileVO> getThumbs(EventVO eventVO) {
    ArrayList<EventFileVO> file_list = new ArrayList<EventFileVO>();
    
    String thumbs = eventVO.getThumb(); // xmas01_2_t.jpg/xmas02_2_t.jpg...
    String files = eventVO.getImage();          // xmas01_2.jpg/xmas02_2.jpg...
    String sizes = eventVO.getImage_size();        // 272558/404087... 
    
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
      
        EventFileVO fileVO = new EventFileVO(thumbs_array[index], files_array[index], sizes_array[index]);
        file_list.add(fileVO);
      }
    } 

    return file_list;
  }

  @Override
  public int update(EventVO eventVO) {
    return eventDAO.update(eventVO);
  }
  @Override
  public int search_count(HashMap hashMap) {
    return eventDAO.search_count(hashMap);
  }
  @Override
  public List<event_managerVO> search_paging(HashMap<String, Object> hashMap) {
    /* 
     ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
     1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
     2 ������: nowPage = 2, (2 - 1) * 10 --> 10
     3 ������: nowPage = 3, (3 - 1) * 10 --> 20
     */
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Event.RECORD_PER_PAGE;
    
     // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
    int startNum = beginOfPage + 1; 
    //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
    int endNum = beginOfPage + Event.RECORD_PER_PAGE;   
    /*
     1 ������: WHERE r >= 1 AND r <= 10
     2 ������: WHERE r >= 11 AND r <= 20
     3 ������: WHERE r >= 21 AND r <= 30
     */
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List<event_managerVO> list = eventDAO.search_paging(hashMap); 
    Iterator<event_managerVO> iter = list.iterator();
    
    while(iter.hasNext() == true) {
      event_managerVO event_managerVO = iter.next();
      String title = Tool.textLength(event_managerVO.getTitle(), 90);
      title = Tool.convertChar(title); // �±� ó��
      event_managerVO.setTitle(title);
      
      String thumbs = event_managerVO.getThumb();
      if (thumbs.length() > 0) { // preview �̹����� �ִ��� �˻�
        String thumb = (thumbs.split("/"))[0]; // ù��° ���ϸ� ����
        event_managerVO.setThumb(thumb);
      }
    }
    
    return list;
  }
  
  @Override
  public List<EventVO> search_paging_member(HashMap<String, Object> hashMap) {
    /* 
     ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
     1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
     2 ������: nowPage = 2, (2 - 1) * 10 --> 10
     3 ������: nowPage = 3, (3 - 1) * 10 --> 20
     */
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Event.RECORD_PER_PAGE;
    
     // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
    int startNum = beginOfPage + 1;
    //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
    int endNum = beginOfPage + Event.RECORD_PER_PAGE;   
    /*
     1 ������: WHERE r >= 1 AND r <= 10
     2 ������: WHERE r >= 11 AND r <= 20
     3 ������: WHERE r >= 21 AND r <= 30
     */
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List<EventVO> list = eventDAO.search_paging_member(hashMap); 
    Iterator<EventVO> iter = list.iterator();
    
    while(iter.hasNext() == true) {
      EventVO eventVO = iter.next();
      String title = Tool.textLength(eventVO.getTitle(), 90);
      title = Tool.convertChar(title); // �±� ó��
      eventVO.setTitle(title);
      
      String thumbs = eventVO.getThumb();
      if (thumbs.length() > 0) { // preview �̹����� �ִ��� �˻�
        String thumb = (thumbs.split("/"))[0]; // ù��° ���ϸ� ����
        eventVO.setThumb(thumb);
      }
    }
    
    return list;
  }
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param title
   * @return ����¡ ���� ���ڿ�
   */ 
  @Override
  public String paging(int managerno,int search_count, int nowPage, String content){ 
    int totalPage = (int)(Math.ceil((double)search_count/Event.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Event.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Event.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Event.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Event.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
    int _nowPage = (nowGrp-1) * Event.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./search_paging.do?content="+content+"&nowPage="+_nowPage+"&managerno="+managerno+"'>����</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./search_paging.do?content="+content+"&nowPage="+i+"&managerno="+managerno+"'>"+i+"</A></span>");   
      } 
    } 

    // 10�� ���� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 1�׷��� ���: (1 * 10) + 1 = 2�׷��� 11
    // ���� 2�׷��� ���: (2 * 10) + 1 = 3�׷��� 21
    _nowPage = (nowGrp * Event.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./search_paging.do?content="+content+"&nowPage="+_nowPage+"&managerno="+managerno+"'>����</A></span>"); 
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
   * @param title
   * @return ����¡ ���� ���ڿ�
   */ 
  @Override
  public String paging_member(int memberno,int search_count, int nowPage, String content){ 
    int totalPage = (int)(Math.ceil((double)search_count/Event.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Event.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Event.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Event.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Event.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
    int _nowPage = (nowGrp-1) * Event.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./search_paging_member.do?content="+content+"&nowPage="+_nowPage+"&memberno="+memberno+"'>����</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./search_paging_member.do?content="+content+"&nowPage="+i+"&memberno="+memberno+"'>"+i+"</A></span>");   
      } 
    } 

    // 10�� ���� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 1�׷��� ���: (1 * 10) + 1 = 2�׷��� 11
    // ���� 2�׷��� ���: (2 * 10) + 1 = 3�׷��� 21
    _nowPage = (nowGrp * Event.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./search_paging_member.do?content="+content+"&nowPage="+_nowPage+"&memberno="+memberno+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  @Override
  public List<event_presentVO> list_present() {
/*List<event_presentVO> list = eventDAO.list_present();
    
    event_presentVO presentVO = null;
    int size = list.size();
    for(int i = 0; i<size; i++){
      presentVO = list.get(i);
      System.out.print("presentVO : "+presentVO.getInfo());
    }
    return list;*/
    return eventDAO.list_present();
  }

  @Override
  public List<event_eventuserVO> read_member() {
    return eventDAO.read_member();
  }

  @Override
  public int usercreate(event_eventuserVO euVO) {
    return eventDAO.usercreate(euVO);
  }

  @Override
  public ArrayList<EventFileVO> getThumbs(event_managerVO event_managerVO) {
      ArrayList<EventFileVO> file_list = new ArrayList<EventFileVO>();
      
      String thumbs = event_managerVO.getThumb(); // xmas01_2_t.jpg/xmas02_2_t.jpg...
      String files = event_managerVO.getImage();          // xmas01_2.jpg/xmas02_2.jpg...
      String sizes = event_managerVO.getImage_size();        // 272558/404087... 
      
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
        
          EventFileVO fileVO = new EventFileVO(thumbs_array[index], files_array[index], sizes_array[index]);
          file_list.add(fileVO);
        }
      } 

      return file_list;
    }

  @Override
  public event_eventuserVO read_win(int eventno) {
    return eventDAO.read_win(eventno);
  }


  
//------------------������� ��÷�� Ȯ�� ������ ����-------------------------//
  @Override
  public List<event_eventuserVO> search_paging_win(HashMap<String, Object> hashMap) {
    /* 
    ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
    2 ������: nowPage = 2, (2 - 1) * 10 --> 10
    3 ������: nowPage = 3, (3 - 1) * 10 --> 20
    */
   int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Event.RECORD_PER_PAGE;
   
    // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
   int startNum = beginOfPage + 1; 
   //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
   int endNum = beginOfPage + Event.RECORD_PER_PAGE;   
   /*
    1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30
    */
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
   
   List<event_eventuserVO> list = eventDAO.search_paging_win(hashMap); 
   Iterator<event_eventuserVO> iter = list.iterator();
   
   /*while(iter.hasNext() == true) {
     event_eventuserVO event_eventuserVO = iter.next();
     String title = Tool.textLength(event_eventuserVO.getTitle(), 90);
     title = Tool.convertChar(title); // �±� ó��
     event_eventuserVO.setTitle(title);*/
     
/*     String thumbs = event_eventuserVO.getThumb();
     if (thumbs.length() > 0) { // preview �̹����� �ִ��� �˻�
       String thumb = (thumbs.split("/"))[0]; // ù��° ���ϸ� ����
       event_eventuserVO.setThumb(thumb);
     }*/
   //}
   
   return list;
  }

  
  @Override
  public int search_count_win(HashMap hashMap) {
    return eventDAO.search_count_win(hashMap);
  }


  @Override
  public String paging_win(int eventno, int search_count, int nowPage, String id) {
    int totalPage = (int)(Math.ceil((double)search_count/Event.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Event.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Event.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Event.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Event.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
    int _nowPage = (nowGrp-1) * Event.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./search_paging_win.do?id="+id+"&nowPage="+_nowPage+"&eventno="+eventno+"'>����</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./search_paging_win.do?id="+id+"&nowPage="+i+"&eventno="+eventno+"'>"+i+"</A></span>");   
      } 
    } 

    // 10�� ���� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 1�׷��� ���: (1 * 10) + 1 = 2�׷��� 11
    // ���� 2�׷��� ���: (2 * 10) + 1 = 3�׷��� 21
    _nowPage = (nowGrp * Event.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./search_paging_win.do?id="+id+"&nowPage="+_nowPage+"&eventno="+eventno+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }


}