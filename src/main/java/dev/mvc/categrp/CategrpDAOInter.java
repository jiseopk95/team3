package dev.mvc.categrp;

import java.util.List;

public interface CategrpDAOInter {
  /**
   * <Xmp>
   * 카테고리 그룹 등록
   * <insert id="create" parameterType="CategrpVO"> 
   * </Xmp>
   * @param categrpVO
   * @return 처리된 레코드 갯수
   */
  public int create(CategrpVO categrpVO);
  
  /**
   * 목록
   * <xmp>
   * <select id="list" resultType="CategrpVO">
   * </xmp> 
   * @return
   */
  public List<CategrpVO> list();
  
  /**
   * 조회
   * <xmp>
   *   <select id="read" resultType="CategrpVO" parameterType="int">
   * </xmp>  
   * @param categrpno
   * @return
   */
  public CategrpVO read (int categrpno);
   
  /**
   * 수정 처리
   * <xmp>
   *   <update id="update" parameterType="CategrpVO"> 
   * </xmp>
   * @param categrpVO
   * @return 처리된 레코드 갯수
   */
  public int update(CategrpVO categrpVO);
  
  /**
   * 삭제 처리
   * <xmp>
   *   <delete id="delete" parameterType="int">
   * </xmp> 
   * @param categrpno
   * @return 처리된 레코드 갯수
   */
  public int delete (int categrpno);
  
  /**
   * 출력 순서 상향
   * <xmp>
   * <update id="update_seqno_up" parameterType="int">
   * </xmp>
   * @param categrpno
   * @return 처리된 레코드 갯수
   */
  public int update_seqno_up(int categrpno);
  
  /**
   * 출력 순서 하향
   * <xmp>
   * <update id="update_seqno_down" parameterType="int">
   * </xmp>
   * @param categrpno
   * @return 처리된 레코드 갯수
   */
  public int update_seqno_down(int categrpno);
  
  /**
   * 트랜잭션 테스트 메소드
   * @param categrpVO
   * @return
   */
  public int create_transaction_test(CategrpVO categrpVO);
  
}








