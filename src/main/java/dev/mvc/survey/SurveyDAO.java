
package dev.mvc.survey;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.survey.SurveyDAO")
public class SurveyDAO implements SurveyDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public SurveyDAO() {
    System.out.println("-----> SurveyDAO created");
  }
  
  @Override
  public int create(SurveyVO surveyVO) {
    
    return sqlSessionTemplate.insert("survey.create", surveyVO);
  }

  @Override
  public List<Manager_SurveyVO> list(int managerno) {
    return sqlSessionTemplate.selectList("survey.list",managerno);
  }

  @Override
  public Manager_SurveyVO read(int surveyno) {
    return sqlSessionTemplate.selectOne("survey.read",surveyno);
  }

  @Override
  public int update(SurveyVO surveyVO) {
    return sqlSessionTemplate.update("survey.update",surveyVO);
  }

  @Override
  public int delete(int surveyno) {
    return sqlSessionTemplate.delete("survey.delete",surveyno);
  }

  @Override
  public int update_seqno_up(int surveyno) {
    
    return sqlSessionTemplate.update("survey.update_seqno_up",surveyno);
  }

  @Override
  public int update_seqno_down(int surveyno) {
    return sqlSessionTemplate.update("survey.update_seqno_down",surveyno);
  }  
  
  @Override
  public int increaseCnt(int surveyno) {
    return sqlSessionTemplate.update("survey.increaseCnt", surveyno);
  }

  @Override
  public int decreaseCnt(int surveyno) {
    return sqlSessionTemplate.update("survey.decreaseCnt", surveyno);
  }

  @Override
  public List<FindVO> findNo(int surveyno) {
    
    return sqlSessionTemplate.selectList("survey.findNo", surveyno);
  }

  @Override
  public List<Manager_SurveyVO> list_by_manager_search(HashMap hashMap) {

    return sqlSessionTemplate.selectList("survey.list_by_manager_search", hashMap);
  }

  @Override
  public List<Manager_SurveyVO> list_by_manager_search_paging(HashMap<String, Object> hashMap) {

    return sqlSessionTemplate.selectList("survey.list_by_manager_search_paging", hashMap);
  }

  @Override
  public int search_count(HashMap hashMap) {
  
    return sqlSessionTemplate.selectOne("survey.search_count", hashMap);
  }

  @Override
  public List<SurveyVO> list_m() {
    // TODO Auto-generated method stub
    return sqlSessionTemplate.selectList("survey.list_m");
  }

  @Override
  public SurveyVO read_m(int surveyno) {
    
    return sqlSessionTemplate.selectOne("survey.read_m", surveyno);
  }




}
