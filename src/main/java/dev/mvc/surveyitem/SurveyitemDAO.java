package dev.mvc.surveyitem;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.surveyitem.SurveyitemDAO")
public class SurveyitemDAO implements SurveyitemDAOInter {
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;

  public SurveyitemDAO(){
    System.out.println("------> SurveyitemDAO created");
  }

  @Override
  public int create(SurveyitemVO surveyitemVO) {
     
    return sqlSessionTemplate.insert("surveyitem.create", surveyitemVO);
   
  }

  @Override
  public List<Survey_ItemVO> list(int surveyno) {
    return sqlSessionTemplate.selectList("surveyitem.list",surveyno);
  }

  @Override
  public Survey_ItemVO read(int surveyitemno) {
    return sqlSessionTemplate.selectOne("surveyitem.read", surveyitemno);
  }

  @Override
  public int update(SurveyitemVO surveyitemVO) {
    return sqlSessionTemplate.update("surveyitem.update", surveyitemVO);
  }

  @Override
  public int delete(int surveyitemno) {
    return sqlSessionTemplate.delete("surveyitem.delete", surveyitemno);
  }

  @Override
  public int update_seqno_up(int surveyitemno) {
    
    return sqlSessionTemplate.update("surveyitem.update_seqno_up",surveyitemno);
  }

  @Override
  public int update_seqno_down(int surveyitemno) {
   
    return sqlSessionTemplate.update("surveyitem.update_seqno_down",surveyitemno);
  }
  
  @Override
  public int countBySurveyno(int surveyno) {
    
    return sqlSessionTemplate.selectOne("surveyitem.countBySurveyno", surveyno);
  }

  @Override
  public int deleteBySurveyno(int surveyno) {
    
    return sqlSessionTemplate.delete("surveyitem.deleteBySurveyno", surveyno);
  }

  @Override
  public int itemCnt(int surveyitemno) {
   
    return sqlSessionTemplate.update("surveyitem.itemCnt", surveyitemno);
    
  }
  
}