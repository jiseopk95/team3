package dev.mvc.choice;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.choice.ChoiceDAO")
public class ChoiceDAO implements ChoiceDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public ChoiceDAO() {
    System.out.println("-----> SurveyDAO created");
  }

  @Override
  public int create(ChoiceVO choiceVO) {
  
    return sqlSessionTemplate.insert("choice.create", choiceVO);
  }

  @Override
  public Surveyitem_ChoiceVO list(int surveyitemno) {
  
    return sqlSessionTemplate.selectOne("choice.list",surveyitemno);
  }

  @Override
  public int countBySurveyitemno(int surveyitemno) {
    
    return sqlSessionTemplate.selectOne("choice.countBySurveyitemno",surveyitemno);
  }

  @Override
  public int update(ChoiceVO choiceVO) {
    
    return sqlSessionTemplate.update("choice.update", choiceVO);
    
    
  }

  @Override
  public int delete(int surveyitemno) {
    
    return sqlSessionTemplate.delete("choice.delete", surveyitemno);
  }
}
