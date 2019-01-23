package dev.mvc.pet;

import java.util.List;
import java.util.Map;

public interface PetDAOInter {
  /**
  ȸ�� ���
  @param memberVO
  @return ��ϵ� ȸ���� 1 or 0
  */
  public int create(PetVO petVO);
  
  /**
   * ȸ�� ��ü ���
   * @return
   */
  public List<PetVO> list();
 
  /**
   * ��ȸ
   * @param mno
   * @return
   */
  public PetVO read(int petno);
  
  /**
   * ��ȸ
   * @param id
   * @return
   */
/*  public PetVO readById(String id);*/
  
  /**
   * ����
   * @param memberVO
   * @return
   */
  public int update(PetVO petVO);
 
  /**
   * ���ڵ� 1�� ����
   * @param mno ������ ȸ�� ��ȣ
   * @return ������ ���ڵ� ����
   */
  public int delete(int petno);

}







