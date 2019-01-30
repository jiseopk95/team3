package dev.mvc.surveyitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

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

import dev.mvc.survey.Manager_SurveyVO;
import dev.mvc.survey.SurveyProcInter;
import dev.mvc.survey.SurveyVO;
import dev.mvc.surveyparty.SurveypartyProcInter;
import dev.mvc.surveyparty.SurveypartyVO;
import nation.web.tool.Tool;
import nation.web.tool.Upload;

@Controller
public class SurveyitemCont {
  @Autowired
  @Qualifier("dev.mvc.surveyitem.SurveyitemProc")
  private SurveyitemProcInter surveyitemProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.survey.SurveyProc")
  private SurveyProcInter surveyProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.surveyparty.SurveypartyProc")
  private SurveypartyProcInter surveypartyProc = null;
  
  public SurveyitemCont(){
    System.out.println("-->  SurveyitemCont created");
  }
//http://localhost:9090/ahr/surveyitem/create.do
  @RequestMapping(value = "/surveyitem/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/surveyitem/create");
    return mav;
  }
  
  @RequestMapping(value="/surveyitem/create.do", method=RequestMethod.POST)
  public ModelAndView create(RedirectAttributes redirectAttributes, HttpServletRequest request,SurveyitemVO surveyitemVO) {
    ModelAndView mav = new ModelAndView();
    
 // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/surveyitem/storage");
    List<MultipartFile> filesMF = surveyitemVO.getFilesMF(); // Spring�� File ��ü��
                                                           // �����ص�.



    String files = ""; // �÷��� ������ ���ϸ�
    String files_item = ""; // �ϳ��� ���ϸ�
    String sizes = "";
    long sizes_item = 0; // �ϳ��� ���� ������
    String thumbs = ""; // Thumb ���ϵ�
    String thumbs_item = ""; // �ϳ��� Thumb ���ϸ�

    int count = filesMF.size(); // ���ε�� ���� ����

    // Spring�� ���� ������ ���ص� 1���� MultipartFile ��ü�� ������.
    // System.out.println("--> ���ε�� ���� ���� count: " + count);

    if (count > 0) { // ���� ������ �����Ѵٸ�
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0 ~
  /*      System.out.println("multipartFile.getName(): " + multipartFile.getName());
*/
        // if (multipartFile.getName().length() > 0) { // ���������� �ִ��� üũ, filesMF
        if (multipartFile.getSize() > 0) { // ���������� �ִ��� üũ
          // System.out.println("���� ������ �ֽ��ϴ�.");
          files_item = Upload.saveFileSpring(multipartFile, upDir);
          sizes_item = multipartFile.getSize();

          if (Tool.isImage(files_item)) {
            thumbs_item = Tool.preview(upDir, files_item, 120, 80); // Thumb �̹���
                                                                    // ����
          }

          if (i != 0 && i < count) { // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
            // �ϳ��� �÷��� �������� ���ϸ��� �����Ͽ� ����, file1.jpg/file2.jpg/file3.jpg
            files = files + "/" + files_item;
            // �ϳ��� �÷��� �������� ���� ����� �����Ͽ� ����, 12546/78956/42658
            sizes = sizes + "/" + sizes_item;
            // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
                                                    // ũ�� üũ
            files = files_item; // file1.jpg
            sizes = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        } // if (multipartFile.getSize() > 0) {  END
        
      }
    }
    surveyitemVO.setFiles(files);
    surveyitemVO.setSizes(sizes);
    surveyitemVO.setThumbs(thumbs);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------

    
    
    count = surveyitemProc.create(surveyitemVO);
    
    if (count == 1) {
      surveyProc.increaseCnt(surveyitemVO.getSurveyno());
    }
    redirectAttributes.addAttribute("count", count); 
    redirectAttributes.addAttribute("surveyno", surveyitemVO.getSurveyno()); 
    

    mav.setViewName("redirect:/surveyitem/create_message.jsp");
    /*mav.setViewName("redirect:/surveyitem/create_message.jsp?count=" + count+"&"+surveyno);*/


    return mav;
  }
  //http://localhost:9090/ahr/surveyitem/list.do?surveyno=1
  @RequestMapping(value = "/surveyitem/list.do", method = RequestMethod.GET)
  public ModelAndView list(int surveyno) {
    ModelAndView mav = new ModelAndView();
    Manager_SurveyVO surveyVO =surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);
    List<Survey_ItemVO> list = surveyitemProc.list(surveyno);
    mav.addObject("list", list);
    
    

    mav.setViewName("/surveyitem/list"); 

    return mav;
  }
  
/*  //http://localhost:9090/ahr/surveyitem/party_list.do?surveyno=1
  @RequestMapping(value = "/surveyitem/party_list.do", method = RequestMethod.GET)
  public ModelAndView party_list(int surveyno,int memberno) {
    ModelAndView mav = new ModelAndView();
    Manager_SurveyVO surveyVO =surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);
    List<Survey_ItemVO> list = surveyitemProc.list(surveyno);
    mav.addObject("list", list);
    mav.addObject("memberno", memberno);
    mav.setViewName("/surveyitem/party_list"); 

    return mav;
  }*/
  //http://localhost:9090/ahr/surveyitem/party_list.do?surveyno=1
  @RequestMapping(value = "/surveyitem/party_list.do", method = RequestMethod.GET)
  public ModelAndView party_list(int surveyno,int memberno) {
    ModelAndView mav = new ModelAndView();
    SurveyVO surveyVO =surveyProc.read_m(surveyno);
    System.out.println("surveyno:"+surveyVO.getSurveyno());
    mav.addObject("surveyVO", surveyVO);
    List<Survey_ItemVO> list = surveyitemProc.list(surveyno);
    mav.addObject("list", list);
    mav.addObject("memberno", memberno);
    mav.setViewName("/surveyitem/party_list"); 

    return mav;
  }
  
  //http://localhost:9090/ahr/surveyitem/party_list.do?surveyno=1
  @RequestMapping(value = "/surveyitem/party_bar.do", method = RequestMethod.GET)
  public ModelAndView party_bar(int surveyno) {
    ModelAndView mav = new ModelAndView();
    Manager_SurveyVO surveyVO =surveyProc.read(surveyno);
    mav.addObject("surveyVO", surveyVO);
    List<Survey_ItemVO> list = surveyitemProc.list(surveyno);
    mav.addObject("list", list);
    
    

    mav.setViewName("/surveyitem/party_bar"); 

    return mav;
  }
  
  /**
   * ���� ���� ���� �� http://localhost:9090/contents/contents/update.do
   * 
   * @param contentsno
   * @return
   */
  //http://localhost:9090/ahr/surveyitem/update.do?surveyitemno=1&surveyno=10
  @RequestMapping(value = "/surveyitem/update.do", method = RequestMethod.GET)
  public ModelAndView update(int surveyitemno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/surveyitem/update"); // /webapp/contents/update.jsp

    Survey_ItemVO survey_itemVO = surveyitemProc.read(surveyitemno);
    mav.addObject("survey_itemVO",survey_itemVO);

    if(survey_itemVO.getThumbs() != null){
    ArrayList<FileVO> file_list = surveyitemProc.getThumbs(survey_itemVO);

    mav.addObject("file_list", file_list);
    }
    
    return mav;
  }
  
  /**
   * - �۸� �����ϴ� ����� ���� - ���ϸ� �����ϴ� ����� ���� - �۰� ������ ���ÿ� �����ϴ� ����� ����
   * 
   * @param request
   * @param contentsVO
   * @return
   */
  @RequestMapping(value = "/surveyitem/update.do", method = RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes, HttpServletRequest request, SurveyitemVO surveyitemVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/surveyitem/storage");
    List<MultipartFile> filesMF = surveyitemVO.getFilesMF(); // Spring�� File ��ü��
                                                           // �����ص�.

    // System.out.println("--> filesMF.get(0).getSize(): " +
    // filesMF.get(0).getSize());

    String files = ""; // �÷��� ������ ���ϸ�
    String files_item = ""; // �ϳ��� ���ϸ�
    String sizes = "";
    long sizes_item = 0; // �ϳ��� ���� ������
    String thumbs = ""; // Thumb ���ϵ�
    String thumbs_item = ""; // �ϳ��� Thumb ���ϸ�

    int count = filesMF.size(); // ���ε�� ���� ����

    // ������ ��� ���� ��ȸ
    Survey_ItemVO surveyitemVO_old = surveyitemProc.read(surveyitemVO.getSurveyitemno());
    if (filesMF.get(0).getSize() > 0) { // ���ο� ������ ��������� ������ ��ϵ� ���� ��� ����
      // thumbs ���� ����
      String thumbs_old = surveyitemVO_old.getThumbs();
      if(thumbs_old != null){
      StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/");
      
      while (thumbs_st.hasMoreTokens()) {
        String fname = upDir + thumbs_st.nextToken();
        Tool.deleteFile(fname);
      }
      }

      // ���� ���� ����
      String files_old = surveyitemVO_old.getFiles();
      StringTokenizer files_st = new StringTokenizer(files_old, "/");
      while (files_st.hasMoreTokens()) {
        String fname = upDir + files_st.nextToken();
        Tool.deleteFile(fname);
      }

      // --------------------------------------------
      // ���ο� ������ ��� ����
      // --------------------------------------------
      // for (MultipartFile multipartFile: filesMF) {
      for (int i = 0; i < count; i++) {
        MultipartFile multipartFile = filesMF.get(i); // 0 ~
    /*    System.out.println("multipartFile.getName(): " + multipartFile.getName());*/

        // if (multipartFile.getName().length() > 0) { // ���������� �ִ��� üũ, filesMF
        if (multipartFile.getSize() > 0) { // ���������� �ִ��� üũ
          // System.out.println("���� ������ �ֽ��ϴ�.");
          files_item = Upload.saveFileSpring(multipartFile, upDir);
          sizes_item = multipartFile.getSize();

          if (Tool.isImage(files_item)) {
            thumbs_item = Tool.preview(upDir, files_item, 120, 80); // Thumb �̹���
                                                                    // ����
          }

          if (i != 0 && i < count) { // index�� 1 �̻��̸�(�ι�° ���� �̻��̸�)
            // �ϳ��� �÷��� �������� ���ϸ��� �����Ͽ� ����, file1.jpg/file2.jpg/file3.jpg
            files = files + "/" + files_item;
            // �ϳ��� �÷��� �������� ���� ����� �����Ͽ� ����, 12546/78956/42658
            sizes = sizes + "/" + sizes_item;
            // �̴� �̹����� �����Ͽ� �ϳ��� �÷��� ����
            thumbs = thumbs + "/" + thumbs_item;
          } else if (multipartFile.getSize() > 0) { // ������ ��� ���� ��ü�� 1�� ����������
                                                    // ũ�� üũ
            files = files_item; // file1.jpg
            sizes = "" + sizes_item; // 123456
            thumbs = thumbs_item; // file1_t.jpg
          }

        }
      } // for END
      // --------------------------------------------
      // ���ο� ������ ��� ����
      // --------------------------------------------

    } else { // �۸� �����ϴ� ���, ������ ���� ���� ����
      files = surveyitemVO_old.getFiles();
      sizes = surveyitemVO_old.getSizes();
      thumbs = surveyitemVO_old.getThumbs();
    }
    surveyitemVO.setFiles(files);
    surveyitemVO.setSizes(sizes);
    surveyitemVO.setThumbs(thumbs);

  

    count = surveyitemProc.update(surveyitemVO);

    redirectAttributes.addAttribute("count", count);

    // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
    redirectAttributes.addAttribute("surveyitemno", surveyitemVO.getSurveyitemno());
    redirectAttributes.addAttribute("surveyno", surveyitemVO.getSurveyno());

    mav.setViewName("redirect:/surveyitem/update_message.jsp");

    return mav;

  }
  
  /**
   * ���� ��http://localhost:9090/ahr/surveyitem/delete_form.do?surveyitemno=1&surveyno=10
   * 
   * @param contentsno
   * @param categoryno
   * @return
   */
  @RequestMapping(value = "/surveyitem/delete_form.do", method = RequestMethod.GET)
  public ModelAndView delete_form(int surveyitemno) {
    // System.out.println("--> delete() GET executed");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/surveyitem/delete_form"); 

    Survey_ItemVO survey_itemVO = surveyitemProc.read(surveyitemno);

    mav.addObject("survey_itemVO", survey_itemVO);

    return mav;
  }
  
  @RequestMapping(value = "/surveyitem/delete_form.do", method = RequestMethod.POST)
  public ModelAndView delete_form(RedirectAttributes redirectAttributes, 
                                        HttpServletRequest request, 
                                        int surveyitemno

      ) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/surveyitem/delete_message"); // /webapp/contents/delete_message.jsp

    String upDir = Tool.getRealPath(request, "/surveyitem/storage"); // ���� ���� ����
                                                                   // ���

    Survey_ItemVO survey_itemVO = surveyitemProc.read(surveyitemno);
                                                           // ����

    String thumbs_old = survey_itemVO.getThumbs();
    String files_old = survey_itemVO.getFiles();
    if(thumbs_old != null){
    StringTokenizer thumbs_st = new StringTokenizer(thumbs_old, "/"); // Thumbs
    while (thumbs_st.hasMoreTokens()) { // �ܾ �ִ��� �˻�
      String fname = upDir + thumbs_st.nextToken(); // �ܾ� ����
      Tool.deleteFile(fname);
    }
    }

    StringTokenizer files_st = new StringTokenizer(files_old, "/"); // files
    while (files_st.hasMoreTokens()) { // �ܾ �ִ��� �˻�
      String fname = upDir + files_st.nextToken(); // �ܾ� ����
      Tool.deleteFile(fname);
    }
    surveypartyProc.delete_item(surveyitemno);
    int count = surveyitemProc.delete(surveyitemno); // ���ڵ� ����

  if (count == 1) {
    surveyProc.decreaseCnt(survey_itemVO.getSurveyno());
      
  }

    // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
    redirectAttributes.addAttribute("count", count); // 1 or 0
    mav.setViewName("redirect:/surveyitem/delete_message.jsp");

    return mav;
  }
  
  @RequestMapping(value="/surveyitem/update_seqno_up.do", method=RequestMethod.POST)
  public ModelAndView update_seqno_up(int surveyitemno,int surveyno) {
    ModelAndView mav = new ModelAndView();

    
    if (surveyitemProc.update_seqno_up(surveyitemno) == 1) {
     
      mav.setViewName("redirect:/surveyitem/list.do?surveyno="+surveyno); // /webapp/categrp/list.jsp
    }

    return mav;
  }

  @RequestMapping(value="/surveyitem/update_seqno_down.do", method=RequestMethod.POST)
  public ModelAndView update_seqno_down(int surveyitemno,int surveyno) {
    ModelAndView mav = new ModelAndView();

    
    if (surveyitemProc.update_seqno_down(surveyitemno) == 1) {
     
      mav.setViewName("redirect:/surveyitem/list.do?surveyno="+surveyno); // /webapp/categrp/list.jsp
    }

    return mav;
  }
  
  @RequestMapping(value="/surveyitem/submit_proc.do", method=RequestMethod.POST)
  public ModelAndView submit_proc(int surveyitemno,SurveypartyVO surveypartyVO) {
    
    ModelAndView mav = new ModelAndView();
    

        surveyitemProc.itemCnt(surveyitemno);
        
        Survey_ItemVO survey_itemVO = surveyitemProc.read(surveyitemno);
        mav.addObject("survey_itemVO",survey_itemVO);
         
        System.out.println("("+survey_itemVO.getSurveyitemno()+")"+survey_itemVO.getQuestion()+" ��itemCntȽ��:"+survey_itemVO.getItemcnt());

          surveypartyProc.create(surveypartyVO);
          
          Manager_SurveyVO surveyVO = surveyProc.read(survey_itemVO.getSurveyno());
          mav.addObject("surveyVO", surveyVO);
          
     
      mav.setViewName("redirect:/survey/list_m.do"); // /webapp/categrp/list.jsp
 
    return mav;
  }


}
