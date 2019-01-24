package dev.mvc.chart;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.reservation.PetVO;
import dev.mvc.reservation.ReservationProcInter;
import dev.mvc.reservation.ReservationVO;
import dev.mvc.reservation.Time_bVO;
import dev.mvc.reservation.Time_hVO;

@Controller
public class ChartCont {
  
  @Autowired
  @Qualifier("dev.mvc.chart.ChartProc")
  private ChartProcInter chartProc = null;
  
  public ChartCont(){
    System.out.println("--> ChartCont created");
  }
  
  /**
   * 등록폼
   * http://localhost:9090/ahr/chart/create.do?petno=2
   * @param petno
   * @return
   */
  @RequestMapping(value = "/chart/create.do", method = RequestMethod.GET)
  public ModelAndView create(int petno, int managerno) {
    ModelAndView mav = new ModelAndView();
    int memberno = 0;
    
    Pet_infoVO pet_infoVO = chartProc.pet_info(petno);
    memberno = pet_infoVO.getMemberno();
    Member_infoVO member_infoVO = chartProc.member_info(memberno);
    
    mav.addObject("petVO", pet_infoVO);
    mav.addObject("memberVO", member_infoVO);
    mav.addObject("managerno", managerno);
    mav.addObject("title", pet_infoVO.getName() + " 진료 기록지");
    mav.setViewName("/chart/create");
    return mav;
  }
  
  
  /**
   * 등록처리 
   * @param reservationVO
   * @return
   */
  @RequestMapping(value = "/chart/create.do", method = RequestMethod.POST)
  public ModelAndView create(ChartVO chartVO) {  
    ModelAndView mav = new ModelAndView();  
    System.out.println("ChartVO.name : " + chartVO.getName());
    System.out.println("ChartVO.phone : " + chartVO.getPhone());
    System.out.println("ChartVO.petname : " + chartVO.getPetname());
    int count = chartProc.create(chartVO);
    if(count == 1) {
      mav.setViewName("redirect:/chart/list.do?managerno=" + chartVO.getManagerno());
    } 
    
    return mav;
  }
  
  /**
   * chart list - 모든 동물이 다 나옴
   * http://localhost:9090/ahr/chart/list.do?managerno=1&petname=춘향이&name=소
   * @return
   */
  /*@RequestMapping(value = "/chart/list.do", method = RequestMethod.GET)
  public ModelAndView list(int managerno, String petname, String name) {
    ModelAndView mav = new ModelAndView();
    
    HashMap<String, String> hashMap = new HashMap<String, String>();
    hashMap.put("petname", petname);
    hashMap.put("name", name);
    
    List<ChartVO> list = chartProc.list_by_search(hashMap);
    mav.addObject("list", list);
    List<ChartVO> list = chartProc.list();
    mav.addObject("list", list);
    mav.addObject("title", "차트 리스트");
    
    mav.addObject("managerno", managerno);
    mav.setViewName("/chart/list");
    return mav;
  }*/
  
  /**
   * 하나의 글 read
   * @param chartno
   * @param petno
   * @param managerno
   * @return
   */
  @RequestMapping(value = "/chart/read.do", method = RequestMethod.GET)
  public ModelAndView read(int chartno, int petno, int managerno) {
    ModelAndView mav = new ModelAndView();
    
    ChartVO chartVO = chartProc.read(chartno);
    Pet_infoVO pet_infoVO = chartProc.pet_info(petno);
    
    mav.addObject("title", chartVO.getPetname() + " 차트");
    mav.addObject("chartVO", chartVO);
    mav.addObject("petVO", pet_infoVO);
    mav.addObject("managerno", managerno);
    mav.setViewName("/chart/read");
    return mav;
  }
  
  /**
   * 삭제폼
   * @param chartno
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/chart/delete.do", 
                             method=RequestMethod.GET,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity deleteOne(int chartno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    ChartVO chartVO = chartProc.read(chartno);
    
    JSONObject json = new JSONObject();
    json.put("chartno", chartVO.getChartno());
    json.put("name", chartVO.getName());
    json.put("petname", chartVO.getPetname());
    json.put("title", chartVO.getTitle());
    json.put("sick", chartVO.getSick());
    json.put("rdate", chartVO.getRdate());
        
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  /**
   * 삭제처리
   * @param chartno
   * @return
   */
  
  @ResponseBody
  @RequestMapping(value="/chart/delete.do", 
                             method=RequestMethod.POST,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity delete_proc(RedirectAttributes redirectAttributes, 
                                              HttpServletRequest request,
                                              int chartno,
                                              @RequestParam(value="petname", defaultValue="") String petname,
                                              @RequestParam(value="name", defaultValue="") String name,
                                              @RequestParam(value="nowPage", defaultValue="1") int nowPage ) {
    HttpHeaders responseHeaders = new HttpHeaders();
    JSONObject json = new JSONObject();
    int count = chartProc.delete(chartno);
    
    if(count==1) {
      json.put("msgs", "삭제를 성공했습니다.");
      
   // 4개의 레코드가 하나의 페이지인경우 5번째 레코드가 삭제되면 페이지수도
      // 2페이지에서 1 페이지로 줄여야합니다. 
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("name", name); // #{categoryno}
      hashMap.put("petname", petname);                  // #{word}
      if (chartProc.search_count(hashMap) % Contents.RECORD_PER_PAGE == 0){ 
        nowPage = nowPage - 1;
        System.out.println("delete nowPage : " + nowPage);
        if (nowPage < 1){
          nowPage = 1;
          System.out.println("delete nowPage < 1  : " + nowPage);
        }
      }
    } else {
      json.put("msgs", "삭제에 실패했습니다.");
    }
    
    redirectAttributes.addAttribute("petname", petname);
    redirectAttributes.addAttribute("name", name);
    redirectAttributes.addAttribute("nowPage", nowPage);
    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  /**
   * 수정폼
   * @param chartno
   * @param petno
   * @return
   */
  @RequestMapping(value = "/chart/update.do", method = RequestMethod.GET)
  public ModelAndView update(int chartno, int petno) {
    ModelAndView mav = new ModelAndView();
    
    ChartVO chartVO = chartProc.read(chartno);
    Pet_infoVO pet_infoVO = chartProc.pet_info(petno);
    
    mav.addObject("title", chartVO.getPetname() + " 차트 수정");
    mav.addObject("chartVO", chartVO);
    mav.addObject("petVO", pet_infoVO);
    
    mav.setViewName("/chart/update");
    
    return mav;
  }
  
  
  @RequestMapping(value = "/chart/update.do", method = RequestMethod.POST)
  public ModelAndView update(ChartVO chartVO) {
    ModelAndView mav = new ModelAndView();
    
    int count = chartProc.update(chartVO);
    
    mav.setViewName("redirect:/chart/update_message.jsp?count=" + count);
    
    return mav;
  }
  
  /**
   * 검색 목록
   * @param petname
   * @param name
   * @return
   */
  @RequestMapping(value = "/chart/list_by_search.do", method = RequestMethod.GET)
  public ModelAndView list_by_search(int managerno, String petname, String name) {
    
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/chart/list");

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("petname", petname);
    hashMap.put("name", name);

    List<ChartVO> list = chartProc.list_by_search(hashMap);
    mav.addObject("list", list);
    mav.addObject("title", "차트 리스트");
    
    mav.addObject("managerno", managerno);

    return mav;
  }
  
  /**
   * 목록 + 검색 + 페이징
   * @param managerno
   * @param petname
   * @param name
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/chart/list.do", 
                                       method = RequestMethod.GET)
  public ModelAndView list_by_category_search_paging(
      @RequestParam(value="managerno") int managerno,
      @RequestParam(value="petname", defaultValue="") String petname,
      @RequestParam(value="name", defaultValue="") String name,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) { 
    System.out.println("--> nowPage: " + nowPage);
    
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/chart/list");   
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("petname", petname);
    hashMap.put("name", name);
    hashMap.put("nowPage", nowPage);
    
//    검색 목록
    List<ChartVO> list = chartProc.list_by_search_paging(hashMap);
    mav.addObject("list", list);
    
 // 검색된 레코드 갯수
    int search_count = chartProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
     * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
     *
     * @param managerno 카테고리번호 
     * @param nowPage     현재 페이지
     * @param petname 검색어
     * @param name 검색어
     * @return 페이징 생성 문자열
     */ 
    String paging = chartProc.paging(managerno, nowPage, search_count, petname, name);
    mav.addObject("paging", paging);
   
    mav.addObject("nowPage", nowPage);
    mav.addObject("title", "차트 리스트");
    mav.addObject("managerno", managerno);
    
    return mav;
  }
  
}
