package dev.mvc.categrp;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.categrp.CategrpDAO") // DBMS 저장소 접근 
public class CategrpDAO implements CategrpDAOInter {
   @Autowired  // 빈을 스프링이 만들어서 자동 할당, 개발자는 new 사용 안함.
   private SqlSessionTemplate sqlSessionTemplate = null;
   
   public CategrpDAO() {
     System.out.println("--> CategrpDAO created.");
   }

  @Override
  public int create(CategrpVO categrpVO) {
    int count = sqlSessionTemplate.insert("categrp.create", categrpVO);
    return count;
  }

  @Override
  public List<CategrpVO> list() {
    List<CategrpVO> list = sqlSessionTemplate.selectList("categrp.list");
    return list;
  }

  @Override
  public CategrpVO read(int categrpno) {
    CategrpVO categrpVO = sqlSessionTemplate.selectOne("categrp.read", categrpno);
    return categrpVO;
  }

  @Override
  public int update(CategrpVO categrpVO) {
    int count = sqlSessionTemplate.update("categrp.update", categrpVO);
    return count;
  }

  @Override
  public int delete(int categrpno) {
    return sqlSessionTemplate.delete("categrp.delete", categrpno);
  }

  @Override
  public int update_seqno_up(int categrpno) {
    return sqlSessionTemplate.update("categrp.update_seqno_up", categrpno);
  }

  @Override
  public int update_seqno_down(int categrpno) {
    return sqlSessionTemplate.update("categrp.update_seqno_down", categrpno);
  }

  /**
   * 트랜잭션 테스트 메소드
   * @param categrpVO
   * @return
   */
  @Override
  public int create_transaction_test(CategrpVO categrpVO) {
    int count = sqlSessionTemplate.insert("categrp.create_transaction_test", categrpVO);
    return count;
  }
   
}






