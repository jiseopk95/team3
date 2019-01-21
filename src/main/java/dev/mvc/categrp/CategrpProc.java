package dev.mvc.categrp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.categrp.CategrpProc")
public class CategrpProc  implements CategrpProcInter {
  @Autowired
  @Qualifier("dev.mvc.categrp.CategrpDAO")
  // @Qualifier("dev.mvc.categrp.CategrpDAO2016")
  // @Qualifier("dev.mvc.categrp.CategrpDAO2017")
  private CategrpDAOInter categrpDAO = null;

  public CategrpProc() {
    System.out.println("--> CategrpProc created.");
  }

  @Override
  public int create(CategrpVO categrpVO) {
    int count = 0;
    // 정상 처리
    count = categrpDAO.create(categrpVO);
     
    return count;
  }

  @Override
  public List<CategrpVO> list() {
    List<CategrpVO> list = categrpDAO.list();
    
    return list;
  }

  @Override
  public CategrpVO read(int categrpno) {
    return categrpDAO.read(categrpno);
  }

  @Override
  public int update(CategrpVO categrpVO) {
    return categrpDAO.update(categrpVO);
  }

  @Override
  public int delete(int categrpno) {
    return categrpDAO.delete(categrpno);
  }

  @Override
  public int update_seqno_up(int categrpno) {
    return categrpDAO.update_seqno_up(categrpno);
  }

  @Override
  public int update_seqno_down(int categrpno) {
    return categrpDAO.update_seqno_down(categrpno);
  }

}

   




