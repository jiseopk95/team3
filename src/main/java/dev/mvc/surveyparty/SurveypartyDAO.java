package dev.mvc.surveyparty;

import java.util.HashMap;
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
  public int mnoCnt(HashMap hashMap) {
    
    return sqlSessionTemplate.selectOne("surveyparty.mnoCnt",hashMap);
  }
  @Override
  public int delete(int surveyno) {
    
    return sqlSessionTemplate.delete("surveyparty.delete", surveyno);
  }
  @Override
  public int delete_item(int surveyitemno) {
   
    return sqlSessionTemplate.delete("surveyparty.delete_item", surveyitemno);
  }
  

}
