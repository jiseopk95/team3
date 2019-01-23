package dev.mvc.surveyitem;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.surveyitem.FileVO;
import nation.web.tool.Tool;



@Component("dev.mvc.surveyitem.SurveyitemProc")
public class SurveyitemProc implements SurveyitemProcInter {

   @Autowired
   @Qualifier("dev.mvc.surveyitem.SurveyitemDAO")
   
   private SurveyitemDAOInter surveyitemDAO = null;
  
   public SurveyitemProc(){
     System.out.println("--> SurveyitemProc created.");
   }

  @Override
  public int create(SurveyitemVO surveyitemVO) {
    
    return surveyitemDAO.create(surveyitemVO);
  }

  @Override
  public List<Survey_ItemVO> list(int surveyno) {
    List<Survey_ItemVO> list =surveyitemDAO.list(surveyno);
    int count = list.size();
    for (int i=0; i < count; i++) {
      Survey_ItemVO survey_itemVO = list.get(i);
      survey_itemVO.setThumb(getThumb(survey_itemVO));
    }
    
    
    return list;
  }
  
  /**
   * 이미지 목록중에 첫번째 이미지 파일명을 추출하여 리턴
   * @param survey_itemVO
   * @return
   */
  public String getThumb(Survey_ItemVO survey_itemVO) {
    String thumbs = survey_itemVO.getThumbs();
    String thumb = "";
    
    if (thumbs != null) {
      String[] thumbs_array = thumbs.split("/");
      int count = thumbs_array.length;
      if (count > 0) {
        thumb = thumbs_array[0];    
      }
    }
    System.out.println("thumb: " + thumb);
    return thumb;
  }

  @Override
  public Survey_ItemVO read(int surveyitemno) {

    return surveyitemDAO.read(surveyitemno);
  }

  @Override
  public ArrayList<FileVO> getThumbs(Survey_ItemVO survey_itemVO) {
 ArrayList<FileVO> file_list = new ArrayList<FileVO>();
    
    String thumbs = survey_itemVO.getThumbs(); // xmas01_2_t.jpg/xmas02_2_t.jpg...
    String files = survey_itemVO.getFiles();          // xmas01_2.jpg/xmas02_2.jpg...
    String sizes = survey_itemVO.getSizes();        // 272558/404087... 
    
    String[] thumbs_array = thumbs.split("/");  // Thumbs
    String[] files_array = files.split("/");   // 파일명 추출
    String[] sizes_array = sizes.split("/"); // 파일 사이즈
 
    int count = sizes_array.length;
    // System.out.println("sizes_array.length: " + sizes_array.length);
    // System.out.println("sizes: " + sizes);
    // System.out.println("files: " + files);
 
    if (files.length() > 0) {
      for (int index = 0; index < count; index++) {
        sizes_array[index] = Tool.unit(new Integer(sizes_array[index]));  // 1024 -> 1KB
      
        FileVO fileVO = new FileVO(thumbs_array[index], files_array[index], sizes_array[index]);
        file_list.add(fileVO);
      }
    } 

    return file_list;
  }

  @Override
  public int update(SurveyitemVO surveyitemVO) {

    return surveyitemDAO.update(surveyitemVO);
  }

  @Override
  public int delete(int surveyitemno) {
    return surveyitemDAO.delete(surveyitemno);
  }

  @Override
  public int update_seqno_up(int surveyitemno) {
   
    return surveyitemDAO.update_seqno_up(surveyitemno);
  }

  @Override
  public int update_seqno_down(int surveyitemno) {
  
    return surveyitemDAO.update_seqno_down(surveyitemno);
  }
  
  @Override
  public int countBySurveyno(int surveyno) {
    
    return surveyitemDAO.countBySurveyno(surveyno);
  }

  @Override
  public int deleteBySurveyno(int surveyno) {
    
    return surveyitemDAO.deleteBySurveyno(surveyno);
  }

  @Override
  public int itemCnt(int surveyitemno) {
    
    return surveyitemDAO.itemCnt(surveyitemno);
  }


}
