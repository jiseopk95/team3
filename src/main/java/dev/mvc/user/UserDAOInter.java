package dev.mvc.user;

import java.util.HashMap;
import java.util.List;

public interface UserDAOInter {

/**
 * ��ü���
 * @return
 */
  public List <member_userVO> list_all_user();
  public List<member_userVO> list_all_eventuserno(int eventno);
  public int cnt_win(int eventno);
  
  public int usercreate(UserVO userVO);
  
  public int usercreate(member_userVO userVO);
  
  //ȸ����ȣ����Ʈ-2���̻�
  public List<member_userVO> list_member(int memberno);
  
  //ȸ��ʹ�ȣ�̱�
  public member_userVO read_member(int memberno); //���� �ϳ��� VO�� �����µ�. LIST�� �ٴ°� ���� �������϶�/

  public int usercreate(HashMap<String, Object> hashMap);

  public int user_cnt(int eventno);

  public int update(member_userVO member_userVO);

  public int winner(HashMap<String, Object> hashMap);
  

  public member_userVO read_event(int eventno);

}