package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




public interface ReviewProcInter {
  
  /**
   * ������ ���
   * @param contentsVO
   * @return
   */
  public int create(ReviewVO reviewVO);
  
  /**
   * ���
   * <xmp>
   * <select id="list" resultType="CategrpVO">
   * </xmp> 
   * @return
   */
  public List<ReviewVO> list();
  
/**
 * 5���� ������ ���
 * @return
 */
  public List<ReviewVO> index_list();
  
  /**
   * ��ȸ
   * @param reviewno
   * @return
   */
  public ReviewVO read(int reviewno);
  
  /**
   * ����
   * @param reviewno
   * @return
   */
  public int delete(int reviewno);
  
  /**
   * ���� ��� ����
   * @param contentsVO
   * @return
   */
  public ArrayList<FileVO> getThumbs(ReviewVO reviewVO);
  
  /**
   *����
   * @param reviewVO
   * @return
   */
  public int update(ReviewVO reviewVO);
  
  /**
   * �˻� ���
   * @param categoryno
   * @return
   */
  public List<ReviewVO> list_by_category_search(HashMap hashMap);

  /**
   * category�� �˻��� ���ڵ� ����
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * �˻� ��� + ����¡
   * @param hashMap
   * @return
   */
  public List<ReviewVO> list_by_category_search_paging(HashMap<String, Object> hashMap);

  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int categoryno, int search_count, int nowPage, String title);
  

  
  
  
  
}




  