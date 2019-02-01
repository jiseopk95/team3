package dev.mvc.pet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.mvc.member.MemberVO;

public interface PetDAOInter {
  /**
  회원 등록
  @param memberVO
  @return 등록된 회원수 1 or 0
  */
  public int create(PetVO petVO);
  
  /**
   * 회원 전체 목록
   * @return
   */
  public List<PetVO> list();
 
  /**
   * 조회
   * @param mno
   * @return
   */
  public PetVO read(int petno);
  
  /**
   * 조회
   * @param id
   * @return
   */
/*  public PetVO readById(String id);*/
  
  /**
   * 변경
   * @param memberVO
   * @return
   */
  public int update(PetVO petVO);
 
  /**
   * 레코드 1건 삭제
   * @param mno 삭제할 회원 번호
   * @return 삭제된 레코드 갯수
   */
  public int delete(int petno);

  /**
   * 검색 목록
   * @param categoryno
   * @return
   */
  public List<PetVO> list_search(HashMap<String, Object> hashMap);

  /**
   * category별 검색된 레코드 갯수
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /**
   * 펫 조회
   * @param mno
   * @return
   */
  /*public PetVO pet_list(int memberno);*/
  public List<PetVO> pet_list(HashMap hashMap);
  public int search_count2(HashMap hashMap);
}







