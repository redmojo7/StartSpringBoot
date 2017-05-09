package com.strat.springboot.Controller.action;

import com.strat.springboot.Controller.service.MyOkHttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OkHttpAction
 *
 * @author : Donald Cai
 * @date :   2017/5/6.
 */
@RestController
@RequestMapping("/api")
public class OkHttpAction {

    private static final Logger log = LoggerFactory.getLogger(OkHttpAction.class);

    @Autowired
    private MyOkHttpService myOkHttpService;

    @RequestMapping("/okHttp/testExecute")
    private ResponseEntity<String> testExecute(){
        myOkHttpService.testExecute();
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }
    @RequestMapping("/okHttp/testEnqueue")
    private ResponseEntity<String> testEnqueue(){
        myOkHttpService.testEnqueue();
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }
}
