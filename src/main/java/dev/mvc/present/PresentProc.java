package dev.mvc.present;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nation.web.tool.Tool;

@Component("dev.mvc.present.PresentProc") 
public class PresentProc implements PresentProcInter {
  @Autowired  
  @Qualifier("dev.mvc.present.PresentDAO")
  private PresentDAOInter presentDAO = null;
   
  public PresentProc() { 
    System.out.println("--> PresentProc created.");
  }
    
  @Override 
  public int create(PresentVO presentVO) {
    return presentDAO.create(presentVO);
  }
  @Override
  public List<PresentVO> list_all_present() {
    return presentDAO.list_all_present();
  }
@Override
public int update(PresentVO presentVO) {
  return presentDAO.update(presentVO);
}
@Override
public int delete(int presentno) {
  return presentDAO.delete(presentno); 
}
@Override
public PresentVO read(int presentno) {
  return presentDAO.read(presentno);
}

/*@Override
public int search_count(HashMap hashMap) {
  return presentDAO.search_count(hashMap);
}*/

/** 
 * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
 * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
 *
 * @param categoryno 카테고리번호 
 * @param search_count 검색(전체) 레코드수 
 * @param nowPage     현재 페이지
 * @param title
 * @return 페이징 생성 문자열
 */
//@Override
/*public String paging(int search_count, int nowPage){ 
  int totalPage = (int)(Math.ceil((double)search_count/Present.RECORD_PER_PAGE)); // 전체 페이지  
  int totalGrp = (int)(Math.ceil((double)totalPage/Present.PAGE_PER_BLOCK));// 전체 그룹 
  int nowGrp = (int)(Math.ceil((double)nowPage/Present.PAGE_PER_BLOCK));    // 현재 그룹 
  int startPage = ((nowGrp - 1) * Present.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
  int endPage = (nowGrp * Present.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
   */
 /* StringBuffer str = new StringBuffer(); 
   
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
  str.append("    border-color: #cccccc;"); */
  //str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/");
  //str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
//  str.append("  }"); 
//  str.append("  .span_box_2{"); 
//  str.append("    text-align: center;");    
//  str.append("    background-color: #668db4;"); 
//  str.append("    color: #FFFFFF;"); 
//  str.append("    font-size: 1em;"); 
//  str.append("    border: 1px;"); 
//  str.append("    border-style: solid;"); 
//  str.append("    border-color: #cccccc;"); 
//  str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
//  str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
//  str.append("  }"); 
//  str.append("</style>"); 
//  str.append("<DIV id='paging'>"); 
//  str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 

  // 이전 10개 페이지로 이동
  // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
  // 현재 2그룹일 경우: (2 - 1) * 10 = 1그룹의 10
  // 현재 3그룹일 경우: (3 - 1) * 10 = 2그룹의 20
//  int _nowPage = (nowGrp-1) * Present.PAGE_PER_BLOCK;  
//  if (nowGrp >= 2){ 
//    str.append("<span class='span_box_1'><A href='./paging.do?&nowPage="+_nowPage+"'>이전</A></span>"); 
//  } 
//
//  for(int i=startPage; i<=endPage; i++){ 
//    if (i > totalPage){ 
//      break; 
//    } 
//
//    if (nowPage == i){ 
//      str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
//    }else{
//      // 현재 페이지가 아닌 페이지
//      str.append("<span class='span_box_1'><A href='./paging.do?&nowPage="+_nowPage+"'>"+i+"</A></span>");   
//    } 
//  } 
//
//  // 10개 다음 페이지로 이동
//  // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
//  // 현재 1그룹일 경우: (1 * 10) + 1 = 2그룹의 11
//  // 현재 2그룹일 경우: (2 * 10) + 1 = 3그룹의 21
//  _nowPage = (nowGrp * Present.PAGE_PER_BLOCK)+1;  
//  if (nowGrp < totalGrp){ 
//    str.append("<span class='span_box_1'><A href='./paging.do?&nowPage="+_nowPage+"'>다음</A></span>"); 
//  }
//
//  @Override
//  public String paging(int search_count, int nowPage) {
//    // TODO Auto-generated method stub
//    return null;
//  } 
//  str.append("</DIV>"); 
//   
//  return str.toString(); 
//}
}
