package dev.mvc.event;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.beauty.BeautyVO;

@Repository("dev.mvc.event.EventDAO") // DBMS 저장소 접근 
public class EventDAO implements EventDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public EventDAO() {
    System.out.println("--> EventDAO created.");
  }
  

  @Override
  public int select_user(HashMap<String, Object> hashMap) {
    return sqlSessionTemplate.selectOne("event.select_user",hashMap);
  }
  
  @Override
  public int create(EventVO eventVO) {
    return sqlSessionTemplate.insert("event.create",eventVO);
  }
  @Override
  public List<EventVO> list_all_event() {
    return sqlSessionTemplate.selectList("event.list_all_event");
  }
  @Override
  public int delete(int eventno) {
    return sqlSessionTemplate.delete("event.delete", eventno);
  }

  @Override
  public int update(EventVO eventVO) {
    return sqlSessionTemplate.update("event.update",eventVO);
  }
  
  @Override
  public event_managerVO read_manager(int eventno) {
    return sqlSessionTemplate.selectOne("event.read_manager", eventno);
  }
  
  @Override
  public EventVO read(int eventno) {
    return sqlSessionTemplate.selectOne("event.read", eventno);
  }
  
  @Override
  public List<EventVO> search_paging_member(HashMap<String, Object> hashMap) {
    return sqlSessionTemplate.selectList("event.search_paging_member", hashMap);
  }
  @Override
  public List<event_managerVO> search_paging(HashMap<String, Object> hashMap) {
    return sqlSessionTemplate.selectList("event.search_paging", hashMap);
  }
  @Override
  public int search_count(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("event.search_count", hashMap);
  }

  @Override
  public List<event_presentVO> list_present() {
    return sqlSessionTemplate.selectList("event.list_present");
  }

  @Override
  public List<event_eventuserVO> read_member() {
    return sqlSessionTemplate.selectList("event.read_member");
  }

  @Override
  public int usercreate(event_eventuserVO euVO) {
    return sqlSessionTemplate.insert("event.eventCheck",euVO);
  }

  @Override
  public event_eventuserVO read_win(int eventno) {
    return sqlSessionTemplate.selectOne("event.read_win",eventno);
  }

  
  
  
  @Override
  public List<event_eventuserVO> search_paging_win(HashMap<String, Object> hashMap) {
    return sqlSessionTemplate.selectList("event.search_paging_win", hashMap);
  }
  @Override
  public int search_count_win(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("event.search_count_win", hashMap);
  }
}