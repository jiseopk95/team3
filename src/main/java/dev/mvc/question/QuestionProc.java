package dev.mvc.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nation.web.tool.Tool;



@Component("dev.mvc.question.QuestionProc")
public class QuestionProc implements QuestionProcInter {
  @Autowired
  @Qualifier("dev.mvc.question.QuestionDAO")
  private QuestionDAOInter questionDAO = null;
  
  public QuestionProc() {
    System.out.println("--> QuestionProc created.");
  }

  @Override
  public int create(QuestionVO questionVO) {
    return questionDAO.create(questionVO);
  }
  
  @Override
  public List<QuestionVO> list(int categoryno) {
    return questionDAO.list(categoryno);
  }

  @Override
  public QuestionVO read(int questionno) {
    return questionDAO.read(questionno);
  }
  
  @Override
  public int passwd_check(int questionno, String passwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("questionno", questionno);
    map.put("passwd", passwd);
    int count = questionDAO.passwd_check(map);
      
    return count;
  }

  @Override
  public int delete(int questionno) {
    return questionDAO.delete(questionno);
  }

  @Override
  public int update(QuestionVO questionVO) {
    return questionDAO.update(questionVO);
  }

  @Override
  public int updateAnsnum(QuestionVO questionVO) {
    int count = 0;
    count = questionDAO.updateAnsnum(questionVO);
    
    return count;
  }

  @Override
  public int reply(QuestionVO questionVO) {
    int count = 0;
    count = questionDAO.reply(questionVO);
    
    return count;
  }

  
}