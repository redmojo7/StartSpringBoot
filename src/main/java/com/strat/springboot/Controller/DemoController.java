package com.strat.springboot.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/4/11.
 * 注意：controller的代码必须和DemoApplication.java位于同级目录或之下
 */
@RestController
@RequestMapping("/api")
public class DemoController {

  @RequestMapping("/demo")
  public String demo() {
    return "欢迎来到Demo! &nbsp;<a><b>www.baidu.com</b></a>";
  }
}

