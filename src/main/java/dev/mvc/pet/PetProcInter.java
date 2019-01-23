package dev.mvc.pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface PetProcInter {
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
   * �α��ε� ȸ�� �������� �˻��մϴ�.
   * @param request
   * @return true: ������
   */
  /*public boolean isMember(HttpSession session);*/
  
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
  /*public PetVO readById(String id);*/
  
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
  
  ArrayList<FileVO> getThumbs(PetVO petVO);

}









