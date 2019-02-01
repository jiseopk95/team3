package dev.mvc.present;

import java.util.HashMap;
import java.util.List;

public interface PresentDAOInter{

  /**
   * �̺�Ʈ ��ǰ ������ ���
   * @param presentVO
   * @return
   */
  public int create(PresentVO presentVO);
/**
 * ��ü���
 * @return
 */
  public List<PresentVO> list_all_present();
  /**�� ����
   * 
   * @param presentno
   * @return
   */
  public int delete(int presentno);
  /**
   * ����ó��
   * @param vo
   * @return
   */
  public int update(PresentVO presentVO);
  public PresentVO read(int presentno);
  /**
   * category�� �˻��� ���ڵ� ����
   * @return
   */
  public int search_count(HashMap hashMap);
}