package dev.mvc.user;

import java.util.HashMap;
import java.util.List;

import dev.mvc.event.EventVO;

public interface UserProcInter{
/**
 * 목록
 * @return
 */
  public List<member_userVO> list_all_user();
  public List<member_userVO> list_all_eventuserno(int eventno);
  public int cnt_win(int eventno);
  /*회원번호 뽑기*/
  public member_userVO read_member(int memberno);
  
  
  
  
  public int usercreate(UserVO userVO);
  
  public int usercreate(member_userVO userVO);
  
  public List<member_userVO> list_member(int memberno);

  public int usercreate(HashMap<String, Object> hashMap);
  
  //참여자수 카운트-이벤트별로 당첨자가 있는거니까 파라미터를 이벤트번호로 받아야할듯.
  public int user_cnt(int eventno);
  
  /**
   * 수정처리
   * @param vo
   * @return
   */
  public int update(member_userVO member_userVO);
  
  public int winner(HashMap<String, Object> hashMap);

  public member_userVO read_event(int memberno);
  
}