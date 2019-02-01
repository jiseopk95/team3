package dev.mvc.reservation;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.reservation.ReservationDAO")
public class ReservationDAO implements ReservationDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public ReservationDAO() {
    System.out.println("-----> ReservationDAO created");
  }
  
  @Override
  public List<ResPetVO> pet_list(int memberno) {
   
    return sqlSessionTemplate.selectList("reservation.pet_list", memberno);
  }
  

  @Override
  public List<Time_hVO> time_h(String resdate) {
    
    return sqlSessionTemplate.selectList("reservation.time_h_list", resdate);
  }

  @Override
  public List<Time_bVO> time_b(String resdate) {
    
    return sqlSessionTemplate.selectList("reservation.time_b_list", resdate);
  }

  @Override
  public List<TimeVO> time(String resdate) {
    
    return sqlSessionTemplate.selectList("reservation.time_list", resdate);
  }
  
  @Override
  public int create(ReservationVO reservationVO) {
    
    return sqlSessionTemplate.insert("reservation.create", reservationVO);
  }

  @Override
  public List<ReservationVO> my_list(HashMap hashMap) {
    
    return sqlSessionTemplate.selectList("reservation.my_list", hashMap);
  }

  @Override
  public ReservationVO read(int reservationno) {
    
    return sqlSessionTemplate.selectOne("reservation.read", reservationno);
  }

  @Override
  public int update(ReservationVO reservationVO) {
    
    return sqlSessionTemplate.update("reservation.update", reservationVO);
  }

  @Override
  public int delete(int reservationno) {
    
    return sqlSessionTemplate.delete("reservation.delete", reservationno);
  }

}
