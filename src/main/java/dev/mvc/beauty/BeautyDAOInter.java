package dev.mvc.beauty;

import java.util.HashMap;
import java.util.List;

public interface BeautyDAOInter{
  /**
   * �̿뽺Ÿ�� ������ ���
   * @param beautyVO
   * @return
   */
  public int create(BeautyVO beautyVO);
/**
 * ��ü���
 * @return
 */
  public List<BeautyVO> list_all_beauty();
  public List<BeautyVO> list_all_cnt();
  public List<BeautyVO> list_all_like1();
  public List<BeautyVO> list_all_rdate();
  /**�� ����
   * 
   * @param styleno
   * @return
   */
  public int delete(int styleno);
  /**
   * ����ó��
   * @param vo
   * @return
   */
  public int update(BeautyVO beautyVO);
  /**
   * ��ȸ
   * @param styleno
   * @return
   */
  public BeautyVO read(int styleno);
  /**
   * ��ȸ�� ����
   * @param styleno
   * @return
   */
  public int increaseCnt(int styleno);
  /**
   * ���ƿ�� ���� 
   * @param styleno
   * @return
   */
  public int increaseLike1(int styleno);
  /**
   * �˻� ��� + ����¡
   * @param hashMap
   * @return
   */
  public List<BeautyVO> search_paging(HashMap<String, Object> hashMap);
  /**
   * category�� �˻��� ���ڵ� ����
   * @return
   */
  public int search_count(HashMap hashMap);
}