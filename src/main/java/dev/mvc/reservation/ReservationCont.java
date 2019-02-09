package dev.mvc.reservation;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
public class ReservationCont {
  @Autowired
  @Qualifier("dev.mvc.reservation.ReservationProc")
  private ReservationProcInter reservationProc = null;
  
  public ReservationCont(){
    System.out.println("--> ReservationCont created");
  }
  
  Calendar cal = Calendar.getInstance();
  String year = Integer.toString(cal.get(Calendar.YEAR));
  String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
  
  
  /**
   * 등록폼
   * http://localhost:9090/ahr/reservation/create.do?memberno=2&resdate=2019-01
   * http://localhost:9090/ahr/calendar/calendar.jsp?memberno=2
   * @param mno
   * @return
   */
  @RequestMapping(value = "/reservation/create.do", method = RequestMethod.GET)
  public ModelAndView create(int memberno, String resdate) {
    ModelAndView mav = new ModelAndView();
    List<ResPetVO> list = reservationProc.pet_list(memberno); // 해당 동물 리스트를 가져온다.
    List<Time_hVO> list_h = reservationProc.time_h(resdate); // 의료(1번)의 예약된 시간을 받아온다
    List<Time_bVO> list_b = reservationProc.time_b(resdate); // 미용(2번)의 예약된 시간을 받아온다
    
    mav.addObject("list", list); // 동물 리스트
    mav.addObject("list_h", list_h); // 병원 예약되어있는 시간 리스트
    mav.addObject("list_b", list_b); // 미용 예약되어있는 시간 리스트
    mav.addObject("memberno", memberno); // 멤버넘버
    mav.addObject("title", "예약");
    mav.setViewName("/reservation/create");
    return mav;
  }
  
  
  /**
   * 등록처리 
   * @param reservationVO
   * @return
   */
  @RequestMapping(value = "/reservation/create.do", method = RequestMethod.POST)
  public ModelAndView create(ReservationVO reservationVO) {
    ModelAndView mav = new ModelAndView();
    int count = reservationProc.create(reservationVO);
    
    Calendar cal = Calendar.getInstance();
    String year = Integer.toString(cal.get(Calendar.YEAR));
    String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
    if (month.length() < 2) {
      month = "0" + month;
    }
    String today = year + "-" + month;
    
    if(count == 1) {
      mav.setViewName("redirect:/reservation/my_list.do?memberno=" + reservationVO.getMemberno() + "&date=" + today);
    }
    
    return mav;
  }
  
  /**
   * 마이리스트 - 회원별 예약리스트
   * http://localhost:9090/ahr/reservation/my_list.do?memberno=2&date=2019-01
   * @param memberno
   * @param date
   * @return
   */
  @RequestMapping(value = "/reservation/my_list.do", method = RequestMethod.GET)
  public ModelAndView my_list(int memberno, String date) {
    ModelAndView mav = new ModelAndView();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("date", date);
    hashMap.put("memberno", memberno);
    
    List<ReservationVO> list = reservationProc.my_list(hashMap);
    mav.addObject("list", list);
    mav.addObject("memberno", memberno);
    mav.setViewName("/reservation/my_list");
    return mav;
  }
  
  /**
   * 수정폼
   * http://localhost:9090/ahr/reservation/update.do?reservationno=2
   * @param reservationno
   * @return
   */
  @RequestMapping(value = "/reservation/update.do", method = RequestMethod.GET)
  public ModelAndView update(int reservationno, int memberno, String resdate) {
    ModelAndView mav = new ModelAndView();
    
    List<ResPetVO> list = reservationProc.pet_list(memberno); // 펫리스트를 가져온다
    List<Time_hVO> list_h = reservationProc.time_h(resdate); // 의료(1번)의 예약된 시간을 받아온다
    List<Time_bVO> list_b = reservationProc.time_b(resdate); // 미용(2번)의 예약된 시간을 받아온다
    ReservationVO reservationVO = reservationProc.read(reservationno); // 해당 글번호의 예약내용을 불러온다.
    
    mav.addObject("list", list); // 펫리스트
    mav.addObject("list_h", list_h); // 병원 예약되어있는 시간 리스트
    mav.addObject("list_b", list_b); // 미용 예약되어있는 시간 리스트
    mav.addObject("reservationVO", reservationVO); // 해당 글번호의 예약내용
    mav.addObject("title", "예약 수정");
    mav.setViewName("/reservation/update");
    
    return mav;
  }
  
  /**
   * JSON 기반 수정폼 - resdate 변경시 작동 
   * http://localhost:9090/ahr/reservation/resdate.do?resdate=2019-01-15
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/reservation/resdate.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public ResponseEntity resdate(String resdate) {
    HttpHeaders responseHeaders = new HttpHeaders();
    List<TimeVO> list = reservationProc.time(resdate); // 의료(1번), 미용(2)의 예약된 시간을 받아온다

    JSONArray json = new JSONArray(list); // 불러온 예약시간을 json리스트에 담는다

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  /**
   * 수정처리
   * @param reservationVO
   * @return
   */
  @RequestMapping(value = "/reservation/update.do", method = RequestMethod.POST)
  public ModelAndView update(ReservationVO reservationVO) {
    ModelAndView mav = new ModelAndView();
    
    int count = reservationProc.update(reservationVO);
    
    mav.setViewName("redirect:/reservation/update_message.jsp?count=" + count);
    
    return mav;
  }
  
  /**
   * 삭제폼
   * @param categrpno
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/reservation/delete.do", 
                             method=RequestMethod.GET,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity deleteOne(int reservationno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    ReservationVO reservationVO = reservationProc.read(reservationno);
    JSONObject json = new JSONObject();
    json.put("name", reservationVO.getName());
    json.put("title", reservationVO.getTitle());
    json.put("resdate", reservationVO.getResdate());
    json.put("restime", reservationVO.getRestime());
    json.put("reservationno", reservationVO.getReservationno());
        
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  /**
   * 삭제처리
   * @param reservationno
   * @param memberno
   * @return
   */
  
  @ResponseBody
  @RequestMapping(value="/reservation/delete.do", 
                             method=RequestMethod.POST,
                             produces="text/plain;charset=UTF-8")
  public ResponseEntity delete_proc(int reservationno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    JSONObject json = new JSONObject();
    int count = reservationProc.delete(reservationno);
    
    if(count==1) {
      json.put("msgs", "삭제를 성공했습니다.");
    } else {
      json.put("msgs", "삭제를 실패했습니다.");
    }
    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  /**
   * 전체글 읽어오기
   * http://localhost:9090/ahr/reservation/read.do?reservationno=10
   * @param reservationno
   * @return
   */
  @RequestMapping(value = "/reservation/read.do", method = RequestMethod.GET)
  public ModelAndView read(int reservationno) {
    ModelAndView mav = new ModelAndView();
    
    ReservationVO reservationVO = reservationProc.read(reservationno);
    
    if(reservationVO.getRestype().equals("1")) {
      reservationVO.setRestype("의료");
    } else if(reservationVO.getRestype().equals("2")) {
      reservationVO.setRestype("미용");
    } else if(reservationVO.getRestype().equals("3")) {
      reservationVO.setRestype("개인");
    }
    
    mav.addObject("reservationVO", reservationVO);
    mav.setViewName("/reservation/read");
    
    return mav;
  }
}
