package com.strat.springboot.controller.action;

import com.strat.springboot.controller.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : Donald
 * @date : 2017/12/5 16:00.
 * @description :
 */
@Controller
public class ConverterController {
    
    @RequestMapping(value = "/convert", produces = {"application/x-donald"})
    public @ResponseBody
    DemoObj convert(@RequestBody DemoObj demoObj) {
        
        return demoObj;
    }
}
