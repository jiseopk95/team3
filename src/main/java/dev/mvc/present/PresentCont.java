package dev.mvc.present;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class PresentCont {
  
  @Autowired
  @Qualifier("dev.mvc.present.PresentProc")
  private PresentProcInter presentProc = null;
  
  /**
   * ��� ��
   */
  @RequestMapping(value="/present/create.do", method=RequestMethod.GET)
  public ModelAndView create(){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/present/create"); //
    
    return mav;
  }
  
  @RequestMapping(value="/present/create.do", method=RequestMethod.POST)
  public ModelAndView create(PresentVO presentVO) {
    ModelAndView mav = new ModelAndView();
    
    int count = presentProc.create(presentVO);
    mav.setViewName("redirect:/present/create_message.jsp?count=" + count); //
  
  return mav;
}
  /**
   * ��ü ���
   * 
   * @return
   */
  // http://localhost:9090/ojt/category/list.do
  @RequestMapping(value = "/present/list_all_present.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();

    List<PresentVO> list = presentProc.list_all_present();
    mav.addObject("list", list);
    
    mav.setViewName("/present/list_all_present"); // /webapp/category/list.jsp

    return mav;
  } 
  /**
   * ������
   * @param presentno
   * @return
   */
   @RequestMapping(value = "/present/update.do", method = RequestMethod.GET,produces="text/plain;charset=UTF-8")
   public ModelAndView update(int presentno) {
     ModelAndView mav = new ModelAndView();
     mav.setViewName("/present/update"); // /webapp/contents/update.jsp
     PresentVO presentVO = presentProc.read(presentno);
     mav.addObject("presentVO", presentVO);
     
/*     PresentVO info = presentProc.read(presentVO.getInfo());
     mav.addObject("presentVO", presentVO);*/
     
     PresentVO end_date = presentProc.read(presentno);
     mav.addObject("presentVO", presentVO);
     
     System.out.println("presentno:"+presentVO.getPresentno()); 
     System.out.println("info:"+presentVO.getInfo()); 
     System.out.println("end_date:"+presentVO.getEnd_date());

     return mav;
   }
   /**
    * ����ó��
    * @param redirectAttributes
    * @param presentVO
    * @return
    */
  @RequestMapping(value="/present/update.do", method=RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes,HttpServletRequest request,PresentVO presentVO) {
    ModelAndView mav = new ModelAndView();
    
    int count = presentProc.update(presentVO);
    redirectAttributes.addAttribute("count", count);
    System.out.println("count:"+count);
    
    // redirect�ÿ��� request�� �����̾ȵ����� �Ʒ��� ����� �̿��Ͽ� ������ ����
    redirectAttributes.addAttribute("presentno", presentVO.getPresentno());
    
    System.out.println("presentno:"+presentVO.getPresentno());
    
    mav.setViewName("redirect:/present/update_message.jsp");

    return mav;
  }
  /**
   * ���� ��
   * 
   * @param presentno
   * @return
   */
  @RequestMapping(value = "/present/delete.do", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
  public ResponseEntity delete(int presentno) {
    // System.out.println("--> delete() GET executed");
    //ModelAndView mav = new ModelAndView();
    HttpHeaders responseHeaders = new HttpHeaders();
  
    JSONObject json = new JSONObject(); 
    PresentVO presentVO = presentProc.read(presentno);
    json.put("presentno", presentVO.getPresentno()); 
    json.put("info", presentVO.getInfo()); 
    json.put("end_date", presentVO.getEnd_date());  

    return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
  }
  /**
   * ����ó��
   * @param redirectAttributes
   * @param presentno
   * @return
   */
  @RequestMapping(value="/present/delete.do", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
  public ResponseEntity delete(RedirectAttributes redirectAttributes, int presentno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    JSONObject json = new JSONObject();
    ArrayList<String> msgs = new ArrayList<String>();
    
    PresentVO presentVO = presentProc.read(presentno);
    int count = presentProc.delete(presentno);
    
    if(count == 1){ 
      msgs.add("���� ������ �����߽��ϴ�.");
    } else if(count == 0) { 
      msgs.add("���� ������ �����߽��ϴ�.");
      msgs.add("�ٽ��ѹ� �õ����ּ���.");
    }
     
    json.put("msgs", msgs);
    
    redirectAttributes.addAttribute("presentno", presentVO.getPresentno());
    
    return new ResponseEntity(json.toString(),responseHeaders,HttpStatus.CREATED);
  }
 
  /**
   * ��� + �˻� + ����¡ ����
   * @param categoryno
   * @param word
   * @param nowPage
   * @return
   */
  /*@RequestMapping(value = "/present/paging.do", 
                                       method = RequestMethod.GET)
  public ModelAndView paging(
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) { 
    // System.out.println("--> list_by_category() GET called.");
    System.out.println("--> nowPage: " + nowPage);
    
    ModelAndView mav = new ModelAndView();
    
    // �˻� ��� �߰�,  /webapp/beauty/search_paging.jsp
    mav.setViewName("/present/paging");   
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("nowPage", nowPage);       
    
    
    // �˻��� ���ڵ� ����
    int search_count = presentProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    // mav.addObject("word", word);
  
    
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     *
     * @param categoryno ī�װ���ȣ 
     * @param search_count �˻�(��ü) ���ڵ�� 
     * @param nowPage     ���� ������
     * @param word �˻���  
     * @return ����¡ ���� ���ڿ�
      
    String paging = presentProc.paging(search_count, nowPage);
    mav.addObject("paging", paging);
  
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }*/
}