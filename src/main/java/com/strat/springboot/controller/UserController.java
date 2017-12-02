package com.strat.springboot.controller;

/**
 * Created by Administrator on 2017/6/9.
 */

import com.strat.springboot.controller.domain.Users;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class UserController {
  
  
  
  // @Autowired
  // private createuser create;
  // @Autowired
  // private announceService announces;
  
  @RequestMapping(value = "/", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
  public String sayhi(){
    
    
    return "index";
    
  }
  
  @RequestMapping(value = "/index", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String Createuser(@ModelAttribute("user") Users user, Model
      model){
    
    // int response=create.checkuser(user);
    int response = 3;
    if(response==1 || response==3)
    {
      // Iterable<announcement> announce =announces.viewannounce();
      model.addAttribute("announce","announce");
      model.addAttribute("user","user");
      return "login";
    }
    else
    {
      model.addAttribute("invalid password","msg");
      return "index";
    }
    
    
  }
  @RequestMapping("/hii")
  public String AuthUser(Model model){
    
    
    return "index";
    
  }
  
  
  
}