package com.strat.springboot.controller.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : Donald
 * @date : 2017/11/29 14:45.
 * @description :
 */
public class WindowsEmailService implements EmailService {
    
    Logger log = LoggerFactory.getLogger(WindowsEmailService.class);
    
    @Override
    public Boolean send(String from, String to, String object, String content) {
        log.debug("[WindowsEmailService] [send] send a email.");
        return true;
    }
}
