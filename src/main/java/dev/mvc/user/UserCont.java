package dev.mvc.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// 리스트만 뽑으면 됨..아마도 
@Controller
public class UserCont {
 
  @Autowired
  @Qualifier("dev.mvc.user.UserProc")
  private UserProcInter userProc = null;
  
  /**
   * 전체 목록/ 이벤트별로 보기
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
/* 이;거 굳이 필요없으ㄹ듯  회원이 참여자리스틀르 굳이 볼 필요가 없으니까 ..
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