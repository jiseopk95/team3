package dev.mvc.beauty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dev.mvc.beauty.BeaFileVO;

public interface BeautyProcInter {
  /**
   * �̿뽺Ÿ�� ������ ���
   * @param beautyVO
   * @return
   */
  public int create(BeautyVO beautyVO);

  public List<BeautyVO> list_all_beauty();
  public List<BeautyVO> list_all_cnt();
  public List<BeautyVO> list_all_like1();
  public List<BeautyVO> list_all_rdate();
  /**
   * ��ȸ
   * @param styleno
   * @return
   */
  public BeautyVO read(int styleno);
  /**�� ����
   * 
   * @param styleno
   * @return
   */
  public int delete(int styleno);
  /**
   * ���� ��� ����
   * @param beautyVO
   * @return
   */
  public ArrayList<BeaFileVO> getThumbs(BeautyVO beautyVO);
  /**
   * ����ó��
   * @param vo
   * @return
   */
  public int update(BeautyVO beautyVO);
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
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param title 
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int categoryno, int search_count, int nowPage, String title);
}
