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
    페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1 페이지: nowPage = 1, (1 - 1) * 10 --> 0 
    2 페이지: nowPage = 2, (2 - 1) * 10 --> 10
    3 페이지: nowPage = 3, (3 - 1) * 10 --> 20
    */
   int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Survey.RECORD_PER_PAGE;
   
    // 시작 rownum, 1 페이지: 1 / 2 페이지: 11 / 3 페이지: 21 
   int startNum = beginOfPage + 1; 
   //  종료 rownum, 1 페이지: 10 / 2 페이지: 20 / 3 페이지: 30
   int endNum = beginOfPage + Survey.RECORD_PER_PAGE;   
   /*
    1 페이지: WHERE r >= 1 AND r <= 10
    2 페이지: WHERE r >= 11 AND r <= 20
    3 페이지: WHERE r >= 21 AND r <= 30
    */
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
   
   List<Manager_SurveyVO> list = surveyDAO.list_by_manager_search_paging(hashMap);
  Iterator<Manager_SurveyVO> iter = list.iterator();
   
   while(iter.hasNext() == true) {
     Manager_SurveyVO manager_surveyVO = iter.next();
     String title = Tool.textLength(manager_surveyVO.getSurvey_title(), 90);
     title = Tool.convertChar(title); // 태그 처리
     manager_surveyVO.setSurvey_title(title);
     
   }
   
   return list;
 }
  
  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param categoryno 카테고리번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  @Override
  public String paging(int managerno, int search_count, int nowPage, String survey_title){ 
    int totalPage = (int)(Math.ceil((double)search_count/Survey.RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/Survey.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Survey.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * Survey.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * Survey.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
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
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 

    // 이전 10개 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 2그룹일 경우: (2 - 1) * 10 = 1그룹의 10
    // 현재 3그룹일 경우: (3 - 1) * 10 = 2그룹의 20
    int _nowPage = (nowGrp-1) * Survey.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list_by_manager_search_paging.do?&survey_title="+survey_title+"&nowPage="+_nowPage+"&managerno="+managerno+"'>이전</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지
        str.append("<span class='span_box_1'><A href='./list_by_manager_search_paging.do?&survey_title="+survey_title+"&nowPage="+i+"&managerno="+managerno+"'>"+i+"</A></span>");   
      } 
    } 

    // 10개 다음 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 1그룹일 경우: (1 * 10) + 1 = 2그룹의 11
    // 현재 2그룹일 경우: (2 * 10) + 1 = 3그룹의 21
    _nowPage = (nowGrp *Survey.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list_by_manager_search_paging.do?&survey_title="+survey_title+"&nowPage="+_nowPage+"&managerno="+managerno+"'>다음</A></span>"); 
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

