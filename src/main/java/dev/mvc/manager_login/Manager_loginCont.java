package dev.mvc.manager_login;

import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.manager_login.Manager_loginVO;
import dev.mvc.manager.ManagerVO;
import dev.mvc.manager_login.Manager_loginProcInter;
/*import dev.mvc.member_login.Member_loginVO;*/
import dev.mvc.pet.PetVO;
/*import nation.web.login.LoginVO;*/
import nation.web.tool.Tool;

@Controller
public class Manager_loginCont {
  @Autowired
  @Qualifier("dev.mvc.manager_login.Manager_loginProc")
  private Manager_loginProcInter manager_loginProc = null;
   
  public Manager_loginCont(){
    System.out.println("--> Manager_loginCont created.");
  }
  
  
  @RequestMapping(value="/manager_login/list.do", method=RequestMethod.GET)
  public ModelAndView list(HttpSession session){
    // System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager_login/login_list"); // webapp/member/list.jsp
    
      List<Manager_loginVO> list = manager_loginProc.list();
      mav.addObject("list", list);
    
    return mav;
  }  
  
 @RequestMapping(value="/manager_login/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int manager_loginno){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager_login/delete"); // webapp/member/delete.jsp
    
    Manager_loginVO manager_loginVO = manager_loginProc.read(manager_loginno);
    mav.addObject("manager_loginVO", manager_loginVO);
    
    return mav;
  }  
  
  @RequestMapping(value="/manager_login/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(RedirectAttributes redirectAttributes,
                                        HttpServletRequest request, int manager_loginno){
    ModelAndView mav = new ModelAndView();
    
    int count = manager_loginProc.delete(manager_loginno);

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("manager_loginno", manager_loginno); // 회원 번호
    
    mav.setViewName("redirect:/manager_login/delete_message.jsp");
   
    return mav;
  }
  
  @RequestMapping(value="/manager_login/read.do", method=RequestMethod.GET)
  public ModelAndView read(int manager_loginno){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/manager_login/read"); // webapp/member/read.jsp
    
    Manager_loginVO manager_loginVO = manager_loginProc.read(manager_loginno);
    mav.addObject("manager_loginVO", manager_loginVO);
    
    return mav;
  }  

}






