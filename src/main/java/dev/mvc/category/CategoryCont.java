package dev.mvc.category;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.categrp.CategrpProcInter;
import dev.mvc.categrp.CategrpVO;
import dev.mvc.review.ReviewProcInter;

@Controller
public class CategoryCont {
  @Autowired
  @Qualifier("dev.mvc.category.CategoryProc")
  private CategoryProcInter categoryProc = null;

  @Autowired
  @Qualifier("dev.mvc.categrp.CategrpProc")
  private CategrpProcInter categrpProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc = null;

  public CategoryCont() {
    System.out.println("--> CategoryCont created.");
  }

  // http://localhost:9090/ojt/category/create.do
  @RequestMapping(value = "/category/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/category/create"); // /webapp/category/create.jsp

    return mav;
  }

  // http://localhost:9090/ojt/category/create.do
  @RequestMapping(value = "/category/create.do", method = RequestMethod.POST)
  public ModelAndView create(CategoryVO categoryVO) {
    ModelAndView mav = new ModelAndView();

    int count = categoryProc.create(categoryVO);
    mav.setViewName("redirect:/category/create_message.jsp?count=" + count); // /webapp/category/create_message.jsp
    // mav.setViewName("redirect:/category/list.do"); //
    // /webapp/category/list.jsp

    return mav;
  }

  // http://localhost:9090/ojt/category/create_json.do?categrpno=1&title=���&seqno=1&visible=Y&ids=admin
  // {"msgs":["ī�װ��� ����߽��ϴ�.","��ϵ� ī�װ� ���"]}
  @ResponseBody
  @RequestMapping(value = "/category/create_json.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public ResponseEntity create_json(CategoryVO categoryVO) {
    HttpHeaders responseHeaders = new HttpHeaders();

    JSONObject json = new JSONObject();
    JSONArray msgs = new JSONArray();

    if (categoryProc.create(categoryVO) == 1) {
      msgs.put("ī�װ��� ����߽��ϴ�.");
      msgs.put("��ϵ� ī�װ� " + categoryVO.getTitle());
    } else {
      msgs.put("ī�װ� ��Ͽ� �����߽��ϴ�.");
      msgs.put("�ٽ��ѹ� �õ����ּ���. �� ����: 000-0000-0000");
    }
    json.put("msgs", msgs);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }

  /**
   * ��ü ���
   * 
   * @return
   */
  // http://localhost:9090/ojt/category/list.do
  @RequestMapping(value = "/category/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();

    List<CategrpVO> categrp_list = categrpProc.list();
    System.out.println("categrp_list: " + categrp_list);
    mav.addObject("categrp_list", categrp_list);
    
    mav.setViewName("/category/list"); // /webapp/category/list.jsp

    return mav;
  }

  /*
   * http://localhost:9090/ojt/category/list_json.do
   * 
   * [ { "categrpno":1, "visible":"Y", "seqno":1, "name":"�ؿ� ����",
   * "categoryno":1, "cnt":0, "ids":"admin", "category_seqno":0, "title":"�Ϻ�" }
   * , { "categrpno":1,...
   * 
   */
  /**
   * JSON ��� ��ü ���
   * 
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/category/list_json.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public ResponseEntity list_json() {
    HttpHeaders responseHeaders = new HttpHeaders();
    List<Categrp_CategoryVO> list = categoryProc.list();

    JSONArray json = new JSONArray(list);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }

  /**
   * ī�װ� �׷캰 ��ü ���
   * 
   * @return
   */
  // http://localhost:9090/ojt/category/list_by_categrp.do
  @RequestMapping(value = "/category/list_by_categrp.do", method = RequestMethod.GET)
  public ModelAndView list_by_categrp(int categrpno) {
    ModelAndView mav = new ModelAndView();

    CategrpVO categrpVO = categrpProc.read(categrpno);
    mav.addObject("categrpVO", categrpVO);
    
    mav.setViewName("/category/list_by_categrp"); // /webapp/category/list_by_categrp.jsp

    return mav;
  }

  /**
   * ī�װ� �׷캰 JSON ��� ��ü ���
   * http://localhost:9090/ojt/category/list_by_categrp_json.do
   * 
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/category/list_by_categrp_json.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public ResponseEntity list_by_categrp_json(int categrpno) {
    HttpHeaders responseHeaders = new HttpHeaders();
    List<Categrp_CategoryVO> list = categoryProc.list_by_categrp(categrpno);

    JSONArray json = new JSONArray(list);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }


  /**
   * ���� ��
   * http://localhost:9090/ojt/category/update.do?categoryno=1
   * @param categrpno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/category/update.do", 
                           method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String update(int categoryno) {
    // System.out.println("--> update() GET executed");
    
    Categrp_CategoryVO categoryVO = categoryProc.read(categoryno);
    JSONObject obj = new JSONObject(categoryVO);
   
    // �⺻ ���� �����ϴ� ���� ���� ���� ���
//    obj.put("categoryno", categoryno);
//    obj.put("categrpno", categoryVO.getCategrpno());
//    obj.put("title", categoryVO.getTitle());
//    obj.put("seqno", categoryVO.getSeqno());
//    obj.put("visible", categoryVO.getVisible());
//    obj.put("ids", categoryVO.getIds());
//    obj.put("rdate", categoryVO.getRdate());

    return obj.toString();
  }

  // http://localhost:9090/ojt/category/update_json.do?categrpno=1&categoryno=1&title=�״�����&seqno=1&visible=Y&ids=admin
  // {"msgs":["ī�װ��� ����߽��ϴ�.","��ϵ� ī�װ� ���"]}
  @ResponseBody
  @RequestMapping(value = "/category/update_json.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public ResponseEntity update_json(CategoryVO categoryVO) {
    HttpHeaders responseHeaders = new HttpHeaders();

    JSONObject json = new JSONObject();
    JSONArray msgs = new JSONArray();

    if (categoryProc.update(categoryVO) == 1) {
      msgs.put("ī�װ��� �����߽��ϴ�.");
      msgs.put("������ ī�װ� " + categoryVO.getTitle());
    } else {
      msgs.put("ī�װ� ������ �����߽��ϴ�.");
      msgs.put("�ٽ��ѹ� �õ����ּ���. �� ����: 000-0000-0000");
    }
    json.put("msgs", msgs);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }

  /**
   * ���� ��
   * http://localhost:9090/ojt/category/delete.do?categoryno=1
   * @param categoryno
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/category/delete.do", 
                           method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String delete(int categoryno) {
    Categrp_CategoryVO categoryVO = categoryProc.read(categoryno);
    JSONObject obj = new JSONObject(categoryVO);

    return obj.toString();
  }
  
  // http://localhost:9090/ojt/category/delete.do?categoryno=1
  @ResponseBody
  @RequestMapping(value = "/category/delete.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public ResponseEntity delete_proc(int categoryno) {
    HttpHeaders responseHeaders = new HttpHeaders();

    JSONObject json = new JSONObject();
    JSONArray msgs = new JSONArray();
    
    String title = categoryProc.read(categoryno).getTitle();
    
    if (categoryProc.delete(categoryno) == 1) {
      msgs.put("ī�װ��� �����߽��ϴ�.");
      msgs.put("������ ī�װ� [" + title + "]");
    } else {
      msgs.put("[" + title + "] ī�װ� ������ �����߽��ϴ�.");
      msgs.put("�ٽ��ѹ� �õ����ּ���. �� ����: 000-0000-0000");
    }
    json.put("msgs", msgs);

    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
 
  
}
 




