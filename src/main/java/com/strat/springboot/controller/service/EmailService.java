package com.strat.springboot.controller.service;

/**
 * @author : Donald
 * @date : 2017/11/29 14:45.
 * @description :
 */
public interface EmailService {
    
    /**
     *
     * @param from
     * @param to
     * @param object
     * @param content
     * @return
     */
    Boolean send(String from, String to, String object, String content);
}
