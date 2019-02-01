package dev.mvc.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.event.EventVO;

@Component("dev.mvc.user.UserProc") 
public class UserProc implements UserProcInter {
  @Autowired  
  @Qualifier("dev.mvc.user.UserDAO")
  private UserDAOInter userDAO = null;
   
  public UserProc() { 
    System.out.println("--> EventProc created.");
  }
 
  @Override
  public List<member_userVO> list_all_user() {
    return userDAO.list_all_user();
  }
  
  @Override
  public List<member_userVO> list_all_eventuserno(int eventno) {
    return userDAO.list_all_eventuserno(eventno);
  }
  
  @Override
  public int cnt_win(int eventno) {
    return userDAO.cnt_win(eventno);
  }

  @Override
  public int usercreate(UserVO userVO) {
    System.out.print("memberno:"+userVO.getMemberno());
    return userDAO.usercreate(userVO);
  }
  
  @Override
  public int usercreate(member_userVO userVO) {
    return userDAO.usercreate(userVO);
  }


  @Override
  public member_userVO read_member(int memberno) {
    return userDAO.read_member(memberno);
  }
  
  @Override
  public List<member_userVO> list_member(int memberno) {
    return userDAO.list_member(memberno);
  }
 

  @Override
  public int usercreate(HashMap<String, Object> hashMap) {
    return userDAO.usercreate(hashMap);
  }

  
  // 참여자수 카운트 세기
  @Override
  public int user_cnt(int eventno){
    return userDAO.user_cnt(eventno);
  }

  @Override
  public int update(member_userVO member_userVO) {
    
    int count = userDAO.user_cnt(member_userVO.getEventno());
    // 참여자 수 만큼 반복->난수생성해라 
/*     Random random = new Random();*/
    int[] event_win = new int[count]; 
    for(int i = 0; i< event_win.length; i++){
      event_win[i] = (int)(Math.random()*10000)+1;
      // 랜덤값 반환
      for(int j=0; j<i; j++){
        if(event_win[i]==event_win[j]){
          i--;
          break;
        } // 중복 값 제거1
      }
    }
    for(int i = 0; i< event_win.length; i++){
      System.out.println(event_win[i]+"\n"); // 잘 있는지 확인
    }
    return userDAO.update(member_userVO);
  }

  @Override
  public int winner(HashMap<String, Object> hashMap) {
    return userDAO.winner(hashMap);
  }

  @Override
  public member_userVO read_event(int memberno) {
    return userDAO.read_event(memberno);
  }
  
  
}