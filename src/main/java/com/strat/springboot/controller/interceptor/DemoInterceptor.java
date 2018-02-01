package com.strat.springboot.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author : Donald
 * @date : 2017/12/5 16:22.
 * @description :
 */
public class DemoInterceptor extends HandlerInterceptorAdapter {
    
    private Logger log = LoggerFactory.getLogger(DemoInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, //②
        HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, //③
        HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTime = System.currentTimeMillis();
        log.debug("本次请求处理时间为:" + new Long(endTime - startTime)+"ms");
        request.setAttribute("handlingTime", endTime - startTime);
    }
}
