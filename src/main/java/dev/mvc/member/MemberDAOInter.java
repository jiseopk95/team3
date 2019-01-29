package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.mvc.member.MemberVO;

public interface MemberDAOInter {
  /**
   * 중복 아이디 검사
   * @param id
   * @return 중복 아이디 갯수
   */
  public int checkId(String id);
  public int checkemail(String email);
  
  /**
  회원 등록
  @param memberVO
  @return 등록된 회원수 1 or 0
  */
  public int create(MemberVO memberVO);
  
  /**
   * 회원 전체 목록
   * @return
   */
  public List<MemberVO> list();
 
  /**
   * 조회
   * @param mno
   * @return
   */
  public MemberVO read(int memberno);

  

  /**
   * 조회
   * @param id
   * @return
   */
  public MemberVO readById(String id);
  
  
  /**
   * 변경
   * @param memberVO
   * @return
   */
  public int update(MemberVO memberVO);
  
  /**
   * 패스워드 변경 
   * @param map
   * @return
   */
  public int passwd_update(Map<String, Object> map);
  
  /**
   * 레코드 1건 삭제
   * @param mno 삭제할 회원 번호
   * @return 삭제된 레코드 갯수
   */
  public int delete(int memberno);

  /**
   * 로그인 
   * @param map
   * @return
   */
  public int login(Map map);
  
  /**
   * 검색 목록
   * @param categoryno
   * @return
   */
  public List<MemberVO> list_search(HashMap hashMap);

  /**
   * category별 검색된 레코드 갯수
   * @return
   */
  public int search_count(HashMap hashMap);
  
  public List<MemberVO> idsearch(HashMap hashMap);
  
  public List<MemberVO> list_id();
  
  public int search_count2(HashMap hashMap);
  
  public List<MemberVO> passwdsearch(HashMap hashMap);
  
  public List<MemberVO> list_passwd();
  
  public int search_count3(HashMap hashMap);
  
  
}







