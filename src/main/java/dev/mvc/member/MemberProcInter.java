package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import dev.mvc.member.MemberVO;

public interface MemberProcInter {
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
   * �α��ε� ȸ�� �������� �˻��մϴ�.
   * @param request
   * @return true: ������
   */
  public boolean isMember(HttpSession session);
  
  /**
   * ��ȸ
   * @param mno
   * @return
   */
  public MemberVO read(int memberno);
  public MemberVO read2(String email);
  public MemberVO read3(String email);

  
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
   * 
   * @param mno ȸ�� ��ȣ
   * @param passwd ������ �н����� ��
   * @return
   */
  public int passwd_update(int memberno, String passwd);

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
  public int login(String id, String passwd);
  
  /**
   * �˻� ���
   * @param categoryno
   * @return
   */
  public List<MemberVO> list_search(HashMap<String, Object> hashMap);

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
  
  public String paging(int search_count, int nowPage, String word);
}









