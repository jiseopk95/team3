package dev.mvc.chart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nation.web.tool.Tool;


@Component("dev.mvc.chart.ChartProc")
public class ChartProc implements ChartProcInter{

  @Autowired
  @Qualifier("dev.mvc.chart.ChartDAO")
  
  private ChartDAOInter chartDAO = null;

  public ChartProc(){
    System.out.println("--> ChartProc created.");
  }

  @Override
  public List<ChartVO> list() {
    List<ChartVO> list = chartDAO.list();
    ChartVO chartVO = null;
  
    int count = list.size();
    String date = "";
    
    for(int i = 0; i < count; i++) {
      chartVO = list.get(i);
      date = chartVO.getRdate();
      chartVO.setRdate(date.replace("/", "-"));
    }
    return list;
  }

  @Override
  public ChartVO read(int chartno) {
    ChartVO chartVO = chartDAO.read(chartno);
    String date = "";
    date = chartVO.getRdate();
    chartVO.setRdate(date.replace("/", "-"));
    return chartVO;
  }

  @Override
  public int delete(int chartno) {
    
    return chartDAO.delete(chartno);
  }

  @Override
  public Pet_infoVO pet_info(int petno) {

    return chartDAO.pet_info(petno);
  }

  @Override
  public Member_infoVO member_info(int memberno) {
    
    return chartDAO.member_info(memberno);
  }

  @Override
  public int create(ChartVO chartVO) {
    
    return chartDAO.create(chartVO);
  }

  @Override
  public int update(ChartVO chartVO) {
    
    return chartDAO.update(chartVO);
  }

//  검색 가능한 리스트
  @Override
  public List<ChartVO> list_by_search(HashMap hashMap) {
   
    return chartDAO.list_by_search(hashMap);
  }
  
  @Override
  public int search_count(HashMap hashMap) {
    return chartDAO.search_count(hashMap);
  }

  @Override
  public List<ChartVO> list_by_search_paging(HashMap<String, Object> hashMap) {
    /* 
     페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
     1 페이지: nowPage = 1, (1 - 1) * 10 --> 0 
     2 페이지: nowPage = 2, (2 - 1) * 10 --> 10
     3 페이지: nowPage = 3, (3 - 1) * 10 --> 20
     */
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * chart_paging.RECORD_PER_PAGE;
    
     // 시작 rownum, 1 페이지: 1 / 2 페이지: 11 / 3 페이지: 21 
    int startNum = beginOfPage + 1; 
    //  종료 rownum, 1 페이지: 10 / 2 페이지: 20 / 3 페이지: 30
    int endNum = beginOfPage + chart_paging.RECORD_PER_PAGE;   
    /*
     1 페이지: WHERE r >= 1 AND r <= 10
     2 페이지: WHERE r >= 11 AND r <= 20
     3 페이지: WHERE r >= 21 AND r <= 30
     */
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List<ChartVO> list = chartDAO.list_by_search_paging(hashMap); 
    Iterator<ChartVO> iter = list.iterator();
    
    while(iter.hasNext() == true) {
      ChartVO chartVO = iter.next();
      String title = Tool.textLength(chartVO.getTitle(), 90);
      title = Tool.convertChar(title); // 태그 처리
      chartVO.setTitle(title);
      
      /*String thumbs = chartVO.getThumbs();
      if (thumbs.length() > 0) { // preview 이미지가 있는지 검사
        String thumb = (thumbs.split("/"))[0]; // 첫번째 파일명 추출
        chartVO.setThumb(thumb);
      }*/
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
  public String paging(int managerno, int nowPage, int search_count, String petname, String name){ 
    int totalPage = (int)(Math.ceil((double)search_count/chart_paging.RECORD_PER_PAGE)); // 전체 페이지    
    int totalGrp = (int)(Math.ceil((double)totalPage/chart_paging.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/chart_paging.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * chart_paging.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * chart_paging.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
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
    int _nowPage = (nowGrp-1) * chart_paging.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list.do?petname="+petname+"&name="+name+"&nowPage="+_nowPage+"&managerno="+managerno+"'>이전</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지
        str.append("<span class='span_box_1'><A href='./list.do?petname="+petname+"&name="+name+"&nowPage="+i+"&managerno="+managerno+"'>"+i+"</A></span>");   
      } 
    } 

    // 10개 다음 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 1그룹일 경우: (1 * 10) + 1 = 2그룹의 11
    // 현재 2그룹일 경우: (2 * 10) + 1 = 3그룹의 21
    _nowPage = (nowGrp * chart_paging.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list.do?petname="+petname+"&name="+name+"&nowPage="+_nowPage+"&managerno="+managerno+"'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  
}
