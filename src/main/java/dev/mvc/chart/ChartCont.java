package dev.mvc.chart;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.reservation.ReservationProcInter;
import dev.mvc.reservation.ReservationVO;

@Controller
public class ChartCont {
  
  @Autowired
  @Qualifier("dev.mvc.chart.ChartProc")
  private ChartProcInter chartProc = null;
  
  public ChartCont(){
    System.out.println("--> ChartCont created");
  }
  
  /**
   * chart list - 모든 동물이 다 나옴
   * http://localhost:9090/ahr/chart/list.do
   * @return
   */
  @RequestMapping(value = "/chart/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    
    List<ChartVO> list = chartProc.list();
    
    mav.addObject("title", "차트 리스트");
    mav.addObject("list", list);
    mav.setViewName("/chart/list");
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
  public ResponseEntity delete_proc(int chartno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    JSONObject json = new JSONObject();
    int count = chartProc.delete(chartno);
    
    if(count==1) {
      json.put("msgs", "삭제를 성공했습니다.");
    } else {
      json.put("msgs", "삭제에 실패했습니다.");
    }
    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
}
