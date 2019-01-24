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
   * �����
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
    mav.addObject("title", pet_infoVO.getName() + " ���� �����");
    mav.setViewName("/chart/create");
    return mav;
  }
  
  
  /**
   * ���ó�� 
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
   * chart list - ��� ������ �� ����
   * http://localhost:9090/ahr/chart/list.do?managerno=1&petname=������&name=��
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
    mav.addObject("title", "��Ʈ ����Ʈ");
    
    mav.addObject("managerno", managerno);
    mav.setViewName("/chart/list");
    return mav;
  }*/
  
  /**
   * �ϳ��� �� read
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
    
    mav.addObject("title", chartVO.getPetname() + " ��Ʈ");
    mav.addObject("chartVO", chartVO);
    mav.addObject("petVO", pet_infoVO);
    mav.addObject("managerno", managerno);
    mav.setViewName("/chart/read");
    return mav;
  }
  
  /**
   * ������
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
   * ����ó��
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
      json.put("msgs", "������ �����߽��ϴ�.");
      
   // 4���� ���ڵ尡 �ϳ��� �������ΰ�� 5��° ���ڵ尡 �����Ǹ� ����������
      // 2���������� 1 �������� �ٿ����մϴ�. 
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
      json.put("msgs", "������ �����߽��ϴ�.");
    }
    
    redirectAttributes.addAttribute("petname", petname);
    redirectAttributes.addAttribute("name", name);
    redirectAttributes.addAttribute("nowPage", nowPage);
    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  /**
   * ������
   * @param chartno
   * @param petno
   * @return
   */
  @RequestMapping(value = "/chart/update.do", method = RequestMethod.GET)
  public ModelAndView update(int chartno, int petno) {
    ModelAndView mav = new ModelAndView();
    
    ChartVO chartVO = chartProc.read(chartno);
    Pet_infoVO pet_infoVO = chartProc.pet_info(petno);
    
    mav.addObject("title", chartVO.getPetname() + " ��Ʈ ����");
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
   * �˻� ���
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
    mav.addObject("title", "��Ʈ ����Ʈ");
    
    mav.addObject("managerno", managerno);

    return mav;
  }
  
  /**
   * ��� + �˻� + ����¡
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
    
//    �˻� ���
    List<ChartVO> list = chartProc.list_by_search_paging(hashMap);
    mav.addObject("list", list);
    
 // �˻��� ���ڵ� ����
    int search_count = chartProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    /*
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     *
     * @param managerno ī�װ���ȣ 
     * @param nowPage     ���� ������
     * @param petname �˻���
     * @param name �˻���
     * @return ����¡ ���� ���ڿ�
     */ 
    String paging = chartProc.paging(managerno, nowPage, search_count, petname, name);
    mav.addObject("paging", paging);
   
    mav.addObject("nowPage", nowPage);
    mav.addObject("title", "��Ʈ ����Ʈ");
    mav.addObject("managerno", managerno);
    
    return mav;
  }
  
}
