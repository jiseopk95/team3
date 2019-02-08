package dev.mvc.beauty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.beauty.BeaFileVO;
import nation.web.tool.Tool;

@Component("dev.mvc.beauty.BeautyProc") 
public class BeautyProc implements BeautyProcInter {
  @Autowired  
  @Qualifier("dev.mvc.beauty.BeautyDAO")
  private BeautyDAOInter beautyDAO = null;
   
  public BeautyProc() { 
    System.out.println("--> BeautyProc created.");
  }
   
  @Override 
  public int create(BeautyVO beautyVO) { 
    return beautyDAO.create(beautyVO);
  }

  @Override
  public List<BeautyVO> list_all_beauty() {
    return beautyDAO.list_all_beauty();
  }
  @Override
  public BeautyVO read(int styleno) {
    return beautyDAO.read(styleno);
  }

  @Override
  public int delete(int styleno) {
    return beautyDAO.delete(styleno);
  }

  /**
   * 이미지 목록중에 첫번째 이미지 파일명을 추출하여 리턴
   * @param contentsVO
   * @return
   */
 /* public String getThumb(BeautyVO beautyVO) {
    String thumbs = beautyVO.getThumb();
    String thumb = "";
    
    if (thumbs != null) {
      String[] thumbs_array = thumbs.split("/");
      int count = thumbs_array.length;
      
      if (count > 0) {
        thumb = thumbs_array[0];    
      }
    }
    // System.out.println("thumb: " + thumb);
    return thumb;
  }*/
  @Override
  public ArrayList<BeaFileVO> getThumbs(BeautyVO beautyVO) {
    ArrayList<BeaFileVO> file_list = new ArrayList<BeaFileVO>();
    
    String thumbs = beautyVO.getThumb(); // xmas01_2_t.jpg/xmas02_2_t.jpg...
    String files = beautyVO.getImage();          // xmas01_2.jpg/xmas02_2.jpg...
    String sizes = beautyVO.getSizes();        // 272558/404087... 
    
    String[] thumbs_array = thumbs.split("/");  // Thumbs
    String[] files_array = files.split("/");   // 파일명 추출
    String[] sizes_array = sizes.split("/"); // 파일 사이즈
 
    int count = sizes_array.length;
    // System.out.println("sizes_array.length: " + sizes_array.length);
    // System.out.println("sizes: " + sizes);
    // System.out.println("files: " + files);
  
    if (files.length() > 0) {
      for (int index = 0; index < count; index++) {
        sizes_array[index] = Tool.unit(new Integer(sizes_array[index]));  // 1024 -> 1KB
      
        BeaFileVO fileVO = new BeaFileVO(thumbs_array[index], files_array[index], sizes_array[index]);
        file_list.add(fileVO);
      }
    } 

    return file_list;
  }

  @Override
  public int update(BeautyVO beautyVO) {
    return beautyDAO.update(beautyVO);
  }

  @Override
  public int increaseCnt(int styleno) {
    return beautyDAO.increaseCnt(styleno);
  }


  @Override
  public int increaseLike1(int styleno) {
    return beautyDAO.increaseLike1(styleno);
  }
  @Override
  public int search_count(HashMap hashMap) {
    return beautyDAO.search_count(hashMap);
  }
  @Override
  public List<BeautyVO> search_paging(HashMap<String, Object> hashMap) {
    /* 
     페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
     1 페이지: nowPage = 1, (1 - 1) * 10 --> 0 
     2 페이지: nowPage = 2, (2 - 1) * 10 --> 10
     3 페이지: nowPage = 3, (3 - 1) * 10 --> 20
     */
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Beauty.RECORD_PER_PAGE;
    
     // 시작 rownum, 1 페이지: 1 / 2 페이지: 11 / 3 페이지: 21 
    int startNum = beginOfPage + 1; 
    //  종료 rownum, 1 페이지: 10 / 2 페이지: 20 / 3 페이지: 30
    int endNum = beginOfPage + Beauty.RECORD_PER_PAGE;   
    /*
     1 페이지: WHERE r >= 1 AND r <= 10
     2 페이지: WHERE r >= 11 AND r <= 20
     3 페이지: WHERE r >= 21 AND r <= 30
     */
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List<BeautyVO> list = beautyDAO.search_paging(hashMap); 
    Iterator<BeautyVO> iter = list.iterator();
    
    while(iter.hasNext() == true) {
      BeautyVO beautyVO = iter.next();
      String title = Tool.textLength(beautyVO.getTitle(), 90);
      title = Tool.convertChar(title); // 태그 처리
      beautyVO.setTitle(title);
      
      String thumbs = beautyVO.getThumb();
      if (thumbs.length() > 0) { // preview 이미지가 있는지 검사
        String thumb = (thumbs.split("/"))[0]; // 첫번째 파일명 추출
        beautyVO.setThumb(thumb);
      }
    }
    
    return list;
  }

  @Override
  public List<BeautyVO> list_all_cnt() {
    return beautyDAO.list_all_cnt();
  }

  @Override
  public List<BeautyVO> list_all_like1() {
    return beautyDAO.list_all_like1();
  }

  @Override
  public List<BeautyVO> list_all_rdate() {
    return beautyDAO.list_all_rdate();
  }
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
  @Override
  public String paging(int search_count, int nowPage, String title){ 
    int totalPage = (int)(Math.ceil((double)search_count/Beauty.RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/Beauty.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Beauty.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * Beauty.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * Beauty.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
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
    int _nowPage = (nowGrp-1) * Beauty.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./search_paging.do?&title="+title+"&nowPage="+_nowPage+"'>이전</A></span>"); 
    } 

    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지
        str.append("<span class='span_box_1'><A href='./search_paging.do?title="+title+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 

    // 10개 다음 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 1그룹일 경우: (1 * 10) + 1 = 2그룹의 11
    // 현재 2그룹일 경우: (2 * 10) + 1 = 3그룹의 21
    _nowPage = (nowGrp * Beauty.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./search_paging.do?&title="+title+"&nowPage="+_nowPage+"'>다음</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
}