package dev.mvc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.manager.File2VO;
import dev.mvc.manager.ManagerVO;
import dev.mvc.member.MemberVO;
import nation.web.tool.Tool;

@Component("dev.mvc.manager.ManagerProc")
public class ManagerProc implements ManagerProcInter {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerDAO")
  private ManagerDAOInter managerDAO = null;
  
  public ManagerProc(){
    System.out.println("--> ManagerProc created.");
  }

  @Override
  public int checkId(String id) {
    int cnt = managerDAO.checkId(id);
    return cnt;
  }

  @Override
  public int create(ManagerVO managerVO) {
    int cnt = managerDAO.create(managerVO);
    return cnt;
  }
  
  @Override
  public List<ManagerVO> list() {
    List<ManagerVO> list = managerDAO.list();
      
    return list;
  }
  
  @Override
  public boolean isManager(HttpSession session) {
    boolean sw = false;
    
    String id = (String)session.getAttribute("id");
    
    if (id != null){
      sw = true;
    }
    return sw;
  }

  @Override
  public ManagerVO read(int managerno) {
    ManagerVO managerVO = managerDAO.read(managerno);
    
    return managerVO;
  }
  
  @Override
  public ManagerVO idsearch(String email) {
    ManagerVO managerVO = managerDAO.idsearch(email);
    
    return managerVO;
  }
  
  @Override
  public ManagerVO kind_update(int managerno) {
    ManagerVO managerVO = managerDAO.kind_update(managerno);
    
    return managerVO;
  }

  @Override
  public ManagerVO readById(String id) {
    ManagerVO managerVO = managerDAO.readById(id);
    
    return managerVO;
  }
  
  @Override
  public int update(ManagerVO managerVO) {
    int count = managerDAO.update(managerVO);
    return count;
  }
  
  @Override
  public int kind_update2(ManagerVO managerVO) {
    int count = managerDAO.kind_update2(managerVO);
    return count;
  }
  
 /* @Override
  public int kind_update(MemberVO memberVO) {
    int count = memberDAO.kind_update(memberVO);
    return count;
  }*/
  
  @Override
  public int passwd_update(int managerno, String passwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("managerno", managerno);
    map.put("passwd", passwd);
    
    int count = managerDAO.passwd_update(map);
    
    return count;
  }

  @Override
  public int delete(int managerno) {
    int count = managerDAO.delete(managerno);
    return count;
  }

  @Override
  public int login(String id, String passwd) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("passwd", passwd);
    int count = managerDAO.login(map);
    
    return count;
  }
  @Override
  public ArrayList<File2VO> getThumbs(ManagerVO managerVO) {
    ArrayList<File2VO> file_list = new ArrayList<File2VO>();
    
    String thumbs = managerVO.getThumbs(); // xmas01_2_t.jpg/xmas02_2_t.jpg...
    String files = managerVO.getFiles();          // xmas01_2.jpg/xmas02_2.jpg...
    String sizes = managerVO.getFilesizes();        // 272558/404087... 
    
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
      
        File2VO fileVO = new File2VO(thumbs_array[index], files_array[index], sizes_array[index]);
        file_list.add(fileVO);
      }
    } 

    return file_list;
  }
  
  @Override
  public List<ManagerVO> list_search(HashMap hashMap) {
    List<ManagerVO> list = managerDAO.list_search(hashMap);
   
    int count = list.size();
    for (int i=0; i < count; i++) {
      ManagerVO managerVO = list.get(i);
    }
    
    return list;
  }

  @Override
  public int search_count(HashMap hashMap) {
    return managerDAO.search_count(hashMap);
  }
  
}




