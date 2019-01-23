package dev.mvc.pet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.pet.PetVO;
import dev.mvc.pet.FileVO;
import nation.web.tool.Tool;


@Component("dev.mvc.pet.PetProc")
public class PetProc implements PetProcInter {
  @Autowired
  @Qualifier("dev.mvc.pet.PetDAO")
  private PetDAOInter petDAO = null;
  
  public PetProc(){
    System.out.println("--> PetProc created.");
  }


  @Override
  public int create(PetVO petVO) {
    int cnt =petDAO.create(petVO);
    return cnt;
  }
  
  @Override
  public List<PetVO> list() {
    List<PetVO> list = petDAO.list();
      
    return list;
  }
  
  /*@Override
  public boolean isMember(HttpSession session) {
    boolean sw = false;
    
    String id = (String)session.getAttribute("id");
    
    if (id != null){
      sw = true;
    }
    return sw;
  }*/

  @Override
  public PetVO read(int petno) {
    PetVO petVO = petDAO.read(petno);
    
    return petVO;
  }
  

 /* @Override
  public PetVO readById(String id) {
    PetVO petVO = petDAO.readById(id);
    
    return petVO;
  }*/
  
  @Override
  public int update(PetVO petVO) {
    int count = petDAO.update(petVO);
    return count;
  }
  
  @Override
  public int delete(int petno) {
    int count = petDAO.delete(petno);
    return count;
  }
  
  @Override
  public ArrayList<FileVO> getThumbs(PetVO petVO) {
    ArrayList<FileVO> file_list = new ArrayList<FileVO>();
    
    String thumbs = petVO.getThumbs(); // xmas01_2_t.jpg/xmas02_2_t.jpg...
    String files = petVO.getFiles();          // xmas01_2.jpg/xmas02_2.jpg...
    String sizes = petVO.getFilesizes();        // 272558/404087... 
    
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
}




