package dev.mvc.reservation;

import java.util.HashMap;
import java.util.List;

public interface ReservationDAOInter {
  
  /**
   * 마이 펫 리스트 - 회원별 출력
   * @param mno
   * @return
   */
  public List<ResPetVO> pet_list(int memberno);
  
  /**
   * 예약 가능한 시간 리스트 - 병원
   * @param restype
   * @return
   */
  public List<Time_hVO>time_h(String resdate);

  /**
   * 예약 가능한 시간 리스트 - 미용
   * @param restype
   * @return
   */
  public List<Time_bVO>time_b(String resdate);
  
  /**
   * 예약 가능한 시간 리스트 - 병원, 미용
   * @param restype
   * @return
   */
  public List<TimeVO>time(String resdate);
  
  /**
   * 예약등록
   * @param reservationVO 입력 받은 예약 정보
   * @return
   */
  public int create(ReservationVO reservationVO);
  
  /**
   * 마이 예약 리스트 - 회원별 출력 
   * @param count 
   * @return
   */
  public List<ReservationVO> my_list(HashMap hashMap);
  
  /**
   * 예약번호에 따른 예약사항 읽어오기 
   * @param reservationno
   * @return
   */
  public ReservationVO read(int reservationno);
  
  /**
   * 예약 수정처리
   * @param reservationVO
   * @return
   */
  public int update(ReservationVO reservationVO);
  
  /**
   * 예약 삭제처리
   * @param reservationno
   * @return
   */
  public int delete(int reservationno);
  
  /**
   * 예약된 동물의 이름
   * @param petno
   * @return
   */
  public String pet_name(int petno);
 
}
