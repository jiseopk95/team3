package dev.mvc.reservation;

import java.util.HashMap;
import java.util.List;

public interface ReservationDAOInter {
  
  /**
   * ���� �� ����Ʈ - ȸ���� ���
   * @param mno
   * @return
   */
  public List<ResPetVO> pet_list(int memberno);
  
  /**
   * ���� ������ �ð� ����Ʈ - ����
   * @param restype
   * @return
   */
  public List<Time_hVO>time_h(String resdate);

  /**
   * ���� ������ �ð� ����Ʈ - �̿�
   * @param restype
   * @return
   */
  public List<Time_bVO>time_b(String resdate);
  
  /**
   * ���� ������ �ð� ����Ʈ - ����, �̿�
   * @param restype
   * @return
   */
  public List<TimeVO>time(String resdate);
  
  /**
   * ������
   * @param reservationVO �Է� ���� ���� ����
   * @return
   */
  public int create(ReservationVO reservationVO);
  
  /**
   * ���� ���� ����Ʈ - ȸ���� ��� 
   * @param count 
   * @return
   */
  public List<ReservationVO> my_list(HashMap hashMap);
  
  /**
   * �����ȣ�� ���� ������� �о���� 
   * @param reservationno
   * @return
   */
  public ReservationVO read(int reservationno);
  
  /**
   * ���� ����ó��
   * @param reservationVO
   * @return
   */
  public int update(ReservationVO reservationVO);
  
  /**
   * ���� ����ó��
   * @param reservationno
   * @return
   */
  public int delete(int reservationno);
  
  /**
   * ����� ������ �̸�
   * @param petno
   * @return
   */
  public String pet_name(int petno);
 
}
