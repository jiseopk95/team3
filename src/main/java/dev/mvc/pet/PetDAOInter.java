package dev.mvc.pet;

import java.util.List;
import java.util.Map;

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

}







