package dev.mvc.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// ����Ʈ�� ������ ��..�Ƹ��� 
@Controller
public class UserCont {
 
  @Autowired
  @Qualifier("dev.mvc.user.UserProc")
  private UserProcInter userProc = null;
  
  /**
   * ��ü ���/ �̺�Ʈ���� ����
   * 
   * @return
   */
  @RequestMapping(value = "/user/list_all_user.do", method = RequestMethod.GET)
  public ModelAndView list() {  
    ModelAndView mav = new ModelAndView();

    List<member_userVO> list = userProc.list_all_user(); 
    mav.addObject("list", list);
    
/*    List<member_userVO> member_userVO = userProc.list_member();
    mav.addObject("member_userVO",member_userVO);
  */
    
    mav.setViewName("/user/list_all_user"); // /webapp/contents/list_all_category.jsp

    return mav;
  }
/* ��;�� ���� �ʿ��������  ȸ���� �����ڸ���Ʋ�� ���� �� �ʿ䰡 �����ϱ� ..
 * @RequestMapping(value="/user/list_event.do", method=RequestMethod.GET)
  public ModelAndView list_event(int eventno) {
    ModelAndView mav = new ModelAndView();

    List<member_userVO> list_event = list_event.list_event(list_event);
    mav.addObject("list_event", list_event);
    
    mav.addObject("list_event", list_event);
    mav.setViewName("/event/list_event"); // /webapp/categrp/list.jsp
    
    return mav;
}*/
}