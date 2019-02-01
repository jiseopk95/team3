package dev.mvc.answer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import nation.web.tool.Tool;



@Component("dev.mvc.answer.AnswerProc")
public class AnswerProc implements AnswerProcInter {
  @Autowired
  @Qualifier("dev.mvc.answer.AnswerDAO")
  private AnswerDAOInter answerDAO = null;
  
  public AnswerProc() {
    System.out.println("--> AnswerProc created.");
  }

  @Override
  public int create(AnswerVO answerVO) {
    return answerDAO.create(answerVO);
    }

  @Override
  public List<AnswerVO> list(int reviewno) {
    return answerDAO.list(reviewno);
  }

  
   
  
}
    