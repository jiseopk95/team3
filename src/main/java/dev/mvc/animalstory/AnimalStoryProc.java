package dev.mvc.animalstory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import nation.web.tool.Tool;

@Component("dev.mvc.animalstory.AnimalStoryProc")
public class AnimalStoryProc implements AnimalStoryProcInter{

  @Autowired
  @Qualifier("dev.mvc.animalstory.AnimalStoryDAO")
  
  private AnimalStoryDAOInter aniDAO = null;
  
  public AnimalStoryProc() {
    System.out.println("--> AnimalStoryProc created");
  }
  
  @Override
  public int create(AnimalStoryVO animalStoryVO) {
    
    return aniDAO.create(animalStoryVO);
  }
  
  @Override
  public List<AnimalStoryVO> list() {
    List<AnimalStoryVO> list = aniDAO.list();
    AnimalStoryVO aniVO = null;
    String title = "";
    String date = "";
    String anitype = "";
    
    int size = list.size();
    for(int i = 0; i < size; i++) {
      aniVO = list.get(i);

      anitype = aniVO.getAnitype();
      if(anitype.equals("1")) {
        aniVO.setAnitype("강아지");
      } else {
        aniVO.setAnitype("고양이");
      }
      
      title = aniVO.getTitle();
      aniVO.setTitle(Tool.textLength(title, 10));
      
      date = aniVO.getRdate();
      aniVO.setRdate(date.replace("/", "-"));
      
      String thumbs = aniVO.getThumbs();
      if (thumbs.length() > 0) { // preview 이미지가 있는지 검사
        String thumb = (thumbs.split("/"))[0]; // 첫번째 파일명 추출
        aniVO.setThumb(thumb);
      }
    }
    
    
    return list;
  }
  
  @Override
  public List<AnimalStoryVO> list_anitype(HashMap hashmap) {
    
    List<AnimalStoryVO> list = aniDAO.list_anitype(hashmap);
    AnimalStoryVO aniVO = null;
    String title = "";
    String date = "";
    String anitype = "";
    
    int size = list.size();
    for(int i = 0; i < size; i++) {
      aniVO = list.get(i);
      
      anitype = aniVO.getAnitype();
      if(anitype.equals("1")) {
        aniVO.setAnitype("강아지");
      } else {
        aniVO.setAnitype("고양이");
      }
      
      title = aniVO.getTitle();
      aniVO.setTitle(Tool.textLength(title, 10));
      
      date = aniVO.getRdate();
      aniVO.setRdate(date.replace("/", "-"));
      
      String thumbs = aniVO.getThumbs();
      if (thumbs.length() > 0) { // preview 이미지가 있는지 검사
        String thumb = (thumbs.split("/"))[0]; // 첫번째 파일명 추출
        aniVO.setThumb(thumb);
      }
    }
    
    
    return list;
  }

  @Override
  public String manager(int managerno) {
    
    return aniDAO.manager(managerno);
  }

  @Override
  public int delete(int anino) {
    
    return aniDAO.delete(anino);
  }

  @Override
  public AnimalStoryVO read(int anino) {
    
    AnimalStoryVO aniVO = aniDAO.read(anino);
    
    String date = "";
    
    date = aniVO.getRdate();
    aniVO.setRdate(date.replace("/", "-"));
    
    return aniVO;
  }

  /**
   * 이미지 목록중에 첫번째 이미지 파일명을 추출하여 리턴
   * @param contentsVO
   * @return
   */
  public String getThumb(AnimalStoryVO aniVO) {
    String thumbs = aniVO.getThumbs();
    String thumb = "";
    
    if (thumbs != null) {
      String[] thumbs_array = thumbs.split("/");
      int count = thumbs_array.length;
      
      if (count > 0) {
        thumb = thumbs_array[0];    
      }
    }
    return thumb;
  }
  
  @Override
  public ArrayList<FileVO> getThumbs(AnimalStoryVO aniVO) {
    ArrayList<FileVO> file_list = new ArrayList<FileVO>();
    
    String thumbs = aniVO.getThumbs(); // xmas01_2_t.jpg/xmas02_2_t.jpg...
    String files = aniVO.getFiles();          // xmas01_2.jpg/xmas02_2.jpg...
    String sizes = aniVO.getSizes();        // 272558/404087... 
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
  public int update(AnimalStoryVO aniVO) {
    
    return aniDAO.update(aniVO);
  }

  @Override
  public List<AnimalStoryVO> list_by_search(String content) {
    List<AnimalStoryVO> list = aniDAO.list_by_search(content);
    AnimalStoryVO aniVO = null;
    String title = "";
    String date = "";
    
    int size = list.size();
    for(int i = 0; i < size; i++) {
      aniVO = list.get(i);
      title = aniVO.getTitle();
      aniVO.setTitle(Tool.textLength(title, 10));
      date = aniVO.getRdate();
      aniVO.setRdate(date.replace("/", "-"));
      String thumbs = aniVO.getThumbs();
      if (thumbs.length() > 0) { // preview 이미지가 있는지 검사
        String thumb = (thumbs.split("/"))[0]; // 첫번째 파일명 추출
        aniVO.setThumb(thumb);
      }
    }
    
    return list;
  }

  

  

}
