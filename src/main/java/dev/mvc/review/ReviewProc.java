package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.review.ReviewDAOInter;
import nation.web.tool.Tool;



@Component("dev.mvc.review.ReviewProc")
public class ReviewProc implements ReviewProcInter {
  @Autowired
  @Qualifier("dev.mvc.review.ReviewDAO")
  private ReviewDAOInter reviewDAO = null;
  
  public ReviewProc() {
    System.out.println("--> ReviewProc created.");
  }

  @Override
  public int create(ReviewVO reviewVO) {
    return reviewDAO.create(reviewVO);
  }
  
  @Override
  public List<ReviewVO> list() {
    return reviewDAO.list();
  }

  @Override
  public ReviewVO read(int reviewno) {
    return reviewDAO.read(reviewno);
  }

  @Override
  public int delete(int reviewno) {
    return reviewDAO.delete(reviewno);
  }

  @Override
  public ArrayList<FileVO> getThumbs(ReviewVO reviewVO) {
   ArrayList<FileVO> file_list = new ArrayList<FileVO>();
    
    String thumbs = reviewVO.getThumbs(); // xmas01_2_t.jpg/xmas02_2_t.jpg...
    String files = reviewVO.getFiles();          // xmas01_2.jpg/xmas02_2.jpg...
    String sizes = reviewVO.getFilesize();      // 272558/404087... 
    
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
  public int update(ReviewVO reviewVO) {
    return reviewDAO.update(reviewVO);
  }

 /* @Override
  public List<ReviewVO> list_by_category_search(HashMap hashMap) {
      List<ReviewVO> list = reviewDAO.list_by_category_search(hashMap);
    
      int count = list.size();
      for (int i=0; i < count; i++) {
        ReviewVO reviewVO = list.get(i);
        reviewVO.setThumbs(getThumbs(reviewVO));
      }
      return list;
  }

  @Override
  public int search_count(HashMap hashMap) {
    // TODO Auto-generated method stub
    return 0;
  }*/




}