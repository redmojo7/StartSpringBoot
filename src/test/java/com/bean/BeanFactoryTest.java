package com.bean;

import com.strat.springboot.controller.domain.DemoObj;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : Donald
 * @date : 2017/12/13 17:57.
 * @description :
 */
public class BeanFactoryTest {
    
    @Test
    public void testSimapleLoad() {
    
        ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
        DemoObj myBean= (DemoObj) context.getBean(DemoObj.class);
        myBean.getName();
        System.out.println("\r\nmyBean : " + myBean.toString());
    }
}
