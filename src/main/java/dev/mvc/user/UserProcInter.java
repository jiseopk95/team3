package dev.mvc.user;

import java.util.HashMap;
import java.util.List;

import dev.mvc.event.EventVO;

public interface UserProcInter{
/**
 * ���
 * @return
 */
  public List<member_userVO> list_all_user();
  public List<member_userVO> list_all_eventuserno(int eventno);
  public int cnt_win(int eventno);
  /*ȸ����ȣ �̱�*/
  public member_userVO read_member(int memberno);
  
  
  
  
  public int usercreate(UserVO userVO);
  
  public int usercreate(member_userVO userVO);
  
  public List<member_userVO> list_member(int memberno);

  public int usercreate(HashMap<String, Object> hashMap);
  
  //�����ڼ� ī��Ʈ-�̺�Ʈ���� ��÷�ڰ� �ִ°Ŵϱ� �Ķ���͸� �̺�Ʈ��ȣ�� �޾ƾ��ҵ�.
  public int user_cnt(int eventno);
  
  /**
   * ����ó��
   * @param vo
   * @return
   */
  public int update(member_userVO member_userVO);
  
  public int winner(HashMap<String, Object> hashMap);

  public member_userVO read_event(int memberno);
  
}