package dev.mvc.question;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


 
@Repository("dev.mvc.question.QuestionDAO") // DBMS 저장소 접근 
public class QuestionDAO implements QuestionDAOInter {
   @Autowired  // 빈을 스프링이 만들어서 자동 할당, 개발자는 new 사용 안함.
   private SqlSessionTemplate sqlSessionTemplate = null;
   
   public QuestionDAO() {
     System.out.println("--> QuestionDAO created.");
   }

   @Override
   public int create(QuestionVO questionVO) {
     return sqlSessionTemplate.insert("question.create", questionVO);
   }

  @Override
  public List<QuestionVO> list(int categoryno) {
    return sqlSessionTemplate.selectList("question.list", categoryno);
  }

  @Override
  public QuestionVO read(int questionno) {
    return  sqlSessionTemplate.selectOne("question.read", questionno);
  }

  @Override
  public int passwd_check(Map map) {
    return sqlSessionTemplate.selectOne("question.passwd_check",map);
  }

  @Override
  public int delete(int questionno) {
    return sqlSessionTemplate.delete("question.delete", questionno);
  }

  @Override
  public int update(QuestionVO questionVO) {
    return sqlSessionTemplate.update("question.update", questionVO);
  }
  
  @Override
  public int updateAnsnum(QuestionVO questionVO) {
    return sqlSessionTemplate.update("question.updateAnsnum", questionVO); 
  }

  @Override
  public int reply(QuestionVO questionVO) {
    return sqlSessionTemplate.insert("question.reply", questionVO);
  }

  
 
}

 




