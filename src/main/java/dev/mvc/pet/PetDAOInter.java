package dev.mvc.pet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.mvc.member.MemberVO;

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

  /**
   * �˻� ���
   * @param categoryno
   * @return
   */
  public List<PetVO> list_search(HashMap<String, Object> hashMap);

  /**
   * category�� �˻��� ���ڵ� ����
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * �� ��ȸ
   * @param mno
   * @return
   */
  /*public PetVO pet_list(int memberno);*/
  public List<PetVO> pet_list(HashMap hashMap);
  public int search_count2(HashMap hashMap);
}







