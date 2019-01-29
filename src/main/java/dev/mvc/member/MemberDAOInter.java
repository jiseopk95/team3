package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.mvc.member.MemberVO;

public interface MemberDAOInter {
  /**
   * �ߺ� ���̵� �˻�
   * @param id
   * @return �ߺ� ���̵� ����
   */
  public int checkId(String id);
  public int checkemail(String email);
  
  /**
  ȸ�� ���
  @param memberVO
  @return ��ϵ� ȸ���� 1 or 0
  */
  public int create(MemberVO memberVO);
  
  /**
   * ȸ�� ��ü ���
   * @return
   */
  public List<MemberVO> list();
 
  /**
   * ��ȸ
   * @param mno
   * @return
   */
  public MemberVO read(int memberno);

  

  /**
   * ��ȸ
   * @param id
   * @return
   */
  public MemberVO readById(String id);
  
  
  /**
   * ����
   * @param memberVO
   * @return
   */
  public int update(MemberVO memberVO);
  
  /**
   * �н����� ���� 
   * @param map
   * @return
   */
  public int passwd_update(Map<String, Object> map);
  
  /**
   * ���ڵ� 1�� ����
   * @param mno ������ ȸ�� ��ȣ
   * @return ������ ���ڵ� ����
   */
  public int delete(int memberno);

  /**
   * �α��� 
   * @param map
   * @return
   */
  public int login(Map map);
  
  /**
   * �˻� ���
   * @param categoryno
   * @return
   */
  public List<MemberVO> list_search(HashMap hashMap);

  /**
   * category�� �˻��� ���ڵ� ����
   * @return
   */
  public int search_count(HashMap hashMap);
  
  public List<MemberVO> idsearch(HashMap hashMap);
  
  public List<MemberVO> list_id();
  
  public int search_count2(HashMap hashMap);
  
  public List<MemberVO> passwdsearch(HashMap hashMap);
  
  public List<MemberVO> list_passwd();
  
  public int search_count3(HashMap hashMap);
  
  
}







