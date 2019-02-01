package dev.mvc.answer;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

 
@Repository("dev.mvc.answer.AnswerDAO") // DBMS 저장소 접근 
public class AnswerDAO implements AnswerDAOInter {
   @Autowired  // 빈을 스프링이 만들어서 자동 할당, 개발자는 new 사용 안함.
   private SqlSessionTemplate sqlSessionTemplate = null;
   
   public AnswerDAO() {
     System.out.println("--> AnswerDAO created.");
   }

   @Override
   public int create(AnswerVO answerVO) {
     return sqlSessionTemplate.insert("answer.create", answerVO);
   }

  @Override
  public List<AnswerVO> list(int reviewno) {
    return sqlSessionTemplate.selectList("answer.list", reviewno);
  }

 
  
 
}






