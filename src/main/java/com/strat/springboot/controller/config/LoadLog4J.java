package com.strat.springboot.controller.config;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * LoadLog4J
 *
 * @author : Donald Cai
 * @date :   2017/5/6.
 */
public class LoadLog4J {
        static Logger log = LoggerFactory.getLogger(LoadLog4J.class);
        public static void load() throws IOException {
            FileInputStream stream=new FileInputStream("log4j.properties");
            Properties pro = new Properties();
            pro.load(stream);
            if(stream != null){
                stream.close();
            }
            PropertyConfigurator.configure(pro);
            log.info("加载log4j配置成功");
        }
}
