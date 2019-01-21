package dev.mvc.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import dev.mvc.manager.File2VO;
import dev.mvc.manager.ManagerVO;

public interface ManagerProcInter {
  /**
   * 중복 아이디 검사
   * @param id
   * @return 중복 아이디 갯수
   */
  public int checkId(String id);
  
  /**
  회원 등록
  @param memberVO
  @return 등록된 회원수 1 or 0
  */
  public int create(ManagerVO managerVO);
  
  /**
   * 회원 전체 목록
   * @return
   */
  public List<ManagerVO> list();
  
  /**
   * 로그인된 회원 계정인지 검사합니다.
   * @param request
   * @return true: 관리자
   */
  public boolean isManager(HttpSession session);
  
  /**
   * 조회
   * @param mno
   * @return
   */
  public ManagerVO read(int managerno);
  public ManagerVO idsearch(String email);

  
  /**
   * 조회
   * @param mno
   * @return
   */
  public ManagerVO kind_update(int managerno);
  
  /**
   * 조회
   * @param id
   * @return
   */
  public ManagerVO readById(String id);
  

  /**
   * 변경
   * @param memberVO
   * @return
   */
  public int update(ManagerVO managerVO);
  public int kind_update2(ManagerVO managerVO);
  
  /**
   * 패스워드 변경
   * 
   * @param mno 회원 번호
   * @param passwd 변경할 패스워드 값
   * @return
   */
  public int passwd_update(int managerno, String passwd);

  /**
   * 레코드 1건 삭제
   * @param mno 삭제할 회원 번호
   * @return 삭제된 레코드 갯수
   */
  public int delete(int managerno);
  
  /**
   * 로그인 
   * @param map
   * @return
   */
  public int login(String id, String passwd);
  
  ArrayList<File2VO> getThumbs(ManagerVO managerVO);
}









