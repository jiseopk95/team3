package dev.mvc.surveyparty;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.surveyparty.SurveypartyDAO")
public class SurveypartyDAO implements SurveypartyDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  public SurveypartyDAO(){
    System.out.println("------> SurveypartyDAO created");
  }
  @Override
  public int create(SurveypartyVO surveypartyVO) {
    
    return sqlSessionTemplate.insert("surveyparty.create", surveypartyVO);
  }
  @Override
  public List<Survey_PartyVO> list() {
   
    return sqlSessionTemplate.selectList("surveyparty.list");
  }
  @Override
  public List<Survey_PartyVO> list_survey(int surveyno) {
    
    return sqlSessionTemplate.selectList("surveyparty.list_survey", surveyno);
  }
  @Override
  public int mnoCnt(int memberno) {
    
    return sqlSessionTemplate.selectOne("surveyparty.mnoCnt",memberno);
  }

}
