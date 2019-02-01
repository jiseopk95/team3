package dev.mvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import dev.mvc.manager.File2VO;
import dev.mvc.manager.ManagerVO;
import dev.mvc.member.MemberVO;

public interface ManagerProcInter {
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
  public int create(ManagerVO managerVO);
  
  /**
   * ȸ�� ��ü ���
   * @return
   */
  public List<ManagerVO> list();
  
  /**
   * �α��ε� ȸ�� �������� �˻��մϴ�.
   * @param request
   * @return true: ������
   */
  public boolean isManager(HttpSession session);
  
  /**
   * ��ȸ
   * @param mno
   * @return
   */
  public ManagerVO read(int managerno);
  public ManagerVO read2(String email);
  public ManagerVO read3(String email);


  
  /**
   * ��ȸ
   * @param mno
   * @return
   */
  public ManagerVO kind_update(int managerno);
  
  /**
   * ��ȸ
   * @param id
   * @return
   */
  public ManagerVO readById(String id);
  

  /**
   * ����
   * @param memberVO
   * @return
   */
  public int update(ManagerVO managerVO);
  public int kind_update2(ManagerVO managerVO);
  
  /**
   * �н����� ����
   * 
   * @param mno ȸ�� ��ȣ
   * @param passwd ������ �н����� ��
   * @return
   */
  public int passwd_update(int managerno, String passwd);

  /**
   * ���ڵ� 1�� ����
   * @param mno ������ ȸ�� ��ȣ
   * @return ������ ���ڵ� ����
   */
  public int delete(int managerno);
  
  /**
   * �α��� 
   * @param map
   * @return
   */
  public int login(String id, String passwd);
  
  ArrayList<File2VO> getThumbs(ManagerVO managerVO);
  
  /**
   * �˻� ���
   * @param categoryno
   * @return
   */
  public List<ManagerVO> list_search(HashMap<String, Object> hashMap);

  /**
   * category�� �˻��� ���ڵ� ����
   * @return
   */
  public int search_count(HashMap hashMap);
  
  public List<ManagerVO> idsearch(HashMap hashMap);
  
  public List<ManagerVO> list_id();
  
  public int search_count2(HashMap hashMap);
  
  public List<ManagerVO> passwdsearch(HashMap hashMap);
  
  public List<ManagerVO> list_passwd();
  
  public int search_count3(HashMap hashMap);
  
  public String paging(int search_count, int nowPage, String word);
}









