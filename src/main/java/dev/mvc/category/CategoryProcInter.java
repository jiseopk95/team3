package dev.mvc.category;

import java.util.List;

public interface CategoryProcInter {
  /**
   * ���
   * @param categoryVO
   * @return ó���� ����Ʈ ����
   */
  public abstract int create (CategoryVO categoryVO); 

  /**
   * ���
   * @return Join ���
   */
  public List<Categrp_CategoryVO> list();
  
  /**
   * ���
   * @param categrpno ī�װ� �׷� ��ȣ
   * @return Join ���
   */
  public List<Categrp_CategoryVO> list_by_categrp(int categrpno);
  
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * @param categrpno
   * @return
   */
  public Categrp_CategoryVO read(int categoryno);
  
  /**
   * ���ڵ带 �����մϴ�.
   * @param categoryVO 
   * @return
   */
  public int update(CategoryVO categoryVO);

  /**
   * �Ѱ��� ���ڵ� ����
   * @param categoryno
   * @return
   */
  public int delete(int categoryno);
  
  /**
   * ī�װ� �׷쿡 ���� ī��Ʈ ����
   * @param categrpno
   * @return
   */
  public int count_by_categrp(int categrpno);

  /**
   * ī�װ� �׷쿡 ���� ����
   * @param categrpno
   * @return
   */
  public int delete_by_categrp(int categrpno);
  
  /**
   * �ۼ� ����
   * @param categrpno
   * @return
   */
  public int increaseCnt(int categoryno);

  /**
   * �ۼ� ����
   * @param categrpno
   * @return
   */
  public int decreaseCnt(int categoryno);
  
  
}






