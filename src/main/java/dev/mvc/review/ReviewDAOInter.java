package dev.mvc.review;

import java.util.HashMap;
import java.util.List;

public interface ReviewDAOInter {
  
  /**
   * ������ ���
   * @param reviewVO
   * @return
   */
  public int create(ReviewVO reviewVO);
  
  /**
   * ���
   * <xmp>
   * <select id="list" resultType="ReviewVO">
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


  
  
  
}








