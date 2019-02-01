package dev.mvc.user;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.user.UserDAO") // DBMS 저장소 접근 
public class UserDAO implements UserDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public UserDAO() {
    System.out.println("--> EventDAO created.");
  }
  
  @Override
  public List<member_userVO> list_all_user() {
    return sqlSessionTemplate.selectList("user.list_all_user");
  }

  @Override
  public int usercreate(UserVO userVO) {
    return sqlSessionTemplate.insert("user.create",userVO);
  }
  
  @Override
  public int winner(HashMap<String, Object> hashMap) {
    return sqlSessionTemplate.insert("user.list_all_user2",hashMap);
  }
  
  
  
  @Override
  public int usercreate(member_userVO userVO) {
    return sqlSessionTemplate.insert("user.create",userVO);
  }

  @Override
  public member_userVO read_member(int memberno) {
    return sqlSessionTemplate.selectOne("user.read_member",memberno);
  }
  
  @Override
  public List<member_userVO> list_member(int memberno) {
    return sqlSessionTemplate.selectList("user.list_member", memberno);
  }

  @Override
  public int usercreate(HashMap<String, Object> hashMap) {
    return sqlSessionTemplate.insert("user.create",hashMap);
  }

  @Override
  public int user_cnt(int eventno) {
    return sqlSessionTemplate.selectOne("user.user_cnt",eventno);
  }

  @Override
  public int update(member_userVO member_userVO) {
    return sqlSessionTemplate.selectOne("user.win_update",member_userVO);
  }

  @Override
  public member_userVO read_event(int memberno) {
    return sqlSessionTemplate.selectOne("user.read_event",memberno);
  }

  @Override
  public List<member_userVO> list_all_eventuserno(int eventno) {
    return sqlSessionTemplate.selectList("user.list_all_eventuserno",eventno);
  }

  @Override
  public int cnt_win(int eventno) {
    return sqlSessionTemplate.selectOne("user.cnt_win",eventno);
  }
}
