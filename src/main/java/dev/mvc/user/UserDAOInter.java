package dev.mvc.user;

import java.util.HashMap;
import java.util.List;

public interface UserDAOInter {

/**
 * 전체목록
 * @return
 */
  public List <member_userVO> list_all_user();
  public List<member_userVO> list_all_eventuserno(int eventno);
  public int cnt_win(int eventno);
  
  public int usercreate(UserVO userVO);
  
  public int usercreate(member_userVO userVO);
  
  //회원번호리스트-2개이상
  public List<member_userVO> list_member(int memberno);
  
  //회우너번호뽑기
  public member_userVO read_member(int memberno); //값이 하나면 VO로 끝나는데. LIST가 붙는건 값이 여러개일때/

  public int usercreate(HashMap<String, Object> hashMap);

  public int user_cnt(int eventno);

  public int update(member_userVO member_userVO);

  public int winner(HashMap<String, Object> hashMap);
  

  public member_userVO read_event(int eventno);

}