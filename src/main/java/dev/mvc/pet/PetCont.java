package dev.mvc.pet;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import dev.mvc.pet.PetVO;

import nation.web.tool.Tool;
import nation.web.tool.Upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class PetCont {
  @Autowired
  @Qualifier("dev.mvc.pet.PetProc")
  private PetProcInter petProc = null;
   
  public PetCont(){
    System.out.println("--> PetCont created.");
  }
  
  // http://localhost:9090/ojt/member/create.do
  @RequestMapping(value = "/pet/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/pet/create"); // webapp/member/create.jsp
    return mav;
  }
  
  /**
   * 등록 처리
   * @param redirectAttributes
   * @param request
   * @param memberVO
   * @return
   */
  @RequestMapping(value="/pet/create.do", method=RequestMethod.POST)
  public ModelAndView create(RedirectAttributes redirectAttributes,
                                        HttpServletRequest request, 
                                        PetVO petVO){
    // System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/pet/storage");
    List<MultipartFile> filesMF = petVO.getFilesMF(); // Spring이 File 객체를
                                                           // 저장해둠.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // 컬럼에 저장할 파일명
    String files_item = ""; // 하나의 파일명
    String filesizes = "";
    long sizes_item = 0; // 하나의 파일 사이즈
    String thumbs = ""; // Thumb 파일들
    String thumbs_item = ""; // 하나의 Thumb 파일명

    int count = filesMF.size(); // 업로드된 파일 갯수

    // Spring은 파일 선택을 안해도 1개의 MultipartFile 객체가 생성됨.
    // System.out.println("--> 업로드된 파일 갯수 count: " + count);

    if (count > 0) { // 전송 파일이 존재한다면
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // 전송파일이 있는지 체크, filesMF
        if (multipartFile.getSize() > 0) { // 전송파일이 있는지 체크
          // System.out.println("전송 파일이 있습니다.");
          files_item = Upload.saveFileSpring(multipartFile, upDir);
          sizes_item = multipartFile.getSize();

          if (Tool.isImage(files_item)) {
            thumbs_item = Tool.preview(upDir, files_item, 120, 80); // Thumb 이미지
                                                                    // 생성
          }

          if (i != 0 && i < count) { // index가 1 이상이면(두번째 파일 이상이면)
            // 하나의 컬럼에 여러개의 파일명을 조합하여 저장, file1.jpg/file2.jpg/file3.jpg
            files = files + "/" + files_item;
            // 하나의 컬럼에 여러개의 파일 사이즈를 조합하여 저장, 12546/78956/42658
            filesizes = filesizes + "/" + sizes_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
            files = files_item; // file1.jpg
            filesizes = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        } // if (multipartFile.getSize() > 0) {  END
        
      }
    }
    petVO.setFiles(files);
    petVO.setFilesizes(filesizes);
    petVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    count = petProc.create(petVO); // 등록
 
    redirectAttributes.addAttribute("sw", "create");
    redirectAttributes.addAttribute("count", count); // 1 or 0
    
    mav.setViewName("redirect:/pet/create_message.jsp");
   
    return mav;
  }
  
  @RequestMapping(value="/pet/list.do", method=RequestMethod.GET)
  public ModelAndView list(HttpSession session){
    // System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/pet/list"); // webapp/member/list.jsp

     mav.setViewName("/pet/list"); // webapp/member/list.jsp
      
     List<PetVO> list = petProc.list();
     mav.addObject("list", list);
     
    return mav;
  }  
  
  @RequestMapping(value="/pet/read.do", method=RequestMethod.GET)
  public ModelAndView read(int petno){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/pet/read"); // webapp/member/read.jsp
    
    PetVO petVO = petProc.read(petno);
    mav.addObject("petVO", petVO);
    
    ArrayList<FileVO> file_list=petProc.getThumbs(petVO);
    mav.addObject("file_list", file_list);
    return mav;
  }  
  
  @RequestMapping(value="/pet/update.do", method=RequestMethod.GET)
  public ModelAndView pet_update(int petno){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/pet/update"); // webapp/member/read.jsp
    
    PetVO petVO = petProc.read(petno);
    mav.addObject("petVO", petVO);
    
    return mav;
  }  
  
  @RequestMapping(value="/pet/update.do", method=RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes,
                                        HttpServletRequest request, PetVO petVO){
    // System.out.println("--> update() POST called.");
    ModelAndView mav = new ModelAndView();
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/pet/storage");
    List<MultipartFile> filesMF = petVO.getFilesMF(); // Spring이 File 객체를
                                                           // 저장해둠.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // 컬럼에 저장할 파일명
    String files_item = ""; // 하나의 파일명
    String filesizes = "";
    long sizes_item = 0; // 하나의 파일 사이즈
    String thumbs = ""; // Thumb 파일들
    String thumbs_item = ""; // 하나의 Thumb 파일명

    int count = filesMF.size(); // 업로드된 파일 갯수

    // 기존의 등록 정보 조회
    PetVO petVO_old = petProc.read(petVO.getPetno());
    if (filesMF.get(0).getSize() > 0) { // 새로운 파일을 등록함으로 기존에 등록된 파일 목록 삭제
      // thumbs 파일 삭제
      String thumbs_old = petVO_old.getThumbs();
      StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname);
      }

      // 원본 파일 삭제
      String files_old = petVO_old.getFiles();
      StringTokenizer files_st = new StringTokenizer(files_old, "/");
      while (files_st.hasMoreTokens()) {
        String fname = upDir + files_st.nextToken();
        Tool.deleteFile(fname);
      }

      // --------------------------------------------
      // 새로운 파일의 등록 시작
      // --------------------------------------------
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0 ~
        System.out.println("multipartFile.getName(): " + multipartFile.getName());

        // if (multipartFile.getName().length() > 0) { // 전송파일이 있는지 체크, filesMF
        if (multipartFile.getSize() > 0) { // 전송파일이 있는지 체크
          // System.out.println("전송 파일이 있습니다.");
          files_item = Upload.saveFileSpring(multipartFile, upDir);
          sizes_item = multipartFile.getSize();

          if (Tool.isImage(files_item)) {
            thumbs_item = Tool.preview(upDir, files_item, 120, 80); // Thumb 이미지
                                                                    // 생성
          }

          if (i != 0 && i < count) { // index가 1 이상이면(두번째 파일 이상이면)
            // 하나의 컬럼에 여러개의 파일명을 조합하여 저장, file1.jpg/file2.jpg/file3.jpg
            files = files + "/" + files_item;
            // 하나의 컬럼에 여러개의 파일 사이즈를 조합하여 저장, 12546/78956/42658
            filesizes = filesizes + "/" + sizes_item;
            // 미니 이미지를 조합하여 하나의 컬럼에 저장
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // 파일이 없어도 파일 객체가 1개 생성됨으로
                                                    // 크기 체크
            files = files_item; // file1.jpg
            filesizes = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        }
      } // for END
      // --------------------------------------------
      // 새로운 파일의 등록 종료
      // --------------------------------------------

    } else { // 글만 수정하는 경우, 기존의 파일 정보 재사용
      files = petVO_old.getFiles();
      filesizes = petVO_old.getFilesizes();
      thumbs = petVO_old.getThumbs();
    }
    petVO.setFiles(files);
    petVO.setFilesizes(filesizes);
    petVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    count = petProc.update(petVO); // 수정

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("petno", petVO.getPetno()); // 회원 번호
    
    mav.setViewName("redirect:/pet/update_message.jsp");
   
    return mav;
  }

  @RequestMapping(value="/pet/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int petno){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/pet/delete"); // webapp/member/delete.jsp
    
    PetVO petVO = petProc.read(petno);
    mav.addObject("petVO", petVO);
    
    return mav;
  }  
  
  @RequestMapping(value="/pet/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(RedirectAttributes redirectAttributes,
                                        HttpServletRequest request, int petno){
    ModelAndView mav = new ModelAndView();
    
    int count = petProc.delete(petno);

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("petno", petno); // 회원 번호
    
    mav.setViewName("redirect:/pet/delete_message.jsp");
   
    return mav;
  }
  
}






