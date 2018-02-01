package com.bean;

import com.strat.springboot.controller.domain.DemoObj;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author : Donald
 * @date : 2017/12/13 17:57.
 * @description :
 */
public class BeanFactoryTest {
    
    private final String CONTEXT_XML = "applicationContext.xml";
    
    @Test
    public void testSimpleLoad() {
    
        /*
         *   1  一般应用
         */
        ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
        DemoObj myBean1= (DemoObj) context.getBean(DemoObj.class);
    
        /*
         *   2  单纯为了测试
         */
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource(CONTEXT_XML));
    
        DemoObj myBean2= (DemoObj) beanFactory.getBean(DemoObj.class);
    
        /*
         *  print it
         */
        System.out.println("\r\n myBean1 : " + myBean1.toString() + "\r\n");
        System.out.println("\r\n myBean2 : " + myBean2.toString() + "\r\n");
    }
}
