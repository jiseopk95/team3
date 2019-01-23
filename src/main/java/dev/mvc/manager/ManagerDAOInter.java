package dev.mvc.manager;

import java.util.List;
import java.util.Map;

public interface ManagerDAOInter {
  /**
   * �ߺ� ���̵� �˻�
   * @param id
   * @return �ߺ� ���̵� ����
   */
  public int checkId(String id);
  
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
   * ��ȸ
   * @param mno
   * @return
   */
  public ManagerVO read(int managerno);
  public ManagerVO idsearch(String email);
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
   * @param map
   * @return
   */
  public int passwd_update(Map<String, Object> map);
  
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
  public int login(Map map);
}







