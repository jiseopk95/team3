package dev.mvc.answer;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

 
@Repository("dev.mvc.answer.AnswerDAO") // DBMS ����� ���� 
public class AnswerDAO implements AnswerDAOInter {
   @Autowired  // ���� �������� ���� �ڵ� �Ҵ�, �����ڴ� new ��� ����.
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






