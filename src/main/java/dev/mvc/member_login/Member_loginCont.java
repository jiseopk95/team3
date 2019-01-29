package dev.mvc.member_login;

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

import dev.mvc.member_login.Member_loginVO;
import dev.mvc.member.MemberVO;
import dev.mvc.member_login.Member_loginProcInter;
/*import dev.mvc.member_login.Member_loginVO;*/
import dev.mvc.pet.PetVO;
/*import nation.web.login.LoginVO;*/
import nation.web.tool.Tool;

@Controller
public class Member_loginCont {
  @Autowired
  @Qualifier("dev.mvc.member_login.Member_loginProc")
  private Member_loginProcInter member_loginProc = null;
   
  public Member_loginCont(){
    System.out.println("--> Member_loginCont created.");
  }
  
  
  @RequestMapping(value="/member_login/list.do", method=RequestMethod.GET)
  public ModelAndView list(HttpSession session){
    // System.out.println("--> create() GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member_login/login_list"); // webapp/member/list.jsp
    
      List<Member_loginVO> list = member_loginProc.list();
      mav.addObject("list", list);
    
    return mav;
  }  
  
 @RequestMapping(value="/member_login/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int member_loginno){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member_login/delete"); // webapp/member/delete.jsp
    
    Member_loginVO member_loginVO = member_loginProc.read(member_loginno);
    mav.addObject("member_loginVO", member_loginVO);
    
    return mav;
  }  
  
  @RequestMapping(value="/member_login/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(RedirectAttributes redirectAttributes,
                                        HttpServletRequest request, int member_loginno){
    ModelAndView mav = new ModelAndView();
    
    int count = member_loginProc.delete(member_loginno);

    redirectAttributes.addAttribute("count", count); // 1 or 0
    redirectAttributes.addAttribute("member_loginno", member_loginno); // 회원 번호
    
    mav.setViewName("redirect:/member_login/delete_message.jsp");
   
    return mav;
  }
  
  @RequestMapping(value="/member_login/read.do", method=RequestMethod.GET)
  public ModelAndView read(int member_loginno){
    // System.out.println("--> read(int memberno) GET called.");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/member_login/read"); // webapp/member/read.jsp
    
    Member_loginVO member_loginVO = member_loginProc.read(member_loginno);
    mav.addObject("member_loginVO", member_loginVO);
    
    return mav;
  }  

}






