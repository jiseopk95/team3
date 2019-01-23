package dev.mvc.choice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.choice.ChoiceProc")
public class ChoiceProc implements ChoiceProcInter {
  @Autowired
  @Qualifier("dev.mvc.choice.ChoiceDAO")
  
  private ChoiceDAOInter choiceDAO = null;
  
  public ChoiceProc(){
    System.out.println("--> ChoiceProc created.");
  }

  @Override
  public int create(ChoiceVO choiceVO) {
    
    return choiceDAO.create(choiceVO);
  }
  
  @Override
  public Surveyitem_ChoiceVO list(int surveyitemno) {
    
    return choiceDAO.list(surveyitemno);
  }


  @Override
  public int countBySurveyitemno(int surveyitemno) {
    
    return choiceDAO.countBySurveyitemno(surveyitemno);
  }

  @Override
  public int update(ChoiceVO choiceVO) {
    
    return choiceDAO.update(choiceVO);
  }

  @Override
  public int delete(int surveyitemno) {
    
    return choiceDAO.delete(surveyitemno);
  }


  
}
