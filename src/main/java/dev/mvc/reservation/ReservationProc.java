package dev.mvc.reservation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.reservation.ReservationProc")
public class ReservationProc implements ReservationProcInter{

  @Autowired
  @Qualifier("dev.mvc.reservation.ReservationDAO")
  
  private ReservationDAOInter reservationDAO = null;
  

  public ReservationProc(){
    System.out.println("--> ReservationProc created.");
  }
  
  @Override
  public List<ResPetVO> pet_list(int memberno) {
    
    return reservationDAO.pet_list(memberno);
  }
  
  @Override
  public List<Time_hVO> time_h(String resdate) {
    
    return reservationDAO.time_h(resdate);
  }

  @Override
  public List<Time_bVO> time_b(String resdate) {

    return reservationDAO.time_b(resdate);
  }
  
  @Override
  public List<TimeVO> time(String resdate) {

    return reservationDAO.time(resdate);
  }
  
  @Override
  public int create(ReservationVO reservationVO) {
    
    return reservationDAO.create(reservationVO);
  }

  @Override
  public List<ReservationVO> my_list(HashMap hashMap) {
    List<ReservationVO> list = reservationDAO.my_list(hashMap);
    ReservationVO reservationVO = null;
    
    int count = list.size();
    
    for (int i = 0; i < count; i++) {
      reservationVO = list.get(i);
      if(reservationVO.getRestype().equals("1")) {
        reservationVO.setRestype("의료");
      } else if(reservationVO.getRestype().equals("2")) {
        reservationVO.setRestype("미용");
      } else if(reservationVO.getRestype().equals("3")) {
        reservationVO.setRestype("개인");
      }
      
      if(reservationVO.getRestime().equals("default")) {
        reservationVO.setRestime("");
      }
    }
    
    return list;
  }

  @Override
  public ReservationVO read(int reservationno) {
    ReservationVO reservationVO = reservationDAO.read(reservationno);
      
    return reservationVO;
  }

  @Override
  public int update(ReservationVO reservationVO) {
    
    return reservationDAO.update(reservationVO);
  }

  @Override
  public int delete(int reservationno) {
    
    return reservationDAO.delete(reservationno);
  }

  

}
