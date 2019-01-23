package dev.mvc.survey;


import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import nation.web.tool.Tool;


@Component("dev.mvc.survey.SurveyProc")
public class SurveyProc implements SurveyProcInter{

  @Autowired
  @Qualifier("dev.mvc.survey.SurveyDAO")
  
  private SurveyDAOInter surveyDAO = null;
  
  public SurveyProc(){
    System.out.println("--> SurveyProc created.");
  }
  

  @Override
  public int create(SurveyVO surveyVO) {
    return surveyDAO.create(surveyVO);
  }


  @Override
  public List<Manager_SurveyVO> list(int managerno) {
    return surveyDAO.list(managerno);
  }


  @Override
  public Manager_SurveyVO read(int surveyno) {
    return surveyDAO.read(surveyno);
  }


  @Override
  public int update(SurveyVO surveyVO) {
    return surveyDAO.update(surveyVO);
  }


  @Override
  public int delete(int surveyno) {
    return surveyDAO.delete(surveyno);
  }


  @Override
  public int update_seqno_up(int surveyno) {
    return surveyDAO.update_seqno_up(surveyno);
  }


  @Override
  public int update_seqno_down(int surveyno) {
    return surveyDAO.update_seqno_down(surveyno);
  }
  
  @Override
  public int increaseCnt(int surveyno) {
    return surveyDAO.increaseCnt(surveyno);
  }

  @Override
  public int decreaseCnt(int surveyno) {
    return surveyDAO.decreaseCnt(surveyno);
  }


  @Override
  public List<FindVO> findNo(int surveyno) {
   
    return surveyDAO.findNo(surveyno);
    
    
  }


  @Override
  public List<Manager_SurveyVO> list_by_manager_search(HashMap hashMap) {
    
    return surveyDAO.list_by_manager_search(hashMap);
  }
  @Override
  public int search_count(HashMap hashMap) {
    
    return surveyDAO.search_count(hashMap);
  }


  @Override
  public List<Manager_SurveyVO> list_by_manager_search_paging(HashMap<String, Object> hashMap) {
    
    /* 
    ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
    2 ������: nowPage = 2, (2 - 1) * 10 --> 10
    3 ������: nowPage = 3, (3 - 1) * 10 --> 20
    */
   int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Survey.RECORD_PER_PAGE;
   
    // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
   int startNum = beginOfPage + 1; 
   //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
   int endNum = beginOfPage + Survey.RECORD_PER_PAGE;   
   /*
    1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30
    */
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
   
   List<Manager_SurveyVO> list = surveyDAO.list_by_manager_search_paging(hashMap);
  Iterator<Manager_SurveyVO> iter = list.iterator();
   
   while(iter.hasNext() == true) {
     Manager_SurveyVO manager_surveyVO = iter.next();
     String title = Tool.textLength(manager_surveyVO.getSurvey_title(), 90);
     title = Tool.convertChar(title); // �±� ó��
     manager_surveyVO.setSurvey_title(title);
     
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
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  @Override
  public String paging(int managerno, int search_count, int nowPage, String survey_title){ 
    int totalPage = (int)(Math.ceil((double)search_count/Survey.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Survey.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Survey.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Survey.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Survey.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
    int _nowPage = (nowGrp-1) * Survey.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list_by_manager_search_paging.do?&survey_title="+survey_title+"&nowPage="+_nowPage+"&managerno="+managerno+"'>����</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./list_by_manager_search_paging.do?&survey_title="+survey_title+"&nowPage="+i+"&managerno="+managerno+"'>"+i+"</A></span>");   
      } 
    } 

    // 10�� ���� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 1�׷��� ���: (1 * 10) + 1 = 2�׷��� 11
    // ���� 2�׷��� ���: (2 * 10) + 1 = 3�׷��� 21
    _nowPage = (nowGrp *Survey.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list_by_manager_search_paging.do?&survey_title="+survey_title+"&nowPage="+_nowPage+"&managerno="+managerno+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }


  @Override
  public List<SurveyVO> list_m() {
   
    return surveyDAO.list_m();
  }


  @Override
  public SurveyVO read_m(int surveyno) {
    // TODO Auto-generated method stub
    return surveyDAO.read_m(surveyno);
  }


}

